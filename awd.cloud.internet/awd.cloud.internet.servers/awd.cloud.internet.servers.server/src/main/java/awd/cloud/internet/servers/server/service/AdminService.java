/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.servers.server.service;

import awd.framework.common.service.api.CrudService;
import awd.cloud.internet.servers.server.entity.AdminEntity;

public interface AdminService extends CrudService<AdminEntity, String> {

	void deleteTable();
}
