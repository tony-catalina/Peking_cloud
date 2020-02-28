/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.servers.server.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import awd.cloud.internet.servers.server.entity.UnitcommonlyEntity;
import awd.cloud.internet.servers.server.service.UnitcommonlyService;
import awd.framework.common.controller.GenericEntityController;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.api.CrudService;
import awd.framework.common.utils.OpenAPI;
import awd.framework.expands.logging.AccessLogger;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@RestController
@RefreshScope
@RequestMapping("/cloud_unitcommonly")
@AccessLogger("Unitcommonly")
@Api(value = "Unitcommonly") 
public class UnitcommonlyController implements GenericEntityController<UnitcommonlyEntity, String, QueryParamEntity, UnitcommonlyEntity> {

	private UnitcommonlyService unitcommonlyService;


	@Override
    public UnitcommonlyEntity modelToEntity(UnitcommonlyEntity model, UnitcommonlyEntity entity) {
        return model;
    }

    @Autowired
    public void setUnitcommonlyService(UnitcommonlyService unitcommonlyService) {
        this.unitcommonlyService = unitcommonlyService;
    }
	 
	@Override
	public CrudService<UnitcommonlyEntity, String> getService() {
		return unitcommonlyService;
	}

	@Override
	@OpenAPI
	@ApiOperation("自定义查询")
	@GetMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<PagerResult<UnitcommonlyEntity>> list(HttpServletRequest arg0, QueryParamEntity arg1) {
		return ResponseMessage.ok(unitcommonlyService.selectPager(arg1));
	}

	@Override
	@ApiOperation("新增")
	@PostMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> save(@RequestBody UnitcommonlyEntity data) {
		return GenericEntityController.super.save(data);
	}

	@Override
	@ApiOperation("根据id更新")
	@PutMapping(path = {"/{id:.+}"})
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage updateByPrimaryKey(@PathVariable String id,@RequestBody UnitcommonlyEntity data) {
		return GenericEntityController.super.updateByPrimaryKey(id, data);
	}
	

	@Override
	@PatchMapping
	@AccessLogger("{save_or_update}")
	@ApiOperation("保存数据,如果数据不存在则新增一条数据")
	@OpenAPI
	public ResponseMessage<String> saveOrUpdate(@RequestBody UnitcommonlyEntity data) {
		return GenericEntityController.super.saveOrUpdate(data);
	}
	
	@PostMapping("/setCommonlyUnit")
	@ApiOperation("设置常用不常用标识")
	@OpenAPI
	public ResponseMessage<String> setCommonlyUnit(@RequestParam("userid") String userid,
			@RequestParam("dwbh") String dwbh,
			@RequestParam("option") String option) {
		String re = unitcommonlyService.setCommonlyUnit(userid, dwbh, option);
		return ResponseMessage.ok(re);
	}
	
	
}
