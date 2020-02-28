/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.servers.server.model;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class UserinfoModel implements Model{
	
	//alias
	public static final String TABLE_ALIAS = "Userinfo";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_DWBH = "单位编号";
	public static final String ALIAS_WXH = "微信号";
	public static final String ALIAS_USERID = "身份证号";
	public static final String ALIAS_USERNAME = "用户名";
	public static final String ALIAS_PASSWORD = "密码";
	public static final String ALIAS_XB = "性别(XB)";
	public static final String ALIAS_JH = "警号";
	public static final String ALIAS_PHONE = "电话";
	public static final String ALIAS_UNIT = "工作单位";
	public static final String ALIAS_ADDRESS = "单位地址";
	public static final String ALIAS_TYPE = "单位类型(SMSYDWLX)";
	public static final String ALIAS_STATE = "删除状态";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATOR = "更新人";
	public static final String ALIAS_UPDATETIME = "更新时间";
	public static final String ALIAS_SHBZ = "审核标志";
	
	//columns START
	
	private String id;


	private String dwbh;

	private String wxh;
	
	private String userid;
	
	private String username;
	
	private String password;
	
	private String xb;
	
	private String jh;
	
	private String phone;
	
	private String unit;
	
	private String address;
	
	private String type;
	
	private String state;
	
	private String shbz;
	
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
	
	private String dwbhString;
	private String typeString;
	private String xbString;
	
	//columns END

	 

	public UserinfoModel(){
	}
	public UserinfoModel(String id) {
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
	public String getWxh() {
		return this.wxh;
	}
	
	public void setWxh(String value) {
		this.wxh = value;
	}
	
	public String getUserid() {
		return userid;
	}
	
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String value) {
		this.username = value;
	}
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String value) {
		this.password = value;
	}
	public String getXb() {
		return this.xb;
	}
	
	public void setXb(String value) {
		this.xb = value;
	}
	public String getJh() {
		return this.jh;
	}
	
	public void setJh(String value) {
		this.jh = value;
	}
	public String getPhone() {
		return this.phone;
	}
	
	public void setPhone(String value) {
		this.phone = value;
	}
	public String getUnit() {
		return this.unit;
	}
	
	public void setUnit(String value) {
		this.unit = value;
	}
	public String getAddress() {
		return this.address;
	}
	
	public void setAddress(String value) {
		this.address = value;
	}
	public String getType() {
		return this.type;
	}
	
	public void setType(String value) {
		this.type = value;
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
	
	public String getShbz() {
		return shbz;
	}
	public void setShbz(String shbz) {
		this.shbz = shbz;
	}

	public String getDwbhString() {
		return dwbhString;
	}

	public void setDwbhString(String dwbhString) {
		this.dwbhString = dwbhString;
	}
	public String getTypeString() {
		return typeString;
	}
	public void setTypeString(String typeString) {
		this.typeString = typeString;
	}
	public String getXbString() {
		return xbString;
	}
	public void setXbString(String xbString) {
		this.xbString = xbString;
	}
	
	
}

