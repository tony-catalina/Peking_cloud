package awd.cloud.internet.servers.server.service.imp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import awd.cloud.internet.servers.server.config.CacheConfig;
import awd.cloud.internet.servers.server.dao.UserinfoDao;
import awd.cloud.internet.servers.server.entity.UserinfoEntity;
import awd.cloud.internet.servers.server.service.UseraddressService;
import awd.cloud.internet.servers.server.service.UserinfoService;
import awd.framework.common.core.IDGenerator;
import awd.framework.common.core.param.TermType;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.entity.Entity;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.simple.DefaultDSLUpdateService;
import awd.framework.common.service.simple.GenericEntityService;
import awd.framework.common.utils.StringUtils;
@Service("userinfoService")
public class SimpleUserinfoService extends GenericEntityService<UserinfoEntity,String>implements UserinfoService{

        @Autowired
        private UserinfoDao userinfoDao;
        
        @Autowired
        private UseraddressService useraddressService;

    	@Autowired
    	private CacheConfig cacheConfig;
    	
        @Override
        protected IDGenerator<String> getIDGenerator(UserinfoEntity entity){
                String jsbh="000000000";
                return()->{
                return getSEQUID(jsbh);
            };
        }

        @Override
        public UserinfoDao getDao(){
                return userinfoDao;
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
        public String insert(UserinfoEntity entity){
                entity.setCreatetime(Calendar.getInstance().getTime());
                return super.insert(entity);
        }

        @Override
        @UseDataSource("write_ds")
        public String saveOrUpdate(UserinfoEntity entity){
                return super.saveOrUpdate(entity);
        }

        @Override
        @UseDataSource("read_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public List<UserinfoEntity> selectByPk(List<String> id){
        	return super.selectByPk(id);
        }

        @Override
        @UseDataSource("read_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public UserinfoEntity selectByPk(String pk){
        return super.selectByPk(pk);
        }

        @Override
        @UseDataSource("read_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public PagerResult<UserinfoEntity> selectPager(Entity arg0){
        	return super.selectPager(arg0);
        }

        @Override
        @UseDataSource("write_ds")
        protected int updateByPk(UserinfoEntity entity){
        return super.updateByPk(entity);
        }

        @Override
        @UseDataSource("write_ds")
        public int updateByPk(List<UserinfoEntity> data){
        return super.updateByPk(data);
        }

        @Override
        @UseDataSource("write_ds")
        public int updateByPk(String pk,UserinfoEntity entity){
        entity.setUpdatetime(Calendar.getInstance().getTime());
        return super.updateByPk(pk,entity);
        }

		@Override
		@UseDataSource("write_ds")
		public int addUser(Map<String, Object> parms) {
			int result=userinfoDao.addUser(parms);
			String wxh=parms.get("wxh").toString();
			QueryParamEntity param=new QueryParamEntity();
			param.setPaging(false);
			param.and("wxh", TermType.eq, wxh);
			UserinfoEntity user=selectSingle(param);
			CacheConfig config = CacheConfig.getInstance();
			//config.setUserCache(user);
			return result;
		}

		@Override
        @UseDataSource("write_ds")
        public String addUserInfoWithCache(UserinfoEntity entity){
                entity.setCreatetime(Calendar.getInstance().getTime());
                entity.setId(entity.getUserid());
    			String id = super.insert(entity);
    			if (!StringUtils.isNullOrEmpty(id)) {
    				//useraddressService.saveUserAddress(entity.getUserid(),entity.getAddress());
    				useraddressService.saveUserAddress(entity);
    				cacheConfig.updateUserCache(entity);
				}
                return id;
        }
		
		@Override
		@UseDataSource("write_ds")
		public int updateUserInfoWithCache(String id,UserinfoEntity entity){
			entity.setCreatetime(Calendar.getInstance().getTime());
			int num = super.updateByPk(id,entity);
			if (num > 0) {
				UserinfoEntity userinfo = this.selectByPk(id);
				//修改用户 更新缓存
				//useraddressService.saveUserAddress(entity.getUserid(),entity.getAddress());
				useraddressService.saveUserAddress(userinfo);
				cacheConfig.updateUserCache(userinfo);
			}
			return num;
		}
		
		@Override
		public List<UserinfoEntity> loginCheck(String userid){
				CacheConfig config = CacheConfig.getInstance();
				Object object = config.getCacheByKey(CacheConfig.CAHCE_USER,userid);
				List<UserinfoEntity> list=null;
				if(object!=null) {
					UserinfoEntity entity = (UserinfoEntity) object;
					list=new ArrayList<UserinfoEntity>();
					list.add(entity);
				}				
				return list;//userinfoDao.loginCheck(wxh);
		}

		@Override
		@UseDataSource("write_ds")
		public int enableUser(String ids, String enable) {
			tryValidateProperty(!StringUtils.isNullOrEmpty(ids), "id", "{id_is_null}");
			DefaultDSLUpdateService.createUpdate(getDao())
            .set("state", enable)
            .where("id", TermType.in, ids)
            .exec();
			return 1;
		}

		@Override
		@UseDataSource("write_ds")
		public int shUser(String ids, String state, String shbz) {
			tryValidateProperty(!StringUtils.isNullOrEmpty(ids), "id", "{id_is_null}");
			DefaultDSLUpdateService.createUpdate(getDao())
            .set("state", state).set("shbz", shbz)
            .where("id", TermType.in, ids)
            .exec();
			return 1;
		}
		
		

}
