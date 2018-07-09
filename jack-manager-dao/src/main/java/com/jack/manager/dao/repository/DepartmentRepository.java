package com.jack.manager.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jack.manager.pojo.app.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer>{
	
}
