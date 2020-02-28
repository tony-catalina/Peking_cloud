package awd.cloud.internet.servers.server.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import awd.cloud.internet.servers.server.config.CacheConfig;
import awd.cloud.internet.servers.server.entity.UnitEntity;
import awd.cloud.internet.servers.server.model.UserDetailModel;
import awd.cloud.internet.servers.server.service.AdminService;
import awd.cloud.internet.servers.server.service.InterfaceService;
import awd.cloud.internet.servers.server.service.UserinfoService;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.utils.OpenAPI;
import awd.framework.expands.logging.AccessLogger;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;



@RestController
@RefreshScope
@RequestMapping("/interface")
@AccessLogger("Interface")
@Api(value = "Interface") 
public class InterfaceController {

	@Autowired
	private InterfaceService interfaceService;
	
	@Autowired
	private CacheConfig cacheConfig;
	
	@Autowired
	private AdminService adminService;
    
	@Autowired
	private UserinfoService userinfoService;
	
	@PostConstruct
    //@Bean
	@OpenAPI
	@GetMapping("/init")
	@ApiOperation("初始化")
    public ResponseMessage init() {
    	interfaceService.init();
    	return ResponseMessage.ok();
    }
    
    @OpenAPI
    @RequestMapping("/getToken")
    public String getToken(HttpServletRequest request) {
    	
    	System.err.println(JSON.toJSON(request.getParameterMap()));
    	String token=request.getParameter("token");
    	return request.getParameter("echostr");
    }
    
    @OpenAPI
    @GetMapping("/get")
    @ApiOperation("测试")
    public ResponseMessage test(@RequestParam("type") String type,@RequestParam(value = "code",required = false) String code){
    	if ("0".equals(type)) {
    		cacheConfig.setOrgCache();
    		return ResponseMessage.ok();
		}else if ("1".equals(type)) {
			Object object = cacheConfig.getCacheByKey(cacheConfig.CAHCE_ORG, code);
			return ResponseMessage.ok(object);
		}else if ("2".equals(type)) {
			Cache cache = cacheConfig.getCacheManager().getCache(cacheConfig.CAHCE_ORG);
			ValueWrapper valueWrapper = cache.get(cacheConfig.CAHCE_ORG);
			Object object = null;
    		if(valueWrapper!=null) {
    			object = valueWrapper.get();
    		}
			return ResponseMessage.ok(object);
		}else if ("3".equals(type)) {
			return ResponseMessage.ok(interfaceService.getUserByOrgCode(code));
		}else if ("4".equals(type)) {
			return ResponseMessage.ok(interfaceService.getUserByCache(code));
		}else if ("5".equals(type)) {
			Cache cache = cacheConfig.getUserCacheManager().getCache(cacheConfig.CAHCE_USER);
			cache.evict(code);
			return ResponseMessage.ok();
		}else if ("6".equals(type)) {
			String ORG_CODE = "";
			System.err.println("code---"+code);
			try {
				ORG_CODE = (String) cacheConfig.getCacheByKey(cacheConfig.CAHCE_ORG, code);
			} catch (Exception e) {
				System.err.println("根据 FULL_NAME 从缓存中获取 ORG_CODE 失败");
			}
			return ResponseMessage.ok("ORG_CODE---"+ORG_CODE);
		}else if ("7".equals(type)) {
			UnitEntity unit = null;
    		try {
    			unit = (UnitEntity) cacheConfig.getCacheByKey(CacheConfig.CAHCE_UNIT, code);
    		} catch (Exception e) {
    			System.err.println("根据 unit_code 从缓存中获取 UNIT 失败");
    		}
    		return ResponseMessage.ok(unit);
		}
    	//interfaceService.setOrgCache();
		return ResponseMessage.ok();
    }
    
	@OpenAPI
	@PostMapping("/getRootOrg")
	@ApiOperation("获取根级组织机构信息")
	public ResponseMessage<Map<String, Object>> getRootOrg(){
		return ResponseMessage.ok(interfaceService.getRootOrg());
	}
	
