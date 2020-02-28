/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.servers.server.entity;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import awd.framework.common.entity.SimpleGenericEntity;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import awd.framework.common.service.web.group.CreateGroup;
import awd.framework.common.service.web.group.UpdateGroup;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class AdminEntity extends SimpleGenericEntity<String>  {

	//alias
	public static final String TABLE_ALIAS = "Admin";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_JSBH = "jsbh";
	public static final String ALIAS_JSMC = "jsmc";
	public static final String ALIAS_PASSWORD = "password";

	//所有组
	@GroupSequence({CreateGroup.class, UpdateGroup.class})
	public interface All {
	}

	//columns START

	@NotNull(message="id不能为空",groups=CreateGroup.class)
    @Length(max=23,message="id最大长度23位" ,groups=CreateGroup.class)
	private java.lang.String id;

    @Length(max=21,message="jsbh最大长度21位" ,groups=CreateGroup.class)
	private java.lang.String jsbh;

    @Length(max=200,message="jsmc最大长度200位" ,groups=CreateGroup.class)
	private java.lang.String jsmc;

    @Length(max=50,message="password最大长度50位" ,groups=CreateGroup.class)
	private java.lang.String password;

	//columns END


	public AdminEntity(){
	}

	public AdminEntity(
		java.lang.String id
	){
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

	public java.lang.String getJsmc() {
		return this.jsmc;
	}

	public void setJsmc(java.lang.String value) {
		this.jsmc = value;
	}

	public java.lang.String getPassword() {
		return this.password;
	}

	public void setPassword(java.lang.String value) {
		this.password = value;
	}
}

