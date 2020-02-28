/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.servers.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import awd.cloud.internet.servers.server.entity.FcjlEntity;
import awd.framework.common.dao.api.CrudDao;
import awd.framework.common.entity.Entity;

public interface FcjlDao extends CrudDao<FcjlEntity, String> {
	
	List<FcjlEntity> query(Entity queryEntity);

    int count(Entity queryEntity);

    int updateFcztByFcuuid(@Param("fczt") String fczt,@Param("fcuuid") String fcuuid);
    
    FcjlEntity selectFcjlByFcuuid(@Param("fcuuid") String fcuuid);
}
