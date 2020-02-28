/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.servers.server.service;

import awd.framework.common.service.api.CrudService;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import awd.cloud.internet.servers.server.entity.UnitEntity;

public interface UnitService extends CrudService<UnitEntity, String> {
	String queryByDwbh(String dwbh);
	
	String getDwmcByDwbhAndType(String dwbh,String type);
	
	UnitEntity getDwmcDwdzByDwbhAndType(String dwbh,String type);
	/**
	 * 查询看守所
	 * @return
	 */
	List<UnitEntity> queryKss();
    /**
     * 查询派出所
     * @return
     */
    List<UnitEntity> queryPcs();

	UnitEntity getUnitCacheByDwbh(String dwbh);
	
	void deleteTable();	//直接清除表中所有数据
}
