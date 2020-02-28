/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.servers.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import awd.cloud.internet.servers.server.entity.FjsysEntity;
import awd.framework.common.dao.api.CrudDao;
import awd.framework.common.entity.Entity;

public interface FjsysDao extends CrudDao<FjsysEntity, String> {
	
	List<FjsysEntity> query(Entity queryEntity);

    int count(Entity queryEntity);

    void updateFjBysJsbh(@Param("sysl")String sysl,@Param("jsbh")String jsbh);

}
