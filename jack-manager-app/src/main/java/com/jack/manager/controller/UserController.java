package com.jack.manager.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jack.manager.common.config.ResultTools;
import com.jack.manager.controller.utils.BaseController;
import com.jack.manager.pojo.app.LoginUser;
import com.jack.manager.service.app.UserLoginService;
import com.jack.manager.service.utils.IgnoreToken;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController{

	@Resource
	UserLoginService userLoginService;
	
	@IgnoreToken
	@PostMapping("/login")
	public ResultTools login(@RequestParam(value="login_name",defaultValue="")String loginName,
			@RequestParam(value="login_pwd",defaultValue="")String loginPwd,HttpServletRequest request) throws Exception{
		LoginUser loginUser = new LoginUser();
		loginUser.setLoginPwd(loginPwd);
		loginUser.setLoginName(loginName);
		return userLoginService.login(loginUser,getIpAddress(request));
	}
	
	@PostMapping("/load_menu_role")
	public ResultTools loadMenuRole() throws Exception{
		return userLoginService.loadMenuRole(loginUser());
	}
	
	
	
}
