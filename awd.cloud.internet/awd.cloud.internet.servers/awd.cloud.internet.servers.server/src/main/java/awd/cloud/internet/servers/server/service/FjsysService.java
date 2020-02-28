/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.servers.server.service;

import awd.cloud.internet.servers.server.entity.FjsysEntity;
import awd.framework.common.service.api.CrudService;
import feign.Param;

public interface FjsysService extends CrudService<FjsysEntity, String> {
	
	 void updateFjBysJsbh(String sysl,String jsbh);
}
