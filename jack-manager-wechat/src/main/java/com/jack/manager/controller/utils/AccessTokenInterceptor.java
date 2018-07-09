package com.jack.manager.controller.utils;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jack.manager.common.config.ResultDic;
import com.jack.manager.common.config.ResultTools;
import com.jack.manager.service.wechat.AccessTokenService;

/**
 * AccessToken拦截器
 * @author liuJack
 *
 */
public class AccessTokenInterceptor extends HandlerInterceptorAdapter {
	
	@Resource
	AccessTokenService accessTokenService;
	
	private List<String> notInterceptor = new ArrayList<>();
	
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		for (String url : notInterceptor) {
			if(request.getRequestURI().toString().endsWith(url) || request.getRequestURI().toString().startsWith(url) || request.getRequestURI().toString().equals("/")){
				return true;
			}
		}
		if(!accessTokenService.gainAccessToken()){
			ResultTools resultTools = ResultTools.ERROR(ResultDic.SYS_ERROR);
			request.getRequestDispatcher("/error/common?code="+resultTools.get("code")+"&msg="+resultTools.get("msg")).forward(request, response);  
			return false;
		}
		return true;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

	public List<String> getNotInterceptor() {
		return notInterceptor;
	}

	public void setNotInterceptor(List<String> notInterceptor) {
		this.notInterceptor = notInterceptor;
	}
}
