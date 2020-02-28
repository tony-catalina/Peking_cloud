/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.servers.server.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import awd.cloud.internet.servers.server.entity.FcjlEntity;
import awd.cloud.internet.servers.server.entity.SmsyEntity;
import awd.cloud.internet.servers.server.model.SmsyAndFcjlModel;
import awd.cloud.internet.servers.server.service.FcjlService;
import awd.cloud.internet.servers.server.service.SmsyService;
import awd.cloud.internet.servers.server.utils.StringUtils;
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
@RequestMapping("/cloud_smsy")
@AccessLogger("Smsy")
@Api(value = "Smsy") 
public class SmsyController implements GenericEntityController<SmsyEntity, String, QueryParamEntity, SmsyEntity> {

	private SmsyService smsyService;
	
	@Autowired
	private FcjlService fcjlService;


	@Override
    public SmsyEntity modelToEntity(SmsyEntity model, SmsyEntity entity) {
        return model;
    }

    @Autowired
    public void setSmsyService(SmsyService smsyService) {
        this.smsyService = smsyService;
    }
	 
	@Override
	public CrudService<SmsyEntity, String> getService() {
		return smsyService;
	}

	@Override
	@OpenAPI
	@ApiOperation("自定义查询")
	@GetMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<PagerResult<SmsyEntity>> list(HttpServletRequest arg0, QueryParamEntity arg1) {
		return ResponseMessage.ok(smsyService.selectPager(arg1));
	}

	@Override
	@ApiOperation("新增")
	@PostMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> save(@RequestBody SmsyEntity data) {
		return GenericEntityController.super.save(data);
	}

	@Override
	@ApiOperation("根据id更新")
	@PutMapping(path = {"/{id:.+}"})
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage updateByPrimaryKey(@PathVariable String id,@RequestBody SmsyEntity data) {
		return GenericEntityController.super.updateByPrimaryKey(id, data);
	}
	
	@ApiOperation("根据ids批量更新")
	@PostMapping("/updateSmsyList")
    @ResponseBody
    ResponseMessage<String> updateSmsyList(@RequestParam(value = "ids") String[] ids,
    			@RequestBody SmsyAndFcjlModel smsyAndFcjlModel){
		SmsyEntity smsyEntity = new SmsyEntity();
		BeanUtils.copyProperties(smsyAndFcjlModel.getSmsyModel(),smsyEntity);
		FcjlEntity fcjlEntity = new FcjlEntity();
		BeanUtils.copyProperties(smsyAndFcjlModel.getFcjlModel(),fcjlEntity);
		
		System.err.println("fcjlEntity---"+JSON.toJSONString(fcjlEntity));
		System.err.println("smsyEntity---"+JSON.toJSONString(smsyEntity));
		if (!"".equals(fcjlEntity.getCph()) || "4".equals(smsyEntity.getPhase())) {	//这个是监管单位发车请求
			String cph = fcjlEntity.getCph();
			long timestamp = new Date().getTime();
	    	String fcuuid = String.valueOf(timestamp) + Integer.valueOf(cph.toString().hashCode());
	    	smsyEntity.setFcuuid(fcuuid);
	    	System.err.println(fcuuid);
	    	fcjlEntity.setFcuuid(fcuuid);
	    	fcjlService.insert(fcjlEntity);
		}
		int reNum = 0;
		for (String id : ids) {
			smsyEntity.setId(id);
			int num = smsyService.updateByPk(id, smsyEntity);
			reNum = num + reNum;
		}
		return ResponseMessage.ok(String.valueOf(reNum));
	}
	
	
	@ApiOperation("根据ids数组字符串 设置上门收押撤回")
	@PostMapping("/pcsZdchSmsy")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
		@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<Map<String, Object>> pcsZdchSmsy(@RequestParam("ids") String idString,@RequestParam("chyy")String chyy){//办案单位名称
		return ResponseMessage.ok(smsyService.pcsZdchSmsy(idString,chyy));
	}

	@OpenAPI
	@ApiOperation("删除信息")
	@DeleteMapping(path = {"/{id:.+}"})
	@Override
	public ResponseMessage<Integer> deleteByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.deleteByPrimaryKey(id);
	}
	
	
	
	
	
}