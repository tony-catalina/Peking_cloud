package awd.cloud.internet.servers.server.config;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Configuration;

import com.alibaba.fastjson.JSON;
import com.google.common.cache.CacheBuilder;

import awd.cloud.internet.servers.server.entity.AdminEntity;
import awd.cloud.internet.servers.server.entity.UnitEntity;
import awd.cloud.internet.servers.server.entity.UserinfoEntity;
import awd.cloud.internet.servers.server.model.UserDetailModel;
import awd.cloud.internet.servers.server.service.AdminService;
import awd.cloud.internet.servers.server.service.InterfaceService;
import awd.cloud.internet.servers.server.service.UnitService;
import awd.cloud.internet.servers.server.service.UserinfoService;
import awd.framework.common.utils.BeanUtils;
import awd.framework.common.utils.StringUtils;

@Configuration
public class CacheConfig {

    @Autowired
    private UnitService unitService;
    @Autowired
    private UserinfoService userinfoService;
	@Autowired
	private InterfaceService interfaceService;
	@Autowired
	private AdminService adminService;

    public static String CAHCE_UNIT = "unit_cahce";
    public static String CAHCE_USER = "user_cahce";
    public static String CAHCE_ORG = "org_cahce";
    
    
    private static GuavaCacheManager cacheManager;	//全局的缓存
    
    private static GuavaCacheManager userCacheManager;	//单独的user的缓存
    
    private static CacheConfig instance;
    
    //获取唯一可用的对象
    public static CacheConfig getInstance(){
        if (instance == null) {
            return new CacheConfig();
        }
       return instance;
    }
  
    public CacheConfig() {
        super();
    }

    public static GuavaCacheManager getCacheManager() {
    	if (cacheManager == null) {
    		cacheManager = new GuavaCacheManager();
    		//最多缓存500 条，失效时间30分钟 
            //cacheManager.setCacheSpecification("maximumSize=500,expireAfterWrite=30m");
            //GuavaCacheManager 的数据结构类似  Map<String,Map<Object,Object>>  map =new HashMap<>();
            cacheManager.setCacheSpecification("maximumSize=500000,expireAfterWrite=3000000000000m");
		}
        return cacheManager;
    }
    
    public static GuavaCacheManager getUserCacheManager() {
    	if (userCacheManager == null) {
    		userCacheManager = new GuavaCacheManager();
    		//最多缓存500000 条，
    		//userCacheManager.setCacheSpecification("maximumSize=500000,expireAfterWrite=1500m");
    		//访问7天之后 失效，防止 别人无登录操作，留下无效缓存数据
    		userCacheManager.setCacheBuilder(CacheBuilder.newBuilder().expireAfterAccess(7,TimeUnit.DAYS));
    		//userCacheManager.setCacheBuilder(CacheBuilder.newBuilder().expireAfterWrite(7,TimeUnit.DAYS));
    	}
    	return userCacheManager;
    }
    
    public void setUnitCache() {
        //所有缓存的名字
        List<String> cacheNames = new ArrayList();

        List<UnitEntity> unitList = unitService.select();
        String cacheName = CAHCE_UNIT;
        if(cacheManager.getCache(cacheName)==null){
        	//为每一个 unit 如果不存在，创建一个新的缓存对象
        	cacheNames.add(cacheName);
        	cacheManager.setCacheNames(cacheNames);
        }
        Cache cache = cacheManager.getCache(cacheName);
        
        cache.clear();	//清除掉缓存
        //将数据放入缓存
        unitList.stream().forEach(unit -> {
            //缓存对象用 unit 的 dwbh 当作缓存的key 用 unit 当作缓存的value
            cache.put(unit.getDwbh(),unit);
            System.err.println("将  dwbh 为"+unit.getDwbh()+ "的 unit 数据做了缓存");
        });
        
        //return cacheManager;
    }
    
    public void setUserCache() {
    	//所有缓存的名字
        List<String> cacheNames = new ArrayList();
        List<UserinfoEntity> userList = userinfoService.select();
        String cacheName = CAHCE_USER;
        if(userCacheManager.getCache(cacheName)==null){
        	//为每一个 userinfo 如果不存在，创建一个新的缓存对象
        	cacheNames.add(cacheName);
        	userCacheManager.setCacheNames(cacheNames);
        }
        Cache cache = userCacheManager.getCache(cacheName);
        //将数据放入缓存
        userList.stream().forEach(user -> {
            //缓存对象用 user 的 wxh 当作缓存的key 用 userinfo 当作缓存的value
            cache.put(user.getUserid(),user);
            System.err.println("将  userid 为"+user.getUserid()+ "的 user 数据做了缓存");
        });
    }
    
