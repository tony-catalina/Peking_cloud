/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.app.smsy.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import awd.cloud.internet.app.smsy.utils.Model;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


@JsonIgnoreProperties(ignoreUnknown = true)
public class FcjlModel implements Model{
	
	//alias
	public static final String TABLE_ALIAS = "Fcjl";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_JSBH = "监所编号";
	public static final String ALIAS_BADW = "办案单位";
	public static final String ALIAS_FCSJ = "发车时间";
	public static final String ALIAS_CPH = "车牌号";
	public static final String ALIAS_MJSL = "民警数量";
	public static final String ALIAS_BASL = "保安数量";
	public static final String ALIAS_YSSL = "医生数量";
	public static final String ALIAS_FCUUID = "发车UUID";
	public static final String ALIAS_FCZT = "发车状态（1 已发车，2 取消发车，3 延迟发车）";
	public static final String ALIAS_STATE = "删除状态 R2正常R3删除";
	
	//columns START
	
	private java.lang.String id;

	private java.lang.String jsbh;
	
	private java.lang.String badw;
	
	private java.lang.String fcsj;
	
	private java.lang.String cph;
	
	private java.lang.String fcuuid;
	
	private java.lang.String fczt;
	
	private java.lang.String state;

    private Integer mjsl;

    private Integer basl;

    private Integer yssl;
	//columns END
	
	//字典的get方法生成结束
	private String jsbhString;
	private String fcztString; 

	public FcjlModel(){
	}
	public FcjlModel(String id) {
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
	public java.lang.String getBadw() {
		return this.badw;
	}
	
	public void setBadw(java.lang.String value) {
		this.badw = value;
	}
	public java.lang.String getFcsj() {
		return this.fcsj;
	}
	
	public void setFcsj(java.lang.String value) {
		this.fcsj = value;
	}
	public java.lang.String getCph() {
		return this.cph;
	}
	
	public void setCph(java.lang.String value) {
		this.cph = value;
	}
	public java.lang.String getFcuuid() {
		return this.fcuuid;
	}
	
	public void setFcuuid(java.lang.String value) {
		this.fcuuid = value;
	}
	public java.lang.String getFczt() {
		return this.fczt;
	}
	
	public void setFczt(java.lang.String value) {
		this.fczt = value;
	}
	public java.lang.String getState() {
		return this.state;
	}
	
	public void setState(java.lang.String value) {
		this.state = value;
	}
	public Integer getMjsl() {
		return this.mjsl;
	}
	
	public void setMjsl(Integer value) {
		this.mjsl = value;
	}
	public Integer getBasl() {
		return this.basl;
	}
	
	public void setBasl(Integer value) {
		this.basl = value;
	}
	public Integer getYssl() {
		return this.yssl;
	}
	
	public void setYssl(Integer value) {
		this.yssl = value;
	}

	//字典的get方法生成结束
	public String getJsbhString() {
		return jsbhString;
	}
	public void setJsbhString(String jsbhString) {
		this.jsbhString = jsbhString;
	}
	public String getFcztString() {
		return fcztString;
	}
	public void setFcztString(String fcztString) {
		this.fcztString = fcztString;
	}
	 
}

