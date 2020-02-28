package awd.cloud.internet.servers.server.service.imp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.atomikos.icatch.SysException;
import com.google.common.collect.Maps;

import awd.cloud.internet.servers.server.config.CacheConfig;
import awd.cloud.internet.servers.server.controller.InterfaceController;
import awd.cloud.internet.servers.server.entity.UnitEntity;
import awd.cloud.internet.servers.server.entity.UserinfoEntity;
import awd.cloud.internet.servers.server.model.UserDetailModel;
import awd.cloud.internet.servers.server.service.InterfaceService;
import awd.cloud.internet.servers.server.service.UseraddressService;
import awd.cloud.internet.servers.server.service.UserinfoService;
import awd.framework.common.utils.BeanUtils;
import awd.framework.common.utils.StringUtils;
import koal.sgpmi.client.author.ClientForAuthor;
import koal.sgpmi.client.bean.ReturnAuthorBean;
import koal.usap.client.bean.ReturnDataBean;
import koal.usap.client.org.ClientForOrg;
import koal.usap.client.user.ClientForUser;


@Service("interfaceService")
public class SimpleInterfaceService implements InterfaceService{
	
	@Autowired
	private CacheConfig cacheConfig;
	@Autowired
	private UserinfoService userinfoService;
	@Autowired
	private UseraddressService useraddressService;
	
    @Value("${koal.user.appcode}")
    private String userAppCode;
    @Value("${koal.user.name}")
    private String userName;
    @Value("${koal.user.pwd}")
    private String userPwd;
    @Value("${koal.user.url}")
    private String userUrl;
    @Value("${koal.user.sqUrl}")
    private String sqUrl;
	
    private int version = 1;
    private long normal = 2000;

    private static ClientForUser clientForUser;
    private static ClientForOrg clientForOrg;
    private static ClientForAuthor clientForAuthor;

    public static Logger logger = Logger.getLogger(InterfaceController.class);
    
