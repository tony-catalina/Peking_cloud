/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.app.smsy.model;

import awd.cloud.internet.app.smsy.utils.Model;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class FileModel implements Model {
	
	//alias
	public static final String TABLE_ALIAS = "File";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_FILE = "文件";
	public static final String ALIAS_FILEPATH = "文件地址";
	public static final String ALIAS_UUID = "UUID";
	public static final String ALIAS_FILENAME = "SUFFIX";
	
	//columns START
	
	private String id;

	
	private byte[] file;
	
	private String filepath;
	
	private String uuid;
	
	private String suffix;
	//columns END

	 

	public FileModel(){
	}
	public FileModel(String id) {
		this.id = id;
	}
	

	public void setId(String value) {
		this.id = value;
	}
	
	public String getId() {
		return this.id;
	}
	
	public byte[] getFile() {
		return this.file;
	}
	
	public void setFile(byte[] value) {
		this.file = value;
	}
	public String getFilepath() {
		return this.filepath;
	}
	
	public void setFilepath(String value) {
		this.filepath = value;
	}
	public String getUuid() {
		return this.uuid;
	}
	
	public void setUuid(String value) {
		this.uuid = value;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	 
}

