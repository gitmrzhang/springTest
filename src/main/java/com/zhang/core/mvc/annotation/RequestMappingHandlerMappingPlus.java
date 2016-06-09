package com.zhang.core.mvc.annotation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
/**
 * Creates {@link RequestMappingInfo} instances from type and method-level
 * {@link RequestMapping @RequestMapping} annotations in
 * {@link Controller @Controller} classes.
 */
public class RequestMappingHandlerMappingPlus extends RequestMappingHandlerMapping {
	
	private static final HandlerMethod PREFLIGHT_AMBIGUOUS_MATCH =
			new HandlerMethod(new EmptyHandler(), ClassUtils.getMethod(EmptyHandler.class, "handle"));
	
	private static final String DEFAULT_HEAD_SERVER_NAME="APP_SERVER";
	
	private String headServerName=DEFAULT_HEAD_SERVER_NAME;
	
	private final MappingRegistry mappingRegistry = new MappingRegistry();
	
	/**
	 * Return RequestMappingInfo by handler method.Thread-safe for concurrent use.
	 */
	public RequestMappingInfo getRequestMappingInfoByHandlerMethod(HandlerMethod method){
		return this.mappingRegistry.getRequestMappingInfoByHandlerMethod(method);
	}
	
	/**
	 * spring look up by getMappingsByUrl but plus based by getMappingsByUrl() and getHandlerMethodsByMappingName()
	 * @see #getHandlerMethodsByMappingName(String mappingName)
	 * Look up the best-matching handler method for the current request.
	 * If multiple matches are found, the best match is selected.
	 * @param lookupPath mapping lookup path within the current servlet mapping
	 * @param request the current request
	 * @return the best-matching handler method, or {@code null} if no match
	 * @see #handleMatch(Object, String, HttpServletRequest)
	 * @see #handleNoMatch(Set, String, HttpServletRequest)
	 */
	@Override
	protected HandlerMethod lookupHandlerMethod(String lookupPath,
			HttpServletRequest request) throws Exception {
		//根据请求获取当前头的 服务名 根据服务名与handlerMethod的映射返回值
		String lookupName = getRequestHead(request);
		List<Match> matches = new ArrayList<Match>();
		 List<HandlerMethod> directNameHandlerMethodMatches = getHandlerMethodsForMappingName(lookupName);
		 if (directNameHandlerMethodMatches != null) {
			 List<RequestMappingInfo> directNameMatches = new ArrayList<RequestMappingInfo>();
			 for(HandlerMethod hm : directNameHandlerMethodMatches){
				 directNameMatches.add(getRequestMappingInfoByHandlerMethod(hm));
			 }
			 addMatchingMappings(directNameMatches, matches, request);//该方法需要的是 T RequestMappingInfo ？需要 HandlerMethod 到 RequestMappingInfo 的查询表
			 if (!matches.isEmpty()) {
					Comparator<Match> comparator = new MatchComparator(getMappingComparator(request));
					Collections.sort(matches, comparator);
					if (logger.isTraceEnabled()) {
						logger.trace("Found " + matches.size() + " matching mapping(s) for [" +
								lookupName + "] : " + matches);
					}
					Match bestMatch = matches.get(0);
					if (matches.size() > 1) {
						if (CorsUtils.isPreFlightRequest(request)) {
							return PREFLIGHT_AMBIGUOUS_MATCH;
						}
						Match secondBestMatch = matches.get(1);
						if (comparator.compare(bestMatch, secondBestMatch) == 0) {
							Method m1 = bestMatch.handlerMethod.getMethod();
							Method m2 = secondBestMatch.handlerMethod.getMethod();
							throw new IllegalStateException("Ambiguous handler methods mapped for HTTP path '" +
									request.getRequestURL()+ " with head server name "+ lookupName + "': {" + m1 + ", " + m2 + "}");
						}
					}
					handleMatch(bestMatch.mapping, lookupPath, request);
					return bestMatch.handlerMethod;
				}
			}else {
				return super.lookupHandlerMethod(lookupPath, request);
			}
		 return handleNoMatch(this.mappingRegistry.getMappings().keySet(), lookupPath, request); 
	}
	
	private void addMatchingMappings(Collection<RequestMappingInfo> mappings, List<Match> matches, HttpServletRequest request) {
		for (RequestMappingInfo mapping : mappings) {
			RequestMappingInfo match = getMatchingMapping(mapping, request);
			if (match != null) {
				matches.add(new Match(match, this.mappingRegistry.getMappings().get(mapping)));
			}
		}
	}
	
	@Override
	public void registerMapping(RequestMappingInfo mapping, Object handler,
			Method method) {
		this.mappingRegistry.register(mapping, handler, method);
	}
	
