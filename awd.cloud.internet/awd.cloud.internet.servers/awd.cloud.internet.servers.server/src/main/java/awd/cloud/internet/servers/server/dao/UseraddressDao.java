/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.servers.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import awd.cloud.internet.servers.server.entity.UseraddressEntity;
import awd.framework.common.dao.api.CrudDao;
import awd.framework.common.entity.Entity;

public interface UseraddressDao extends CrudDao<UseraddressEntity, String> {
	
	List<UseraddressEntity> query(Entity queryEntity);

    int count(Entity queryEntity);
    
    String getAddressByUserid(String userid);
    
    void updateAddressByUserid(@Param("userid") String userid,@Param("address") String address);
    
}
