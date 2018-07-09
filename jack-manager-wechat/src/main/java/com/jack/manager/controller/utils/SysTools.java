package com.jack.manager.controller.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 系统工具类
 * @author liuJack
 *
 */
public class SysTools {
	
	/**
	 * 获得HttpServletRequest对象
	 * @return HttpServletRequest对象
	 */
	public static final HttpServletRequest getRequest(){
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	}
	
}