	private String getRequestHead(HttpServletRequest request){
		return request.getHeader(headServerName);
	}

	public String getHeadServerName() {
		return headServerName;
	}

	public void setHeadServerName(String headServerName) {
		this.headServerName = headServerName;
	}
	
	class MappingRegistry {

		private final Map<RequestMappingInfo, MappingRegistration<RequestMappingInfo>> registry = new HashMap<RequestMappingInfo, MappingRegistration<RequestMappingInfo>>();

		private final Map<RequestMappingInfo, HandlerMethod> mappingLookup = new LinkedHashMap<RequestMappingInfo, HandlerMethod>();
		
		private final Map<HandlerMethod,RequestMappingInfo> methodLookup = new ConcurrentHashMap<HandlerMethod,RequestMappingInfo>();

		private final MultiValueMap<String, RequestMappingInfo> urlLookup = new LinkedMultiValueMap<String, RequestMappingInfo>();

		private final Map<String, List<HandlerMethod>> nameLookup =
				new ConcurrentHashMap<String, List<HandlerMethod>>();

		private final Map<HandlerMethod, CorsConfiguration> corsLookup =
				new ConcurrentHashMap<HandlerMethod, CorsConfiguration>();

		private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

		/**
		 * Return all mappings and handler methods. Not thread-safe.
		 * @see #acquireReadLock()
		 */
		public Map<RequestMappingInfo, HandlerMethod> getMappings() {
			return this.mappingLookup;
		}

		/**
		 * Return matches for the given URL path. Not thread-safe.
		 * @see #acquireReadLock()
		 */
		public List<RequestMappingInfo> getMappingsByUrl(String urlPath) {
			return this.urlLookup.get(urlPath);
		}

		/**
		 * Return handler methods by mapping name. Thread-safe for concurrent use.
		 */
		public List<HandlerMethod> getHandlerMethodsByMappingName(String mappingName) {
			return this.nameLookup.get(mappingName);
		}
		
		/**
		 * Return RequestMappingInfo by handler method.Thread-safe for concurrent use.
		 */
		public RequestMappingInfo getRequestMappingInfoByHandlerMethod(HandlerMethod method){
			return this.methodLookup.get(method);
		}

		/**
		 * Return CORS configuration. Thread-safe for concurrent use.
		 */
		public CorsConfiguration getCorsConfiguration(HandlerMethod handlerMethod) {
			HandlerMethod original = handlerMethod.getResolvedFromHandlerMethod();
			return this.corsLookup.get(original != null ? original : handlerMethod);
		}

		/**
		 * Acquire the read lock when using getMappings and getMappingsByUrl.
		 */
		public void acquireReadLock() {
			this.readWriteLock.readLock().lock();
		}

		/**
		 * Release the read lock after using getMappings and getMappingsByUrl.
		 */
		public void releaseReadLock() {
			this.readWriteLock.readLock().unlock();
		}

		public void register(RequestMappingInfo mapping, Object handler, Method method) {
			this.readWriteLock.writeLock().lock();
			try {
				HandlerMethod handlerMethod = createHandlerMethod(handler, method);
				assertUniqueMethodMapping(handlerMethod, mapping);

				if (logger.isInfoEnabled()) {
					logger.info("Mapped \"" + mapping + "\" onto " + handlerMethod);
				}
				this.mappingLookup.put(mapping, handlerMethod);
				
				this.methodLookup.put( handlerMethod,mapping);

				List<String> directUrls = getDirectUrls(mapping);
				for (String url : directUrls) {
					this.urlLookup.add(url, mapping);
				}

				String name = null;
				if (getNamingStrategy() != null) {
					name = getNamingStrategy().getName(handlerMethod, mapping);
					addMappingName(name, handlerMethod);
				}

				CorsConfiguration corsConfig = initCorsConfiguration(handler, method, mapping);
				if (corsConfig != null) {
					this.corsLookup.put(handlerMethod, corsConfig);
				}

				this.registry.put(mapping, new MappingRegistration<RequestMappingInfo>(mapping, handlerMethod, directUrls, name));
			}
			finally {
				this.readWriteLock.writeLock().unlock();
			}
		}

		private void assertUniqueMethodMapping(HandlerMethod newHandlerMethod, RequestMappingInfo mapping) {
			HandlerMethod handlerMethod = this.mappingLookup.get(mapping);
			if (handlerMethod != null && !handlerMethod.equals(newHandlerMethod)) {
				throw new IllegalStateException(
						"Ambiguous mapping. Cannot map '" +	newHandlerMethod.getBean() + "' method \n" +
						newHandlerMethod + "\nto " + mapping + ": There is already '" +
						handlerMethod.getBean() + "' bean method\n" + handlerMethod + " mapped.");
			}
		}

