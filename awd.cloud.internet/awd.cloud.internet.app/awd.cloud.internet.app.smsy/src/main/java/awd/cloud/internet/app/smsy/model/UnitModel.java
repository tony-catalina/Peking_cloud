/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.app.smsy.model;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import awd.cloud.internet.app.smsy.utils.Model;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class UnitModel implements Model {
	
	//alias
	public static final String TABLE_ALIAS = "Unit";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_DWBH = "单位编号";
	public static final String ALIAS_TYPE = "单位类型(SMSYDWLX)";
	public static final String ALIAS_DWMC = "单位名称";
	public static final String ALIAS_DWDZ = "单位地址";
	public static final String ALIAS_STATE = "删除状态";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATOR = "更新人";
	public static final String ALIAS_UPDATETIME = "更新时间";
	
	//columns START
	
	private String id;

	
	private String dwbh;
	
	private String type;
	
	private String dwmc;
	
	private String dwdz;
	
	private String state;
	
	private String creator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createtime;
	
	private String updator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updatetime;
	//columns END

	 

	public UnitModel(){
	}
	public UnitModel(String id) {
		this.id = id;
	}
	

	public void setId(String value) {
		this.id = value;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getDwbh() {
		return this.dwbh;
	}
	
	public void setDwbh(String value) {
		this.dwbh = value;
	}
	public String getType() {
		return this.type;
	}
	
	public void setType(String value) {
		this.type = value;
	}
	public String getDwmc() {
		return this.dwmc;
	}
	
	public void setDwmc(String value) {
		this.dwmc = value;
	}
	public String getDwdz() {
		return this.dwdz;
	}
	
	public void setDwdz(String value) {
		this.dwdz = value;
	}
	public String getState() {
		return this.state;
	}
	
	public void setState(String value) {
		this.state = value;
	}
	public String getCreator() {
		return this.creator;
	}
	
	public void setCreator(String value) {
		this.creator = value;
	}
	
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	
	public String getUpdator() {
		return this.updator;
	}
	
	public void setUpdator(String value) {
		this.updator = value;
	}
	
	public java.util.Date getUpdatetime() {
		return this.updatetime;
	}
	
	public void setUpdatetime(java.util.Date value) {
		this.updatetime = value;
	}
	
	 
}

