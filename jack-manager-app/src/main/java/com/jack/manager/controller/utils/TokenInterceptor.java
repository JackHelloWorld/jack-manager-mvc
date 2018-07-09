package com.jack.manager.controller.utils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jack.manager.common.config.ResultDic;
import com.jack.manager.common.config.ResultTools;
import com.jack.manager.pojo.app.LoginUser;
import com.jack.manager.service.app.UserLoginService;
import com.jack.manager.service.utils.PermissionService;
import com.jack.manager.service.utils.ThreadData;

/**
 * 公共参数设置截器
 * @author liuJack
 *
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {
	
	@Resource
	UserLoginService userLoginService;
	
	@Resource
	PermissionService permissionService;
	
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		if(permissionService.checkToken(handlerMethod.getMethod())) return true;
		
		response.addHeader("Access-Control-Allow-Origin","*");
        response.addHeader("Access-Control-Allow-Methods","*");
        response.addHeader("Access-Control-Max-Age","100");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type");
        response.addHeader("Access-Control-Allow-Credentials","false");
		String token = request.getHeader("token");
		LoginUser loginUser = userLoginService.getUserByToken(token);
		if(loginUser != null){
			ThreadData.setLoginUser(loginUser);
		}else{
			ResultTools resultTools = ResultTools.ERROR(ResultDic.NOT_LOGIN);
			request.getRequestDispatcher("/error/common?code="+resultTools.get("code")+"&msg="+resultTools.get("msg")).forward(request, response); 
			return false;
		}
		return true;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		userLoginService.setCacheUser(ThreadData.getLoginUser());
	}

}
