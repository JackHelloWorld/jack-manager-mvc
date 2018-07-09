package com.jack.manager.controller.utils;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jack.manager.common.config.Const;
import com.jack.manager.pojo.sys.User;
import com.jack.manager.service.ActionService;
import com.jack.manager.service.MenuService;
import com.jack.manager.service.SessionCacheService;
import com.jack.manager.service.utils.ThreadData;

/**
 * 用户拦截器
 * @author liuJack
 *
 */
public class UserInterceptor extends HandlerInterceptorAdapter {

	private List<String> uncheckUrls;

	@Resource
	MenuService menuService;
	
	@Resource
	SessionCacheService SessionCacheService;

	@Resource
	ActionService actionService;

	public void setUncheckUrls(List<String> uncheckUrls) {
		this.uncheckUrls = uncheckUrls;
	}

	private boolean isAjax(HttpServletRequest request){
		return  (request.getHeader("X-Requested-With") != null  
				&& "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString())) ;
	}

	public static boolean isAjaxRequest(WebRequest webRequest) {
		String requestedWith = webRequest.getHeader("X-Requested-With");
		return requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false;
	}

	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		ServletContext servletContext = request.getSession().getServletContext();
		ThreadData.setUser((User)request.getSession().getAttribute(Const.ServletConfig.SessionUser));
		if(StringUtils.isEmpty(servletContext.getAttribute("contextPath"))){
			servletContext.setAttribute("contextPath",servletContext.getContextPath());
		}
		String requestUrl = request.getRequestURI();
		for (String unUrl : uncheckUrls) {
			if(unUrl.equals("/")) {
				if(requestUrl.equals(unUrl)) return true;
			}
			else{
				if (requestUrl.startsWith(unUrl)) return true;
			}
		}
		if(SessionCacheService.getSessionAttribute(request.getSession().getId(), Const.ServletConfig.SessionUser)==null){
			if(isAjax(request)){
				response.sendRedirect("/notLoginAjax");
			}else {
				response.sendRedirect("/toLogin");
			}
			return false;
		}
		request.setAttribute("path", request.getContextPath());
		return true;  //继续执行action
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		ThreadData.removeUser();
	}
}
