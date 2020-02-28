/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.web.manager.models;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;


import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


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
	public static final String ALIAS_FCSJ = "发车时间";
	public static final String ALIAS_CPH = "车牌号";
	public static final String ALIAS_MJSL = "民警数量";
	public static final String ALIAS_BASL = "保安数量";
	public static final String ALIAS_YSSL = "医生数量";
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
	private String phaseString;
	private String chyy;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date smsyrq;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date fcsj;
	
	private String cph;
	
	private Integer mjsl;
	
	private Integer basl;
	
	private Integer yssl;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
	//columns END

	 

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
	
	
	
	public String getPhaseString() {
		if(this.phase=="1") {
			this.phaseString = "申请中";
		}else if(this.phase=="2") {
			this.phaseString = "审批通过";
		}
		else if(this.phase=="3") {
			this.phaseString = "审批退回";
		}else if(this.phase=="4") {
			this.phaseString = "主动撤销";
		}else if(this.phase=="5") {
			this.phaseString = "确认收押";
		}
		return this.phaseString;
	}
	public void setPhaseString(String phaseString) {
		this.phaseString = phaseString;
	}
	public String getChyy() {
		return this.chyy;
	}
	
	public void setChyy(String value) {
		this.chyy = value;
	}
	
	public java.util.Date getSmsyrq() {
		return this.smsyrq;
	}
	
	public void setSmsyrq(java.util.Date value) {
		this.smsyrq = value;
	}
	
	
	public java.util.Date getFcsj() {
		return this.fcsj;
	}
	
	public void setFcsj(java.util.Date value) {
		this.fcsj = value;
	}
	
	public String getCph() {
		return this.cph;
	}
	
	public void setCph(String value) {
		this.cph = value;
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
	
	 
}

