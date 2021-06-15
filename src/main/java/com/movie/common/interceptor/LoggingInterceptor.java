package com.movie.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoggingInterceptor implements HandlerInterceptor {

	final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod method = (HandlerMethod) handler;
			logger.info("preHandle() 실행 : {}", method);
		} else {
			logger.info("preHandle() 실행 : {}", handler);
		}
		request.setAttribute("interceptor.startTime", System.currentTimeMillis());
		return true;
	} // preHandle

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mav)
			throws Exception {
	} // postHandle

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		Long startTime = (Long) request.getAttribute("interceptor.startTime");
		long duration = System.currentTimeMillis() - startTime;
		logger.info("{} : 수행시간 = {}", request.getRequestURI(), duration);
		if (ex != null) {
			logger.error("afterCompletion : {}", ex.getMessage(), ex);
		}
	} // afterCompletion
} // class
