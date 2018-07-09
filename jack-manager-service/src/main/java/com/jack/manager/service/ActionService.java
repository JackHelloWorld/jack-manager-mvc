package com.jack.manager.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jack.manager.service.utils.BaseService;

@Service
@Transactional(rollbackFor = Exception.class)
public class ActionService extends BaseService{
	
}
