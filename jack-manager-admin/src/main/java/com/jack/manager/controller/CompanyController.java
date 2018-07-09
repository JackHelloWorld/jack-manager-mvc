package com.jack.manager.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jack.manager.common.config.ResultTools;
import com.jack.manager.controller.utils.BaseController;
import com.jack.manager.pojo.sys.Company;
import com.jack.manager.pojo.utils.PageInfo;
import com.jack.manager.service.CompanyService;
import com.jack.manager.service.utils.IgnorePermission;

@Controller
@RequestMapping("/company")
public class CompanyController extends BaseController {

	@Resource
	CompanyService companyService;
	
	@GetMapping("")
	public String index(HttpServletRequest request){
		setMenus(request);
		return "sys_manager/company";
	}
	
	@ResponseBody
	@PostMapping("/data")
	public PageInfo data(@RequestParam(value="pageNumber",defaultValue="1")Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue="20")Integer pageSize,
			@RequestParam(value="name",defaultValue="")String name,@RequestParam(value="status",defaultValue="0")Integer status){
		return companyService.pageData(pageNumber,pageSize,name,status);
	}
	
	@ResponseBody
	@PostMapping("/save")
	public ResultTools save(Company company) throws Exception{
		return companyService.save(company,getUser());
	}
	
	@ResponseBody
	@PostMapping("/delete")
	public ResultTools delete(@RequestParam("id")Integer id){
		return companyService.delete(id);
	}
	
	@ResponseBody
	@PostMapping("/update")
	public ResultTools update(Company company) throws Exception{
		return companyService.update(company);
	}
	
	@IgnorePermission
	@ResponseBody
	@PostMapping("/find_id")
	public ResultTools findById(Integer id){
		return companyService.findById(id);
	}
	
	@ResponseBody
	@PostMapping("/renewal")
	public ResultTools renewal(Integer id,@RequestParam(value="day",defaultValue="0")String day){
		return companyService.renewal(id,day,getUser());
	}
	
}
