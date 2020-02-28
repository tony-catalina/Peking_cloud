/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.servers.server.service;

import awd.cloud.internet.servers.server.entity.FileEntity;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.service.api.CrudService;
import org.springframework.web.multipart.MultipartFile;

public interface FileService extends CrudService<FileEntity, String> {
    FileEntity queryByUuid(String uuid);

    ResponseMessage<String> upload(MultipartFile file, String UUID);

    ResponseMessage<String> uploads(MultipartFile[] files, String UUID);
}
