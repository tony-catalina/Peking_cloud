/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.servers.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import awd.framework.common.dao.api.CrudDao;
import awd.framework.common.entity.Entity;
import awd.cloud.internet.servers.server.entity.UnitEntity;

public interface UnitDao extends CrudDao<UnitEntity, String> {
	
	List<UnitEntity> query(Entity queryEntity);

    int count(Entity queryEntity);
    
    String queryByDwbh(String dwbh);
    
    String getDwmcByDwbhAndType(@Param("dwbh")String dwbh,@Param("type")String type);
    
    UnitEntity getDwmcDwdzByDwbhAndType(@Param("dwbh")String dwbh,@Param("type")String type);
    
    List<UnitEntity> queryKss();
    
    List<UnitEntity> queryPcs();
    
    void deleteTable();	//直接清除表中所有数据
}
