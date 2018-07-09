package com.jack.manager.service.utils;

import com.jack.manager.pojo.app.LoginUser;
import com.jack.manager.pojo.sys.User;

public class ThreadData {


	private static final ThreadLocal<User> USER = new ThreadLocal<User>();

	private static final ThreadLocal<LoginUser> LOGINUSER = new ThreadLocal<LoginUser>();

	public static LoginUser getLoginUser(){
		return LOGINUSER.get();
	}

	public static final void setLoginUser(LoginUser loginUser){
		LOGINUSER.set(loginUser);
	}

	/**
	 * 获得登陆名称
	 * @return
	 */
	public static final String getUserName(){
		return USER.get().getLoginName();
	}

	public static final void setUser(User user){
		USER.set(user);
	}

	public static final void removeUser(){
		USER.remove();
	}

	/**
	 * 获得当前登陆用户
	 * @return
	 */
	public static final User getUser(){
		return USER.get();
	}


}
