package com.jack.manager.service;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jack.manager.common.config.Const;
import com.jack.manager.common.config.ResultCode;
import com.jack.manager.common.config.ResultDic;
import com.jack.manager.common.config.ResultTools;
import com.jack.manager.common.utils.SysTools;
import com.jack.manager.common.utils.Tools;
import com.jack.manager.dao.repository.UserRepository;
import com.jack.manager.pojo.sys.User;
import com.jack.manager.service.utils.BaseService;

@Service
@Transactional(rollbackFor = Exception.class)
public class SysService extends BaseService{

	@Resource
	private UserRepository userRepository;	

	/**
	 * 用户登录
	 * @return 是否登录成功
	 */
	public ResultTools login(User user){
		
		User result = userRepository.findTop1ByLoginNameAndStatus(user.getLoginName(),0);
		
		if(result==null) return ResultTools.DIY_ERROR(ResultCode.DataErrorCode,"用户不存在");

		String pwd = "";
		try {
			pwd = Tools.MD5(user.getLoginPwd(), result.getLoginEncry());
		} catch (Exception e) {
			return ResultTools.ERROR(ResultDic.SYS_ERROR);
		}
		if(!pwd.equals(result.getLoginPwd())) return ResultTools.DIY_ERROR(ResultCode.DataErrorCode,"密码错误");
		SysTools.getSession().setAttribute(Const.ServletConfig.SessionUser, result);
		result.setLastLoginTime(new Date());
		userRepository.save(result);
		return ResultTools.SUCCESS();
	}
	
	public ResultTools updatePwd(String oldPwd,String newPwd,Integer id){
		final User user = userRepository.findOne(id);
		if(user==null) return ResultTools.ERROR(ResultDic.SYS_ERROR);
		try {
			if(!Tools.MD5(oldPwd, user.getLoginEncry()).equals(user.getLoginPwd())) 
				return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "原密码输入错误");
			String newPwdD = Tools.MD5(newPwd, user.getLoginEncry());
			if(user.getLoginPwd().equals(newPwdD))
				return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "原密码不能与新密码输入一致");
			user.setLoginPwd(newPwdD);
			userRepository.save(user);
			return ResultTools.SUCCESS();
		} catch (Exception e) {
			return ResultTools.ERROR(ResultDic.SYS_ERROR);
		}
	}

	/**
	 * 生成验证码
	 * @Title 
	 * @Desc 
	 * @return
	 */
	public String generateCode() {
		return generateEncry(4);
	}

	

}
