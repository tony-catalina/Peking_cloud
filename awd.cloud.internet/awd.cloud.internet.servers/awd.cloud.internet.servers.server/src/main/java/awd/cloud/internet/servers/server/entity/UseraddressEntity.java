/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.servers.server.entity;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import awd.framework.common.entity.SimpleGenericEntity;
import awd.framework.common.service.web.group.CreateGroup;
import awd.framework.common.service.web.group.UpdateGroup;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */


public class UseraddressEntity extends SimpleGenericEntity<String> {

    //alias
    public static final String TABLE_ALIAS = "Useraddress";
    public static final String ALIAS_ID = "id";
    public static final String ALIAS_USERID = "身份证号";
    public static final String ALIAS_ADDRESS = "单位地址";
    public static final String ALIAS_USERSTATE = "用户状态";

    //所有组
    @GroupSequence({CreateGroup.class, UpdateGroup.class})
    public interface All {
    }

    //columns START


    @Length(max = 50, message = "微信号最大长度50位", groups = CreateGroup.class)
    private String userid;
    
    @Length(max = 255, message = "单位地址最大长度255位", groups = CreateGroup.class)
    private String address;
    
    @Length(max = 2, message = "单位地址最大长度255位", groups = CreateGroup.class)
    private String userstate;

    //columns END


    public UseraddressEntity() {
    }

    public UseraddressEntity(
            String id
    ) {
        super.setId(id);
    }


    public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}


    public String getAddress() {
    	return address;
    }

    public void setAddress(String value) {
        this.address = value;
    }

	public String getUserstate() {
		return userstate;
	}

	public void setUserstate(String userstate) {
		this.userstate = userstate;
	}
    
}

