package com.jack.manager.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jack.manager.common.config.ResultTools;
import com.jack.manager.controller.utils.BaseController;
import com.jack.manager.pojo.app.ClockInInfo;
import com.jack.manager.service.app.OAService;

@RestController
@RequestMapping("/app/oa")
public class OAController extends BaseController{

	@Resource
	OAService oAService;
	
	@PostMapping("/clock_in")
	public ResultTools clockIn(ClockInInfo clockInInfo,HttpServletRequest request) throws Exception{
		clockInInfo.setIp(getIpAddress(request));
		clockInInfo.setUserId(loginUser().getId());
		return oAService.clockIn(clockInInfo);
	}
	
	@PostMapping("/re_clock_in")
	public ResultTools reClockIn(ClockInInfo clockInInfo,HttpServletRequest request) throws Exception{
		clockInInfo.setIp(getIpAddress(request));
		clockInInfo.setUserId(loginUser().getId());
		return oAService.reClockIn(clockInInfo);
	}
	
	@PostMapping("/find_day_clockin")
	public ResultTools findDayClockIn() throws Exception{
		return oAService.findDayClockIn(loginUser());
	}
	
}
