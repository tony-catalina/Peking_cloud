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

import static awd.cloud.internet.servers.server.utils.DictionaryMap.SMSYDWLX_MAP;

import java.util.Map;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */


public class UserinfoEntity extends SimpleGenericEntity<String> {

    //alias
    public static final String TABLE_ALIAS = "Userinfo";
    public static final String ALIAS_ID = "id";
    public static final String ALIAS_DWBH = "单位编号";
    public static final String ALIAS_USERID = "身份证号";
    public static final String ALIAS_WXH = "微信号";
    public static final String ALIAS_USERNAME = "用户名";
    public static final String ALIAS_PASSWORD = "密码";
    public static final String ALIAS_XB = "性别(XB)";
    public static final String ALIAS_JH = "警号";
    public static final String ALIAS_PHONE = "电话";
    public static final String ALIAS_UNIT = "工作单位";
    public static final String ALIAS_ADDRESS = "单位地址";
    public static final String ALIAS_TYPE = "单位类型(SMSYDWLX)";
    public static final String ALIAS_STATE = "删除状态";
    public static final String ALIAS_CREATOR = "创建人";
    public static final String ALIAS_CREATETIME = "创建时间";
    public static final String ALIAS_UPDATOR = "更新人";
    public static final String ALIAS_UPDATETIME = "更新时间";
    public static final String ALIAS_SHBZ = "审核标志";

    //所有组
    @GroupSequence({CreateGroup.class, UpdateGroup.class})
    public interface All {
    }

    //columns START


    @Length(max = 50, message = "单位编号最大长度50位", groups = CreateGroup.class)
    private String dwbh;

    @Length(max = 50, message = "微信号最大长度50位", groups = CreateGroup.class)
    private String userid;
    
    @Length(max = 50, message = "微信号最大长度50位", groups = CreateGroup.class)
    private String wxh;

    @Length(max = 100, message = "用户名最大长度100位", groups = CreateGroup.class)
    private String username;

    @Length(max = 50, message = "密码最大长度50位", groups = CreateGroup.class)
    private String password;

    @Length(max = 1, message = "性别(XB)最大长度1位", groups = CreateGroup.class)
    private String xb;

    @Length(max = 30, message = "警号最大长度30位", groups = CreateGroup.class)
    private String jh;

    @Length(max = 30, message = "电话最大长度30位", groups = CreateGroup.class)
    private String phone;

    @Length(max = 50, message = "工作单位最大长度50位", groups = CreateGroup.class)
    private String unit;

    @Length(max = 255, message = "单位地址最大长度255位", groups = CreateGroup.class)
    private String address;

    @Length(max = 1, message = "单位类型(SMSYDWLX)最大长度1位", groups = CreateGroup.class)
    private String type;

    @Length(max = 2, message = "删除状态最大长度2位", groups = CreateGroup.class)
    private String state;

    @NotNull(message = "创建人不能为空", groups = CreateGroup.class)
    @Length(max = 50, message = "创建人最大长度50位", groups = CreateGroup.class)
    private String creator;
    
    @Length(max = 1, message = "审核标志最大长度1位", groups = CreateGroup.class)
    private String shbz;

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


    public UserinfoEntity() {
    }

    public UserinfoEntity(
            String id
    ) {
        super.setId(id);
    }

    public String getDwbh() {
        return this.dwbh;
    }

    public String getDwbhString() {
        UnitEntity entity = null;
        try {
			CacheConfig config = CacheConfig.getInstance();

			Object object = CacheConfig.getInstance().getCacheByKey(CacheConfig.CAHCE_UNIT, dwbh);
			entity = (UnitEntity) object;
			if (!StringUtils.isNullOrEmpty(entity)) {
				return entity.getDwmc();
			}
			 
			/*
			 * Object object =
			 * CacheConfig.getInstance().getCacheByKey(CacheConfig.CAHCE_ORG,this.dwbh); if
			 * (!StringUtils.isNullOrEmpty(object)) { Map<String, Object> org = (Map<String,
			 * Object>) object; return (String) org.get("ORG_NAME"); }
			 */
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return "";
    }
    public void setDwbh(String value) {
        this.dwbh = value;
    }

    public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getWxh() {
        return this.wxh;
    }

    public void setWxh(String value) {
        this.wxh = value;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String value) {
        this.username = value;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String value) {
        this.password = value;
    }

    public String getXb() {
        return this.xb;
    }
    
    public String getXbString() {
        return "1".equals(this.xb)?"男":"女";
    }

    public void setXb(String value) {
        this.xb = value;
    }

    public String getJh() {
        return this.jh;
    }

    public void setJh(String value) {
        this.jh = value;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String value) {
        this.phone = value;
    }

    public String getUnit() {
    	if(StringUtils.isNullOrEmpty(this.unit)) {
    		UnitEntity entity = null;
            try {
                CacheConfig config = CacheConfig.getInstance();
                Object object = CacheConfig.getInstance().getCacheByKey(CacheConfig.CAHCE_UNIT,this.dwbh);
                entity = (UnitEntity) object;
                if (!StringUtils.isNullOrEmpty(entity)) {
                	return entity.getDwmc();
                }
				/*
				 * Object object = config.getCacheByKey(CacheConfig.CAHCE_ORG,this.dwbh); if
				 * (!StringUtils.isNullOrEmpty(object)) { Map<String, Object> org = (Map<String,
				 * Object>) object; return (String) org.get("ORG_NAME"); }
				 */
                
            } catch (Exception e) {
                //e.printStackTrace();
            }
    	}
        return this.unit;
    }

    public void setUnit(String value) {
        this.unit = value;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String value) {
        this.address = value;
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

	public String getShbz() {
		return shbz;
	}

	public void setShbz(String shbz) {
		this.shbz = shbz;
	}
}

