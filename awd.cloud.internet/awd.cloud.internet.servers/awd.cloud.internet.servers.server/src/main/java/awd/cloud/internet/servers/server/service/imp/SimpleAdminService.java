package awd.cloud.internet.servers.server.service.imp;
import awd.framework.common.entity.PagerResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import awd.framework.common.entity.Entity;
import awd.framework.common.core.IDGenerator;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.service.simple.GenericEntityService;
import awd.cloud.internet.servers.server.entity.AdminEntity;
import awd.cloud.internet.servers.server.dao.AdminDao;
import awd.cloud.internet.servers.server.service.AdminService;
import java.util.Calendar;
import java.util.List;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Service("adminService")
public class SimpleAdminService extends GenericEntityService<AdminEntity,String>implements AdminService{

        @Autowired
        private AdminDao adminDao;

        @Override
        protected IDGenerator<String> getIDGenerator(AdminEntity entity){
                String jsbh=entity.getJsbh();
                return()->{
                return getSEQUID(jsbh);
            };
        }

        @Override
        public AdminDao getDao(){
                return adminDao;
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
        public String insert(AdminEntity entity){
                return super.insert(entity);
        }

        @Override
        @UseDataSource("write_ds")
        public String saveOrUpdate(AdminEntity entity){
                return super.saveOrUpdate(entity);
        }

        @Override
        @UseDataSource("read_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public List<AdminEntity> selectByPk(List<String> id){
        return super.selectByPk(id);
        }

        @Override
        @UseDataSource("read_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public AdminEntity selectByPk(String pk){
        return super.selectByPk(pk);
        }

        @Override
        @UseDataSource("read_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public PagerResult<AdminEntity> selectPager(Entity arg0){
        return super.selectPager(arg0);
        }

        @Override
        @UseDataSource("write_ds")
        protected int updateByPk(AdminEntity entity){
        return super.updateByPk(entity);
        }

        @Override
        @UseDataSource("write_ds")
        public int updateByPk(List<AdminEntity> data){
        return super.updateByPk(data);
        }

        @Override
        @UseDataSource("write_ds")
        public int updateByPk(String pk,AdminEntity entity){
        return super.updateByPk(pk,entity);
        }
        
        /**
         * //清空所有数据
         * @return
         */
        @Override
        @UseDataSource("write_ds")
        public void deleteTable(){
        	adminDao.deleteTable();
        }
        
        /**
         * 	清空admin表，并把缓存中获取的组织机构信息 存入admin表
         */
        public void refreshAdminUseCache() {
        	deleteTable();	
        	//获取缓存中的
		}
        
}
