package com.jack.manager.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jack.manager.pojo.sys.CompanyRenewalInfo;

@Repository
public interface CompanyRenewalInfoRepository extends JpaRepository<CompanyRenewalInfo, Integer>{
	
}