    /**
     * 	吧从4a获取的组织机构数据放入缓存
     */
    public void setOrgCache() {
    	//所有缓存的名字
        List<String> cacheNames = new ArrayList<String>();
        List<Map<String, Object>> orgList = interfaceService.getOrgList();
        //将数据放入缓存
        
        String org_cacheName = CAHCE_ORG;
        String unit_cacheName = CAHCE_UNIT;
        if(cacheManager.getCache(org_cacheName)==null){
        	cacheNames.add(org_cacheName);
        	cacheNames.add(unit_cacheName);
        	cacheManager.setCacheNames(cacheNames);
        }
        
        Cache org_cache = cacheManager.getCache(org_cacheName);
        Cache unit_cache = cacheManager.getCache(unit_cacheName);
        
        org_cache.clear();	//因为我是准备从接口中获取, 所以我就把以前的缓存清空掉, 这样就是最新的缓存了
        unit_cache.clear();	//因为我是准备从接口中获取, 所以我就把以前的缓存清空掉, 这样就是最新的缓存了
        		
        adminService.deleteTable();					//清空admin表所有数据
        
        unitService.deleteTable();					//清空unit表所有数据
        
        orgList.stream().forEach(org -> {	//循环把机构信息放入缓存, 并同步把信息保存到admin表
            String orgCode = (String) org.get("ORG_CODE");
			String orgName = (String) org.get("ORG_NAME");
			String orgFullName = (String) org.get("FULL_NAME");
            
			//把机构信息 放入unit表中
			UnitEntity unit = new UnitEntity();
			unit.setId(orgCode);
			unit.setDwbh(orgCode);
			unit.setDwmc(orgFullName);
			unit.setState("R2");
			unit.setDwdz("暂无");
			unit.setCreator("系统");
			unit.setCreatetime(Calendar.getInstance().getTime());
			if (iSJgdw(orgFullName) || iSJgdw(orgName)) {	//假如名称中包含看守所或者拘留所,那么就把他当成监管单位,
				org.put("TYPE", "1");
				unit.setType("1");
				AdminEntity admin = new AdminEntity();
				admin.setId(orgCode);
				admin.setJsbh(orgCode);
				admin.setJsmc(orgFullName);
				admin.setPassword("123456");
				adminService.insert(admin);
			}else {	//否则就是办案单位
				org.put("TYPE", "0");
				unit.setType("0");
				/*
				 * AdminEntity admin = new AdminEntity(); admin.setId(orgCode);
				 * admin.setJsbh(orgCode); admin.setJsmc(orgFullName);
				 * admin.setPassword("123456"); adminService.insert(admin);
				 */
			}
			org_cache.put(orgCode,org);
			
			unitService.insert(unit);
			
			unit_cache.put(unit.getDwbh(),unit);	//用机构代码作为key缓存
			unit_cache.put(unit.getDwmc(),unit);	//用机构名称作为key缓存
            System.err.println("将  dwbh 为"+unit.getDwbh()+ "的 unit 数据做了缓存");
            
            //System.err.println("将  orgCode 为"+ orgCode + "的 "+orgFullName+" 组织机构  数据做了缓存");
        });
        //setUnitCache();	//循环结束之后再把unit信息保存进缓存
        org_cache.put(CAHCE_ORG,orgList);	//最后再把整个list放进缓存去
    }
    
    public boolean iSJgdw(String dwmc) {	//判断是否是监管单位
		boolean flag = false;
		//判断是否包含“...强制...科”
		Pattern s1 = Pattern.compile("(\\S*)强制(\\S*)科(\\S*)");
		flag = s1.matcher(dwmc).matches();
		if(flag){
			return  true;
		}
		Pattern s2 = Pattern.compile("(\\S*)室");
		if (s2.matcher(dwmc).matches()) {
			return false;
		}
		
		String regexString = "看守所,拘留所,戒毒所,医疗所,收容教育";
    	String[] regex = regexString.split(",");
		for (String string : regex) {
			if (dwmc.contains(string)) {
				return true;
			}
		}
    	return flag;
	}
    
    
    public void setUserCache(UserDetailModel userinfo) {
         String cacheName = CAHCE_USER;
         Cache cache = userCacheManager.getCache(cacheName);
         //缓存对象用 user 的 wxh 当作缓存的key 用 userinfo 当作缓存的value
         cache.put(userinfo.getUserid(),userinfo);
    }
    
    //我们缓存中 存的是userdetailInfo
    public UserDetailModel updateUserCache(UserinfoEntity userinfo) {
		String userid = userinfo.getUserid();
		Cache cache = userCacheManager.getCache(CAHCE_USER);
		UserDetailModel userDetailInfo = new UserDetailModel();
		try {
			userDetailInfo = cache.get(userid, UserDetailModel.class);
			if (StringUtils.isNullOrEmpty(userDetailInfo.getId())) {
				UserDetailModel userDetailModel = new UserDetailModel();
				userDetailModel.setUserid(userid);
				interfaceService.getUserModelFrom4A(userDetailModel);
			}
			userDetailInfo.setId(userid);
			userDetailInfo.setAddress(userinfo.getAddress());
			userDetailInfo.setState(userinfo.getState());
			userDetailInfo.setShbz(userinfo.getShbz());
			cache.put(userid, userDetailInfo);
		} catch (BeansException e) {
			//e.printStackTrace();
			cache.put(userid, userinfo);
			BeanUtils.copyProperties(userinfo, userDetailInfo);
		}
		return userDetailInfo;
	}
    
    public UserDetailModel getUserCache(String userid) {
    	Cache cache = userCacheManager.getCache(CAHCE_USER);
    	UserDetailModel userDetailInfo = new UserDetailModel();
		try {
			userDetailInfo = cache.get(userid, UserDetailModel.class);
		} catch (BeansException e) {
			//e.printStackTrace();
		}
		return userDetailInfo;
	}
    
    public Object getCacheByKey(String cacheName,String key) {
    	System.err.println("cacheName:"+cacheName+" ,key:"+key);
    	Cache cache = getCacheManager().getCache(cacheName.toString());
    	Object object=null;
    	if(cache!=null) {
    		Cache.ValueWrapper valueWrapper = cache.get(key);
    		if(valueWrapper!=null) {
    			object = valueWrapper.get();
    		}        	
    	}    	
    	return object;
	}
    
}
