package com.jack.manager.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.jack.manager.common.config.Const;
import com.jack.manager.common.config.ResultCode;
import com.jack.manager.common.config.ResultDic;
import com.jack.manager.common.config.ResultTools;
import com.jack.manager.common.exception.BusinessException;
import com.jack.manager.common.utils.Tools;
import com.jack.manager.dao.PageDataDao;
import com.jack.manager.dao.repository.ActionRepository;
import com.jack.manager.dao.repository.ActionUserRepository;
import com.jack.manager.dao.repository.MenuRepository;
import com.jack.manager.dao.repository.MenuUserRepository;
import com.jack.manager.dao.repository.UserRepository;
import com.jack.manager.pojo.sys.ActionUser;
import com.jack.manager.pojo.sys.Menu;
import com.jack.manager.pojo.sys.MenuUser;
import com.jack.manager.pojo.sys.User;
import com.jack.manager.pojo.utils.PageInfo;
import com.jack.manager.pojo.utils.PageRun;
import com.jack.manager.pojo.utils.PageUtils;
import com.jack.manager.service.utils.BaseService;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserService extends BaseService{

	@Resource
	PageDataDao pageDataDao;
	
	@Resource
	UserRepository userRepository;
	
	@Resource
	MenuRepository menuRepository;
	
	@Resource
	ActionRepository actionRepository;
	
	@Resource
	ActionUserRepository actionUserRepository;
	
	@Resource
	MenuUserRepository menuUserRepository;
	
	public PageInfo findUser(Integer pageNumber, Integer pageSize, String name) {
		
		return PageUtils.pageQuery(pageNumber, pageSize, new PageRun() {
			
			@Override
			public Page<?> run() {
				return pageDataDao.userPage(Tools.isEmpty(name)?null:"%"+name.trim()+"%");
			}
		});
	}

	public ResultTools save(User user) throws Exception {
		
		if(user.getId() != null)
			return ResultTools.ERROR(ResultDic.DATA_WRONG);
		
		if(Tools.isEmpty(user.getNickName()))
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "请输入名称");
		user.setNickName(user.getNickName().trim());
		
		if(Tools.isEmpty(user.getLoginName()))
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "请输入登录名");
		user.setLoginName(user.getLoginName().trim());
		
		long count = userRepository.countByLoginNameAndStatus(user.getLoginName(),0);
			
		if(count > 0)
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "登录名已存在");
		
		user.setCreatetime(new Date());
		
		user.setLoginEncry(generateEncry(4));
		
		user.setLoginPwd(Tools.MD5(Const.USER_DEFAULT_PWD, user.getLoginEncry()));
		
		user.setStatus(0);
		
		user.setIsAdmin(false);
		
		userRepository.save(user);
		
		return ResultTools.SUCCESS();
	}

	public ResultTools update(User user) {
		
		if(user.getId() == null)
			return ResultTools.ERROR(ResultDic.DATA_WRONG);
		
		User u = userRepository.getOne(user.getId());
		
		if(u == null)
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "用户信息不存在");
		
		if(Tools.isEmpty(user.getNickName()))
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "请输入名称");
		u.setNickName(user.getNickName().trim());
		
		if(Tools.isEmpty(user.getLoginName()))
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "请输入登录名");
		u.setLoginName(user.getLoginName().trim());
		
		u.setAdminDesc(user.getAdminDesc());
		
		List<User> users = userRepository.findByLoginNameAndStatus(user.getLoginName(),0);
			
		if(users.size() > 1)
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "登录名已存在");
		
		if(users.size() == 1){
			if(!users.get(0).getId().equals(u.getId())){
				return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "登录名已存在");
			}
		}
		
		userRepository.save(u);
		
		return ResultTools.SUCCESS();
	}
	
	public ResultTools delete(Integer id,User user) {
		
		if(id == null)
			return ResultTools.ERROR(ResultDic.DATA_WRONG);
		
		if(user.getId().equals(id))
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "无法删除自己");
			
		
		User u = userRepository.getTop1ByIdAndStatus(id,0);
		
		if(u == null)
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "用户信息不存在");

		u.setStatus(4);
		
		userRepository.save(u);
		
		return ResultTools.SUCCESS();
	}

	public ResultTools findById(Integer id) {
		
		if(id == null)
			return ResultTools.ERROR(ResultDic.DATA_WRONG);
		
		User u = userRepository.findOne(id);
		
		if(u == null)
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "用户信息不存在");
		
		return ResultTools.SUCCESS(u);
	}

	public Map<String, Object> findMenus() {
		
		List<Menu> menus = menuRepository.findByStatus(0);
		
		for (Menu menu : menus) {
			menu.setActions(actionRepository.findByMenuId(menu.getId()));
		}
		
		List<Menu> list = initMenu(menus, 0);
		
		Map<String, Object> result = new HashMap<>();
		
		result.put("roleMenus", list);
		
		return result;
	}

	public ResultTools getUserRole(Integer id) {
		
		if(id == null || !userRepository.exists(id))
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "授权用户不存在");
		
		List<ActionUser> actionUsers = actionUserRepository.findByUserId(id);
		List<MenuUser> menuUsers = menuUserRepository.findByUserId(id);
		
		Map<String, Object> result = new HashMap<>();
		result.put("actionUsers", actionUsers);
		result.put("menuUsers", menuUsers);

		return ResultTools.SUCCESS(result);
	}

	public ResultTools role(Integer id, String[] actions, String[] menus) throws BusinessException {

		if(id == null)
			return ResultTools.ERROR(ResultDic.DATA_WRONG);
		
		if(!userRepository.exists(id))
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "授权用户不存在");
		
		Date createTime = new Date();
		
		menuUserRepository.deleteByUserId(id);
		actionUserRepository.deleteByUserId(id);
		
		if(menus != null)
		for (String menuId : menus) {
			if(!Tools.isInteger(menuId))
				throw new BusinessException(ResultTools.ERROR(ResultDic.DATA_WRONG));
			
			if(menuRepository.countByIdAndStatus(Integer.parseInt(menuId),0) == 0)
				throw new BusinessException(ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "授权菜单不存在"));
			
			MenuUser menuUser = new MenuUser();
			menuUser.setCreateTime(createTime);
			menuUser.setMenuId(Integer.parseInt(menuId));
			menuUser.setUserId(id);
			menuUserRepository.save(menuUser);
		}
		if(actions != null)
		for (String actionId : actions) {
			if(!Tools.isInteger(actionId))
				throw new BusinessException(ResultTools.ERROR(ResultDic.DATA_WRONG));
			
			if(!actionRepository.exists(Integer.parseInt(actionId)))
				throw new BusinessException(ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "授权资源不存在"));
			
			ActionUser actionUser = new ActionUser();
			actionUser.setCreateTime(createTime);
			actionUser.setActionId(Integer.parseInt(actionId));
			actionUser.setUserId(id);
			actionUserRepository.save(actionUser);
		}
		return ResultTools.SUCCESS();
	}

}
