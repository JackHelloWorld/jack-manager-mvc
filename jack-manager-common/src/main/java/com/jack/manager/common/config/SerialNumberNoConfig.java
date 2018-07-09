package com.jack.manager.common.config;

/**
 * 序列号配置
 * @author Jack
 *
 */
public enum SerialNumberNoConfig {

	/**支出序列号配置*/
	ZC_CONFIG("ZC","ZC_CONFIG_C"),
	/**收入序列号配置*/
	SR_CONFIG("SR","SR_CONFIG_C"),
	/**银行序列号配置*/
	YH_CONFIG("YH","YH_CONFIG_C");
	
	private String prefix;
	
	private String columnName;
	
	SerialNumberNoConfig(String prefix,String columnName){
		this.columnName = columnName;
		this.prefix = prefix;
	}

	public String getPrefix() {
		return prefix;
	}

	public String getColumnName() {
		return columnName;
	}
	
	
	
}
