/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.servers.server.controller;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import awd.cloud.internet.servers.server.config.CacheConfig;
import awd.cloud.internet.servers.server.entity.UserinfoEntity;
import awd.cloud.internet.servers.server.model.UserDetailModel;
import awd.cloud.internet.servers.server.model.UserinfoModel;
import awd.cloud.internet.servers.server.service.UserinfoService;
import awd.framework.common.controller.GenericEntityController;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.datasource.orm.core.param.Term;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.api.CrudService;
import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import awd.framework.expands.logging.AccessLogger;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@RestController
@RefreshScope
@RequestMapping("/cloud_userinfo")
@AccessLogger("Userinfo")
@Api(value = "Userinfo") 
public class UserinfoController implements GenericEntityController<UserinfoEntity, String, QueryParamEntity, UserinfoEntity> {
	private UserinfoService userinfoService;

	@Autowired
	private CacheConfig cacheConfig;

	@Override
    public UserinfoEntity modelToEntity(UserinfoEntity model, UserinfoEntity entity) {
        return model;
    }

    @Autowired
    public void setUserinfoService(UserinfoService userinfoService) {
        this.userinfoService = userinfoService;
    }
	 
	@Override
	public CrudService<UserinfoEntity, String> getService() {
		return userinfoService;
	}

	@Override
	@OpenAPI
	@ApiOperation("自定义查询")
	@GetMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<PagerResult<UserinfoEntity>> list(HttpServletRequest arg0, QueryParamEntity arg1) {
		if(!arg1.isPaging()&&arg1.getTerms().size()==1) {
			PagerResult<UserinfoEntity> pageResult=new PagerResult();
			Term term=arg1.getTerms().get(0);
			if("wxh".equals(term.getColumn())&&!StringUtils.isNullOrEmpty(term.getValue())) {
				CacheConfig cacheConfig = CacheConfig.getInstance();
				Object object = CacheConfig.getInstance().getCacheByKey(CacheConfig.CAHCE_USER,term.getValue().toString());
				if(object!=null) {
					UserinfoEntity entity = (UserinfoEntity) object;
					List<UserinfoEntity> list=new ArrayList<UserinfoEntity>();
					System.err.println(JSONUtil.toJson(entity));
					list.add(entity);
					pageResult.setData(list);
					pageResult.setTotal(1);
				}
				return ResponseMessage.ok(pageResult);
			}	
			return ResponseMessage.ok(userinfoService.selectPager(arg1));
		}else {
			return ResponseMessage.ok(userinfoService.selectPager(arg1));
		}
		
	}

	@Override
	@ApiOperation("新增")
	@PostMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> save(@RequestBody UserinfoEntity data) {
		return ResponseMessage.ok(userinfoService.addUserInfoWithCache(data));
		//return GenericEntityController.super.save(data);
	}
	
	@ApiOperation("新增")
	@PostMapping("/registeUser")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
		@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<UserDetailModel> registeUser(@RequestBody UserinfoEntity data) {
		userinfoService.addUserInfoWithCache(data);
		return ResponseMessage.ok(cacheConfig.getUserCache(data.getUserid()));
	}

	@Override
	@ApiOperation("根据id更新")
	@PutMapping(path = {"/{id:.+}"})
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> updateByPrimaryKey(@PathVariable String id,@RequestBody UserinfoEntity data) {
		int num = userinfoService.updateUserInfoWithCache(id, data);
		if (num > 0) {
			return ResponseMessage.ok("success");
		}
		return ResponseMessage.ok("error");
	}
	
	@ApiOperation("录入人员")
	@PostMapping("/addUser")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<Integer> addUser(HttpServletRequest request,Map<String, Object> parms) {
		//插入数据 返回0或 1    1代表插入成功
		String head=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
		int num  = (int)((Math.random()*9+1)*100000);
		String body = String.valueOf(num);
		parms.put("id", head+body);
		parms.put("wxh", request.getParameter("wxh"));
		parms.put("dwbh", request.getParameter("dwbh"));
		parms.put("type", request.getParameter("type"));
		parms.put("username", request.getParameter("username"));
		parms.put("xb", request.getParameter("xb"));
		parms.put("jh", request.getParameter("jh"));
		parms.put("phone", request.getParameter("phone"));
		return ResponseMessage.ok(userinfoService.addUser(parms));
	}
	
	@ApiOperation("登录检查注册")
	@PostMapping("/loginCheck")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<UserinfoEntity> loginCheck(HttpServletRequest request,String wxh) {//返回null或者UserinfoEntity对象
		List<UserinfoEntity> list = userinfoService.loginCheck(wxh);
		if(list!=null) {
			UserinfoEntity user = list.get(0);
			request.getSession().setAttribute("user", user);
			return ResponseMessage.ok(user);
		}else {
			return ResponseMessage.error("登录失败！");
		}
		
	}
	
	@ApiOperation("用户禁用启用")
	@PostMapping("/enableUser")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "保存成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> enableUser(@RequestBody UserinfoModel model) {
		System.err.println(model.getId());
		System.err.println(model.getState());
		int result = userinfoService.enableUser(model.getId(),model.getState());
		if(result==1) {
			return ResponseMessage.ok("保存成功");
		}else {
			return ResponseMessage.error("保存失败！");
		}
		
	}
	
	@ApiOperation("审核用户")
	@PostMapping("/shUser")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "保存成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> shUser(@RequestBody UserinfoModel model) {
		System.err.println(model.getId());
		System.err.println(model.getState());
		System.err.println(model.getShbz());
		int result = userinfoService.shUser(model.getId(),model.getState(),model.getShbz());
		if(result==1) {
			return ResponseMessage.ok("保存成功");
		}else {
			return ResponseMessage.error("保存失败！");
		}
		
	}
	
}
