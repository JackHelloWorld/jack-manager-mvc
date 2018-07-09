package com.jack.manager.service.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jack.manager.common.config.ResultCode;
import com.jack.manager.common.config.ResultDic;
import com.jack.manager.common.config.ResultTools;
import com.jack.manager.common.utils.DatePattern;
import com.jack.manager.common.utils.Tools;
import com.jack.manager.dao.LoginUserInfoDao;
import com.jack.manager.dao.repository.ClockInInfoRepository;
import com.jack.manager.dao.utils.DaoParam;
import com.jack.manager.pojo.app.ClockInInfo;
import com.jack.manager.pojo.app.LoginUser;
import com.jack.manager.pojo.utils.PageUtils;
import com.jack.manager.service.utils.BaseService;

@Service
@Transactional(rollbackFor=Exception.class)
public class OAService extends BaseService{
	
	@Resource
	ClockInInfoRepository clockInInfoRepository;
	
	@Resource
	LoginUserInfoDao loginUserInfoDao;
	
	public ResultTools clockIn(ClockInInfo clockInInfo) throws Exception {
		
		if(clockInInfo.getId() != null)
			return ResultTools.ERROR(ResultDic.DATA_WRONG);
		
		if(!Tools.isBigDecimal(clockInInfo.getTimestamp()))
			return ResultTools.ERROR(ResultDic.DATA_WRONG);
		
		if(clockInInfo.getUserId()==null)
			return ResultTools.ERROR(ResultDic.DATA_WRONG);
		
		if(clockInInfo.getLatitude() == null || clockInInfo.getLongitude() == null)
			return ResultTools.ERROR(ResultDic.DATA_WRONG);
		
		if(clockInInfo.getType() == null || clockInInfo.getType() !=1 && clockInInfo.getType()!=2)
			return ResultTools.ERROR(ResultDic.DATA_WRONG);
		
		if(Tools.isEmpty(clockInInfo.getCoordsType()))
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "坐标类型有误");
		
		Date date = new Date();
		clockInInfo.setCreateTime(date);
		clockInInfo.setStatus(0);
		
		clockInInfoRepository.save(clockInInfo);
		
		return ResultTools.SUCCESS();
	}
	
	public ResultTools reClockIn(ClockInInfo clockInInfo) throws Exception {
		
		if(clockInInfo.getId() == null)
			return ResultTools.ERROR(ResultDic.DATA_WRONG);
		
		if(!Tools.isBigDecimal(clockInInfo.getTimestamp()))
			return ResultTools.ERROR(ResultDic.DATA_WRONG);
		
		if(clockInInfo.getLatitude() == null || clockInInfo.getLongitude() == null)
			return ResultTools.ERROR(ResultDic.DATA_WRONG);
		
		if(clockInInfo.getType() == null || clockInInfo.getType() !=1 && clockInInfo.getType()!=2)
			return ResultTools.ERROR(ResultDic.DATA_WRONG);
		
		if(Tools.isEmpty(clockInInfo.getCoordsType()))
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "坐标类型有误");
		
		ClockInInfo cc = clockInInfoRepository.findTop1ByUserIdAndStatusOrderByCreateTimeDesc(clockInInfo.getUserId(),0);
		
		if(cc == null)
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "更新信息不存在");
		
		if(!Tools.getDateToStr(cc.getCreateTime(), DatePattern.YYYYMMDD).equals(Tools.getDateToStr(new Date(), DatePattern.YYYYMMDD)))
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "只能更新当天的打卡记录");
		
		if(!cc.getUserId().equals(cc.getUserId()))
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "无权操作");
		
		if(!cc.getId().equals(clockInInfo.getId()))
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "只能更新最后一条打卡记录");
		
		if(cc.getStatus() != 0)
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "打卡记录不正常,无法更新");
		
		cc.setStatus(1);
		
		Date date = new Date();
		clockInInfo.setCreateTime(date);
		clockInInfo.setStatus(0);
		clockInInfo.setId(null);
		
		clockInInfoRepository.save(clockInInfo);
		cc.setReId(clockInInfo.getId());
		clockInInfoRepository.save(cc);
		
		return ResultTools.SUCCESS();
	
	}
	
	public ResultTools findDayClockIn(LoginUser loginUser) throws Exception {
		DaoParam param = DaoParam.GetParam();
		param.put("begin_time", Tools.getDateToStr(DatePattern.YYYYMMDD)+" 00:00:00");
		param.put("end_time", Tools.getDateToStr(DatePattern.YYYYMMDD)+" 23:59:59");
		param.put("user_id", loginUser.getId());
		List<Map<String, Object>> list = loginUserInfoDao.findClockInfo(param);
		List<ClockInInfo> clockInInfos = new ArrayList<>();
		for (Map<String, Object> map : list) {
			clockInInfos.add(PageUtils.mapResultToPojo(map, ClockInInfo.class));
		}
		return ResultTools.SUCCESS(clockInInfos);
		
	}

}
