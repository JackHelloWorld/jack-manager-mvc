package com.jack.manager.controller.utils;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jack.manager.common.config.Const;
import com.jack.manager.common.config.ResultDic;
import com.jack.manager.common.config.ResultTools;
import com.jack.manager.pojo.sys.User;
import com.jack.manager.service.SessionCacheService;
import com.jack.manager.service.utils.PermissionService;
import com.jack.manager.service.utils.ThreadData;

/**
 * 权限拦截器
 * @author liuJack
 *
 */
public class PermissionInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger LOGGER = Logger.getLogger(HandlerInterceptorAdapter.class);

	private List<String> uncheckUrls;

	@Resource
	PermissionService permissionService;
	
	@Resource
	SessionCacheService sessionCacheService;

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
		ThreadData.setUser((User)sessionCacheService.getSessionAttribute(request.getSession().getId(),Const.ServletConfig.SessionUser));
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
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		if(!permissionService.check(requestUrl, (User)sessionCacheService.getSessionAttribute(request.getSession().getId(),Const.ServletConfig.SessionUser),handlerMethod.getMethod())){
			LOGGER.debug("无权访问:\t"+requestUrl);
			if(isAjax(request)){
				ResultTools resultTools = ResultTools.ERROR(ResultDic.NOT);
				request.getRequestDispatcher("/error/common?code="+resultTools.get("code")+"&msg="+resultTools.get("msg")).forward(request, response);  
			}else {
				request.getRequestDispatcher("/WEB-INF/view/NOT.jsp").forward(request,response);
			}
			return false;
		}
		return true;  //继续执行action
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
}
