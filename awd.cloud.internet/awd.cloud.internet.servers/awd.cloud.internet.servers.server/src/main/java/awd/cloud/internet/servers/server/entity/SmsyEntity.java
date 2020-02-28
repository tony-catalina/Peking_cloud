/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.servers.server.entity;

import awd.cloud.internet.servers.server.config.CacheConfig;
import awd.framework.common.entity.SimpleGenericEntity;
import awd.framework.common.service.web.group.CreateGroup;
import awd.framework.common.service.web.group.UpdateGroup;
import awd.framework.common.utils.StringUtils;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

import static awd.cloud.internet.servers.server.utils.DictionaryMap.PHASE_MAP;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */


public class SmsyEntity extends SimpleGenericEntity<String> {



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
    public static final String ALIAS_FCUUID = "发车UUID";
    public static final String ALIAS_SYRQ = "收押日期";
    public static final String ALIAS_JSLY = "拒收理由";
    public static final String ALIAS_STATE = "删除状态R2正常R3删除";
    public static final String ALIAS_CREATOR = "创建人";
    public static final String ALIAS_CREATETIME = "创建时间";
    public static final String ALIAS_UPDATOR = "更新人";
    public static final String ALIAS_UPDATETIME = "更新时间";

    //所有组
    @GroupSequence({CreateGroup.class, UpdateGroup.class})
    public interface All {
    }

    //columns START

    @NotNull(message = "id不能为空", groups = CreateGroup.class)
    @Length(max = 23, message = "id最大长度23位", groups = CreateGroup.class)
    private String id;

    @NotNull(message = "办案单位不能为空", groups = CreateGroup.class)
    @Length(max = 50, message = "办案单位最大长度50位", groups = CreateGroup.class)
    private String badw;

    @NotNull(message = "办案单位地址不能为空", groups = CreateGroup.class)
    @Length(max = 255, message = "办案单位地址最大长度255位", groups = CreateGroup.class)
    private String badwdz;

    @NotNull(message = "关押监所不能为空", groups = CreateGroup.class)
    @Length(max = 21, message = "关押监所最大长度21位", groups = CreateGroup.class)
    private String gyjs;

    @NotNull(message = "嫌疑人姓名不能为空", groups = CreateGroup.class)
    @Length(max = 100, message = "嫌疑人姓名最大长度100位", groups = CreateGroup.class)
    private String xyrxm;

    @NotNull(message = "UUID(与file表关联)不能为空", groups = CreateGroup.class)
    @Length(max = 30, message = "UUID(与file表关联)最大长度30位", groups = CreateGroup.class)
    private String uuid;

    @NotNull(message = "阶段(PHASE)不能为空", groups = CreateGroup.class)
    @Length(max = 1, message = "阶段(PHASE)最大长度1位", groups = CreateGroup.class)
    private String phase;

    @Length(max = 65535, message = "撤回原因最大长度65535位", groups = CreateGroup.class)
    private String chyy;

	@Length(max=50,message="发车UUID最大长度50位" ,groups=CreateGroup.class)
	private java.lang.String fcuuid;

    @JSONField(format = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.util.Date syrq;

    @Length(max = 255, message = "拒收理由最大长度255位", groups = CreateGroup.class)
    private String jsly;

    @NotNull(message = "删除状态R2正常R3删除不能为空", groups = CreateGroup.class)
    @Length(max = 2, message = "删除状态R2正常R3删除最大长度2位", groups = CreateGroup.class)
    private String state;

    @NotNull(message = "创建人不能为空", groups = CreateGroup.class)
    @Length(max = 50, message = "创建人最大长度50位", groups = CreateGroup.class)
    private String creator;


    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date createtime;

    @Length(max = 50, message = "更新人最大长度50位", groups = CreateGroup.class)
    private String updator;


    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date updatetime;
    
    
    private Integer filenum;

    //columns END


    public SmsyEntity() {
    }

    public SmsyEntity(
            String id
    ) {
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

    public String getPhaseString() {
        return PHASE_MAP.get(this.phase) == null ? "" : PHASE_MAP.get(this.phase);
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
        UnitEntity entity = null;
        if(!StringUtils.isNullOrEmpty(badw)) {
        	try {
                CacheConfig config = CacheConfig.getInstance();
                Object object = CacheConfig.getInstance().getCacheByKey(CacheConfig.CAHCE_UNIT,badw);
                entity = (UnitEntity) object;
                if (!StringUtils.isNullOrEmpty(entity)) {
                    return entity.getDwmc();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }        
        return badw;
    }
    
    public String getGyjsString() {
        UnitEntity entity = null;
        if(!StringUtils.isNullOrEmpty(gyjs)) {
        	try {
                CacheConfig config = CacheConfig.getInstance();
                Object object = CacheConfig.getInstance().getCacheByKey(CacheConfig.CAHCE_UNIT,gyjs);
                entity = (UnitEntity) object;
                if (!StringUtils.isNullOrEmpty(entity)) {
                    return entity.getDwmc();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }        
        return gyjs;
    }

	public Integer getFilenum() {
		return filenum;
	}

	public void setFilenum(Integer filenum) {
		this.filenum = filenum;
	}
    
    

}

