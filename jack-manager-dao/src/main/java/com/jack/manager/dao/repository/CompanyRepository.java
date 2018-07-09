package com.jack.manager.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jack.manager.pojo.sys.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer>{

	long countByNameAndStatusNot(String name, Integer status);

	Company findTop1ByNameAndStatusNot(String name, Integer status);

}
