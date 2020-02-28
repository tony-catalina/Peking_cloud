

/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.servers.server.dao;

import java.util.List;
import java.util.Map;

import awd.framework.common.dao.api.CrudDao;
import awd.framework.common.entity.Entity;
import awd.cloud.internet.servers.server.entity.SmsyEntity;
import org.apache.ibatis.annotations.Param;

public interface SmsyDao extends CrudDao<SmsyEntity, String> {

    List<SmsyEntity> query(Entity queryEntity);

    int count(Entity queryEntity);
    
    //根据id 重置上门收押发车信息
    int resetFcjlById(@Param("id") String id);
    
    List<String> selectSmsyByFcuuid(@Param("fcuuid") String fcuuid);
    
    int selectSmsyCountByFcuuid(@Param("fcuuid") String fcuuid,@Param("badw") String badw);
}

