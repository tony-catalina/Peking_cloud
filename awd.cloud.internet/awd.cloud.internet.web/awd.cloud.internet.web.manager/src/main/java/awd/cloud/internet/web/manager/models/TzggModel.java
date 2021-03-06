/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.web.manager.models;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import awd.cloud.internet.web.manager.utils.KssDictionay;
import awd.framework.common.model.Model;
import awd.framework.common.utils.StringUtils;


/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class TzggModel implements Model{
	
	//alias
	public static final String TABLE_ALIAS = "Tzgg";
	public static final String ALIAS_ID = "Id";
	public static final String ALIAS_DWBH = "单位编号";
	public static final String ALIAS_GGNR = "公告内容";
	public static final String ALIAS_GZRQ = "通知公告日期";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String dwbh;
	
	private java.lang.String ggnr;
	
	@JSONField(format = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private java.util.Date gzrq;
	
	private java.lang.String state;
	
	private java.lang.String creator;
	//创建时间
	private java.util.Date createtime;
	//columns END
	
	private String dwmc;


	
	public String getDwmc() {
    	try {
    		if(!StringUtils.isNullOrEmpty(this.getDwbh())) {
    			KssDictionay kd = KssDictionay.getDictionary();
    			return kd.getMap().get(this.getDwbh()).toString();
    		}
    	}catch (Exception e) {
    		e.printStackTrace();
    		return this.getDwbh();
		}
		return dwmc;
		
	}
	public void setDwmc(String dwmc) {
		this.dwmc = dwmc;
	}
	public TzggModel(){
	}
	public TzggModel(String id) {
		this.id = id;
	}
	

	public java.lang.String getState() {
		return state;
	}
	public void setState(java.lang.String state) {
		this.state = state;
	}
	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	
	public java.lang.String getDwbh() {
		return this.dwbh;
	}
	
	public void setDwbh(java.lang.String value) {
		this.dwbh = value;
	}
	public java.lang.String getGgnr() {
		return this.ggnr;
	}
	
	public void setGgnr(java.lang.String value) {
		this.ggnr = value;
	}
	
	public java.util.Date getGzrq() {
		return this.gzrq;
	}
	
	public void setGzrq(java.util.Date value) {
		this.gzrq = value;
	}
	
	public java.lang.String getCreator() {
		return this.creator;
	}
	
	public void setCreator(java.lang.String value) {
		this.creator = value;
	}
	
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	
	
}

