package com.zhang.core.mvc.annotation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.ClassUtils;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.AbstractHandlerMethodMapping.EmptyHandler;
import org.springframework.web.servlet.handler.AbstractHandlerMethodMapping.Match;
import org.springframework.web.servlet.handler.AbstractHandlerMethodMapping.MatchComparator;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

public class RequestMappingHandlerMappingPlus extends RequestMappingHandlerMapping {
	
	private static final HandlerMethod PREFLIGHT_AMBIGUOUS_MATCH =
			new HandlerMethod(new EmptyHandler(), ClassUtils.getMethod(EmptyHandler.class, "handle"));
	
	private static final String DEFAULT_HEAD_SERVER_NAME="APP_SERVER";
	
	private String headServerName=DEFAULT_HEAD_SERVER_NAME;

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
		 List<HandlerMethod> directNameMatches = getHandlerMethodsForMappingName(lookupName);
		 if (directNameMatches != null) {
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

		public void handle() {
			throw new UnsupportedOperationException("not implemented");
		}
	}
}
