/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.servers.server.service;

import java.util.List;

import awd.cloud.internet.servers.server.entity.UnitcommonlyEntity;
import awd.framework.common.service.api.CrudService;

public interface UnitcommonlyService extends CrudService<UnitcommonlyEntity, String> {

	List<UnitcommonlyEntity> getCommonlyUnit(String userid);
	
	String setCommonlyUnit(String userid,String dwbh,String option);
}
