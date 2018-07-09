package com.jack.manager.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.jack.manager.pojo.sys.ActionUser;

@Repository
public interface ActionUserRepository extends JpaRepository<ActionUser,Integer>{

	long countByUserIdAndActionId(Integer userId, Integer actionId);

	List<ActionUser> findByUserId(Integer userId);

	@Modifying
	void deleteByUserId(Integer userId);
	
}
