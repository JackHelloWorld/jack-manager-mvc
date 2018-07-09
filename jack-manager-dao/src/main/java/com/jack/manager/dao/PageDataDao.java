package com.jack.manager.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;

@Repository
public interface PageDataDao {

	Page<Map<String, Object>> menuPage(@Param("menuText") String menuText);
	
	Page<Map<String, Object>> userPage(@Param("name") String name);

	Page<?> actionPageByMenuId(@Param("menuId")Integer menuId);

	Page<?> companyPage(@Param("name") String name, @Param("status") Integer status);

}
