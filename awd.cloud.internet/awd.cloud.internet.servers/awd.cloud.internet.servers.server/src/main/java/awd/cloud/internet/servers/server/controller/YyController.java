/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.servers.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import awd.cloud.internet.servers.server.entity.YyEntity;
import awd.cloud.internet.servers.server.service.YyService;
import awd.framework.common.controller.GenericEntityController;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.api.CrudService;
import awd.framework.expands.logging.AccessLogger;
import io.swagger.annotations.Api;


@RestController
@RefreshScope
@RequestMapping("/tsyy")
@AccessLogger("Yy")
@Api(value = "Yy") 
public class YyController implements GenericEntityController<YyEntity, String, QueryParamEntity, YyEntity> {

	private YyService yyService;


	@Override
    public YyEntity modelToEntity(YyEntity model, YyEntity entity) {
        return model;
    }

    @Autowired
    public void setYyService(YyService yyService) {
        this.yyService = yyService;
    }
	 
	@Override
	public CrudService<YyEntity, String> getService() {
		return yyService;
	}

	@Override
	public ResponseMessage<String> save(@RequestBody YyEntity data) {
		// TODO Auto-generated method stub
		System.err.println(JSON.toJSONString(data));
		System.err.println("data.id"+data.getId());
		System.err.println("data.getJsbh"+data.getJsbh());
		return GenericEntityController.super.save(data);
	}
	
	
	

}
