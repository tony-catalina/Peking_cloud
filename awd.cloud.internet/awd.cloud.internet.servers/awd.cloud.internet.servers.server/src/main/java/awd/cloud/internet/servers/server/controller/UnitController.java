/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.servers.server.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import awd.framework.expands.logging.AccessLogger;
import awd.framework.common.controller.GenericEntityController;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.api.CrudService;
import awd.cloud.internet.servers.server.entity.UnitEntity;
import awd.cloud.internet.servers.server.service.UnitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@RestController
@RefreshScope
@RequestMapping("/cloud_unit")
@AccessLogger("Unit")
@Api(value = "Unit") 
public class UnitController implements GenericEntityController<UnitEntity, String, QueryParamEntity, UnitEntity> {

	private UnitService unitService;


	@Override
    public UnitEntity modelToEntity(UnitEntity model, UnitEntity entity) {
        return model;
    }

    @Autowired
    public void setUnitService(UnitService unitService) {
        this.unitService = unitService;
    }
	 
	@Override
	public CrudService<UnitEntity, String> getService() {
		return unitService;
	}

	@Override
	@OpenAPI
	@ApiOperation("自定义查询")
	@GetMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<PagerResult<UnitEntity>> list(HttpServletRequest arg0, QueryParamEntity arg1) {
		return ResponseMessage.ok(unitService.selectPager(arg1));
	}

	@Override
	@ApiOperation("新增")
	@PostMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> save(@RequestBody UnitEntity data) {
		return GenericEntityController.super.save(data);
	}

	@Override
	@ApiOperation("根据id更新")
	@PutMapping(path = {"/{id:.+}"})
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage updateByPrimaryKey(@PathVariable String id,@RequestBody UnitEntity data) {
		return GenericEntityController.super.updateByPrimaryKey(id, data);
	}
	
	@ApiOperation("根据dwbh查询")
	@PostMapping("/queryByDwbh")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> queryByDwbh(String dwbh) {
		return ResponseMessage.ok(unitService.queryByDwbh(dwbh));
	}
	
	@ApiOperation("根据dwbh和type查询dwmc")
	@PostMapping("/getDwmcByDwbhAndType")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
		@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> getDwmcByDwbhAndType(String dwbh,String type) {
		return ResponseMessage.ok(unitService.getDwmcByDwbhAndType(dwbh,type));
	}
	
	@ApiOperation("根据dwbh和type查询dwmc和dwdz")
	@PostMapping("/getDwmcDwdzByDwbhAndType")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
		@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<UnitEntity> getDwmcDwdzByDwbhAndType(String dwbh,String type) {
		return ResponseMessage.ok(unitService.getDwmcDwdzByDwbhAndType(dwbh,type));
	}
	
	@ApiOperation("查询看守所")
	@PostMapping("/queryKss")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<List<UnitEntity>> queryKss(HttpServletResponse response) {
			return ResponseMessage.ok(unitService.queryKss()); 
	}
	
	@ApiOperation("查询派出所")
	@PostMapping("/queryPcs")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<List<UnitEntity>> queryPcs(HttpServletResponse response) {
			return ResponseMessage.ok(unitService.queryPcs()); 
	}

	@Override
	@PatchMapping
	@AccessLogger("{save_or_update}")
	@ApiOperation("保存数据,如果数据不存在则新增一条数据")
	@OpenAPI
	public ResponseMessage<String> saveOrUpdate(@RequestBody UnitEntity data) {
		return GenericEntityController.super.saveOrUpdate(data);
	}
	
	
	
}
