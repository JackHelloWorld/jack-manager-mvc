package com.jack.manager.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuDao {

	List<Map<String, Object>> selectUserMenus(@Param("userId")Integer userId);
	
}
