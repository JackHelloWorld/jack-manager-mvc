package com.jack.manager.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jack.manager.common.config.ResultTools;
import com.jack.manager.controller.utils.BaseController;
import com.jack.manager.service.utils.IgnoreToken;

@RestController
public class IndexController extends BaseController{

	@IgnoreToken
	@RequestMapping("/")
	public ResultTools index(){
		return ResultTools.SUCCESS("manager app is up and running.....");
	}
	
}
