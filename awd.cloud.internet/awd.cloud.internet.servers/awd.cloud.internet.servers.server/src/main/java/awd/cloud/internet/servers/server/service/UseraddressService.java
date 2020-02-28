/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.servers.server.service;

import awd.cloud.internet.servers.server.entity.UseraddressEntity;
import awd.cloud.internet.servers.server.entity.UserinfoEntity;
import awd.framework.common.service.api.CrudService;

public interface UseraddressService extends CrudService<UseraddressEntity, String> {
	
	void saveUserAddress(UserinfoEntity userinfo);
	
	void saveUserAddress(String userid,String address);
	
	String getAddressByUserid(String userid);
    
    void updateAddressByUserid(String userid,String address);
}
