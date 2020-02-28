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
import io.swagger.annotations.ApiModelProperty;



/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class YyEntity extends SimpleGenericEntity<String> {
	
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
    @Length(max=21,message="id最大长度21位" ,groups=SaveGroup.class)
	private java.lang.String id;

    @Length(max=21,message="预约ID最大长度21位" ,groups=SaveGroup.class)
	private java.lang.String yyid;

    @Length(max=21,message="律师证号最大长度21位" ,groups=SaveGroup.class)
	private java.lang.String lszh;

    @Length(max=21,message="人员编号最大长度21位" ,groups=SaveGroup.class)
	private java.lang.String rybh;

    @Length(max=9,message="监所编号最大长度9位" ,groups=SaveGroup.class)
	private java.lang.String jsbh;

    @Length(max=30,message="人员姓名最大长度30位" ,groups=SaveGroup.class)
	private java.lang.String ryxm;

	
	@NotNull(message="预约开始时间不能为空")
	private java.util.Date kssj;

	
	@NotNull(message="预约结束时间不能为空")
	private java.util.Date jssj;

    @Length(max=20,message="会见室最大长度20位" ,groups=SaveGroup.class)
	private java.lang.String hjs;

    @Length(max=1,message="是否撤销  0代表未撤销，1代表撤销最大长度1位" ,groups=SaveGroup.class)
	private java.lang.String revokes;

    @Length(max=65535,message="撤销原因最大长度65535位" ,groups=SaveGroup.class)
	private java.lang.String reasom;

    @Length(max=1,message="会见类型  1律师提出的预约会见 2在押人员要求的律师会见最大长度1位" ,groups=SaveGroup.class)
	private java.lang.String hjlx;

	
	@NotNull(message="创建时间不能为空")
	private java.util.Date createtime;

    @Length(max=5,message="r3删除 R2显示最大长度5位" ,groups=SaveGroup.class)
	private java.lang.String state;

    @Length(max=30,message="操作人最大长度30位" ,groups=SaveGroup.class)
	private java.lang.String operator;

    @Length(max=1,message="上传标志最大长度1位" ,groups=SaveGroup.class)
	private java.lang.String scbz;

    @Length(max=65535,message="备注最大长度65535位" ,groups=SaveGroup.class)
	private java.lang.String bz;

	
	@NotNull(message="预约日期不能为空")
	private java.util.Date yyrq;

    @Length(max=1,message="状态   0审核中 1通过 2 未通过最大长度1位" ,groups=SaveGroup.class)
	private java.lang.String flag;

    @Length(max=30,message="律师姓名最大长度30位" ,groups=SaveGroup.class)
	private java.lang.String lsxm;

    @Length(max=60,message="律师单位最大长度60位" ,groups=SaveGroup.class)
	private java.lang.String lsdw;

    @Length(max=30,message="单位电话最大长度30位" ,groups=SaveGroup.class)
	private java.lang.String dwdh;

    @Length(max=50,message="律师手机号最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String sjh;

    @Length(max=1,message="是否是律师会见还是提审会见最大长度1位" ,groups=SaveGroup.class)
	private java.lang.String islshj;

	//columns END


	public YyEntity(){
	}

	public YyEntity(
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
	@ApiModelProperty(hidden = true)
	public String getKssjString() {
		return DateTimeUtils.format(getKssj(), DateTimeUtils.YEAR_MONTH_DAY);
	}
	public java.util.Date getKssj() {
			return this.kssj;
			}

	public void setKssj(java.util.Date value) {
			this.kssj = value;
			}
	@ApiModelProperty(hidden = true)
	public String getJssjString() {
		return DateTimeUtils.format(getJssj(), DateTimeUtils.YEAR_MONTH_DAY);
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
	@ApiModelProperty(hidden = true)
	public String getCreatetimeString() {
		return DateTimeUtils.format(getCreatetime(), DateTimeUtils.YEAR_MONTH_DAY);
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
	@ApiModelProperty(hidden = true)
	public String getYyrqString() {
		return DateTimeUtils.format(getYyrq(), DateTimeUtils.YEAR_MONTH_DAY);
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

