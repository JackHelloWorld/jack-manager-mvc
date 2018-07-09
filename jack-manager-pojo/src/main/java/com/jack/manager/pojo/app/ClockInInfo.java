package com.jack.manager.pojo.app;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 打卡记录
 * @author liuji
 *
 */
@Entity
@Table(name="app_clock_in_info")
public class ClockInInfo implements Serializable{

	private static final long serialVersionUID = -7895436049675369429L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="a_user_id")
	private Integer userId;
	
	/**类型{1:上班,2:下班}*/
	@Column(name="a_type")
	private Integer type;
	
	/**状态:{0:正常,1:已更新}*/
	@Column(name="a_status")
	private Integer status;
	
	/**对应更新的签到id*/
	@Column(name="a_re_id")
	private Integer reId;
	
	/**Ip地址*/
	@Column(name="a_ip")
	private String ip;
	
	/**时间*/
	@Column(name="a_create_time")
	private Date createTime;
	
	/**坐标类型*/
	@Column(name="a_coords_type")
	private String coordsType;
	
	/**区县*/
	@Column(name="a_district")
	private String district;
	
	/**国*/
	@Column(name="a_country")
	private String country;
	
	/**省*/
	@Column(name="a_province")
	private String province;
	
	/**市*/
	@Column(name="a_city")
	private String city;
	
	/**街道*/
	@Column(name="a_street")
	private String street;
	
	/**街道编号*/
	@Column(name="a_street_num")
	private String streetNum;
	
	/**详细地址*/
	@Column(name="a_addresses")
	private String addresses;
	
	/**维度*/
	@Column(name="a_latitude")
	private String latitude;
	
	/**经度*/
	@Column(name="a_longitude")
	private String longitude;
	
	/**精确度*/
	@Column(name="a_accuracy")
	private String accuracy;
	
	/**高度*/
	@Column(name="a_altitude")
	private String altitude;
	
	/**定位时间戳*/
	@Column(name="a_timestamp")
	private String timestamp;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCoordsType() {
		return coordsType;
	}

	public void setCoordsType(String coordsType) {
		this.coordsType = coordsType;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreetNum() {
		return streetNum;
	}

	public void setStreetNum(String streetNum) {
		this.streetNum = streetNum;
	}

	public String getAddresses() {
		return addresses;
	}

	public String getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(String accuracy) {
		this.accuracy = accuracy;
	}

	public String getAltitude() {
		return altitude;
	}

	public void setAltitude(String altitude) {
		this.altitude = altitude;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public void setAddresses(String addresses) {
		this.addresses = addresses;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getReId() {
		return reId;
	}

	public void setReId(Integer reId) {
		this.reId = reId;
	}

	
	
}