		private List<String> getDirectUrls(RequestMappingInfo mapping) {
			List<String> urls = new ArrayList<String>(1);
			for (String path : getMappingPathPatterns(mapping)) {
				if (!getPathMatcher().isPattern(path)) {
					urls.add(path);
				}
			}
			return urls;
		}

		private void addMappingName(String name, HandlerMethod handlerMethod) {
			List<HandlerMethod> oldList = this.nameLookup.get(name);
			if (oldList == null) {
				oldList = Collections.<HandlerMethod>emptyList();
			}

			for (HandlerMethod current : oldList) {
				if (handlerMethod.equals(current)) {
					return;
				}
			}

			if (logger.isTraceEnabled()) {
				logger.trace("Mapping name '" + name + "'");
			}

			List<HandlerMethod> newList = new ArrayList<HandlerMethod>(oldList.size() + 1);
			newList.addAll(oldList);
			newList.add(handlerMethod);
			this.nameLookup.put(name, newList);

			if (newList.size() > 1) {
				if (logger.isTraceEnabled()) {
					logger.trace("Mapping name clash for handlerMethods " + newList +
							". Consider assigning explicit names.");
				}
			}
		}

		public void unregister(RequestMappingInfo mapping) {
			this.readWriteLock.writeLock().lock();
			try {
				MappingRegistration<RequestMappingInfo> definition = this.registry.remove(mapping);
				if (definition == null) {
					return;
				}

				this.mappingLookup.remove(definition.getMapping());

				for (String url : definition.getDirectUrls()) {
					List<RequestMappingInfo> list = this.urlLookup.get(url);
					if (list != null) {
						list.remove(definition.getMapping());
						if (list.isEmpty()) {
							this.urlLookup.remove(url);
						}
					}
				}

				removeMappingName(definition);

				this.corsLookup.remove(definition.getHandlerMethod());
			}
			finally {
				this.readWriteLock.writeLock().unlock();
			}
		}

		private void removeMappingName(MappingRegistration<RequestMappingInfo> definition) {
			String name = definition.getMappingName();
			if (name == null) {
				return;
			}
			HandlerMethod handlerMethod = definition.getHandlerMethod();
			List<HandlerMethod> oldList = this.nameLookup.get(name);
			if (oldList == null) {
				return;
			}
			if (oldList.size() <= 1) {
				this.nameLookup.remove(name);
				return;
			}
			List<HandlerMethod> newList = new ArrayList<HandlerMethod>(oldList.size() - 1);
			for (HandlerMethod current : oldList) {
				if (!current.equals(handlerMethod)) {
					newList.add(current);
				}
			}
			this.nameLookup.put(name, newList);
		}
	}
	
	@SuppressWarnings("hiding")
	private static class MappingRegistration<RequestMappingInfo> {

		private final RequestMappingInfo mapping;

		private final HandlerMethod handlerMethod;

		private final List<String> directUrls;

		private final String mappingName;

		public MappingRegistration(RequestMappingInfo mapping, HandlerMethod handlerMethod, List<String> directUrls, String mappingName) {
			Assert.notNull(mapping);
			Assert.notNull(handlerMethod);
			this.mapping = mapping;
			this.handlerMethod = handlerMethod;
			this.directUrls = (directUrls != null ? directUrls : Collections.<String>emptyList());
			this.mappingName = mappingName;
		}

		public RequestMappingInfo getMapping() {
			return this.mapping;
		}

		public HandlerMethod getHandlerMethod() {
			return this.handlerMethod;
		}

		public List<String> getDirectUrls() {
			return this.directUrls;
		}

		public String getMappingName() {
			return this.mappingName;
		}
	}


	/**
	 * A thin wrapper around a matched HandlerMethod and its mapping, for the purpose of
	 * comparing the best match with a comparator in the context of the current request.
	 */
	private class Match {

		private final RequestMappingInfo mapping;

		private final HandlerMethod handlerMethod;

		public Match(RequestMappingInfo mapping, HandlerMethod handlerMethod) {
			this.mapping = mapping;
			this.handlerMethod = handlerMethod;
		}

		@Override
		public String toString() {
			return this.mapping.toString();
		}
	}


	private class MatchComparator implements Comparator<Match> {

		private final Comparator<RequestMappingInfo> comparator;

		public MatchComparator(Comparator<RequestMappingInfo> comparator) {
			this.comparator = comparator;
		}

		@Override
		public int compare(Match match1, Match match2) {
			return this.comparator.compare(match1.mapping, match2.mapping);
		}
	}


	private static class EmptyHandler {

		@SuppressWarnings("unused")
		public void handle() {
			throw new UnsupportedOperationException("not implemented");
		}
	}
}
