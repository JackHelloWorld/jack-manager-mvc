package com.jack.manager.controller.index;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jack.manager.common.config.Const;
import com.jack.manager.common.config.ResultCode;
import com.jack.manager.common.config.ResultDic;
import com.jack.manager.common.config.ResultTools;
import com.jack.manager.common.utils.SysTools;
import com.jack.manager.common.utils.Tools;
import com.jack.manager.controller.utils.BaseController;
import com.jack.manager.controller.utils.RandomImageGenerator;
import com.jack.manager.pojo.sys.User;
import com.jack.manager.service.SysService;
import com.jack.manager.service.utils.IgnorePermission;

@Controller
public class IndexController extends BaseController {

	@Resource
	private SysService service;
	
	@IgnorePermission
	@RequestMapping("/login")
	@ResponseBody
	public ResultTools login(@RequestParam(value="login_name",defaultValue="") String name ,
			@RequestParam(value="login_code",defaultValue="") String code,
			@RequestParam(value="login_pwd",defaultValue="") String pwd,HttpServletRequest request){
		if(Tools.isEmpty(code))
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "请输入验证码");
		String loginCode = getAttribute("login_code");
		if(Tools.isEmpty(loginCode) || !code.trim().equals(loginCode.trim()))
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "验证码输入错误");
		User user = new User();
		user.setLoginName(name);
		user.setLoginPwd(pwd);
		if(StringUtils.isEmpty(user.getLoginName())||StringUtils.isEmpty(user.getLoginPwd()))
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "请输入用户名和密码");
		return service.login(user);
	}

	@IgnorePermission
	@RequestMapping(value={"/toLogin"})
	public String toLogin(HttpServletRequest request){
		removeAttribute(Const.ServletConfig.SessionUser);
		return "login";
	}
	
	@IgnorePermission
	@RequestMapping("/notLoginAjax")
	@ResponseBody
	public ResultTools notLoginAjax(){
		return ResultTools.ERROR(ResultDic.NOT_LOGIN);
	}
	
	@IgnorePermission
	@RequestMapping("/index")
	public String index(HttpServletRequest request){
		setMenus(request);
		request.setAttribute("page_title", "控制中心");
		return "index";
	}

	@IgnorePermission
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request){
		removeAttribute(Const.ServletConfig.SessionUser);
		return "login";
	}

	@IgnorePermission
	@RequestMapping(value="/update_pwd",method=RequestMethod.POST)
	@ResponseBody
	public ResultTools updatePwd(@RequestParam(value="old_pwd",defaultValue="")String oldPwd,@RequestParam(value="new_pwd",defaultValue="")String newPwd){
		if(Tools.isEmpty(newPwd) || Tools.isEmpty(oldPwd)) return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "请输入密码");
		return service.updatePwd(oldPwd, newPwd, getUser().getId());
	}
	
	@IgnorePermission
	@RequestMapping("/generate_code")
	public void generateCode(HttpServletRequest request,HttpServletResponse response){
		String code = service.generateCode();
		SysTools.getSession().setAttribute("login_code", code);
		try {
			RandomImageGenerator.render(code, response.getOutputStream(),130,40);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@IgnorePermission
	@GetMapping("/update_pass_page")
	public String updatePassPage(String toPage){
		return "personal_center/update_pass";
	}

}
