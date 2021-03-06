/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.servers.server.model;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class ClglModel implements Model{
	
	//alias
	public static final String TABLE_ALIAS = "Clgl";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_JSBH = "jsbh";
	public static final String ALIAS_CPH = "cph";
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String cph;
	
	//columns END

	 

	public ClglModel(){
	}
	public ClglModel(String id) {
		this.id = id;
	}
	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	
	public java.lang.String getJsbh() {
		return this.jsbh;
	}
	
	public void setJsbh(java.lang.String value) {
		this.jsbh = value;
	}
	public java.lang.String getCph() {
		return this.cph;
	}
	
	public void setCph(java.lang.String value) {
		this.cph = value;
	}
	 
}

