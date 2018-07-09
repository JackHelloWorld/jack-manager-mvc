package com.jack.manager.pojo.sys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 序列号记录
 * @author jack
 *
 */
@Entity
@Table(name="p_serial_number")
public class SerialNumber implements Serializable{

	private static final long serialVersionUID = 2988989312275381148L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	public SerialNumber(String columnName) {
		this.columnName = columnName;
	}

	/**创建时间*/
	@Column(name="f_column_name")
	private String columnName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

}
