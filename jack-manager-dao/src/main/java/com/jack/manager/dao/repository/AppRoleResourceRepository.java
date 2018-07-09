package com.jack.manager.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jack.manager.pojo.app.AppRoleResource;

@Repository
public interface AppRoleResourceRepository extends JpaRepository<AppRoleResource,Integer>{

	@Query("from AppRoleResource arre,AppRoleResourceUser arru where arru.resourceId = arre.id and arru.userId = :userId")
	List<Object[]> findByUser(@Param("userId")Integer userId);
	
}
