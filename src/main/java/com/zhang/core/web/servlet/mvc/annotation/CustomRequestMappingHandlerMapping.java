package com.zhang.core.web.servlet.mvc.annotation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
/**
 * Creates {@link RequestMappingInfo} instances from type and method-level
 * {@link RequestMapping @RequestMapping} annotations in
 * {@link Controller @Controller} classes.
 */
public class CustomRequestMappingHandlerMapping extends RequestMappingHandlerMapping {
	
	
	private static final String DEFAULT_HEAD_SERVER_NAME="APP_SERVER";
	
	private String headServerName=DEFAULT_HEAD_SERVER_NAME;
	
	private static final HandlerMethod PREFLIGHT_AMBIGUOUS_MATCH =
			new HandlerMethod(new EmptyHandler(), ClassUtils.getMethod(EmptyHandler.class, "handle"));
	
	private final Map<HandlerMethod,RequestMappingInfo> mappingInfoLookup = new ConcurrentHashMap<HandlerMethod, RequestMappingInfo>();
	
	private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
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
		List<HandlerMethod> directNameHandlerMethodMatches = null;
		if(StringUtils.isEmpty(lookupName)){
			if (logger.isDebugEnabled()) {
				logger.debug("server name not found in request header " + lookupName);
			}
		}else{
			directNameHandlerMethodMatches = getHandlerMethodsForMappingName(lookupName);
		}
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
					if (logger.isDebugEnabled()) {
						logger.debug("server name  in request header found handler method return" );
					}
					return bestMatch.handlerMethod;
				}
			}
			return super.lookupHandlerMethod(lookupPath, request);
	}
	
	private void addMatchingMappings(Collection<RequestMappingInfo> mappings, List<Match> matches, HttpServletRequest request) {
		for (RequestMappingInfo mapping : mappings) {
			RequestMappingInfo match = getMatchingMapping(mapping, request);
			if (match != null) {
				matches.add(new Match(match,getHandlerMethods().get(mapping)));
			}
		}
	}
	
	@Override
	protected void registerHandlerMethod(Object handler, Method method,
			RequestMappingInfo mapping) {
		this.readWriteLock.writeLock().lock();
		try{
			HandlerMethod handlerMethod = createHandlerMethod(handler, method);
			this.mappingInfoLookup.put(handlerMethod, mapping);
		}finally{
			this.readWriteLock.writeLock().unlock();
		}
		super.registerHandlerMethod(handler, method, mapping);
	}
	
//	@Override
//	public void registerMapping(RequestMappingInfo mapping, Object handler,
//			Method method) {
//		if(logger.isDebugEnabled()){
//			logger.debug("CustomRequestMappingHandlerMapping registerMapping");
//		}
//		this.readWriteLock.writeLock().lock();
//		try{
//			HandlerMethod handlerMethod = createHandlerMethod(handler, method);
//			this.mappingInfoLookup.put(handlerMethod, mapping);
//		}finally{
//			this.readWriteLock.writeLock().unlock();
//			if(logger.isDebugEnabled()){
//				logger.debug("CustomRequestMappingHandlerMapping registerMapping size "+this.mappingInfoLookup.size());
//			}
//			System.out.println(">>>>>>>>>>>>>>>>CustomRequestMappingHandlerMapping registerMapping size "+this.mappingInfoLookup.size());
//		}
//		super.registerMapping(mapping, handler, method);
//	}
	
	public RequestMappingInfo getRequestMappingInfoByHandlerMethod(HandlerMethod handlerMethod){
		return this.mappingInfoLookup.get(handlerMethod);
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
