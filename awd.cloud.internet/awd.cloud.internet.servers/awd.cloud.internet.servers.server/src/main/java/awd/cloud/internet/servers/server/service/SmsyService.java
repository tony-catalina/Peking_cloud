/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.servers.server.service;

import java.util.List;
import java.util.Map;

import awd.cloud.internet.servers.server.entity.SmsyEntity;
import awd.framework.common.service.api.CrudService;

public interface SmsyService extends CrudService<SmsyEntity, String> {

	Map<String, Object> pcsZdchSmsy(String idString, String chyy);
	
	String resetFcjlById(String id);
	
	List<String> selectSmsyByFcuuid(String fcuuid);
	
	int selectSmsyCountByFcuuid(String fcuuid,String badw);
}
