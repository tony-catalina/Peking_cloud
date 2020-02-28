package awd.cloud.internet.servers.server.service.imp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import awd.cloud.internet.servers.server.dao.UseraddressDao;
import awd.cloud.internet.servers.server.entity.UseraddressEntity;
import awd.cloud.internet.servers.server.entity.UserinfoEntity;
import awd.cloud.internet.servers.server.service.UseraddressService;
import awd.framework.common.core.IDGenerator;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.entity.Entity;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.service.simple.GenericEntityService;
import awd.framework.common.utils.StringUtils;
@Service("useraddressService")
public class SimpleUseraddressService extends GenericEntityService<UseraddressEntity,String>implements UseraddressService{

        @Autowired
        private UseraddressDao useraddressDao;

        @Override
        protected IDGenerator<String> getIDGenerator(UseraddressEntity entity){
                String jsbh="000000000";
                return()->{
                return getSEQUID(jsbh);
            };
        }

        @Override
        public UseraddressDao getDao(){
                return useraddressDao;
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
        public String insert(UseraddressEntity entity){
                return super.insert(entity);
        }

        @Override
        @UseDataSource("write_ds")
        public String saveOrUpdate(UseraddressEntity entity){
                return super.saveOrUpdate(entity);
        }

        @Override
        @UseDataSource("read_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public List<UseraddressEntity> selectByPk(List<String> id){
        	return super.selectByPk(id);
        }

        @Override
        @UseDataSource("read_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public UseraddressEntity selectByPk(String pk){
        return super.selectByPk(pk);
        }

        @Override
        @UseDataSource("read_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public PagerResult<UseraddressEntity> selectPager(Entity arg0){
        	return super.selectPager(arg0);
        }

        @Override
        @UseDataSource("write_ds")
        protected int updateByPk(UseraddressEntity entity){
        return super.updateByPk(entity);
        }

        @Override
        @UseDataSource("write_ds")
        public int updateByPk(List<UseraddressEntity> data){
        return super.updateByPk(data);
        }

        @Override
        @UseDataSource("write_ds")
        public int updateByPk(String pk,UseraddressEntity entity){
        return super.updateByPk(pk,entity);
        }

        /**
         * 根据userid获取该人的单位地址
         */
		@Override
		public String getAddressByUserid(String userid) {
			String address = useraddressDao.getAddressByUserid(userid);
			if (!StringUtils.isNullOrEmpty(address)) {
				return address;
			}
			return "";
		}

        /**
         * 根据userid 更新该人的单位地址
         */
		@Override
		public void updateAddressByUserid(String userid, String address) {
			useraddressDao.updateAddressByUserid(userid, address);
		}

		@Override
		public void saveUserAddress(String userid, String address) {
			UseraddressEntity entity = new UseraddressEntity();
			entity.setId(userid);
			entity.setUserid(userid);
			entity.setAddress(address);
			entity.setUserstate("R2");
			this.saveOrUpdate(entity);
		}
		
		@Override
		public void saveUserAddress(UserinfoEntity userinfo) {
			UseraddressEntity entity = new UseraddressEntity();
			entity.setId(userinfo.getUserid());
			entity.setUserid(userinfo.getUserid());
			entity.setUserstate(userinfo.getState());
			entity.setAddress(userinfo.getAddress());
			this.saveOrUpdate(entity);
		}

}
