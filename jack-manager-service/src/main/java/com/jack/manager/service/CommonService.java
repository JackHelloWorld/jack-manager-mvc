package com.jack.manager.service;

import javax.annotation.Resource;

import org.apache.hadoop.fs.FileSystem;
import org.springframework.stereotype.Service;

import com.jack.manager.common.config.ResultTools;
import com.jack.manager.service.utils.BaseService;

@Service
public class CommonService extends BaseService{

	@Resource
	FileSystem fileSystem;
	
	public ResultTools uploadFile(byte[] bytes) {
		
		
		
		
		
		return null;
	}
	
}
