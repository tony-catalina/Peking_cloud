package awd.cloud.internet.servers.server.service.imp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import awd.cloud.internet.servers.server.dao.FcjlDao;
import awd.cloud.internet.servers.server.entity.FcjlEntity;
import awd.cloud.internet.servers.server.entity.SmsyEntity;
import awd.cloud.internet.servers.server.service.FcjlService;
import awd.cloud.internet.servers.server.service.SmsyService;
import awd.framework.common.core.IDGenerator;
import awd.framework.common.core.param.TermType;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.entity.Entity;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.simple.GenericEntityService;
@Service("fcjlService")
public class SimpleFcjlService extends GenericEntityService<FcjlEntity,String>implements FcjlService{

        @Autowired
        private FcjlDao fcjlDao;

        @Autowired
        private SmsyService smsyService;
        
        @Override
        protected IDGenerator<String> getIDGenerator(FcjlEntity entity){
                String jsbh=entity.getJsbh();
                return()->{
                return getSEQUID(jsbh);
            };
        }

        @Override
        public FcjlDao getDao(){
                return fcjlDao;
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
        public String insert(FcjlEntity entity){
        	return super.insert(entity);
        }

        @Override
        @UseDataSource("write_ds")
        public String saveOrUpdate(FcjlEntity entity){
        	return super.saveOrUpdate(entity);
        }

        @Override
        @UseDataSource("read_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public List<FcjlEntity> selectByPk(List<String> id){
        	return super.selectByPk(id);
        }

        @Override
        @UseDataSource("read_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public FcjlEntity selectByPk(String pk){
        	return super.selectByPk(pk);
        }

        @Override
        @UseDataSource("read_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public PagerResult<FcjlEntity> selectPager(Entity arg0){
        	return super.selectPager(arg0);
        }

        @Override
        @UseDataSource("write_ds")
        protected int updateByPk(FcjlEntity entity){
        	return super.updateByPk(entity);
        }

        @Override
        @UseDataSource("write_ds")
        public int updateByPk(List<FcjlEntity> data){
        	return super.updateByPk(data);
        }

        @Override
        @UseDataSource("write_ds")
        public int updateByPk(String pk,FcjlEntity entity){
        	return super.updateByPk(pk,entity);
        }

        @Override
        @UseDataSource("write_ds")
        public String updateFcjlWithFczt(FcjlEntity fcjlEntity) {
        	String fczt = fcjlEntity.getFczt();
        	if ("1".equals(fczt)) {
        		
			}else if ("2".equals(fczt)){
				
			}else if ("3".equals(fczt)){
				
			}else if ("4".equals(fczt)){	//取消发车
				//先把发车记录修改为R3
				fcjlEntity.setState("R3");
				//再把smsy的 对应数据的关于发车的信息给修改掉
				QueryParamEntity query = new QueryParamEntity();
				query.and("fcuuid", TermType.eq, fcjlEntity.getFcuuid())
					.and("state", TermType.eq, "R2");
				List<SmsyEntity> smsyList = smsyService.select(query);
				smsyList.forEach(smsy->{
					smsyService.resetFcjlById(smsy.getId());
				});
			}
        	return this.saveOrUpdate(fcjlEntity);
        }

		@Override
		public String updateFcjlAfterDsm(String fcuuid,String badw,String fczt) {
			
			List<String> list = smsyService.selectSmsyByFcuuid(fcuuid);
			boolean flag = true;
			boolean hasOver = false;
			
			for (String string : list) {
				if ("4".equals(string)) {	//4 是等待收押，有4 代表着 本次发车记录还有没有完成收押的
					flag = false;
				}
				if ("5".equals(string)) {	//5 是收押入所，有5代表着 本次发车记录有正常收押的
					hasOver = true;
				}
			}
			if (flag) {	//假如为true，代表该次发车记录的人员已经全部完成
				if (hasOver) {
					updateFcztByFcuuid("3",fcuuid);	
				}else {
					updateFcztByFcuuid("4",fcuuid);	
				}
				return "1";
			}else {
				int num = smsyService.selectSmsyCountByFcuuid(fcuuid, badw);
				if (num == 0) {
					FcjlEntity fcjlEntity = fcjlDao.selectFcjlByFcuuid(fcuuid);
					String badwStr = fcjlEntity.getBadw();
					String newBadw = badwStr.replace(badw, "");
					fcjlEntity.setBadw(newBadw);
					saveOrUpdate(fcjlEntity);
				}
				return "0";
			}
		}

		@Override
		public int updateFcztByFcuuid(String fczt, String fcuuid) {
			return fcjlDao.updateFcztByFcuuid(fczt, fcuuid);
		}
        
}
