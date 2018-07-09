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
 * accessToken信息
 * @createTime 2018年1月16日
 * @author Jack
 *
 */
@Entity
@Table(name="wx_access_token")
public class AccessToken implements Serializable{
	
	private static final long serialVersionUID = -1241062496069804415L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	/**accessToken值*/
	@Column(name="a_access_token")
	private String accessToken;
	
	/**过期时间,秒*/
	@Column(name="a_expires_in")
	private Long expiresIn;
	
	/**获得时间*/
	@Column(name="create_time")
	private Date createTime;

	/**
	 * 检查当前AccessToken是否过期
	 * @Title 
	 * @Desc 
	 * @return
	 * true:已过期<br>
	 * false:正常,未过期
	 */
	public boolean staleDated(){
		if(createTime.getTime()+expiresIn*1000<=System.currentTimeMillis()){
			return true;
		}
		return false;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Long getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(Long expiresIn) {
		this.expiresIn = expiresIn;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	
}