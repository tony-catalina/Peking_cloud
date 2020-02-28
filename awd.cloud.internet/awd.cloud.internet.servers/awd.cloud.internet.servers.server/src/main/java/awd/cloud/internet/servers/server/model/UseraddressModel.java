/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.servers.server.model;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import awd.framework.common.service.web.group.CreateGroup;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class UseraddressModel implements Model{
	
	//alias
	public static final String TABLE_ALIAS = "Userinfo";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_USERID = "身份证号";
	public static final String ALIAS_ADDRESS = "单位地址";
	public static final String ALIAS_USERSTATE = "用户状态";
	
	//columns START
	
	private String id;

	private String userid;

	private String address;

	private String userstate;
	//columns END

	 

	public UseraddressModel(){
	}
	public UseraddressModel(String id) {
		this.id = id;
	}
	

	public void setId(String value) {
		this.id = value;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getUserid() {
		return userid;
	}
	
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public void setAddress(String value) {
		this.address = value;
	}

	public String getUserstate() {
		return userstate;
	}

	public void setUserstate(String userstate) {
		this.userstate = userstate;
	}
}

