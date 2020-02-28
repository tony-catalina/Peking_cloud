/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.servers.server.model;

import awd.framework.common.model.Model;
import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;


/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class YyModel implements Model{
	
	//alias
	public static final String TABLE_ALIAS = "Yy";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_YYID = "预约ID";
	public static final String ALIAS_LSZH = "律师证号";
	public static final String ALIAS_RYBH = "人员编号";
	public static final String ALIAS_JSBH = "监所编号";
	public static final String ALIAS_RYXM = "人员姓名";
	public static final String ALIAS_KSSJ = "预约开始时间";
	public static final String ALIAS_JSSJ = "预约结束时间";
	public static final String ALIAS_HJS = "会见室";
	public static final String ALIAS_REVOKES = "是否撤销  0代表未撤销，1代表撤销";
	public static final String ALIAS_REASOM = "撤销原因";
	public static final String ALIAS_HJLX = "会见类型  1律师提出的预约会见 2在押人员要求的律师会见";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_STATE = "r3删除 R2显示";
	public static final String ALIAS_OPERATOR = "操作人";
	public static final String ALIAS_SCBZ = "上传标志";
	public static final String ALIAS_BZ = "备注";
	public static final String ALIAS_YYRQ = "预约日期";
	public static final String ALIAS_FLAG = "状态   0审核中 1通过 2 未通过";
	public static final String ALIAS_LSXM = "律师姓名";
	public static final String ALIAS_LSDW = "律师单位";
	public static final String ALIAS_DWDH = "单位电话";
	public static final String ALIAS_SJH = "律师手机号";
	public static final String ALIAS_ISLSHJ = "是否是律师会见还是提审会见";
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String yyid;
	
	private java.lang.String lszh;
	
	private java.lang.String rybh;
	
	private java.lang.String jsbh;
	
	private java.lang.String ryxm;
	//预约开始时间
	private java.util.Date kssj;
	//预约结束时间
	private java.util.Date jssj;
	
	private java.lang.String hjs;
	
	private java.lang.String revokes;
	
	private java.lang.String reasom;
	
	private java.lang.String hjlx;
	//创建时间
	private java.util.Date createtime;
	
	private java.lang.String state;
	
	private java.lang.String operator;
	
	private java.lang.String scbz;
	
	private java.lang.String bz;
	//预约日期
	private java.util.Date yyrq;
	
	private java.lang.String flag;
	
	private java.lang.String lsxm;
	
	private java.lang.String lsdw;
	
	private java.lang.String dwdh;
	
	private java.lang.String sjh;
	
	private java.lang.String islshj;
	//columns END


	public YyModel(){
	}
	public YyModel(String id) {
		this.id = id;
	}
	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	
	public java.lang.String getYyid() {
		return this.yyid;
	}
	
	public void setYyid(java.lang.String value) {
		this.yyid = value;
	}
	public java.lang.String getLszh() {
		return this.lszh;
	}
	
	public void setLszh(java.lang.String value) {
		this.lszh = value;
	}
	public java.lang.String getRybh() {
		return this.rybh;
	}
	
	public void setRybh(java.lang.String value) {
		this.rybh = value;
	}
	public java.lang.String getJsbh() {
		return this.jsbh;
	}
	
	public void setJsbh(java.lang.String value) {
		this.jsbh = value;
	}
	public java.lang.String getRyxm() {
		return this.ryxm;
	}
	
	public void setRyxm(java.lang.String value) {
		this.ryxm = value;
	}
	
	public java.util.Date getKssj() {
		return this.kssj;
	}
	
	public void setKssj(java.util.Date value) {
		this.kssj = value;
	}
	
	
	public java.util.Date getJssj() {
		return this.jssj;
	}
	
	public void setJssj(java.util.Date value) {
		this.jssj = value;
	}
	
	public java.lang.String getHjs() {
		return this.hjs;
	}
	
	public void setHjs(java.lang.String value) {
		this.hjs = value;
	}
	public java.lang.String getRevokes() {
		return this.revokes;
	}
	
	public void setRevokes(java.lang.String value) {
		this.revokes = value;
	}
	public java.lang.String getReasom() {
		return this.reasom;
	}
	
	public void setReasom(java.lang.String value) {
		this.reasom = value;
	}
	public java.lang.String getHjlx() {
		return this.hjlx;
	}
	
	public void setHjlx(java.lang.String value) {
		this.hjlx = value;
	}
	
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	
	public java.lang.String getState() {
		return this.state;
	}
	
	public void setState(java.lang.String value) {
		this.state = value;
	}
	public java.lang.String getOperator() {
		return this.operator;
	}
	
	public void setOperator(java.lang.String value) {
		this.operator = value;
	}
	public java.lang.String getScbz() {
		return this.scbz;
	}
	
	public void setScbz(java.lang.String value) {
		this.scbz = value;
	}
	public java.lang.String getBz() {
		return this.bz;
	}
	
	public void setBz(java.lang.String value) {
		this.bz = value;
	}
	
	public java.util.Date getYyrq() {
		return this.yyrq;
	}
	
	public void setYyrq(java.util.Date value) {
		this.yyrq = value;
	}
	
	public java.lang.String getFlag() {
		return this.flag;
	}
	
	public void setFlag(java.lang.String value) {
		this.flag = value;
	}
	public java.lang.String getLsxm() {
		return this.lsxm;
	}
	
	public void setLsxm(java.lang.String value) {
		this.lsxm = value;
	}
	public java.lang.String getLsdw() {
		return this.lsdw;
	}
	
	public void setLsdw(java.lang.String value) {
		this.lsdw = value;
	}
	public java.lang.String getDwdh() {
		return this.dwdh;
	}
	
	public void setDwdh(java.lang.String value) {
		this.dwdh = value;
	}
	public java.lang.String getSjh() {
		return this.sjh;
	}
	
	public void setSjh(java.lang.String value) {
		this.sjh = value;
	}
	public java.lang.String getIslshj() {
		return this.islshj;
	}
	
	public void setIslshj(java.lang.String value) {
		this.islshj = value;
	}
	
}

