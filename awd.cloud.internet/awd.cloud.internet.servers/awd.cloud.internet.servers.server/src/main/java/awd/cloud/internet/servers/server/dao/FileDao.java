/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.servers.server.dao;

import java.util.List;
import java.util.Map;

import awd.framework.common.dao.api.CrudDao;
import awd.framework.common.entity.Entity;
import awd.cloud.internet.servers.server.entity.FileEntity;

public interface FileDao extends CrudDao<FileEntity, String> {
	
	List<FileEntity> query(Entity queryEntity);

    int count(Entity queryEntity);

    int updateFile(Map<String, Object> conditions);
    
    int insertOne(Map<String, Object> conditions);
    
    FileEntity queryByUuid(String uuid);
    
}
