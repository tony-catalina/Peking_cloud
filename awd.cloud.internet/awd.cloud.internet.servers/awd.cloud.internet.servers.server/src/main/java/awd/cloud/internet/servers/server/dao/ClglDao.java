/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.servers.server.dao;

import java.util.List;

import awd.framework.common.dao.api.CrudDao;
import awd.framework.common.entity.Entity;
import awd.cloud.internet.servers.server.entity.ClglEntity;

public interface ClglDao extends CrudDao<ClglEntity, String> {
	
	List<ClglEntity> query(Entity queryEntity);

    int count(Entity queryEntity);

}
