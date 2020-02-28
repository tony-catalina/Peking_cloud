/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.servers.server.service;

import awd.framework.common.service.api.CrudService;

import java.util.List;
import java.util.Map;

import awd.cloud.internet.servers.server.entity.UserinfoEntity;

public interface UserinfoService extends CrudService<UserinfoEntity, String> {
	int addUser(Map<String, Object> parms);
	/**
	 * 	根据微信号查询人员信息
	 * @param wxh
	 * @return
	 */
	List<UserinfoEntity> loginCheck(String wxh);
	
	/**
	 * 	新增人员，并放入缓存
	 * @param userinfoEntity
	 * @return
	 */
	String addUserInfoWithCache(UserinfoEntity userinfoEntity);
	
	/**
	 * 	修改人员，并放入缓存
	 * @param userinfoEntity
	 * @return
	 */
	int updateUserInfoWithCache(String id,UserinfoEntity userinfoEntity);
	
	int enableUser(String ids, String enable);
	
	int shUser(String ids, String enable ,String shbz);
}
