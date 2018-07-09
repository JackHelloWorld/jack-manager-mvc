package com.jack.manager.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jack.manager.common.config.ResultTools;
import com.jack.manager.service.utils.IgnorePermission;

@RestController
@RequestMapping("/error")
public class ErrorController{

	@IgnorePermission
	@RequestMapping(value="/common",method={RequestMethod.GET,RequestMethod.POST})
	public ResultTools error(String msg,Integer code){
		return ResultTools.ERROR(code, msg);
	}
	
}
