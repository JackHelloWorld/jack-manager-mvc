package com.jack.manager.service.wechat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jack.manager.common.exception.BusinessException;
import com.jack.manager.common.utils.HttpClientUtils;
import com.jack.manager.common.utils.WxConst;
import com.jack.manager.dao.wechat.AccessTokenRepository;
import com.jack.manager.pojo.utils.PageUtils;
import com.jack.manager.pojo.wechat.AccessToken;
import com.jack.manager.service.utils.BaseService;

@Service
@Transactional(rollbackFor = Exception.class)
public class AccessTokenService extends BaseService{

	@Resource
	AccessTokenRepository accessTokenRepository;

	public static final ThreadLocal<AccessToken> accessToken = new ThreadLocal<>();
	
	public static final Logger LOGGER = Logger.getLogger(AccessTokenService.class);

	/**
	 * 获得AccessToken信息
	 * @Title 
	 * @Desc 
	 * @return
	 * @throws BusinessException 
	 */
	public boolean gainAccessToken(){
		if(accessToken.get()==null || accessToken.get().staleDated()){
			Map<String, Object> rMap = accessTokenRepository.getTop1OrderByCreateTime();
			AccessToken accessToken1 = null;
			try {
				accessToken1 = PageUtils.mapResultToPojo(rMap, AccessToken.class);
			} catch (Exception e) {
				LOGGER.error("mapResultToPojo Error", e);
				return false;
			}
			if(accessToken1!=null && !accessToken1.staleDated()){
				accessToken.set(accessToken1);
			}else{
				//获得AccessToken信息
				Map<String,String> param = new HashMap<String,String>();  
				param.put("grant_type", "client_credential");
				param.put("appid", WxConst.Config.appId);
				param.put("secret", WxConst.Config.AppSecret);
				String result = HttpClientUtils.doGet(WxConst.URL.GET_ACCESS_TOKEN_URL,param);
				JSONObject jsonObject = new JSONObject(result);
				Map<String, Object> map = jsonObject.toMap();
				if(map.containsKey("access_token") && map.containsKey("expires_in")){
					final AccessToken token = new AccessToken();
					token.setAccessToken(map.get("access_token").toString());
					token.setExpiresIn(((Number)map.get("expires_in")).longValue());
					token.setCreateTime(new Date());
					accessTokenRepository.save(token);
					accessToken.set(token);
				}else{
					LOGGER.error("获取AccessToken失败:"+result);
					return false;
				}
			}
		}
		return true;
	}

}
