package com.jack.manager.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jack.manager.common.config.ResultTools;
import com.jack.manager.controller.utils.BaseController;
import com.jack.manager.service.wechat.HomeService;

@RestController
@RequestMapping("/home")
public class HomeController extends BaseController{

	@Resource
	HomeService homeService;
	
	/**
	 * 加载banner图//模块图片链接
	 * @return
	 */
	@RequestMapping("/load_banner")
	public ResultTools loadBanner(){
		return homeService.loadBanner();
	}
	
	/**
	 * 列表数据请求
	 * @return
	 */
	@RequestMapping("/load_list")
	public ResultTools loadList(@RequestParam(value="page_number",defaultValue="1")Integer pageNumber,
			@RequestParam(value="page_size",defaultValue="20")Integer pageSize,
			@RequestParam(value="title",defaultValue="")String title){
		return homeService.loadList(pageNumber,pageSize,title);
	}
	
}
