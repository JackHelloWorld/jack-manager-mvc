package com.jack.manager.pojo.sys;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 操作员
 * @author Administrator
 *
 */
@Entity
@Table(name="sys_user")
public class User implements java.io.Serializable{
	
	private static final long serialVersionUID = -3377663041843504469L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	/**昵称*/
	@Column(name="nick_name")
	private String nickName;
	
	/**登录名称*/
	@Column(name="login_name")
	private String loginName;
	
	/**登录密码*/
	@Column(name="login_pwd")
	private String loginPwd;
	
	/**登录密码--加密基数*/
	@Column(name="login_encry")
	private String loginEncry;
	
	/**描述信息*/
	@Column(name="admin_desc")
	private String adminDesc;
	
	/**用户创建时间*/
	@Column(name="createtime")
	private Date createtime;
	
	/**最后一次登录时间*/
	@Column(name="last_login_time")
	private Date lastLoginTime;
	
	/**头像地址*/
	@Column(name="head_portrait")
	private String headPortrait;
	
	/**是否为老板*/
	@Column(name="is_admin")
	private Boolean isAdmin;
	
	/**账号状态,0:正常,1:申请中,2:驳回账号,3:注销,4:已经删除*/
	@Column(name="u_status",columnDefinition="INT default 0")
	private Integer status;
	
	/**角色名称*/
	@Transient
	private String roleName;
	
	/**电话*/
	@Column(name="phone")
	private String phone;

	public Integer getId() {
		return id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}


	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public String getAdminDesc() {
		return adminDesc;
	}

	public void setAdminDesc(String adminDesc) {
		this.adminDesc = adminDesc;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getHeadPortrait() {
		return headPortrait;
	}

	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getLoginEncry() {
		return loginEncry;
	}

	public void setLoginEncry(String loginEncry) {
		this.loginEncry = loginEncry;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	
}