	@OpenAPI
	@PostMapping("/getOrgsByParentCode")
	@ApiOperation("根据根级组织机构信息获取下级组织机构信息")
	public ResponseMessage<List<Map<String, Object>>> getOrgsByParentCode(@RequestParam("rootCode") String rootCode){
		System.err.println("rootCode---"+rootCode);
		return ResponseMessage.ok(interfaceService.getOrgsByParentCode(rootCode));
	}
	
	@OpenAPI
	@PostMapping("/getUserByOrgCode")
	@ApiOperation("根据机构代码 获取机构人员的数据")
	public ResponseMessage<List<Map<String, Object>>> getUserByOrgCode(@RequestParam("orgCode") String orgCode){
		System.err.println("orgCode---"+orgCode);
		return ResponseMessage.ok(interfaceService.getUserByOrgCode(orgCode));
	}
	
	@OpenAPI
	@PostMapping("/getOrgInfo")
	@ApiOperation("获取orgCode获取组织机构信息")
	public ResponseMessage<Map<String, Object>> getOrgInfo(@RequestParam("orgCode") String orgCode){
		System.err.println("orgCode---"+orgCode);
		return ResponseMessage.ok(interfaceService.getOrgInfo(orgCode));
	}
	
	@OpenAPI
	@PostMapping("/getUserInfo")
	@ApiOperation("根据userCode获取人员信息")
	public ResponseMessage<Map<String, Object>> getUserInfo(@RequestParam("userCode") String userCode){
		return ResponseMessage.ok(interfaceService.getUserInfo(userCode));
	}
	
	@OpenAPI
	@PostMapping("/getResourceByAppCode")
	@ApiOperation("获取所有资源数据")
	public ResponseMessage<List<Map<String, Object>>> getResourceByAppCode(){
		return ResponseMessage.ok(interfaceService.getResourceByAppCode());
	}
	
	@OpenAPI
	@PostMapping("/getRoleByAppCode")
	@ApiOperation("获取所有角色数据")
	public ResponseMessage<List<Map<String, Object>>> getRoleByAppCode(){
		return ResponseMessage.ok(interfaceService.getRoleByAppCode());
	}
	
	@OpenAPI
	@PostMapping("/getUserModelFrom4A")
	@ApiOperation("根据userinfoEntity 获取人员信息")
	public ResponseMessage<UserDetailModel> getUserModelFrom4A(@RequestBody UserDetailModel userinfoModel){
		return ResponseMessage.ok(interfaceService.getUserModelFrom4A(userinfoModel));
	}
	
	/**
	 * 
	 * @param type	0查询 办案单位，1查询 监管单位，null或者 其他查询办案单位
	 * @return
	 */
	@OpenAPI
	@PostMapping("/getOrgList")
    @ApiOperation("从缓存中获取所有的组织机构信息")
    public ResponseMessage<List<Map<String, Object>>> getOrgListByCache(@RequestParam(value = "type",required = false) String type){
		return ResponseMessage.ok(interfaceService.getOrgListByCache(type));
    }
	
	/**
	 * 清除远程服务器用户缓存
	 */
	@OpenAPI
	@PostMapping("/deleteUserCacheByUserid")
	@ApiOperation("清除远程服务器用户缓存")
	public ResponseMessage<String> deleteUserCacheByUserid(@RequestParam("userid") String userid) {
		Cache cache;
		try {
			cache = cacheConfig.getUserCacheManager().getCache(cacheConfig.CAHCE_USER);
			cache.evict(userid);
			//userinfoService.deleteByPk(userid);	//我吧id全都设置成userid，然后 清欢存再把表数据删掉，下次登录就重新添加
		} catch (Exception e) {
			//e.printStackTrace();
			return ResponseMessage.error("清除缓存失败");
		}
		return ResponseMessage.ok("清除缓存成功");
		
	}
	
	
}
