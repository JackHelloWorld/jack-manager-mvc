package com.jack.manager.controller;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jack.manager.common.config.ResultCode;
import com.jack.manager.common.config.ResultTools;
import com.jack.manager.controller.utils.BaseController;
import com.jack.manager.service.CommonService;

@RestController
@RequestMapping("/app/common")
public class CommonController extends BaseController{
	
	@Resource
	CommonService commonService;
	
	@PostMapping("/upload")
	public ResultTools upload(@RequestParam(value="file",required=false)MultipartFile file) throws IOException{
		if(file == null)
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "上传文件不存在");
		return commonService.uploadFile(file.getBytes());
		
	}
	
}
