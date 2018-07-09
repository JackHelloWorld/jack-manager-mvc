package com.jack.manager.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jack.manager.pojo.app.ClockInInfo;

@Repository
public interface ClockInInfoRepository extends JpaRepository<ClockInInfo, Integer>{

	ClockInInfo findTop1ByUserIdAndStatusOrderByCreateTimeDesc(Integer userId, Integer status);
	
}
