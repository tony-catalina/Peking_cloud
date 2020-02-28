/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.servers.server.dao;

import java.util.List;

import awd.framework.common.dao.api.CrudDao;
import awd.framework.common.entity.Entity;
import awd.cloud.internet.servers.server.entity.AdminEntity;

public interface AdminDao extends CrudDao<AdminEntity, String> {
	
	List<AdminEntity> query(Entity queryEntity);

    int count(Entity queryEntity);

    void deleteTable();	//直接清除表中所有数据
    
}
