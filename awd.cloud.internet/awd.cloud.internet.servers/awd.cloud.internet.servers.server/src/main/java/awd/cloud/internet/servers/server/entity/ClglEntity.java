/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.servers.server.entity;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import awd.framework.common.entity.SimpleGenericEntity;
import awd.framework.common.service.web.group.CreateGroup;
import awd.framework.common.service.web.group.UpdateGroup;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class ClglEntity extends SimpleGenericEntity<String>  {

	//alias
	public static final String TABLE_ALIAS = "Clgl";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_JSBH = "jsbh";
	public static final String ALIAS_CPH = "cph";

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

    @Length(max=200,message="cph最大长度200位" ,groups=CreateGroup.class)
	private java.lang.String cph;

	//columns END


	public ClglEntity(){
	}

	public ClglEntity(
		java.lang.String id
	){
		this.id = id;
	}

	public void setId(String value) {
		this.id = value;
	}

	public String getId() {
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

