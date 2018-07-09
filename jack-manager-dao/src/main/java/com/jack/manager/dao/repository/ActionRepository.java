package com.jack.manager.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jack.manager.pojo.sys.Action;

@Repository
public interface ActionRepository extends JpaRepository<Action, Integer>{

	Action findTop1ByUrl(String url);

	long countByUrl(String url);

	Action getTop1ByUrl(String url);

	List<Action> findByMenuId(Integer menuId);

}
