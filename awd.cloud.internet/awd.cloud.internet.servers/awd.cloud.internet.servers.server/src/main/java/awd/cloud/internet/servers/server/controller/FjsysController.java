/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.servers.server.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import awd.cloud.internet.servers.server.entity.FjsysEntity;
import awd.cloud.internet.servers.server.service.FjsysService;
import awd.framework.common.controller.GenericEntityController;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.api.CrudService;
import awd.framework.common.utils.OpenAPI;
import awd.framework.expands.logging.AccessLogger;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RefreshScope
@RequestMapping("/fjsys")
@AccessLogger("Fjsys")
@Api(value = "Fjsys") 
public class FjsysController implements GenericEntityController<FjsysEntity, String, QueryParamEntity, FjsysEntity> {

	private FjsysService fjsysService;


	@Override
    public FjsysEntity modelToEntity(FjsysEntity model, FjsysEntity entity) {
        return model;
    }

    @Autowired
    public void setFjsysService(FjsysService fjsysService) {
        this.fjsysService = fjsysService;
    }
	 
	@Override
	public CrudService<FjsysEntity, String> getService() {
		return fjsysService;
	}
	
	
	
	@Override
	public ResponseMessage<PagerResult<FjsysEntity>> list(HttpServletRequest arg0, QueryParamEntity arg1) {
		// TODO Auto-generated method stub
		return GenericEntityController.super.list(arg0, arg1);
	}
	

	@Override
	public ResponseMessage<String> save(FjsysEntity data) {
		// TODO Auto-generated method stub
		return GenericEntityController.super.save(data);
	}
	

	@OpenAPI
	@ApiOperation("查询监所是否存在房间记录，有则更新数据否则新增数据")
	@PostMapping("/updateFjByJsbh")
	@ResponseBody
	public ResponseMessage<?> updateFjByJsbh(HttpServletRequest request,@RequestParam("sysl")String sysl,@RequestParam("jsbh")String jsbh){
		//首先判断监所是否存在于表中
		System.err.println("jsbh:"+jsbh);
		System.err.println("sysl:"+sysl);
		QueryParamEntity qParam = new QueryParamEntity();
		qParam.and("jsbh", jsbh);
		ResponseMessage<PagerResult<FjsysEntity>> re=this.list(request, qParam);
		//不存在执行保存操作
		if(re.getResult().getTotal() == 0) {
			FjsysEntity entity = new FjsysEntity();
			entity.setJsbh(jsbh);
			entity.setSysl(sysl);
			this.save(entity);
		}else {
		//存在则更新记录
			fjsysService.updateFjBysJsbh(sysl, jsbh);
			
		}
		return ResponseMessage.ok("执行成功");
		
	}
	
	
}
