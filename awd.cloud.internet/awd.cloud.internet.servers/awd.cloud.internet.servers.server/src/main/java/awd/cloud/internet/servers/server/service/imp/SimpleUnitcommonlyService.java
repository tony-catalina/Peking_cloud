package awd.cloud.internet.servers.server.service.imp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;

import awd.cloud.internet.servers.server.config.CacheConfig;
import awd.cloud.internet.servers.server.dao.UnitcommonlyDao;
import awd.cloud.internet.servers.server.entity.UnitEntity;
import awd.cloud.internet.servers.server.entity.UnitcommonlyEntity;
import awd.cloud.internet.servers.server.service.UnitcommonlyService;
import awd.cloud.internet.servers.server.utils.StringUtils;
import awd.framework.common.core.IDGenerator;
import awd.framework.common.core.param.TermType;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.entity.Entity;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.simple.GenericEntityService;
import awd.framework.common.utils.JSONUtil;


@Service("unitcommonlyService")
public class SimpleUnitcommonlyService extends GenericEntityService<UnitcommonlyEntity,String>implements UnitcommonlyService{

        @Autowired
        private UnitcommonlyDao unitcommonlyDao;

        @Override
        protected IDGenerator<String> getIDGenerator(UnitcommonlyEntity entity){
                String jsbh="000000000";
                return()->{
                return getSEQUID(jsbh);
            };
        }

        @Override
        public UnitcommonlyDao getDao(){
                return unitcommonlyDao;
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
        public String insert(UnitcommonlyEntity entity){
                entity.setCreatetime(Calendar.getInstance().getTime());
                return super.insert(entity);
        }

        @Override
        @UseDataSource("write_ds")
        public String saveOrUpdate(UnitcommonlyEntity entity){
                return super.saveOrUpdate(entity);
        }

        @Override
        @UseDataSource("read_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public List<UnitcommonlyEntity> selectByPk(List<String> id){
        return super.selectByPk(id);
        }

        @Override
        @UseDataSource("read_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public UnitcommonlyEntity selectByPk(String pk){
        return super.selectByPk(pk);
        }

        @Override
        @UseDataSource("read_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public PagerResult<UnitcommonlyEntity> selectPager(Entity arg0){
        return super.selectPager(arg0);
        }

        @Override
        @UseDataSource("write_ds")
        protected int updateByPk(UnitcommonlyEntity entity){
        return super.updateByPk(entity);
        }

        @Override
        @UseDataSource("write_ds")
        public int updateByPk(List<UnitcommonlyEntity> data){
        return super.updateByPk(data);
        }

        @Override
        @UseDataSource("write_ds")
        public int updateByPk(String pk,UnitcommonlyEntity entity){
        entity.setUpdatetime(Calendar.getInstance().getTime());
        return super.updateByPk(pk,entity);
        }

        /**
         * 	根据userid 查询该人的常用监管单位
         */
        @Override
        @UseDataSource("write_ds")
		public List<UnitcommonlyEntity> getCommonlyUnit(String userid){
        	QueryParamEntity qEntity = new QueryParamEntity();
        	qEntity.and("userid", TermType.like, userid);
        	List<UnitcommonlyEntity> list = this.select(qEntity);
        	
        	return list;
        }
        
        /**
         * 	用户设置该单位为常用单位
         * @param usreid	用户id
         * @param dwbh		监管单位编号
         * @param option	用户惭怍类型，1 是设置常用 2 是设置不常用
         * @return
         */
        @Override
        @UseDataSource("write_ds")
        public String setCommonlyUnit(String userid,String dwbh,String option) {
        	QueryParamEntity qEntity = new QueryParamEntity();
        	qEntity.and("dwbh", TermType.eq, dwbh)
        		.and("state", TermType.eq, "R2");
        	UnitcommonlyEntity entity = this.selectSingle(qEntity);
        	System.err.println("entity----"+JSON.toJSONString(entity));
        	if (StringUtils.isBlank(entity)) {
        		String dwmc = "";
                Object object = CacheConfig.getInstance().getCacheByKey(CacheConfig.CAHCE_UNIT,dwbh);
                UnitEntity unit = (UnitEntity) object;
        		if (StringUtils.isNotBlank(unit)) {
        			dwmc = unit.getDwmc();
				}
        		entity = new UnitcommonlyEntity();
        		entity.setId(dwbh);
        		entity.setDwbh(dwbh);
        		entity.setDwmc(dwmc);
        		entity.setType("1");
        		entity.setState("R2");
        		entity.setCreator("系统");
        		entity.setCreatetime(new Date());
			}
        	
        	String userids = entity.getUserids();
        	if (StringUtils.isBlank(userids)) {
        		userids = "";
			}
        	if ("1".equals(option)) {
        		userids = userids + "," + userid;
			}else if ("2".equals(option)) {
				userids = userids.replace(userid, "").replace(",,", ",");
			}
        	
        	entity.setUserids(userids);
        	return saveOrUpdate(entity);
		}
        
}
