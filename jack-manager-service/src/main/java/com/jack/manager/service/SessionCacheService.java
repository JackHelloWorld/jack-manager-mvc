package com.jack.manager.service;

import java.util.Enumeration;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.jack.manager.common.utils.Tools;
import com.jack.manager.pojo.app.LoginUser;

@Service
public class SessionCacheService {
	
	@Resource
	private RedisTemplate<String, Object> redisTemplate;
	
	private static final String SESSION_INFO = "SESSION_INFO_";
	
	private static final String USER_TOKEN = "USER_TOKEN_";
	
	//分
	private static final long SESSION_TIME_OUT = 30;
	
	//天
	private static final long USER_TIME_OUT = 15;
	
	@SuppressWarnings("unchecked")
	public <T> T getSessionAttribute(String sessionId,String name){
		return (T) redisTemplate.opsForHash().get(SESSION_INFO.concat(sessionId),name);
	}
	
	public void deleteSessionAttribute(String sessionId,String name){
		redisTemplate.opsForHash().delete(SESSION_INFO.concat(sessionId), name);
	}

	public void copySessionAttributes(HttpSession session) {
		HashOperations<String, String, Object> hashOperations = redisTemplate.opsForHash();
		redisTemplate.expire(SESSION_INFO.concat(session.getId()), SESSION_TIME_OUT, TimeUnit.MINUTES); 
		@SuppressWarnings("unchecked")
		Enumeration<String> attrs = session.getAttributeNames();
		while (attrs.hasMoreElements()) {
			String name = (String) attrs.nextElement();
			hashOperations.put(SESSION_INFO.concat(session.getId()), name, session.getAttribute(name));
		}
	}
	
	public void setLoginUser(String token,LoginUser user) {
		redisTemplate.opsForValue().set(USER_TOKEN.concat(token), user, USER_TIME_OUT, TimeUnit.DAYS);
	}
	
	public LoginUser getLoginUser(String token) {
		if(Tools.isEmpty(token)) return null;
		return (LoginUser) redisTemplate.opsForValue().get(USER_TOKEN.concat(token));
	}
	
}
