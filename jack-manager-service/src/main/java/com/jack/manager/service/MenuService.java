package com.jack.manager.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.jack.manager.common.config.ResultCode;
import com.jack.manager.common.config.ResultDic;
import com.jack.manager.common.config.ResultTools;
import com.jack.manager.common.utils.Tools;
import com.jack.manager.dao.PageDataDao;
import com.jack.manager.dao.repository.ActionRepository;
import com.jack.manager.dao.repository.MenuRepository;
import com.jack.manager.pojo.sys.Action;
import com.jack.manager.pojo.sys.Menu;
import com.jack.manager.pojo.utils.PageInfo;
import com.jack.manager.pojo.utils.PageRun;
import com.jack.manager.pojo.utils.PageUtils;

@Service
@Transactional(rollbackFor = Exception.class)
public class MenuService {

	@Resource
	PageDataDao pageDataDao;
	
	@Resource
	MenuRepository menuRepository;
	
	@Resource
	ActionRepository actionRepository;
	
	public PageInfo findMenu(Integer pageNumber, Integer pageSize, String menuText) {
		
		return PageUtils.pageQuery(pageNumber, pageSize, new PageRun() {
			
			@Override
			public Page<?> run() {
				return pageDataDao.menuPage(Tools.isEmpty(menuText)?null:"%"+menuText.trim()+"%");
			}
		});
	}

	public ResultTools delete(Integer id) {
		
		Menu menu = menuRepository.getOne(id);
		
		if(menu == null)
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "数据不存在");
		
		menuRepository.delete(menu);
		
		return ResultTools.SUCCESS();
	}

	public ResultTools findById(Integer id) {

		Menu menu = menuRepository.findOne(id);
		
		if(menu == null)
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "数据不存在");
		
		return ResultTools.SUCCESS(menu);
	}

	public ResultTools update(Menu menu) {
		
		if(menu.getId() == null)
			return ResultTools.ERROR(ResultDic.DATA_WRONG);
		
		Menu men = menuRepository.findOne(menu.getId());
		
		if(Tools.isEmpty(menu.getText())){
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "菜单名称不能为空");
		}
		
		if(men == null)
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "信息不存在");
		
		if(menu.getParentid() == null || menu.getParentid() == 0)
			menu.setParentid(0);
		else{
			if(!menuRepository.exists(menu.getParentid())){
				return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "父级菜单选择错误");
			}
		}
		
		if(menu.getParentid().equals(menu.getId()))
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "父级菜单选择错误");
		
		menuRepository.save(menu);
		
		return ResultTools.SUCCESS();
	}
	
	public ResultTools save(Menu menu) {
		
		if(menu.getId() != null)
			return ResultTools.ERROR(ResultDic.DATA_WRONG);

		if(Tools.isEmpty(menu.getText())){
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "菜单名称不能为空");
		}
		
		menu.setText(menu.getText().trim());
		
		menu.setStatus(0);
		
		long countByText = menuRepository.countByText(menu.getText());
		
		if(countByText > 0)
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "菜单名称重复");

		if(menu.getParentid() == null || menu.getParentid() == 0)
			menu.setParentid(0);
		else{
			if(!menuRepository.exists(menu.getParentid()))
				return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "父级菜单选择错误");
		}
		
		menuRepository.save(menu);
		
		return ResultTools.SUCCESS();
	}

	public Map<String, Object> initPage() {
		Map<String, Object> map = new HashMap<>();
		map.put("select_menus", menuRepository.findAll());
		return map;
	}

	public PageInfo actionData(Integer pageNumber, Integer pageSize, Integer menuId) {
		return PageUtils.pageQuery(pageNumber, pageSize, new PageRun() {
			
			@Override
			public Page<?> run() {
				return pageDataDao.actionPageByMenuId(menuId);
			}
		});
	}

	public ResultTools actionAdd(Action action) {
		
		if(action.getMenuId() == null || action.getId() != null) return ResultTools.ERROR(ResultDic.DATA_WRONG);
		
		if(!menuRepository.exists(action.getMenuId()))
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "菜单不存在");
		
		if(Tools.isEmpty(action.getName()))
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "资源名称不能为空");
		action.setName(action.getName().trim());
		
		if(Tools.isEmpty(action.getUrl()))
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "资源路径不能为空");
		action.setUrl(action.getUrl().trim());
		
		long count = actionRepository.countByUrl(action.getUrl().trim());
		
		if(count > 0)
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "资源路径已存在");
		
		actionRepository.save(action);
		
		
		return ResultTools.SUCCESS();
	}
	
	public ResultTools actionUpdate(Action action) {
		
		if(action.getMenuId() == null || action.getId() == null) return ResultTools.ERROR(ResultDic.DATA_WRONG);
		
		if(!menuRepository.exists(action.getMenuId()))
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "菜单不存在");
		
		if(Tools.isEmpty(action.getName()))
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "资源名称不能为空");
		action.setName(action.getName().trim());
		
		if(Tools.isEmpty(action.getUrl()))
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "资源路径不能为空");
		action.setUrl(action.getUrl().trim());
		
		Action an = actionRepository.getTop1ByUrl(action.getUrl().trim());
		
		if(an != null && !an.getId().equals(action.getId()))
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "资源路径已存在");
		
		actionRepository.save(action);
		
		
		return ResultTools.SUCCESS();
	}
	
	public ResultTools actionDelete(Integer id) {

		if(id == null)
			return ResultTools.ERROR(ResultDic.DATA_WRONG);
		
		if(!actionRepository.exists(id))
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "资源不存在");

		actionRepository.delete(id);
		
		return ResultTools.SUCCESS();
	}

	public ResultTools findActionById(Integer id) {
		
		if(id == null)
			return ResultTools.ERROR(ResultDic.DATA_WRONG);
		
		if(!actionRepository.exists(id))
			return ResultTools.DIY_ERROR(ResultCode.DataErrorCode, "资源不存在");
		
		Action action = actionRepository.findOne(id);
		
		return ResultTools.SUCCESS(action);
	}
	
}
