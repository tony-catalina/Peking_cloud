/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */
package awd.cloud.internet.servers.server.entity;

import static awd.cloud.internet.servers.server.utils.DictionaryMap.FCZT_MAP;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import awd.cloud.internet.servers.server.config.CacheConfig;
import awd.framework.common.entity.SimpleGenericEntity;
import awd.framework.common.service.web.group.CreateGroup;
import awd.framework.common.service.web.group.UpdateGroup;
import awd.framework.common.utils.StringUtils;


/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


@JsonIgnoreProperties(ignoreUnknown = true)
public class FcjlEntity extends SimpleGenericEntity<String>  {

	//alias
	public static final String TABLE_ALIAS = "Fcjl";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_JSBH = "监所编号";
	public static final String ALIAS_BADW = "办案单位";
	public static final String ALIAS_FCSJ = "发车时间";
	public static final String ALIAS_CPH = "车牌号";
	public static final String ALIAS_FCUUID = "发车UUID";
	public static final String ALIAS_FCZT = "发车状态（1 已发车，2 取消发车，3 延迟发车）";
	public static final String ALIAS_STATE = "删除状态 R2正常R3删除";

	//所有组
	@GroupSequence({CreateGroup.class, UpdateGroup.class})
	public interface All {
	}

		@Length(max=21,message="监所编号最大长度21位" ,groups=CreateGroup.class)
		private java.lang.String jsbh;
	
		@Length(max=65535,message="检查内容最大长度65535位" ,groups=CreateGroup.class)
		private java.lang.String badw;
	
		@Length(max=20,message="发车时间最大长度20位" ,groups=CreateGroup.class)
		private java.lang.String fcsj;
	
		@Length(max=30,message="车牌号最大长度30位" ,groups=CreateGroup.class)
		private java.lang.String cph;
	
		@Length(max=50,message="发车UUID最大长度50位" ,groups=CreateGroup.class)
		private java.lang.String fcuuid;
	
		@Length(max=1,message="发车状态（1 已发车，2 取消发车，3 延迟发车）最大长度1位" ,groups=CreateGroup.class)
		private java.lang.String fczt;
	
		@Length(max=2,message="删除状态 R2正常R3删除最大长度2位" ,groups=CreateGroup.class)
		private java.lang.String state;
	
	    private Integer mjsl;

	    private Integer basl;

	    private Integer yssl;
	    
	//columns END


	public FcjlEntity(){
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
	
    public String getFcztString() {
        return FCZT_MAP.get(this.fczt) == null ? "" : FCZT_MAP.get(this.fczt);
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
    
    public String getJsbhString() {
        UnitEntity entity = null;
        if(!StringUtils.isNullOrEmpty(jsbh)) {
        	try {
                CacheConfig config = CacheConfig.getInstance();
                Object object = CacheConfig.getInstance().getCacheByKey(CacheConfig.CAHCE_UNIT,jsbh);
                entity = (UnitEntity) object;
                if (!StringUtils.isNullOrEmpty(entity)) {
                    return entity.getDwmc();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }        
        return jsbh;
    }
}

