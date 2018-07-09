package com.jack.manager.service.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.jack.manager.common.config.SerialNumberNoConfig;
import com.jack.manager.common.utils.DatePattern;
import com.jack.manager.common.utils.FtpUtils;
import com.jack.manager.common.utils.Tools;
import com.jack.manager.dao.MenuDao;
import com.jack.manager.dao.repository.MenuRepository;
import com.jack.manager.dao.repository.SerialNumberRepository;
import com.jack.manager.pojo.sys.Menu;
import com.jack.manager.pojo.sys.SerialNumber;
import com.jack.manager.pojo.sys.User;
import com.jack.manager.pojo.utils.PageUtils;

public class BaseService {

	@Resource
	FtpUtils ftpUtils;

	@Resource
	MenuRepository menuRepository;

	@Resource
	SerialNumberRepository serialNumberRepository;
	
	@Resource
	MenuDao menuDao;


	private static final Character[] DIC = new Character[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','1','2','3','4','5','6','7','8','9','0'};

	protected static final ThreadLocal<Random> random = new ThreadLocal<Random>(){
		protected Random initialValue() {
			return new Random();
		};
	};

	/**
	 * 生成加密基数
	 * @param len 基数长度
	 * @return 结果字符串
	 */
	protected String generateEncry(int len){
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < len; i++) {
			result.append(DIC[random.get().nextInt(26)]);
		}
		return result.toString();
	}

	/**
	 * 生成编号
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	protected String generateNo(SerialNumberNoConfig config) {
		StringBuffer result = new StringBuffer(config.getPrefix());
		result.append(Tools.getDateToStr(DatePattern.YYYYMMDDD));
		serialNumberRepository.save(new SerialNumber(config.getColumnName()));
		Long count = serialNumberRepository.countByColumnName(config.getColumnName());
		result.append(String.format("%06d", count));
		return result.toString();
	}


	/**
	 * 处理菜单
	 * @param menus
	 * @param parentId
	 * @return
	 */
	protected List<Menu> initMenu(List<Menu> menus,Integer parentId){
		List<Menu> nodes = new ArrayList<>();
		for (Menu menu : menus) {
			if(menu.getParentid().equals(parentId)){
				nodes.add(menu);
				List<Menu> myNodes = initMenu(menus,menu.getId());
				menu.setNodes(myNodes);
			}
		}
		return nodes;
	}

	/**
	 * 文件上传,
	 * @param b byte数组
	 * @param fileName 文件名称
	 * @return 文件路径
	 */
	public String upload(byte[] b,String fileName){
		try {
			InputStream input = new ByteArrayInputStream(b);
			ftpUtils.uploadFile(fileName, input);
			return fileName;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	protected void dosDbAction(int result) throws Exception{
		if(result != 1){
			throw new Exception("操作错误");
		}
	}

	public Map<String, Object> setAttrMenus(User user,String menuUrl) throws Exception {
		if(menuUrl.trim().startsWith("/"))
			menuUrl = menuUrl.trim().substring(1, menuUrl.trim().length());
		Map<String, Object> result = new HashMap<>();
		List<Menu> menus = new ArrayList<>();
		if(user.getIsAdmin()){
			menus = menuRepository.findAll();
		}else{
			List<Map<String, Object>> list = menuDao.selectUserMenus(user.getId()); 
			for (Map<String, Object> map : list) {
				menus.add(PageUtils.mapResultToPojo(map, Menu.class));
			}
		}
		result.put("view_menus", initMenu(menus, 0));

		List<Menu> menusT = new ArrayList<>();
		Menu menu = menuRepository.findTop1ByUrl(menuUrl);
		if(menu != null){
			result.put("page_title", menu.getText());
			dosMenuBar(menu, menusT);
			result.put("page_title_bar", menusT);
		}


		return result;
	}

	private void dosMenuBar(Menu menu,List<Menu> menus){
		if(menu.getParentid() != 0){
			Menu temp = menuRepository.findOne(menu.getParentid());
			if(menus.size() == 0)
				menus.add(temp);
			else
				menus.set(0, temp);
			dosMenuBar(temp, menus);
		}
	}

}
