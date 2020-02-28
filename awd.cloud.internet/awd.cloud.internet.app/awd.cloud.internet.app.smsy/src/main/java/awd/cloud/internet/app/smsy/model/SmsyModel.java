/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.app.smsy.model;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import awd.cloud.internet.app.smsy.utils.Model;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class SmsyModel implements Model{
	
	//alias
	public static final String TABLE_ALIAS = "Smsy";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_BADW = "办案单位";
	public static final String ALIAS_BADWDZ = "办案单位地址";
	public static final String ALIAS_GYJS = "关押监所";
	public static final String ALIAS_XYRXM = "嫌疑人姓名";
	public static final String ALIAS_UUID = "UUID(与file表关联)";
	public static final String ALIAS_PHASE = "阶段(PHASE)";
	public static final String ALIAS_CHYY = "撤回原因";
	public static final String ALIAS_SMSYRQ = "上门收押时间";
	public static final String ALIAS_FCUUID = "发车UUID";
	public static final String ALIAS_FCSJ = "发车时间";
	public static final String ALIAS_CPH = "车牌号";
	public static final String ALIAS_SYRQ = "收押日期";
	public static final String ALIAS_JSLY = "拒收理由";
	public static final String ALIAS_STATE = "删除状态R2正常R3删除";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATOR = "更新人";
	public static final String ALIAS_UPDATETIME = "更新时间";
	
	//columns START
	
    private String id;

    private String badw;

    private String badwdz;

    private String gyjs;

    private String xyrxm;

    private String uuid;

    private String phase;

    private String chyy;

	private java.lang.String fcuuid;

    @JSONField(format = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.util.Date syrq;

    private String jsly;

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
    
    
    private Integer filenum;
	//columns END

	private String badwString;
	private String gyjsString;
	private String phaseString; 

	public SmsyModel(){
	}
	public SmsyModel(String id) {
		this.id = id;
	}
	

	public void setId(String value) {
		this.id = value;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getBadw() {
		return this.badw;
	}
	
	public void setBadw(String value) {
		this.badw = value;
	}
	public String getBadwdz() {
		return this.badwdz;
	}
	
	public void setBadwdz(String value) {
		this.badwdz = value;
	}
	public String getGyjs() {
		return this.gyjs;
	}
	
	public void setGyjs(String value) {
		this.gyjs = value;
	}
	public String getXyrxm() {
		return this.xyrxm;
	}
	
	public void setXyrxm(String value) {
		this.xyrxm = value;
	}
	public String getUuid() {
		return this.uuid;
	}
	
	public void setUuid(String value) {
		this.uuid = value;
	}
	public String getPhase() {
		return this.phase;
	}
	
	public void setPhase(String value) {
		this.phase = value;
	}
	public String getChyy() {
		return this.chyy;
	}
	
	public void setChyy(String value) {
		this.chyy = value;
	}
	
	public java.lang.String getFcuuid() {
		return fcuuid;
	}
	
	public void setFcuuid(java.lang.String fcuuid) {
		this.fcuuid = fcuuid;
	}
	
	public java.util.Date getSyrq() {
		return this.syrq;
	}
	
	public void setSyrq(java.util.Date value) {
		this.syrq = value;
	}
	
	public String getJsly() {
		return this.jsly;
	}
	
	public void setJsly(String value) {
		this.jsly = value;
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
	public String getBadwString() {
		return badwString;
	}
	public void setBadwString(String badwString) {
		this.badwString = badwString;
	}
	public String getGyjsString() {
		return gyjsString;
	}
	public void setGyjsString(String gyjsString) {
		this.gyjsString = gyjsString;
	}
	public String getPhaseString() {
		return phaseString;
	}
	public void setPhaseString(String phaseString) {
		this.phaseString = phaseString;
	}

	public Integer getFilenum() {
		return filenum;
	}
	public void setFilenum(Integer filenum) {
		this.filenum = filenum;
	}

	
	 
}

