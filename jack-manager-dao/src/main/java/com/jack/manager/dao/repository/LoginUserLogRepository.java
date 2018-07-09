package com.jack.manager.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jack.manager.pojo.app.LoginUserLog;

@Repository
public interface LoginUserLogRepository extends JpaRepository<LoginUserLog, Integer>{

}
