package com.jack.manager.pojo.sys;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 菜单信息
 * @author liuji
 *
 */
@Entity
@Table(name="sys_menu")
public class Menu implements java.io.Serializable{

	private static final long serialVersionUID = 3086887722373613548L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="m_text")
	private String text;
	
	@Column(name="m_url")
	private String url;
	
	@Column(name="m_icon")
	private String icon;
	
	@Column(name="m_desc")
	private String desc;
	
	@Column(name="m_parentid")
	private Integer parentid;
	
	@Column(name="m_goorder")
	private Integer goorder;
	
	@Column(name="m_status")
	private Integer status;
	
	@Transient
	private List<Menu> nodes;
	
	@Transient
	private List<Action> actions;
	
	@Transient
	private Boolean isCheck;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	public Integer getGoorder() {
		return goorder;
	}

	public void setGoorder(Integer goorder) {
		this.goorder = goorder;
	}

	public List<Menu> getNodes() {
		return nodes;
	}

	public void setNodes(List<Menu> nodes) {
		this.nodes = nodes;
	}

	public Boolean getIsCheck() {
		return isCheck==null?false:isCheck;
	}

	public void setIsCheck(Boolean isCheck) {
		this.isCheck = isCheck;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<Action> getActions() {
		return actions;
	}

	public void setActions(List<Action> actions) {
		this.actions = actions;
	}
	
	

}
