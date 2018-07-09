package com.jack.manager.dao.wechat;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jack.manager.pojo.wechat.AccessToken;


@Repository
public interface AccessTokenRepository extends JpaRepository<AccessToken,Integer>{

	@Query(value="select * from wx_access_token order by create_time desc limit 1",nativeQuery=true)
	public Map<String, Object> getTop1OrderByCreateTime();
	
}
