package com.jack.manager.service.utils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.jack.manager.common.utils.Tools;
import com.jack.manager.common.utils.WxConst;

/**
 * 微信统一下单数据封装
 * @createTime 2018年3月7日
 * @author Jack
 *
 */
public class WeChatPlaceAnOrderDto {

	/**公众账号ID*/
	private String appid;

	/**商户号*/
	private String mch_id;

	/**设备号*/
	private String device_info;

	/**随机字符串*/
	private String nonce_str;

	/**签名*/
	private String sign;

	/**签名类型*/
	private String sign_type;

	/**商品描述*/
	private String body;

	/**商品详情*/
	private Object detail;

	/**附加数据*/
	private String attach;

	/**商户订单号*/
	private String out_trade_no;

	/**标价币种*/
	private String fee_type = "CNY";

	/**标价金额*/
	private Integer total_fee;

	/**终端IP*/
	private String spbill_create_ip;

	/**交易起始时间,订单生成时间，格式为yyyyMMddHHmmss*/
	private String time_start;

	/**交易结束时间,格式为yyyyMMddHHmmss*/
	private String time_expire;

	/**订单优惠标记*/
	private String goods_tag;

	/**通知地址*/
	private static final String notify_url = WxConst.Config.NOTIFY_URL;

	/**交易类型<br>
	 * JSAPI 公众号支付
	 *	NATIVE 扫码支付
	 *	APP APP支付
	 * */
	private String trade_type;
	
	/**指定支付方式*/
	private String limit_pay;
	
	/**用户标识*/
	private String openid;
	
	/**场景信息*/
	private Object scene_info;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getDevice_info() {
		return device_info;
	}

	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getSign() {
		return sign;
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Object getDetail() {
		return detail;
	}

	public void setDetail(Object detail) {
		this.detail = detail;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	public Integer getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(Integer total_fee) {
		this.total_fee = total_fee;
	}

	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}

	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}

	public String getTime_start() {
		return time_start;
	}

	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}

	public String getTime_expire() {
		return time_expire;
	}

	public void setTime_expire(String time_expire) {
		this.time_expire = time_expire;
	}

	public String getGoods_tag() {
		return goods_tag;
	}

	public void setGoods_tag(String goods_tag) {
		this.goods_tag = goods_tag;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getLimit_pay() {
		return limit_pay;
	}

	public void setLimit_pay(String limit_pay) {
		this.limit_pay = limit_pay;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public Object getScene_info() {
		return scene_info;
	}

	public void setScene_info(Object scene_info) {
		this.scene_info = scene_info;
	}

	public static String getNotifyUrl() {
		return notify_url;
	}
	
	/**
	 * 生成提交数据
	 * @Title 
	 * @Desc 
	 * @return
	 */
	public String generateRequest(){
		generateSign();
		Field[] fields = this.getClass().getDeclaredFields();
		StringBuffer result = new StringBuffer("<xml>");
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				Object object = field.get(this);
				if(object != null){
					if(object instanceof String){
						result.append("<").append(field.getName()).append(">")
						.append(object.toString())
						.append("</").append(field.getName()).append(">");
					}else{
						result.append("<").append(field.getName()).append(">")
						.append("<![CDATA[")
							.append(Tools.entityToJson(object))
						.append("]]>")
						.append("</").append(field.getName()).append(">");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		result.append("</xml>");
		return result.toString();
	}
	
	private void generateSign(){
		Field[] fields = this.getClass().getDeclaredFields();
		Map<String, Object> map = new HashMap<>();
		try {
			for (Field field : fields) {
				field.setAccessible(true);
				if(Tools.notNull(field.get(this))){
					map.put(field.getName(), field.get(this));
				}
			}
			Object[] names =  map.keySet().toArray();
			Arrays.sort(names);
			StringBuffer tempSign = new StringBuffer();
			for (Object name : names) {
				Object value = map.get(name.toString());
				tempSign.append(name).append("=");
				if(value instanceof String){
					tempSign.append(value.toString());
				}else{
					tempSign.append(Tools.entityToJson(value));
				}
				tempSign.append("&");
			}
			tempSign.append("key=").append(WxConst.Config.keyPass);
			this.sign = Tools.MD5(tempSign.toString(),"");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
