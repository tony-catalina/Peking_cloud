/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.servers.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import awd.cloud.internet.servers.server.entity.TzggEntity;
import awd.cloud.internet.servers.server.service.TzggService;
import awd.framework.common.controller.GenericEntityController;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.api.CrudService;
import awd.framework.expands.logging.AccessLogger;
import io.swagger.annotations.Api;


@RestController
@RefreshScope
@RequestMapping("/tzgg")
@AccessLogger("通知公告")
@Api(value = "Tzgg") 
public class TzggController implements GenericEntityController<TzggEntity, String, QueryParamEntity, TzggEntity> {

	private TzggService tzggService;


	@Override
    public TzggEntity modelToEntity(TzggEntity model, TzggEntity entity) {
        return model;
    }

    @Autowired
    public void setTzggService(TzggService tzggService) {
        this.tzggService = tzggService;
    }
	 
	@Override
	public CrudService<TzggEntity, String> getService() {
		return tzggService;
	}

	
	@Override
	public ResponseMessage<String> saveOrUpdate(@RequestBody TzggEntity data) {
		System.err.println(JSON.toJSONString(data)+"=======");
		// TODO Auto-generated method stub
		return GenericEntityController.super.saveOrUpdate(data);
	}
	
	
	

}