    /**
     * 	初始化接口
     */
    public void init() {
        try {
	        System.err.println("授权初始化-----------------------");
	        ClientForAuthor clientForAuthor = new ClientForAuthor();
	        clientForAuthor.initHttp(version, userAppCode, userName, userPwd, sqUrl);
	        this.clientForAuthor = clientForAuthor;
	        System.err.println("用户初始化-----------------------");
	        ClientForUser clientForUser = new ClientForUser();
	        ClientForOrg clientForOrg = new ClientForOrg();
	        clientForUser.initHttp(version, userAppCode, userName, userPwd, userUrl);
	        clientForOrg.initHttp(version, userAppCode, userName, userPwd, userUrl);
	        this.clientForUser = clientForUser;
	        this.clientForOrg = clientForOrg;
            
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
    
    /**
	 *	获取根级机构信息
     * @return
     */
    public Map<String, Object> getRootOrg() {
    	Map<String, Object> outMap = Maps.newHashMap();
    	try {
    		ReturnDataBean<Map<String, Object>> orgData = clientForOrg.getRootOrg();
    		outMap = orgData.getData();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return outMap;
	}
    
    /**
     * 	根据机构代码 获取下级机构的数据
     * @return
     */
    public List<Map<String, Object>> getOrgsByParentCode(String rootCode) {
    	List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    	try {
    		ReturnDataBean<List<Map<String, Object>>> orgData = clientForOrg.getOrgsByParentCode(rootCode);
    		List<Map<String, Object>> org = orgData.getData();
    		result = org;
			/*
			 * org.forEach(map->{
			 * System.err.println("ORG_CODE---"+JSON.toJSONString(map.get("ORG_CODE")));
			 * System.err.println("ORG_NAME---"+JSON.toJSONString(map.get("ORG_NAME"))); });
			 */
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return result;
    }
    
    /**
     * 	根据机构代码 获取机构人员的数据
     * @return
     */
    public List<Map<String, Object>> getUserByOrgCode(String orgCode) {
    	List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    	try {
    		ReturnDataBean<List<Map<String, Object>>> orgData = clientForUser.getUserByOrgCode(orgCode,1);
    		List<Map<String, Object>> org = orgData.getData();
    		result = org;
    		/*
    		 * org.forEach(map->{
    		 * System.err.println("ORG_CODE---"+JSON.toJSONString(map.get("ORG_CODE")));
    		 * System.err.println("ORG_NAME---"+JSON.toJSONString(map.get("ORG_NAME"))); });
    		 */
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return result;
    }
    
    /**
     *	 查询机构信息
     * @param orgCode
     * @return
     */
    public Map<String, Object> getOrgInfo(String orgCode) {
    	Map<String, Object> org = Maps.newHashMap();
    	try {
    		ReturnDataBean<Map<String, Object>> data = clientForOrg.getOrgByCode(orgCode);
    		if (data.getTotalNum() == 0) {
    			return org;
    		}
    		org = data.getData();
    		
    		Object orgBaseStatusStr = org.get("ORG_STATUS");
    		if (orgBaseStatusStr != null) {
    			Long userBaseStatus = Long.valueOf((String) orgBaseStatusStr);
    			if (userBaseStatus < normal) {
    				throw new SysException("机构状态异常，您无权操作");
    			}
    		}
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    		throw new SysException("获取机构信息异常");
    	}
    	return org;
    }
    
    /**
     * 	查询人员信息
     *
     * @param userCode
     * @return
     */
    public Map<String, Object> getUserInfo(String userCode) {
    	System.err.println("userCode---"+userCode);
    	
    	Map<String, Object> user = Maps.newHashMap();
        try {
            ReturnDataBean<List<Map<String, Object>>> data = clientForUser.getUserAndExtUserByCode(userCode);
            if (data.getTotalNum() == 0 || data.getData().size() == 0) {
                return user;
            }else {
            	user = data.getData().get(0);
			}
            Object userBaseStatusStr = user.get("USER_BASE_STATUS");
            if (userBaseStatusStr != null) {
                Long userBaseStatus = Long.valueOf((String) userBaseStatusStr);
                if (userBaseStatus < normal) {
                    //throw new SysException("用户状态异常，您无权操作");
                	System.err.println("用户状态异常，您无权操作");
                }
                return user;
            }
        } catch (Exception e) {
        	e.printStackTrace();
            //throw new SysException("获取用户信息异常");
        	System.err.println("获取用户信息异常");
        }
        return user;
    }
    
    /**
     * 	获取所有资源数据,参数 页码
     */
    public List<Map<String, Object>> getResourceByAppCode() {
    	List<Map<String, Object>> dataList = new ArrayList<>();
    	try {
    		ReturnAuthorBean<List<Map<String, Object>>> allAuthor = clientForAuthor.getResourceByAppCode(1);
    		dataList = allAuthor.getData();
			/*
			 * dataList.forEach(map->{
			 * System.err.println("getResourceByAppCode---map---"+JSON.toJSONString(map));
			 * });
			 */
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return dataList;
	}
    
    /**
     * 	获取所有角色数据，参数 页码
     */
    public List<Map<String, Object>> getRoleByAppCode() {
    	List<Map<String, Object>> dataList = new ArrayList<>();
    	try {
    		ReturnAuthorBean<List<Map<String, Object>>> allAuthor = clientForAuthor.getRoleByAppCode(1);
    		dataList = allAuthor.getData();
			/*
			 * dataList.forEach(map->{
			 * System.err.println("getRoleByAppCode---map---"+JSON.toJSONString(map)); });
			 */
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return dataList;
    }
    
    /**
     * 	从4a获取机构信息
     */
    public List<Map<String, Object>> getOrgList(){
    	
    	List<Map<String, Object>> dataList = new ArrayList<>();
    	List<String> nameList = new ArrayList<>();
    	//先获取最上层根机构的机构代码
    	String rootCode = "310000000000";
    	try {
    		Map<String, Object> root = getRootOrg();
    		dataList.add(root);
    		rootCode = (String) root.get("ORG_CODE");
    		List<Map<String, Object>> rootOrgList = getOrgsByParentCode(rootCode);	
    		List<Map<String, Object>> tmp = new ArrayList<>();	
    		
    		for (Map<String, Object> map : rootOrgList) {
    			List<Map<String, Object>> childrenList = new ArrayList<>();	//二级节点
    			String childrenCode = (String) map.get("ORG_CODE");
				String childrenName = (String) map.get("ORG_NAME");
				childrenList = getOrgsByParentCode(childrenCode);
				
				Long orgStatus = Long.valueOf((String) map.get("ORG_STATUS"));
    			if (orgStatus >= normal) {
    				nameList.add(childrenName);
    				dataList.add(map);
    			}
	    		if (childrenList.size() > 0){
					boolean isList = true;
					while (isList) {
						for (Map<String, Object> map1 : childrenList) {
							String childrenCode1 = (String) map1.get("ORG_CODE");
							String childrenName1 = (String) map1.get("ORG_NAME");
							tmp = getOrgsByParentCode(childrenCode1);
							for (int i = 0; i < 5; i++) {
								if (tmp.size() == 0) {
									isList = false;
									Long orgStatus1 = Long.valueOf((String) map1.get("ORG_STATUS"));
					    			if (orgStatus1 >= normal) {
					    				nameList.add(childrenName1);
					    				dataList.add(map1);
					    			}
									break;
								}else {
									for (Map<String, Object> map2 : tmp) {
										String childrenCode2 = (String) map2.get("ORG_CODE");
										String childrenName2 = (String) map2.get("ORG_NAME");
										Long orgStatus2 = Long.valueOf((String) map1.get("ORG_STATUS"));
						    			if (orgStatus2 >= normal) {
						    				nameList.add(childrenName2);
						    				dataList.add(map2);
						    			}
										tmp = getOrgsByParentCode(childrenCode2);
										continue ;
									}
								}
							}
						}
					}
				}
    		}
				
		} catch (Exception e) {
			// 异常处理
		}
    	return dataList;
    }
    
    
    /**
     *	吧4a获取的人员信息
     */
    public UserDetailModel getUserModelFrom4A(UserDetailModel model) {
    	System.err.println("userDetailInfo----"+JSON.toJSONString(model));
    	model.setCreatetime(Calendar.getInstance().getTime());
    	model.setUpdatetime(Calendar.getInstance().getTime());
    	String userCode = model.getUserid();
		Cache user_cache = CacheConfig.getUserCacheManager().getCache(CacheConfig.CAHCE_USER);
		try {
			UserDetailModel userInfo = user_cache.get(userCode, UserDetailModel.class);
			if (!StringUtils.isNullOrEmpty(userInfo)) {	//假如系统缓存没有，就查询4A数据
				System.err.println("缓存中有user");
				//BeanUtils.copyProperties(userInfo, model);
				return userInfo;
			}
		} catch (BeansException e1) {
			System.err.println("从缓存中获取失败");
		}
		
		/*
		 * QueryParamEntity qEntity = new QueryParamEntity(); qEntity.and("userid",
		 * userCode); UserinfoEntity user = userinfoService.selectSingle(qEntity);
		 */
		UserinfoEntity user = userinfoService.selectByPk(userCode);
		if (!StringUtils.isNullOrEmpty(user)) {
			BeanUtils.copyProperties(user, model);
			System.err.println("user----------------"+JSON.toJSONString(user));
		}
		System.err.println("model----------------"+JSON.toJSONString(model));
		model.setAddress(useraddressService.getAddressByUserid(userCode));
		/*
		 * UseraddressEntity useraddress = useraddressService.selectByPk(userCode); if
		 * (StringUtils.isNullOrEmpty(useraddress)) {
		 * model.setAddress(useraddress.getAddress());
		 * model.setState(useraddress.getUserstate()); }
		 */
		
    	//根据userCode 从4A 那边获取到人员信息
    	Map<String, Object> userMap = getUserInfo(userCode);
    	if (userMap.size() == 0) {
			model.setUsername(model.getName());
			model.setPhone(model.getMobile());
			if ("1".equals(model.getGender())) {
				model.setXb("1");
	    		model.setXbString("男");
			}else {	
				model.setXb("2");
				model.setXbString("女");
			}
    		cacheConfig.setUserCache(model);
    		return model;
		}
    	model.setUserid((String) userMap.get("USER_CODE"));
    	if (!StringUtils.isNullOrEmpty(userMap.get("USER_NAME"))) {
    		model.setUsername((String) userMap.get("USER_NAME"));
    	}
    	model.setJh((String) userMap.get("USER_POLICE_ID"));
    	model.setUnit((String) userMap.get("FULL_NAME"));
    	//model.setAddress((String) userMap.get("OFFICE_ADDRESS"));
    	String type = "0";	//先默认是0 办案单位
    	String unit_code = "";
    	if (StringUtils.isNullOrEmpty(userMap.get("ORG_CODE"))) {
    		unit_code = (String) userMap.get("FULL_NAME");
		}else {
			unit_code = (String) userMap.get("ORG_CODE");
		}
    	if (!"".equals(unit_code)) {
    		UnitEntity unit = null;
    		try {
    			unit = (UnitEntity) cacheConfig.getCacheByKey(CacheConfig.CAHCE_UNIT, (String) userMap.get("ORG_CODE"));
    			type = unit.getType();
    			model.setDwbh(unit.getDwbh());
    		} catch (Exception e) {
    			System.err.println("根据 unit_code 从缓存中获取 UNIT 失败");
    		}
		}
    	model.setType(type);
    	model.setDwbhString((String) userMap.get("FULL_NAME"));
    	model.setEmail((String) userMap.get("OFFICE_EMAIL"));
    	model.setPhone(model.getMobile());
		/*
		 * if (!StringUtils.isNullOrEmpty(userMap.get("PHONE"))) {
		 * model.setPhone((String) userMap.get("PHONE")); }
		 */
    	//获取单位类型

    	if ("10".equals(userMap.get("USER_SEX"))) {	//10是男性
    		model.setXb("1");
    		model.setXbString("男");
		}else {	//20是女性
			model.setXb("2");
			model.setXbString("女");
		}
    	String userPhotoUrl = model.getAvatar();
    	String imageBase64 = (String) userMap.get("USER_IMG");
    	if (!StringUtils.isNullOrEmpty(imageBase64)) {
    		userPhotoUrl = "data:image/jpeg;base64," + imageBase64;
		}
    	//userPhotoUrl = "http://pic25.nipic.com/20121112/9252150_150552938000_2.jpg";
    	model.setImageBase64(userPhotoUrl);
    	
    	cacheConfig.setUserCache(model);
    	
    	return model;
	}
    
    /**
     *	从缓存中获取所有的组织机构信息
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getOrgListByCache(String type) {
    	Cache cache = cacheConfig.getCacheManager().getCache(cacheConfig.CAHCE_ORG);
    	/*
    	 * ValueWrapper valueWrapper = cache.get(cacheConfig.CAHCE_ORG); Object object =
    	 * null; if(valueWrapper!=null) { object = valueWrapper.get(); }
    	 */
    	List<Map<String, Object>> resultList = new ArrayList<>();
    	List<Map<String, Object>> list = cache.get(cacheConfig.CAHCE_ORG, resultList.getClass());
    	if (getFlag(type)) {
    		for (Map<String, Object> map : list) {
    			String org_type = (String) map.get("TYPE");
    			if (StringUtils.isNullOrEmpty(org_type) && org_type.equals(type)) {
    				resultList.add(map);
    			}
			}
		}else {
			resultList = list;
		}
    	return resultList;
    }
    
	/**
	 * 先查询 系统缓存，假如没有，就从接口查询用户信息，并重新放入缓存——测试方法，没写完
	 */
	public UserDetailModel getUserByCache(String userid) {
		Cache user_cache = CacheConfig.getUserCacheManager().getCache(CacheConfig.CAHCE_USER);
		UserDetailModel userInfo = user_cache.get(userid, UserDetailModel.class);
		if (!StringUtils.isNullOrEmpty(userInfo)) {	//假如系统缓存没有，就查询4A数据
			System.err.println("缓存中有user");
			System.err.println("userInfo---"+JSON.toJSONString(userInfo));
			return userInfo;
		}
		return userInfo;
	}
    
    
    public boolean getFlag(String type) {
    	boolean flag = false;
    	try {
			if ("0,1".indexOf(type) > 0) {
				flag = true;
			}
		} catch (Exception e) {
			flag = false;
		}
    	return flag;
	}
    
    
    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass)
            throws Exception {
        if (map == null)
            return null;
        Object obj = beanClass.newInstance();
        org.apache.commons.beanutils.BeanUtils.populate(obj, map);
        return obj;
    }

    public static Map<?, ?> objectToMap(Object obj) {
        if (obj == null) {
            return null;
        }
        return new org.apache.commons.beanutils.BeanMap(obj);
    }
    
    
    
}
