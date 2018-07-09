package com.jack.manager.pojo.wechat;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 微信统一下单信息
 * @createTime 2018年1月16日
 * @author Jack
 *
 */
@Entity
@Table(name="wx_pay_info")
public class WeChatPayInfo implements Serializable{

	private static final long serialVersionUID = -1241062496069804415L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	/**公众账号ID*/
	@Column(name="appid")
	private String appId;
	
	/**openid*/
	@Column(name="openid")
	private String openId;
	
	/**系统对应订单号*/
	@Column(name="out_trade_no")
	private String outTradeNo;

	/**商户号*/
	@Column(name="mch_id")
	private String mchId;

	/**设备号*/
	@Column(name="device_info")
	private String deviceInfo;

	/**数据关联id*/
	@Column(name="data_info_id")
	private Integer dataInfoId;
	
	/**订单创建时间*/
	@Column(name="create_time")
	private Date createTime;

	/**数据类型<br>
	 * 1:缴费,2:充值
	 * */
	@Column(name="data_info_type")
	private Integer dataInfoType;

	/**随机字符串---微信返回的随机字符串*/
	@Column(name="nonce_str")
	private String nonceStr;

	/**签名,微信返回的签名值*/
	@Column(name="a_sign")
	private String sign;

	/**业务结果(SUCCESS/FAIL)*/
	@Column(name="result_code")
	private String resultCode;

	/**错误代码*/
	@Column(name="err_code")
	private String errCode;

	/**交易类型,
	 * JSAPI 公众号支付
	 * NATIVE 扫码支付
	 * APP APP支付
	 * 
	 * */
	@Column(name="trade_type")
	private String tradeType;

	/**错误代码描述*/
	@Column(name="err_code_des")
	private String errCodeDes;
	
	/**预支付交易会话标识*/
	@Column(name="prepay_id")
	private String prepayId;
	
	/**二维码链接,trade_type为NATIVE时有返回，用于生成二维码，展示给用户进行扫码支付*/
	@Column(name="code_url")
	private String codeUrl;

	/**支付金额,单位(分)*/
	@Column(name="a_money")
	private Integer money;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrCodeDes() {
		return errCodeDes;
	}

	public void setErrCodeDes(String errCodeDes) {
		this.errCodeDes = errCodeDes;
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public Integer getDataInfoId() {
		return dataInfoId;
	}

	public void setDataInfoId(Integer dataInfoId) {
		this.dataInfoId = dataInfoId;
	}

	public Integer getDataInfoType() {
		return dataInfoType;
	}

	public void setDataInfoType(Integer dataInfoType) {
		this.dataInfoType = dataInfoType;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getPrepayId() {
		return prepayId;
	}

	public void setPrepayId(String prepayId) {
		this.prepayId = prepayId;
	}

	public String getCodeUrl() {
		return codeUrl;
	}

	public void setCodeUrl(String codeUrl) {
		this.codeUrl = codeUrl;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

}