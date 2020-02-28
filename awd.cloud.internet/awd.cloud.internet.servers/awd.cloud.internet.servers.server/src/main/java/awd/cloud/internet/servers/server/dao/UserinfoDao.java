/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.servers.server.dao;

import java.util.List;
import java.util.Map;

import awd.framework.common.dao.api.CrudDao;
import awd.framework.common.entity.Entity;
import awd.cloud.internet.servers.server.entity.UserinfoEntity;

public interface UserinfoDao extends CrudDao<UserinfoEntity, String> {
	
	List<UserinfoEntity> query(Entity queryEntity);

    int count(Entity queryEntity);
    
    int addUser(Map<String, Object> parms);
    
    List<UserinfoEntity> loginCheck(String wxh);
    
    
}
