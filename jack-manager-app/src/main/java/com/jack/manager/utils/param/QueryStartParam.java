package com.jack.manager.utils.param;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import com.jack.manager.common.utils.ParamRecord;
import com.jack.manager.common.utils.Tools;

public class QueryStartParam {

	public static final ParamRecord getStartParam(HttpServletRequest request,String start){
		@SuppressWarnings("unchecked")
		Enumeration<String> names = request.getParameterNames();
		ParamRecord record = new ParamRecord();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			if(name.startsWith(start)){
				String value = request.getParameter(name);
				if(Tools.notNull(value)) value = value.trim();
				record.put(name.substring(start.length()), value);
			}
		}
		return record;
	}
	
	public static final ParamRecord getStartParam(HttpServletRequest request){
		@SuppressWarnings("unchecked")
		Enumeration<String> names = request.getParameterNames();
		ParamRecord record = new ParamRecord();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			String value = request.getParameter(name);
			if(Tools.notNull(value)) value = value.trim();
			record.put(name, value);
			
		}
		return record;
	}
	
}
