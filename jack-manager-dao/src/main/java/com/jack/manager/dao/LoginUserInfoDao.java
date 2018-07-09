package com.jack.manager.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jack.manager.dao.utils.DaoParam;

@Repository
public interface LoginUserInfoDao {

	/**
	 * 根据条件查询签到列表
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> findClockInfo(DaoParam param);

	
	
}
