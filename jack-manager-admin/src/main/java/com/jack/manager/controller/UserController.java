package com.jack.manager.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jack.manager.common.config.ResultTools;
import com.jack.manager.common.exception.BusinessException;
import com.jack.manager.controller.utils.BaseController;
import com.jack.manager.pojo.sys.User;
import com.jack.manager.pojo.utils.PageInfo;
import com.jack.manager.service.UserService;
import com.jack.manager.service.utils.IgnorePermission;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	
	@Resource
	UserService userService;

	@GetMapping("")
	public String page(HttpServletRequest request){
		setMenus(request);
		setAttribute(request, userService.findMenus());
		return "sys_manager/user";
	}
	
	@RequestMapping(value="/data",method = RequestMethod.POST)
	@ResponseBody
	public PageInfo data(@RequestParam(value="pageNumber",defaultValue="1")Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue="20")Integer pageSize,
			@RequestParam(value="name",defaultValue="")String name){
		return userService.findUser(pageNumber, pageSize, name);
	}
	
	@RequestMapping(value="/save",method = RequestMethod.POST)
	@ResponseBody
	public ResultTools save(User user) throws Exception{
		return userService.save(user);
	}
	
	@RequestMapping(value="/update",method = RequestMethod.POST)
	@ResponseBody
	public ResultTools update(User user){
		return userService.update(user);
	}
	
	@RequestMapping(value="/role",method = RequestMethod.POST)
	@ResponseBody
	public ResultTools role(Integer id,String[] actions,String[] menus) throws BusinessException{
		return userService.role(id,actions,menus);
	}
	
	@IgnorePermission
	@RequestMapping(value="/get_user_role",method = RequestMethod.POST)
	@ResponseBody
	public ResultTools getUserRole(@RequestParam(value="id",defaultValue="0")Integer id){
		return userService.getUserRole(id);
	}
	
	@IgnorePermission
	@RequestMapping(value="/find_id",method = RequestMethod.POST)
	@ResponseBody
	public ResultTools findById(@RequestParam(value="id",defaultValue="0")Integer id){
		return userService.findById(id);
	}
	
	@RequestMapping(value="/delete",method = RequestMethod.POST)
	@ResponseBody
	public ResultTools delete(@RequestParam(value="id",defaultValue="0")Integer id){
		return userService.delete(id, getUser());
	}
	
}
