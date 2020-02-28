package awd.cloud.internet.servers.server.service.imp;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import awd.cloud.internet.servers.server.config.CacheConfig;
import awd.cloud.internet.servers.server.dao.UnitDao;
import awd.cloud.internet.servers.server.entity.UnitEntity;
import awd.cloud.internet.servers.server.service.UnitService;
import awd.framework.common.core.IDGenerator;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.entity.Entity;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.service.simple.GenericEntityService;
import awd.framework.common.utils.StringUtils;


@Service("unitService")
public class SimpleUnitService extends GenericEntityService<UnitEntity,String>implements UnitService{

        @Autowired
        private UnitDao unitDao;

        @Autowired
    	private CacheConfig cacheConfig;
        
        @Override
        protected IDGenerator<String> getIDGenerator(UnitEntity entity){
                String jsbh="000000000";
                return()->{
                return getSEQUID(jsbh);
            };
        }

        @Override
        public UnitDao getDao(){
                return unitDao;
        }
        
        @Override
        public UnitEntity getUnitCacheByDwbh(String dwbh) {
			UnitEntity entity = null;
			try {
				Object object = cacheConfig.getCacheByKey(cacheConfig.CAHCE_UNIT,dwbh);
				entity = (UnitEntity) object;
				if (!StringUtils.isNullOrEmpty(entity)) {
					return entity;
				}
			} catch (Exception e) {
				//e.printStackTrace();
				entity = new UnitEntity();
			}
			return entity;
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
        public String insert(UnitEntity entity){
                entity.setCreatetime(Calendar.getInstance().getTime());
                return super.insert(entity);
        }

        @Override
        @UseDataSource("write_ds")
        public String saveOrUpdate(UnitEntity entity){
                return super.saveOrUpdate(entity);
        }

        @Override
        @UseDataSource("read_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public List<UnitEntity> selectByPk(List<String> id){
        return super.selectByPk(id);
        }

        @Override
        @UseDataSource("read_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public UnitEntity selectByPk(String pk){
        return super.selectByPk(pk);
        }

        @Override
        @UseDataSource("read_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public PagerResult<UnitEntity> selectPager(Entity arg0){
        return super.selectPager(arg0);
        }

        @Override
        @UseDataSource("write_ds")
        protected int updateByPk(UnitEntity entity){
        return super.updateByPk(entity);
        }

        @Override
        @UseDataSource("write_ds")
        public int updateByPk(List<UnitEntity> data){
        return super.updateByPk(data);
        }

        @Override
        @UseDataSource("write_ds")
        public int updateByPk(String pk,UnitEntity entity){
        entity.setUpdatetime(Calendar.getInstance().getTime());
        return super.updateByPk(pk,entity);
        }

		@Override
		@UseDataSource("read_ds")
		public String queryByDwbh(String dwbh) {
			
			UnitEntity unit = getUnitCacheByDwbh(dwbh);
			if (!StringUtils.isNullOrEmpty(unit.getType())) {
				return unit.getType();
			}
			if(unitDao.queryByDwbh(dwbh)==null) {
				return "0";
			}else {
				return unitDao.queryByDwbh(dwbh);
			}
		}

		@Override
		public List<UnitEntity> queryKss() {
			return unitDao.queryKss();
		}

		@Override
		public List<UnitEntity> queryPcs() {
			return unitDao.queryPcs();
		}

		@Override
		@UseDataSource("read_ds")
		public String getDwmcByDwbhAndType(String dwbh, String type) {
			return unitDao.getDwmcByDwbhAndType(dwbh,type);
		}
		
		@Override
		@UseDataSource("read_ds")
		public UnitEntity getDwmcDwdzByDwbhAndType(String dwbh, String type) {
			return unitDao.getDwmcDwdzByDwbhAndType(dwbh,type);
		}
		
        /**
         * //清空所有数据
         * @return
         */
        @Override
        @UseDataSource("write_ds")
        public void deleteTable(){
        	unitDao.deleteTable();
        }
}
