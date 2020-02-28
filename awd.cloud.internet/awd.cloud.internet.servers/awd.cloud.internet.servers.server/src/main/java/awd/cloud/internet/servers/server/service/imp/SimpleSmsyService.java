package awd.cloud.internet.servers.server.service.imp;

import awd.framework.common.entity.PagerResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import awd.framework.common.entity.Entity;
import awd.framework.common.core.IDGenerator;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.service.simple.GenericEntityService;
import awd.framework.common.utils.StringUtils;
import awd.cloud.internet.servers.server.entity.SmsyEntity;
import awd.cloud.internet.servers.server.dao.SmsyDao;
import awd.cloud.internet.servers.server.service.SmsyService;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;

@Service("smsyService")
public class SimpleSmsyService extends GenericEntityService<SmsyEntity, String> implements SmsyService {

    @Autowired
    private SmsyDao smsyDao;

    @Override
    protected IDGenerator<String> getIDGenerator(SmsyEntity entity) {
        String jsbh = "000000000";
        return () -> {
            return getSEQUID(jsbh);
        };
    }

    @Override
    public SmsyDao getDao() {
        return smsyDao;
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
    public String insert(SmsyEntity entity) {
        entity.setCreatetime(Calendar.getInstance().getTime());
        return super.insert(entity);
    }

    @Override
    @UseDataSource("write_ds")
    public String saveOrUpdate(SmsyEntity entity) {
        return super.saveOrUpdate(entity);
    }

    @Override
    @UseDataSource("read_ds")
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<SmsyEntity> selectByPk(List<String> id) {
        return super.selectByPk(id);
    }

    @Override
    @UseDataSource("read_ds")
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public SmsyEntity selectByPk(String pk) {
        return super.selectByPk(pk);
    }

    @Override
    @UseDataSource("read_ds")
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public PagerResult<SmsyEntity> selectPager(Entity arg0) {
        return super.selectPager(arg0);
    }

    @Override
    @UseDataSource("write_ds")
    protected int updateByPk(SmsyEntity entity) {
        return super.updateByPk(entity);
    }

    @Override
    @UseDataSource("write_ds")
    public int updateByPk(List<SmsyEntity> data) {
        return super.updateByPk(data);
    }

    @Override
    @UseDataSource("write_ds")
    public int updateByPk(String pk, SmsyEntity entity) {
        entity.setUpdatetime(Calendar.getInstance().getTime());
        return super.updateByPk(pk, entity);
    }

    @Override
    public String resetFcjlById(String id) {
    	int re = smsyDao.resetFcjlById(id);
    	return String.valueOf(re);
    }

	@Override
	public Map<String, Object> pcsZdchSmsy(String idString,String chyy) {
		Map<String, Object> map = Maps.newHashMap();
		if (!StringUtils.isNullOrEmpty(idString)) {
			String[] ids = idString.split(",");
			for (String id : ids) {
				SmsyEntity entity = new SmsyEntity();
				entity.setPhase("2");
				entity.setChyy(chyy);
				this.updateByPk(id, entity);
			}
			map.put("code", 200);
			map.put("msg", "撤销成功！");
		}else {
			map.put("code", 500);
			map.put("msg", "未获取到id");
		}
		return map;
	}

	@Override
	public List<String> selectSmsyByFcuuid(String fcuuid) {
		return smsyDao.selectSmsyByFcuuid(fcuuid);
	}
	
	@Override
	public int selectSmsyCountByFcuuid(String fcuuid,String badw) {
		return smsyDao.selectSmsyCountByFcuuid(fcuuid,badw);
	}

}
