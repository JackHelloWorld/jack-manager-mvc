package com.jack.manager.service;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.jack.manager.common.config.Const;
import com.jack.manager.common.config.ResultCode;
import com.jack.manager.common.config.ResultDic;
import com.jack.manager.common.config.ResultTools;
import com.jack.manager.common.utils.Tools;
import com.jack.manager.dao.PageDataDao;
import com.jack.manager.dao.repository.CompanyRenewalInfoRepository;
import com.jack.manager.dao.repository.CompanyRepository;
import com.jack.manager.dao.repository.LoginUserRepository;
import com.jack.manager.pojo.app.LoginUser;
import com.jack.manager.pojo.sys.Company;
import com.jack.manager.pojo.sys.CompanyRenewalInfo;
import com.jack.manager.pojo.sys.User;
import com.jack.manager.pojo.utils.PageInfo;
import com.jack.manager.pojo.utils.PageRun;
import com.jack.manager.pojo.utils.PageUtils;
import com.jack.manager.service.utils.BaseService;

@Service
@Transactional(rollbackFor = Exception.class)
public class CompanyService extends BaseService{

	@Resource
	PageDataDao pageDataDao;
	
	@Resource
	CompanyRepository companyRepository;
	
	@Resource
	LoginUserRepository loginUserRepository;
	
	@Resource
	CompanyRenewalInfoRepository companyRenewalInfoRepository;
	
	public PageInfo pageData(Integer pageNumber, Integer pageSize, String name, Integer status) {
		
		return PageUtils.pageQuery(pageNumber, pageSize, new PageRun() {
			
			@Override
			public Page<?> run() {
				return pageDataDao.companyPage(Tools.isEmpty(name)?null:"%".concat(name.trim()).concat("%"),status);
			}
		});
	}

	public ResultTools save(Company company, User user) throws Exception {
		
		if(company.getId() != null)
			return ResultTools.ERROR(ResultDic.DATA_WRONG);
		
		if(Tools.isEmpty(company.getName()))
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "请输入公司名称");
		company.setName(company.getName().trim());
		
		long count = companyRepository.countByNameAndStatusNot(company.getName(),2);
		
		if(count > 0)
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "公司名称已重复");
		
		if(Tools.isEmpty(company.getManagerName()))
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "请输入管理员登陆名");
		
		long countLoginName = loginUserRepository.countByLoginNameAndStatus(company.getManagerName().trim(),0);
		
		if(countLoginName > 0)
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "输入登陆名重复");
		
		company.setCreateTime(new Date());
		company.setExpireTime(new Date());
		company.setCreateUser(user.getId());
		company.setStatus(0);
		
		companyRepository.save(company);
		
		LoginUser loginUser = new LoginUser();
		loginUser.setCompanyId(company.getId());
		loginUser.setCreateTime(new Date());
		loginUser.setLoginEncry(generateEncry(5));
		loginUser.setLoginName(company.getManagerName().trim());
		loginUser.setStatus(0);
		loginUser.setNickName(company.getName());
		loginUser.setCreateTime(new Date());
		loginUser.setIsAdmin(true);
		loginUser.setLoginPwd(Tools.MD5(Const.USER_DEFAULT_PWD, loginUser.getLoginEncry()));
		loginUserRepository.save(loginUser);
		
		return ResultTools.SUCCESS();
	}
	
	public ResultTools update(Company company) throws Exception {
		
		if(company.getId() == null)
			return ResultTools.ERROR(ResultDic.DATA_WRONG);
		
		Company cc = companyRepository.findOne(company.getId());
		if(cc == null)
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "数据不存在");
		
		if(cc.getStatus().equals(2))
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "公司已删除,无法修改");
		
		if(Tools.isEmpty(company.getName()))
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "请输入公司名称");
		cc.setName(company.getName().trim());

		if(Tools.isEmpty(company.getManagerName()))
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "请输入管理员登陆名");
		
		long countLoginName = loginUserRepository.countByLoginNameAndStatus(company.getManagerName().trim(),0);
		
		if(countLoginName > 1)
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "输入登陆名重复");
		
		Company tempC = companyRepository.findTop1ByNameAndStatusNot(cc.getName(),2);
		
		if(tempC != null && !tempC.getId().equals(company.getId()))
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "公司名称已重复");
		
		cc.setDescription(company.getDescription());
		
		cc.setManagerName(company.getManagerName().trim());
		
		companyRepository.save(cc);
		
		LoginUser loginUser = loginUserRepository.findTop1ByLoginNameAndStatus(cc.getManagerName(), 0);
		if(loginUser == null){
			loginUser = new LoginUser();
			loginUser.setCompanyId(company.getId());
			loginUser.setCreateTime(new Date());
			loginUser.setLoginEncry(generateEncry(5));
			loginUser.setLoginName(company.getManagerName().trim());
			loginUser.setStatus(0);
			loginUser.setIsAdmin(true);
			loginUser.setNickName(company.getName());
			loginUser.setCreateTime(new Date());
			loginUser.setLoginPwd(Tools.MD5(Const.USER_DEFAULT_PWD, loginUser.getLoginEncry()));
		}else{
			loginUser.setLoginName(cc.getManagerName());
		}
		
		loginUserRepository.save(loginUser);
		
		return ResultTools.SUCCESS();
	}
	
	public ResultTools delete(Integer id) {
		
		if(id == null)
			return ResultTools.ERROR(ResultDic.DATA_WRONG);
		
		Company cc = companyRepository.findOne(id);
		if(cc == null)
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "数据不存在");
		
		if(cc.getStatus() == 2)
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "数据已删除,无法重复删除");
		
		cc.setStatus(2);
		
		companyRepository.save(cc);
		
		return ResultTools.SUCCESS();
	}
	
	public ResultTools renewal(Integer id,String day,User user) {
		
		if(id == null)
			return ResultTools.ERROR(ResultDic.DATA_WRONG);
		
		if(!Tools.isInteger(day))
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "请输入续期时间(整数)");
		
		int d = Integer.parseInt(day);
		
		if(d < 1)
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "续期时间不能少于1天");
		
		Company company = companyRepository.findOne(id);
		if(company == null)
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "数据不存在");
		
		if(company.getStatus() == 2)
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "数据已删除,无法续期");
		
		if(company.getStatus() == 1)
			company.setStatus(0);
		
		Date date = new Date(company.getExpireTime().getTime() + (1000 * 60 * 60 * 24 * d));
		
		company.setExpireTime(date);
		
		CompanyRenewalInfo companyRenewalInfo = new CompanyRenewalInfo();
		companyRenewalInfo.setCompanyId(id);
		companyRenewalInfo.setCreateTime(new Date());
		companyRenewalInfo.setCreateUser(user.getId());
		companyRenewalInfo.setDay(d);
		
		companyRepository.save(company);
		companyRenewalInfoRepository.save(companyRenewalInfo);
		
		return ResultTools.SUCCESS();
	}

	public ResultTools findById(Integer id) {
		
		if(id == null)
			return ResultTools.ERROR(ResultDic.DATA_WRONG);
		
		Company company = companyRepository.findOne(id);
		
		if(company == null)
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "数据不存在");
		
		return ResultTools.SUCCESS(company);
	}

}
