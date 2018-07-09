package com.jack.manager.service.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jack.manager.common.config.ResultCode;
import com.jack.manager.common.config.ResultTools;
import com.jack.manager.common.utils.Tools;
import com.jack.manager.dao.repository.AppRoleResourceRepository;
import com.jack.manager.dao.repository.CompanyRepository;
import com.jack.manager.dao.repository.LoginUserLogRepository;
import com.jack.manager.dao.repository.LoginUserRepository;
import com.jack.manager.pojo.app.AppRoleResource;
import com.jack.manager.pojo.app.LoginUser;
import com.jack.manager.pojo.app.LoginUserLog;
import com.jack.manager.pojo.sys.Company;
import com.jack.manager.service.SessionCacheService;
import com.jack.manager.service.utils.BaseService;
import com.jack.manager.service.utils.ThreadData;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserLoginService extends BaseService{

	@Resource
	SessionCacheService sessionCacheService;
	
	@Resource
	LoginUserRepository loginUserRepository;
	
	@Resource
	AppRoleResourceRepository appRoleResourceRepository;
	
	@Resource
	LoginUserLogRepository loginUserLogRepository;
	
	@Resource
	CompanyRepository companyRepository;
	
	public LoginUser getUserByToken(String token) {
		return sessionCacheService.getLoginUser(token);
	}
	
	public void setCacheUser(LoginUser loginUser) {
		if(loginUser == null) return;
		sessionCacheService.setLoginUser(loginUser.getToken(), loginUser);
	}

	public ResultTools login(LoginUser user,String ip) throws Exception {
		
		if(Tools.isEmpty(user.getLoginName()))
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "请输入登录名");
		
		if(Tools.isEmpty(user.getLoginPwd()))
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "请输入登录密码");
		
		LoginUser loginUser = loginUserRepository.findTop1ByLoginNameAndStatus(user.getLoginName().trim(),0);
		
		if(loginUser == null)
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "登录用户不存在");
		
		String pwd = Tools.MD5(user.getLoginPwd(), loginUser.getLoginEncry());
		
		if(!pwd.equals(loginUser.getLoginPwd()))
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "用户名或密码输入错误");
		
		Company company = companyRepository.findOne(loginUser.getCompanyId());
		
		if(company == null)
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "当前公司信息不存在");
		
		/**状态:{0:正常,1:过期,2:已删除}*/
		switch (company.getStatus()) {
		case 1:
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "当前公司已过期");
		case 2:
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "当前公司已删除");

		default:
			if(company.getStatus() != 0)
				return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "当前公司信息有误");
			break;
		}
		
		if(company.getExpireTime().getTime() < System.currentTimeMillis()){
			company.setStatus(1);
			companyRepository.save(company);
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "当前公司已过期");
		}
		
		//token生成
		String token = Tools.MD5(loginUser.getId()+"token"+System.currentTimeMillis()+generateEncry(5), generateEncry(5));
		loginUser.setCompany(company);
		loginUser.setToken(token);
		ThreadData.setLoginUser(loginUser);
		
		//保存日志
		LoginUserLog log = new LoginUserLog();
		log.setLoginIp(ip);
		log.setLoginTime(new Date());
		log.setUserId(loginUser.getId());
		loginUserLogRepository.save(log);
		
		return ResultTools.SUCCESS(token);
	}

	public ResultTools loadMenuRole(LoginUser loginUser) {
		
		if(loginUser.getIsAdmin())
			return ResultTools.SUCCESS(appRoleResourceRepository.findAll());

		List<Object[]> list = appRoleResourceRepository.findByUser(loginUser.getId());
		
		List<AppRoleResource> result = new ArrayList<>();
		
		for (Object[] objects : list) {
			result.add((AppRoleResource)objects[0]);
		}
		
		return ResultTools.SUCCESS(result);
		
	}

}
