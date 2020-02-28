/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.servers.server.entity;

import awd.framework.common.entity.SimpleGenericEntity;
import awd.framework.common.service.web.group.CreateGroup;
import awd.framework.common.service.web.group.UpdateGroup;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;

import static awd.cloud.internet.servers.server.utils.DictionaryMap.SMSYDWLX_MAP;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */


public class UnitEntity extends SimpleGenericEntity<String> {

    //alias
    public static final String TABLE_ALIAS = "Unit";
    public static final String ALIAS_ID = "id";
    public static final String ALIAS_DWBH = "单位编号";
    public static final String ALIAS_TYPE = "单位类型(SMSYDWLX)";
    public static final String ALIAS_DWMC = "单位名称";
    public static final String ALIAS_DWDZ = "单位地址";
    public static final String ALIAS_STATE = "删除状态";
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

    @NotNull(message = "单位编号不能为空", groups = CreateGroup.class)
    @Length(max = 20, message = "单位编号最大长度20位", groups = CreateGroup.class)
    private String dwbh;

    @NotNull(message = "单位类型(SMSYDWLX)不能为空", groups = CreateGroup.class)
    @Length(max = 1, message = "单位类型(SMSYDWLX)最大长度1位", groups = CreateGroup.class)
    private String type;

    @NotNull(message = "单位名称不能为空", groups = CreateGroup.class)
    @Length(max = 50, message = "单位名称最大长度50位", groups = CreateGroup.class)
    private String dwmc;

    @Length(max = 255, message = "单位地址最大长度255位", groups = CreateGroup.class)
    private String dwdz;

    @NotNull(message = "删除状态不能为空", groups = CreateGroup.class)
    @Length(max = 2, message = "删除状态最大长度2位", groups = CreateGroup.class)
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

    //columns END


    public UnitEntity() {
    }

    public UnitEntity(
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


    public String getDwbh() {
        return this.dwbh;
    }

    public void setDwbh(String value) {
        this.dwbh = value;
    }

    public String getType() {
        return this.type;
    }

    public String getTypeString() {
        return SMSYDWLX_MAP.get(this.type) == null ? "" : SMSYDWLX_MAP.get(this.type);
    }

    public void setType(String value) {
        this.type = value;
    }

    public String getDwmc() {
        return this.dwmc;
    }

    public void setDwmc(String value) {
        this.dwmc = value;
    }

    public String getDwdz() {
        return this.dwdz;
    }

    public void setDwdz(String value) {
        this.dwdz = value;
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

