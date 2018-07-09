package com.jack.manager.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jack.manager.pojo.sys.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu,Integer>{

	Menu findTop1ByUrl(String url);

	long countByText(String text);

	List<Menu> findByStatus(Integer status);

	long countByIdAndStatus(Integer id, Integer status);
	
}
