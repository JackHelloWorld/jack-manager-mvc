package com.jack.manager.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jack.manager.pojo.sys.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

	long countByIdAndStatus(Integer id, Integer status);

	List<User> findByStatus(Integer status);

	User findByLoginName(String loginName);

	long countByLoginNameAndStatus(String loginName, Integer status);

	List<User> findByLoginNameAndStatus(String loginName, Integer status);

	User getTop1ByIdAndStatus(Integer id, Integer status);

	User findTop1ByLoginNameAndStatus(String loginName, Integer status);
	
}
