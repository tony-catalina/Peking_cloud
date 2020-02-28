/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.servers.server.controller;

import awd.cloud.internet.servers.server.entity.LogsEntity;
import awd.cloud.internet.servers.server.service.LogsService;
import awd.framework.common.controller.GenericEntityController;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.api.CrudService;
import awd.framework.common.utils.OpenAPI;
import awd.framework.expands.logging.AccessLogger;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RefreshScope
@RequestMapping("/cloud_logs")
@AccessLogger("Logs")
@Api(value = "Logs")
public class LogsController implements GenericEntityController<LogsEntity, String, QueryParamEntity, LogsEntity> {

    private LogsService logsService;


    @Override
    public LogsEntity modelToEntity(LogsEntity model, LogsEntity entity) {
        return model;
    }

    @Autowired
    public void setLogsService(LogsService logsService) {
        this.logsService = logsService;
    }

    @Override
    public CrudService<LogsEntity, String> getService() {
        return logsService;
    }

    @Override
    @OpenAPI
    @ApiOperation("自定义查询")
    @GetMapping
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<PagerResult<LogsEntity>> list(HttpServletRequest arg0, QueryParamEntity arg1) {
        return ResponseMessage.ok(logsService.selectPager(arg1));
    }

    @Override
    @ApiOperation("新增")
    @PostMapping
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> save(@RequestBody LogsEntity data) {
        return GenericEntityController.super.save(data);
    }

    @Override
    @ApiOperation("根据id更新")
    @PutMapping(path = {"/{id:.+}"})
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage updateByPrimaryKey(@PathVariable String id, @RequestBody LogsEntity data) {
        return GenericEntityController.super.updateByPrimaryKey(id, data);
    }

    @Override
    @OpenAPI
    public ResponseMessage<String> saveOrUpdate(@RequestBody LogsEntity data) {
        return GenericEntityController.super.saveOrUpdate(data);
    }

    @Override
    @OpenAPI
    public ResponseMessage<LogsEntity> getByPrimaryKey(@PathVariable String id) {
        return GenericEntityController.super.getByPrimaryKey(id);
    }

    @Override
    @OpenAPI
    public ResponseMessage<Integer> deleteByPrimaryKey(@PathVariable String id) {
        return GenericEntityController.super.deleteByPrimaryKey(id);
    }
}
