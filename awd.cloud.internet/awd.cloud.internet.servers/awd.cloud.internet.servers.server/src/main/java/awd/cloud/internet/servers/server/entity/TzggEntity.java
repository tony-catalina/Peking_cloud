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


public class TzggEntity extends SimpleGenericEntity<String> {
	
	//alias
	public static final String TABLE_ALIAS = "Tzgg";
	public static final String ALIAS_ID = "Id";
	public static final String ALIAS_DWBH = "单位编号";
	public static final String ALIAS_GGNR = "公告内容";
	public static final String ALIAS_GZRQ = "通知公告日期";
	public static final String ALIAS_STATE = "数据状态";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	
	//保存组（不需要id验证）
	public static interface SaveGroup {};  
	//新增组（需要id验证）
	public static interface UpdateGroup {};
	//所有组
	@GroupSequence({SaveGroup.class, UpdateGroup.class})  
	public interface All {       
	} 

	//columns START

	@NotNull(message="Id不能为空",groups=SaveGroup.class)
    @Length(max=21,message="Id最大长度21位" ,groups=SaveGroup.class)
	private java.lang.String id;

	@NotNull(message="单位编号不能为空",groups=SaveGroup.class)
    @Length(max=20,message="单位编号最大长度20位" ,groups=SaveGroup.class)
	private java.lang.String dwbh;

	@NotNull(message="公告内容不能为空",groups=SaveGroup.class)
    @Length(max=1000,message="公告内容最大长度1000位" ,groups=SaveGroup.class)
	private java.lang.String ggnr;

	
	@NotNull(message="通知公告日期不能为空")
	private java.util.Date gzrq;

	
	@Length(max=2,message="数据状态最大长度2位" ,groups=SaveGroup.class)
	private java.lang.String state;

    @Length(max=50,message="创建人最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String creator;

    	
	
	@NotNull(message="创建时间不能为空")
	private java.util.Date createtime;

	//columns END


	public TzggEntity(){
	}

	public TzggEntity(
		java.lang.String id
	){
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
	@ApiModelProperty(hidden = true)
	public String getGzrqString() {
		return DateTimeUtils.format(getGzrq(), DateTimeUtils.YEAR_MONTH_DAY);
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
	
}

