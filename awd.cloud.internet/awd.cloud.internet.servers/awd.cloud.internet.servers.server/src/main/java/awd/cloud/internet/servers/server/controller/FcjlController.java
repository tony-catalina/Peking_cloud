/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.servers.server.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import awd.cloud.internet.servers.server.entity.FcjlEntity;
import awd.cloud.internet.servers.server.service.FcjlService;
import awd.framework.common.controller.GenericEntityController;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.api.CrudService;
import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.OpenAPI;
import awd.framework.expands.logging.AccessLogger;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RefreshScope
@RequestMapping("/cloud_fcjl")
@AccessLogger("Fcjl")
@Api(value = "Fcjl") 
public class FcjlController implements GenericEntityController<FcjlEntity, String, QueryParamEntity, FcjlEntity> {

	private FcjlService fcjlService;

	@Override
    public FcjlEntity modelToEntity(FcjlEntity model, FcjlEntity entity) {
        return model;
    }

    @Autowired
    public void setFcjlService(FcjlService fcjlService) {
        this.fcjlService = fcjlService;
    }
	 
	@Override
	public CrudService<FcjlEntity, String> getService() {
		return fcjlService;
	}

	@Override
	@OpenAPI
	@ApiOperation("自定义查询")
	@GetMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<PagerResult<FcjlEntity>> list(HttpServletRequest arg0, QueryParamEntity arg1) {
		return ResponseMessage.ok(fcjlService.selectPager(arg1));
	}

	@Override
	@ApiOperation("新增")
	@PostMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> save(@RequestBody FcjlEntity data) {
		return GenericEntityController.super.save(data);
	}

	@Override
	@ApiOperation("根据id更新")
	@PutMapping(path = {"/{id:.+}"})
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage updateByPrimaryKey(@PathVariable String id,@RequestBody FcjlEntity data) {
		//return GenericEntityController.super.updateByPrimaryKey(id, data);
		return ResponseMessage.ok(fcjlService.updateFcjlWithFczt(data));
	}
	
	@Override
	@PatchMapping
	@AccessLogger("{save_or_update}")
	@ApiOperation("保存数据,如果数据不存在则新增一条数据")
	@OpenAPI
	public ResponseMessage<String> saveOrUpdate(@RequestBody FcjlEntity data) {
		return GenericEntityController.super.saveOrUpdate(data);
	}

	@Override
	@GetMapping(path = {"/{id:.+}"})
	@ApiOperation("根据主键查询数据")
	@AccessLogger("{get_by_id}")
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<FcjlEntity> getByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.getByPrimaryKey(id);
	}

	@Override
	@DeleteMapping(path = {"/{id:.+}"})
	@AccessLogger("{delete_by_primary_key}")
	@ApiOperation("根据ID删除数据")
	@ApiResponses({@ApiResponse(code = 200, message = "删除成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "要删除的数据不存在")})
	@OpenAPI
	public ResponseMessage<Integer> deleteByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.deleteByPrimaryKey(id);
	}

	@PostMapping("/updateFcjlAfterDsm")
	@ApiOperation("根据fcuuid更新发车记录的fczt")
	@OpenAPI
	public ResponseMessage<String> updateFcjlAfterDsm(@RequestParam("fcuuid") String fcuuid,
			@RequestParam("badw") String badw,
			@RequestParam("fczt") String fczt) {
		return ResponseMessage.ok(fcjlService.updateFcjlAfterDsm(fcuuid,badw,fczt));
	}
	
    @OpenAPI
    @RequestMapping(value = "/test",method = {RequestMethod.POST,RequestMethod.GET})
    public ResponseMessage<String> test(HttpServletRequest request,@RequestParam("cph") String cph) {
    	if ("".equals(cph)) {
    		cph = "沪A·66666";
		}
    	
    	long timestamp = new Date().getTime();
		System.err.println(timestamp);
    	String uuid = String.valueOf(timestamp) + Integer.valueOf(cph.toString().hashCode());
    	System.err.println(uuid);
    	return ResponseMessage.ok(uuid);
    }
	
}
