/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.servers.server.entity;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import awd.framework.common.entity.SimpleGenericEntity;
import awd.framework.common.utils.DateTimeUtils;



/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class FjsysEntity extends SimpleGenericEntity<String> {
	
	//alias
	public static final String TABLE_ALIAS = "Fjsys";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_JSBH = "单位编号";
	public static final String ALIAS_SYSL = "剩余房间数量";
	
	//保存组（不需要id验证）
	public static interface SaveGroup {};  
	//新增组（需要id验证）
	public static interface UpdateGroup {};
	//所有组
	@GroupSequence({SaveGroup.class, UpdateGroup.class})  
	public interface All {       
	} 

	//columns START

	@NotNull(message="id不能为空",groups=SaveGroup.class)
    @Length(max=23,message="id最大长度23位" ,groups=SaveGroup.class)
	private java.lang.String id;

	@NotNull(message="单位编号不能为空",groups=SaveGroup.class)
    @Length(max=20,message="单位编号最大长度20位" ,groups=SaveGroup.class)
	private java.lang.String jsbh;

	@NotNull(message="剩余房间数量不能为空",groups=SaveGroup.class)
    @Length(max=1,message="剩余房间数量最大长度1位" ,groups=SaveGroup.class)
	private java.lang.String sysl;

	//columns END


	public FjsysEntity(){
	}

	public FjsysEntity(
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

	public java.lang.String getSysl() {
		return this.sysl;
	}
	
	public void setSysl(java.lang.String value) {
		this.sysl = value;
	}
	
}

