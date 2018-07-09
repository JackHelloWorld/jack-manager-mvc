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
import com.jack.manager.controller.utils.BaseController;
import com.jack.manager.pojo.sys.Action;
import com.jack.manager.pojo.sys.Menu;
import com.jack.manager.pojo.utils.PageInfo;
import com.jack.manager.service.MenuService;
import com.jack.manager.service.utils.IgnorePermission;

@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController{
	
	@Resource
	MenuService menuService;

	@GetMapping("")
	public String page(HttpServletRequest request){
		setMenus(request);
		setAttribute(request, menuService.initPage());
		return "sys_manager/menu";
	}
	
	@RequestMapping(value="/data",method = RequestMethod.POST)
	@ResponseBody
	public PageInfo data(@RequestParam(value="pageNumber",defaultValue="1")Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue="20")Integer pageSize,
			@RequestParam(value="text",defaultValue="")String menuText){
		return menuService.findMenu(pageNumber, pageSize, menuText);
	}
	
	@RequestMapping(value="/delete",method = RequestMethod.POST)
	@ResponseBody
	public ResultTools delete(@RequestParam(value="id",defaultValue="0")Integer id){
		return menuService.delete(id);
	}
	
	@IgnorePermission
	@RequestMapping(value="/find_id",method = RequestMethod.POST)
	@ResponseBody
	public ResultTools findById(@RequestParam(value="id",defaultValue="0")Integer id){
		return menuService.findById(id);
	}
	
	@IgnorePermission
	@RequestMapping(value="/action/find_id",method = RequestMethod.POST)
	@ResponseBody
	public ResultTools findActionById(@RequestParam(value="id",defaultValue="0")Integer id){
		return menuService.findActionById(id);
	}
	
	@RequestMapping(value="/update",method = RequestMethod.POST)
	@ResponseBody
	public ResultTools update(Menu menu){
		return menuService.update(menu);
	}
	
	@RequestMapping(value="/save",method = RequestMethod.POST)
	@ResponseBody
	public ResultTools save(Menu menu){
		return menuService.save(menu);
	}
	
	@IgnorePermission
	@RequestMapping(value="/action/data",method = RequestMethod.POST)
	@ResponseBody
	public PageInfo actionData(@RequestParam(value="pageNumber",defaultValue="1")Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue="20")Integer pageSize,
			@RequestParam(value="menu_id",defaultValue="0")Integer menuId){
		return menuService.actionData(pageNumber,pageSize,menuId);
	}
	
	@RequestMapping(value="/action/add",method = RequestMethod.POST)
	@ResponseBody
	public ResultTools actionData(Action action){
		return menuService.actionAdd(action);
	}
	
	@RequestMapping(value="/action/update",method = RequestMethod.POST)
	@ResponseBody
	public ResultTools actionUpdate(Action action){
		return menuService.actionUpdate(action);
	}
	
	@RequestMapping(value="/action/delete",method = RequestMethod.POST)
	@ResponseBody
	public ResultTools actionDelete(@RequestParam(value="id",defaultValue="0")Integer id){
		return menuService.actionDelete(id);
	}
	
}
