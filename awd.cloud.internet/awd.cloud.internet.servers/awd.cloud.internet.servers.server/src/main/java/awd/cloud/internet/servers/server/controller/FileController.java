/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.servers.server.controller;

import awd.cloud.internet.servers.server.entity.FileEntity;
import awd.cloud.internet.servers.server.entity.SmsyEntity;
import awd.cloud.internet.servers.server.service.FileService;
import awd.cloud.internet.servers.server.service.SmsyService;
import awd.framework.common.controller.GenericEntityController;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.core.param.TermType;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.api.CrudService;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import awd.framework.expands.logging.AccessLogger;

import com.alibaba.fastjson.JSON;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RefreshScope
@RequestMapping("/cloud_file")
@AccessLogger("File")
@Api(value = "File")
public class FileController implements GenericEntityController<FileEntity, String, QueryParamEntity, FileEntity> {

    private FileService fileService;

    @Autowired
    private SmsyService smsyService;
    
    @Override
    public FileEntity modelToEntity(FileEntity model, FileEntity entity) {
        return model;
    }

    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public CrudService<FileEntity, String> getService() {
        return fileService;
    }

    @Override
    @OpenAPI
    @ApiOperation("自定义查询")
    @GetMapping
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<PagerResult<FileEntity>> list(HttpServletRequest arg0, QueryParamEntity arg1) {
        return ResponseMessage.ok(fileService.selectPager(arg1));
    }

    @ApiOperation("根据UUid查询文件")
    @PostMapping("/queryByUuid")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<FileEntity> queryByUuid(@RequestParam("UUID") String uuid) { //uuid
        return ResponseMessage.ok(fileService.queryByUuid(uuid));
    }
    
	@ApiOperation("根据文件的id查询一个文件")
	@GetMapping(path = {"/{id:.+}"})
	@AccessLogger("{get_by_id}")
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    @Override
	public ResponseMessage<FileEntity> getByPrimaryKey(String id) {
		return GenericEntityController.super.getByPrimaryKey(id);
	}

	@ApiOperation("根据人员的id查询多个文件")
    @PostMapping("/queryFilesByid")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
    	@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<List<FileEntity>> queryFilesByid(@RequestParam("id") String uuid) { //uuid
    	QueryParamEntity querySmsyEntity = new QueryParamEntity();
    	
    	
    	QueryParamEntity qEntity = new QueryParamEntity();
    	qEntity.setPaging(false);
    	Set<String> excludes=new HashSet<String>();
    	excludes.add("file");
    	qEntity.setExcludes(excludes);
    	qEntity.and("uuid", TermType.eq, uuid)
    		.and("state", TermType.eq, "R2");
    	return ResponseMessage.ok(fileService.select(qEntity));
    }

    @ApiOperation("新增法律文书")
    @PostMapping("/upload")
    @OpenAPI
    public ResponseMessage<String> singleFileUpload(@RequestPart("file") MultipartFile file,
                                                    @RequestParam("UUID") String UUID) {

        if (file.isEmpty() || StringUtils.isNullOrEmpty(UUID)) {
            return ResponseMessage.error("参数错误！");
        }
        return fileService.upload(file, UUID);
    }

    @ApiOperation("批量新增法律文书")
    @PostMapping("/uploads")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> FileUploads(@RequestParam("file") MultipartFile[] files,
                                               @RequestParam("UUID") String UUID) {

        if (files.length == 0 || StringUtils.isNullOrEmpty(UUID)) {
            return ResponseMessage.error("参数错误！");
        }

        return fileService.uploads(files, UUID);
    }

    @ApiOperation("删除法律文书")
    @PostMapping("/deleteFile")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> fileDelete(@RequestParam("path") String path,
                                              @RequestParam("suffix")String suffix,
                                              @RequestParam("id")String id) {

        if (StringUtils.isNullOrEmpty(path)) {
            return ResponseMessage.error("文件目录为空！");
        }
        File file = new File(path + "/" + id + "." + suffix);
        if (file.exists()) {
            if (file.delete()) {
                fileService.deleteByPk(id);
                return ResponseMessage.ok("文件删除成功！");
            } else {
                return ResponseMessage.error("文件删除失败");
            }
        } else {
            return ResponseMessage.error("文件不存在！");
        }
    }
}
