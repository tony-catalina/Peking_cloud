package awd.cloud.internet.servers.server.service.imp;

import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.entity.PagerResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import awd.framework.common.entity.Entity;
import awd.framework.common.core.IDGenerator;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.service.simple.GenericEntityService;
import awd.cloud.internet.servers.server.entity.FileEntity;
import awd.cloud.internet.servers.server.dao.FileDao;
import awd.cloud.internet.servers.server.service.FileService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service("fileService")
public class SimpleFileService extends GenericEntityService<FileEntity, String> implements FileService {

    @Autowired
    private FileDao fileDao;


    @Value("${flws.location}")
    private String UPLOADED_FOLDER;

    @Override
    protected IDGenerator<String> getIDGenerator(FileEntity entity) {
        String jsbh = "000000000";
        return () -> {
            return getSEQUID(jsbh);
        };
    }

    @Override
    public FileDao getDao() {
        return fileDao;
    }

    @Override
    @UseDataSource("write_ds")
    public int deleteByPk(String pk) {
        return super.deleteByPk(pk);
    }

    @Override
    @UseDataSource("write_ds")
    public String getSEQUID(String jsbh) {
        return super.getSEQUID(jsbh);
    }

    @Override
    @UseDataSource("write_ds")
    public String insert(FileEntity entity) {
        return super.insert(entity);
    }

    @Override
    @UseDataSource("write_ds")
    public String saveOrUpdate(FileEntity entity) {
        return super.saveOrUpdate(entity);
    }

    @Override
    @UseDataSource("read_ds")
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<FileEntity> selectByPk(List<String> id) {
        return super.selectByPk(id);
    }

    @Override
    @UseDataSource("read_ds")
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public FileEntity selectByPk(String pk) {
        return super.selectByPk(pk);
    }

    @Override
    @UseDataSource("read_ds")
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public PagerResult<FileEntity> selectPager(Entity arg0) {
        return super.selectPager(arg0);
    }

    @Override
    @UseDataSource("write_ds")
    protected int updateByPk(FileEntity entity) {
        return super.updateByPk(entity);
    }

    @Override
    @UseDataSource("write_ds")
    public int updateByPk(List<FileEntity> data) {
        return super.updateByPk(data);
    }

    @Override
    @UseDataSource("write_ds")
    public int updateByPk(String pk, FileEntity entity) {
        return super.updateByPk(pk, entity);
    }

    @Override
    public FileEntity queryByUuid(String uuid) {
        return fileDao.queryByUuid(uuid);
    }

    @Override
    public ResponseMessage<String> upload(MultipartFile file, String UUID) {
        try {
            byte[] bytes = file.getBytes();
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
            ;
            FileEntity fileEntity = new FileEntity();
            //fileEntity.setFile(bytes);
            fileEntity.setFilepath(UPLOADED_FOLDER + UUID);
            fileEntity.setUuid(UUID);
            fileEntity.setSuffix(suffix);
            String id = this.insert(fileEntity);
            File f = new File(UPLOADED_FOLDER + UUID);
            if (!f.exists()) {
                f.mkdir();
            }
            Path path = Paths.get(UPLOADED_FOLDER + UUID + "/" + id + "." + suffix);
            Files.write(path, bytes);
            fileEntity.setFilepath(UPLOADED_FOLDER + UUID + "/" + id + "." + suffix);
            this.updateByPk(fileEntity);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println();
            return ResponseMessage.error("文件保存失败");
        }
        return ResponseMessage.ok("文件保存成功");
    }

    @Override
    public ResponseMessage<String> uploads(MultipartFile[] files, String UUID) {
        String content = "";
        for (MultipartFile file : files) {
            if (file.getSize() > 10000000) {
                content += file.getOriginalFilename() + "文件过大！";
                continue;
            }
            ResponseMessage msg = upload(file, UUID);
            if (msg.getStatus() != 200) {
                content += file.getOriginalFilename() + "保存失败！";
            }
        }
        if ("".equals(content)) {
            return ResponseMessage.ok("文件保存成功！");
        }
        return ResponseMessage.ok(content);
    }
}
