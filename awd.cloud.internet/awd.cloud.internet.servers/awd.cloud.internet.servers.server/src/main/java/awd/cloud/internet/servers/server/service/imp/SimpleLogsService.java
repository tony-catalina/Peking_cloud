package awd.cloud.internet.servers.server.service.imp;

import awd.cloud.internet.servers.server.dao.LogsDao;
import awd.cloud.internet.servers.server.entity.LogsEntity;
import awd.cloud.internet.servers.server.service.LogsService;
import awd.framework.common.core.IDGenerator;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.entity.Entity;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.service.simple.GenericEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service("logsService")
public class SimpleLogsService extends GenericEntityService<LogsEntity,String>implements LogsService{

        @Autowired
        private LogsDao logsDao;

        @Override
        protected IDGenerator<String> getIDGenerator(LogsEntity entity){
                String jsbh="000000000"+System.currentTimeMillis();
                return()->{
                return getSEQUID(jsbh);
            };
        }

        @Override
        public LogsDao getDao(){
                return logsDao;
        }
        @Override
        @UseDataSource("write_ds")
        public int deleteByPk(String pk){
                return super.deleteByPk(pk);
        }

        @Override
        @UseDataSource("write_ds")
        public String getSEQUID(String jsbh){
                return super.getSEQUID(jsbh);
        }

        @Override
        @UseDataSource("write_ds")
        public String insert(LogsEntity entity){
                return super.insert(entity);
        }

        @Override
        @UseDataSource("write_ds")
        public String saveOrUpdate(LogsEntity entity){
                return super.saveOrUpdate(entity);
        }

        @Override
        @UseDataSource("read_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public List<LogsEntity> selectByPk(List<String> id){
        return super.selectByPk(id);
        }

        @Override
        @UseDataSource("read_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public LogsEntity selectByPk(String pk){
        return super.selectByPk(pk);
        }

        @Override
        @UseDataSource("read_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public PagerResult<LogsEntity> selectPager(Entity arg0){
        return super.selectPager(arg0);
        }

        @Override
        @UseDataSource("write_ds")
        protected int updateByPk(LogsEntity entity){
        return super.updateByPk(entity);
        }

        @Override
        @UseDataSource("write_ds")
        public int updateByPk(List<LogsEntity> data){
        return super.updateByPk(data);
        }

        @Override
        @UseDataSource("write_ds")
        public int updateByPk(String pk,LogsEntity entity){
        return super.updateByPk(pk,entity);
        }
}
