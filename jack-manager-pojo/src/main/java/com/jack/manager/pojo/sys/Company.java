package com.jack.manager.pojo.sys;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 公司信息
 * @author liuji
 *
 */
@Entity
@Table(name="sys_company")
public class Company implements Serializable{

	private static final long serialVersionUID = -2083784608308632228L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="a_name")
	private String name;
	
	@Column(name="a_desc")
	private String description;
	
	/**状态:{0:正常,1:过期,2:已删除}*/
	@Column(name="a_status")
	private Integer status;
	
	/**管理员登陆名*/
	@Column(name="a_manager_name")
	private String managerName;
	
	@Column(name="create_time")
	private Date createTime;
	
	@Column(name="create_user")
	private Integer createUser;
	
	/**到期时间*/
	@Column(name="expire_time")
	private Date expireTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public Integer getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

}
