package com.jack.manager.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jack.manager.pojo.app.LoginUser;

@Repository
public interface LoginUserRepository extends JpaRepository<LoginUser, Integer>{

	LoginUser findTop1ByLoginNameAndStatus(String loginName,Integer status);

	long countByLoginNameAndStatus(String loginName,Integer status);
	
}
