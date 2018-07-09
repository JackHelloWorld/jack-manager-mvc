package com.jack.manager.pojo.app;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.jack.manager.pojo.sys.Company;

/**
 * 登录用户
 * @author liuji
 *
 */
@Entity
@Table(name="app_login_user")
public class LoginUser implements Serializable{

	private static final long serialVersionUID = -2393854194042071118L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="u_nick_name")
	private String nickName;
	
	@Column(name="u_login_name")
	private String loginName;
	
	@Column(name="u_login_pwd")
	private String loginPwd;

	/**登录密码--加密基数*/
	@Column(name="u_login_encry")
	private String loginEncry;
	
	@Column(name="u_phone")
	private String phone;
	
	/**状态{0:正常,1:注销,2:删除}*/
	@Column(name="u_status")
	private Integer status;
	
	/**创建时间*/
	@Column(name="u_create_time")
	private Date createTime;
	
	/**是否为管理员账号*/
	@Column(name="u_is_admin")
	private Boolean isAdmin;
	
	/**创建人--登录用户id*/
	@Column(name="u_create_user")
	private Integer createUser;
	
	/**所属部门*/
	@Column(name="u_department")
	private Integer department;
	
	/**所属公司*/
	@Column(name="u_company_id")
	private Integer companyId;
	
	/**token校验值*/
	@Transient
	private String token;
	
	/**公司信息*/
	@Transient
	private Company company;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getLoginEncry() {
		return loginEncry;
	}

	public void setLoginEncry(String loginEncry) {
		this.loginEncry = loginEncry;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public Integer getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	public Integer getDepartment() {
		return department;
	}

	public void setDepartment(Integer department) {
		this.department = department;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	
}
