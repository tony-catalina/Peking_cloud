package awd.cloud.internet.web.manager.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import awd.cloud.internet.web.manager.api.AwdApi;
import awd.cloud.internet.web.manager.models.UserinfoModel;
import awd.framework.common.core.IDGenerator;
import awd.framework.common.dao.api.CrudDao;
import awd.framework.common.service.simple.DefaultDSLQueryService;
import awd.framework.common.service.simple.GenericEntityService;
import awd.framework.common.utils.JSONUtil;
import awd.framework.expands.authorization.Authentication;
import awd.framework.expands.authorization.AuthenticationInitializeService;
import awd.framework.expands.authorization.Permission;
import awd.framework.expands.authorization.access.DataAccessConfig;
import awd.framework.expands.authorization.simple.SimpleAuthentication;
import awd.framework.expands.authorization.simple.SimplePermission;
import awd.framework.expands.authorization.simple.SimpleRole;
import awd.framework.expands.authorization.simple.SimpleUser;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.apache.commons.collections.CollectionUtils.isEmpty;
import static org.apache.commons.collections.CollectionUtils.isNotEmpty;

@Service("authorizationSettingService")
public class SimpleAuthorizationSettingService 
        implements  AuthenticationInitializeService {
	
	@Autowired 
	AwdApi awdApi;
	

	@Override
	public Authentication initUserAuthorization(String userId) {
		System.out.println("userId:"+userId);
		if (null == userId) return null;
		UserinfoModel userinfoModel = new UserinfoModel();
		userinfoModel.setId("admin");
		userinfoModel.setUsername("admin");
		userinfoModel.setPassword("admin");
		userinfoModel.setState("R2");
        if (userinfoModel == null) return null;
        
        SimpleAuthentication authentication = new SimpleAuthentication();
        // 用户信息
		authentication.setUser(new SimpleUser(userId, userinfoModel.getUsername(), userinfoModel.getWxh(),userinfoModel.getDwbh()));
               
        //权限
        List<Permission> permissions = new ArrayList<>();
        SimplePermission permission=new SimplePermission();
		Set<String> actions = new HashSet<>();
        Set<DataAccessConfig> dataAccessConfigs = new HashSet<>();
        permission.setId("1");            	
        permission.setActions(actions);
        permission.setDataAccesses(dataAccessConfigs);
        permissions.add(permission);

        authentication.setPermissions(permissions);
        return authentication;
	}


}
