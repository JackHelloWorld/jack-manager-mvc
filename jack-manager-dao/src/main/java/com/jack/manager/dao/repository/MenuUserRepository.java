package com.jack.manager.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.jack.manager.pojo.sys.MenuUser;

@Repository
public interface MenuUserRepository extends JpaRepository<MenuUser,Integer>{

	long countByUserIdAndMenuId(Integer userId, Integer menuId);

	List<MenuUser> findByUserId(Integer userId);

	@Modifying
	void deleteByUserId(Integer userId);
	
}
