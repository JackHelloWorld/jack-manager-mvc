package com.jack.manager.service.utils;

import java.lang.reflect.Method;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jack.manager.dao.repository.ActionRepository;
import com.jack.manager.dao.repository.ActionUserRepository;
import com.jack.manager.dao.repository.MenuRepository;
import com.jack.manager.dao.repository.MenuUserRepository;
import com.jack.manager.pojo.sys.Action;
import com.jack.manager.pojo.sys.Menu;
import com.jack.manager.pojo.sys.User;

@Service
public class PermissionService {

	@Resource
	ActionRepository actionRepository;
	
	@Resource
	MenuRepository menuRepository;
	
	@Resource
	MenuUserRepository menuUserRepository;
	
	@Resource
	ActionUserRepository actionUserRepository;
	
	
	
	public Action getUserAction(String url, User user) {
		
		Action an = actionRepository.findTop1ByUrl(url);
		
		if(an == null) return null;
		
		if(user.getIsAdmin()) return an;
		
		long countByUserId = actionUserRepository.countByUserIdAndActionId(user.getId(),an.getId());
		
		if(countByUserId > 0)
			return an;
		
		return null;
	}
	
	public boolean check(String uri,User user,Method method){
		
		IgnorePermission ignorePermission = method.getAnnotation(IgnorePermission.class);
		if(ignorePermission != null)
			return true;
		
		if(user.getIsAdmin()) return true;
		
		Action action = actionRepository.findTop1ByUrl(uri);
		
		Menu menu = menuRepository.findTop1ByUrl(uri.trim().startsWith("/")?uri.trim().substring(1):uri.trim());
		
		if(menu != null){
			long countMenu = menuUserRepository.countByUserIdAndMenuId(user.getId(),menu.getId());
			if(countMenu > 0)
				return true;
		}
		
		if(action != null){
			long countByUserId = actionUserRepository.countByUserIdAndActionId(user.getId(),action.getId());
			
			if(countByUserId > 0)
				return true;
		}
		return false;
	}

	public boolean checkToken(Method method) {
		
		IgnoreToken ignoreToken = method.getAnnotation(IgnoreToken.class);
		
		return ignoreToken != null;
	}
}
