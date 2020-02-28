/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.servers.server.dao;

import java.util.List;

import awd.cloud.internet.servers.server.entity.YyEntity;
import awd.framework.common.dao.api.CrudDao;
import awd.framework.common.entity.Entity;

public interface YyDao extends CrudDao<YyEntity, String> {
	
	List<YyEntity> query(Entity queryEntity);

    int count(Entity queryEntity);

}
