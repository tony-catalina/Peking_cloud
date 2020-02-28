package awd.cloud.internet.servers.server.service.imp;
import awd.framework.common.entity.PagerResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import awd.framework.common.entity.Entity;
import awd.framework.common.core.IDGenerator;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.service.simple.GenericEntityService;
import awd.framework.common.utils.DateTimeUtils;
import awd.framework.common.utils.StringUtils;
import awd.cloud.internet.servers.server.entity.ClglEntity;
import awd.cloud.internet.servers.server.dao.ClglDao;
import awd.cloud.internet.servers.server.service.ClglService;
import java.util.Calendar;
import java.util.List;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Service("clglService")
public class SimpleClglService extends GenericEntityService<ClglEntity,String>implements ClglService{

        @Autowired
        private ClglDao clglDao;

        @Override
        protected IDGenerator<String> getIDGenerator(ClglEntity entity){
                String jsbh=entity.getJsbh();
                return()->{
                return getSEQUID(jsbh);
            };
        }

        @Override
        public ClglDao getDao(){
                return clglDao;
        }
        @Override
        @UseDataSource("write_ds")
        public int deleteByPk(String pk){
                return super.deleteByPk(pk);
        }

        @Override
    	@UseDataSource("write_ds")
    	public String getSEQUID(String jsbh) {
    		String squid = "";
    		if (StringUtils.isNullOrEmpty(jsbh)) {
    			jsbh = "999999999";
    		}

    		String rq = DateTimeUtils.format(Calendar.getInstance().getTime(), "yyyyMMdd");
    		
    		if (jsbh.length() == 12) {
    			squid = jsbh + rq + StringUtils.lpad(this.getDao().sequid(), 3, "0");
			}else {
				squid = jsbh + rq + StringUtils.lpad(this.getDao().sequid(), 6, "0");
			}
    		return squid;
    	}
        
        
        
        @Override
        @UseDataSource("write_ds")
        public String insert(ClglEntity entity){
                return super.insert(entity);
        }

        @Override
        @UseDataSource("write_ds")
        public String saveOrUpdate(ClglEntity entity){
                return super.saveOrUpdate(entity);
        }

        @Override
        @UseDataSource("read_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public List<ClglEntity> selectByPk(List<String> id){
        return super.selectByPk(id);
        }

        @Override
        @UseDataSource("read_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public ClglEntity selectByPk(String pk){
        return super.selectByPk(pk);
        }

        @Override
        @UseDataSource("read_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public PagerResult<ClglEntity> selectPager(Entity arg0){
        return super.selectPager(arg0);
        }

        @Override
        @UseDataSource("write_ds")
        protected int updateByPk(ClglEntity entity){
        return super.updateByPk(entity);
        }

        @Override
        @UseDataSource("write_ds")
        public int updateByPk(List<ClglEntity> data){
        return super.updateByPk(data);
        }

        @Override
        @UseDataSource("write_ds")
        public int updateByPk(String pk,ClglEntity entity){
        return super.updateByPk(pk,entity);
        }
}
