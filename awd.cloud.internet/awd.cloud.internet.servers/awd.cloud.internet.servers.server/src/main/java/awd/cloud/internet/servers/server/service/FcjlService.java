/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.servers.server.service;

import awd.cloud.internet.servers.server.entity.FcjlEntity;
import awd.framework.common.service.api.CrudService;

public interface FcjlService extends CrudService<FcjlEntity, String> {

	String updateFcjlWithFczt(FcjlEntity fcjlEntity);
	
	String updateFcjlAfterDsm(String fcuuid,String badw,String fczt);
	
	int updateFcztByFcuuid(String fczt,String fcuuid);
}
