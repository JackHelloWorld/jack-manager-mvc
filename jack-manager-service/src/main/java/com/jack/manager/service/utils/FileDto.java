package com.jack.manager.service.utils;

import java.io.Serializable;

public class FileDto implements Serializable{

	private static final long serialVersionUID = 7420882721598219237L;
	
	private String name;
	
	private byte[] bytes;
	
	private String contentType;
	
	private String suffix;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	
}
