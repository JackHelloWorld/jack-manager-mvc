package com.jack.manager.controller.utils;

import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.jack.manager.common.config.Const;
import com.jack.manager.common.utils.SysTools;
import com.jack.manager.common.utils.Tools;
import com.jack.manager.pojo.sys.Action;
import com.jack.manager.pojo.sys.User;
import com.jack.manager.service.SessionCacheService;
import com.jack.manager.service.utils.PermissionService;

public class ActionPermissionTag extends RequestContextAwareTag {

	private static final long serialVersionUID = 5304423000118835713L;

	private PermissionService actionPermissionService;
	
	private SessionCacheService sessionCacheService;

	@Override
	protected int doStartTagInternal() throws Exception {
		if(actionPermissionService == null)
			actionPermissionService = this.getRequestContext().getWebApplicationContext().getBean(PermissionService.class);
		if(sessionCacheService == null)
			sessionCacheService = this.getRequestContext().getWebApplicationContext().getBean(SessionCacheService.class);

		if(Tools.notEmpty(url)){
			Action result = actionPermissionService.getUserAction(url,(User)sessionCacheService.getSessionAttribute(SysTools.getSession().getId(), Const.ServletConfig.SessionUser));
			if(result != null){
				return TagSupport.EVAL_BODY_INCLUDE;//输出标签体内容 
			}
		}
		return TagSupport.SKIP_BODY;//不输出标签体内容  
	}

	public ActionPermissionTag() {
		actionPermissionService=null;
		sessionCacheService=null;
		url = "";
	}

	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}



}
