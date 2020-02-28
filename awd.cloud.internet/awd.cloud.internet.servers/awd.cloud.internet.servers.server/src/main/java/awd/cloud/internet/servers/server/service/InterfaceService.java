/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.servers.server.service;

import java.util.List;
import java.util.Map;

import awd.cloud.internet.servers.server.model.UserDetailModel;

public interface InterfaceService {
	
	void init();
	
	Map<String, Object> getRootOrg();
	
	List<Map<String, Object>> getOrgsByParentCode(String rootCode);
	
	List<Map<String, Object>> getUserByOrgCode(String orgCode);
	
	Map<String, Object> getOrgInfo(String orgCode);
	
	Map<String, Object> getUserInfo(String userCode);
	
	List<Map<String, Object>> getResourceByAppCode();
	
	List<Map<String, Object>> getRoleByAppCode();
	
	List<Map<String, Object>> getOrgList();
	
	UserDetailModel getUserByCache(String userid);
	
	UserDetailModel getUserModelFrom4A(UserDetailModel userinfoModel);

	List<Map<String, Object>> getOrgListByCache(String type);
}
