package com.jack.manager.controller.utils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jack.manager.service.SessionCacheService;

/**
 * session拦截器
 * @author liuJack
 *
 */
public class SessionInterceptor extends HandlerInterceptorAdapter {

	@Resource
	SessionCacheService sessionCacheService;

	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		
		return true;
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		sessionCacheService.copySessionAttributes(request.getSession());
	}
}
