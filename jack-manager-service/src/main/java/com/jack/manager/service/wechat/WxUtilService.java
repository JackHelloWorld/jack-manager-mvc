package com.jack.manager.service.wechat;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.dom4j.CDATA;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jack.manager.common.utils.DatePattern;
import com.jack.manager.common.utils.HttpClientUtils;
import com.jack.manager.common.utils.Tools;
import com.jack.manager.common.utils.WxConst;
import com.jack.manager.dao.wechat.WeChatPayInfoRepository;
import com.jack.manager.pojo.wechat.WeChatPayInfo;
import com.jack.manager.service.utils.BaseService;
import com.jack.manager.service.utils.WeChatPlaceAnOrderDto;

/**
 * 微信工具服务
 * @createTime 2018年3月6日
 * @author Jack
 *
 */
@Service
public class WxUtilService extends BaseService{

	private static final Logger log = Logger.getLogger(WxUtilService.class);

	@Resource
	WeChatPayInfoRepository weChatPayInfoRepository;

	/**
	 * 统一下单封装
	 * @Title 
	 * @Desc 
	 * @param infoId 关联数据id
	 * @param money 操作金额
	 * @param dataType 数据类型,1:缴费,2:充值
	 * @param desc 数据摘要描述
	 * @param ip 终端ip
	 * @return 操作结果对应的数据,为空则为下单失败
	 */
	@Transactional(rollbackFor=Exception.class)
	public WeChatPayInfo unifiedOrder(Integer infoId,BigDecimal money,int dataType,String desc,String ip,String openId){
		WeChatPlaceAnOrderDto weChatPlaceAnOrderDto = new WeChatPlaceAnOrderDto();
		weChatPlaceAnOrderDto.setAppid(WxConst.Config.appId);
		weChatPlaceAnOrderDto.setMch_id(WxConst.Config.mchId);
		weChatPlaceAnOrderDto.setDevice_info("WEB");
		weChatPlaceAnOrderDto.setBody(desc);
		//随机字符串
		weChatPlaceAnOrderDto.setNonce_str(generateEncry(32));
		weChatPlaceAnOrderDto.setOpenid(openId);
		weChatPlaceAnOrderDto.setSign_type("MD5");
		weChatPlaceAnOrderDto.setOut_trade_no(Tools.getDateToStr(new Date(), DatePattern.YYYYMMDDHHMMSSS)+generateEncry(8));
		weChatPlaceAnOrderDto.setTotal_fee(money.multiply(new BigDecimal("100")).intValue());
		weChatPlaceAnOrderDto.setSpbill_create_ip(ip);
		weChatPlaceAnOrderDto.setTrade_type("JSAPI");
		String request = weChatPlaceAnOrderDto.generateRequest();
		log.debug("request body:-----------------------"+request);
		String result = HttpClientUtils.doAction(WxConst.URL.PAY_UNIFIED_ORDER, request, "post");
		System.out.println(result);
		Map<String, Object> maptemp = new HashMap<>();
		try {
			Document document = DocumentHelper.parseText(result);
			maptemp = Tools.dom2Map(document);
		} catch (DocumentException e) {
			e.printStackTrace();
			return null;
		}
		Map<String, Object> map = new HashMap<>();
		for (String key : maptemp.keySet()) {
			if(key.indexOf('_')!=-1){
				map.put(key.replaceAll("_", "").toLowerCase(), maptemp.get(key));
			}else{
				map.put(key, maptemp.get(key));
			}
		}
		if("SUCCESS".equals(map.get("returncode").toString())){
			//成功
			final WeChatPayInfo weChatPayInfo = new WeChatPayInfo();
			Field[] fields = weChatPayInfo.getClass().getDeclaredFields();
			try {
				for (Field field : fields) {
					field.setAccessible(true);
					Object value = map.get(field.getName().toLowerCase());
					if(Tools.notNull(value)){
						if(field.getGenericType().toString().endsWith("String")){
							field.set(weChatPayInfo, value.toString());
						}else{
							field.set(weChatPayInfo, value);
						}
					}
				}
				weChatPayInfo.setDataInfoId(infoId);
				weChatPayInfo.setDataInfoType(dataType);
				weChatPayInfo.setMoney(weChatPlaceAnOrderDto.getTotal_fee());
				weChatPayInfo.setCreateTime(new Date());
				weChatPayInfo.setOpenId(openId);
				weChatPayInfo.setOutTradeNo(weChatPlaceAnOrderDto.getOut_trade_no());
				weChatPayInfoRepository.save(weChatPayInfo);
			} catch (Exception e) {
				log.error(e);
				return null;
			}
		}else{
			//失败
			log.error("统一下单失败:----------------------------"+map.get("returnmsg"));
			return null;
		}
		return null;
	}
	
	private String payActionResult(String returnCode,String returnMsg) {
		Element root = DocumentFactory.getInstance().createElement("xml");
		Element returnCodeElement = DocumentFactory.getInstance().createElement("return_code");
		Element returnMsgElement = DocumentFactory.getInstance().createElement("return_msg");
		CDATA cdataReturnMsg = DocumentFactory.getInstance().createCDATA(returnMsg);
		CDATA cdataReturnCode = DocumentFactory.getInstance().createCDATA(returnCode);
		returnCodeElement.add(cdataReturnCode);
		returnMsgElement.add(cdataReturnMsg);
		root.add(returnCodeElement);
		root.add(returnMsgElement);
		return root.asXML();
	}

	/**
	 * 支付通知处理
	 * @Title 
	 * @Desc 
	 * @param paramMap 请求参数
	 * @return
	 */
	public String payAction(Map<String, Object> paramMap) {
		String returnCode = paramMap.get("return_code").toString();
		if("SUCCESS".equals(returnCode.trim().toUpperCase())){
			String resultCode = paramMap.get("result_code").toString();
			log.debug("pay action request body:"+Tools.entityToJson(paramMap));
			final WeChatPayInfo weChatPayInfo = weChatPayInfoRepository.getTop1ByOutTradeNo(paramMap.get("out_trade_no").toString());
			if(weChatPayInfo==null){
				return payActionResult("FAIL", "支付信息不存在");
			}
			//成功处理............
			int dateType = weChatPayInfo.getDataInfoType();
			
			if(resultCode.trim().toUpperCase().equals("SUCCESS")){
				switch (dateType) {
				
				}
				return payActionResult("SUCCESS", "OK");
			}else{
				//业务回滚..................
				
				return payActionResult("FAIL", paramMap.get("return_msg").toString());
			}
		}else{
			log.error(paramMap.get("return_msg"));
			return payActionResult("FAIL", paramMap.get("return_msg").toString());
		}
	}
}
