/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.servers.server.model;

import awd.framework.common.model.Model;
import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;


/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class FjsysModel implements Model{
	
	//alias
	public static final String TABLE_ALIAS = "Fjsys";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_DWBH = "单位编号";
	public static final String ALIAS_SYSL = "剩余房间数量";
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String dwbh;
	
	private java.lang.String sysl;
	//columns END


	public FjsysModel(){
	}
	public FjsysModel(String id) {
		this.id = id;
	}
	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	
	public java.lang.String getDwbh() {
		return this.dwbh;
	}
	
	public void setDwbh(java.lang.String value) {
		this.dwbh = value;
	}
	public java.lang.String getSysl() {
		return this.sysl;
	}
	
	public void setSysl(java.lang.String value) {
		this.sysl = value;
	}
	
}

