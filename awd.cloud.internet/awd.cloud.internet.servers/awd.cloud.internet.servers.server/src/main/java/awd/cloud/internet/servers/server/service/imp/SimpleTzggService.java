/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.servers.server.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import awd.cloud.internet.servers.server.dao.TzggDao;
import awd.cloud.internet.servers.server.entity.TzggEntity;
import awd.cloud.internet.servers.server.service.TzggService;
import awd.framework.common.core.IDGenerator;
import awd.framework.common.service.simple.GenericEntityService;

@Service("tzggService")
public class SimpleTzggService extends GenericEntityService<TzggEntity, String> implements TzggService {

	@Autowired
    private TzggDao tzggDao;

    @Override
    protected IDGenerator<String> getIDGenerator(TzggEntity entity) {
    	String jsbh="00000";
        return () -> {
            return getSEQUID(jsbh);
        };
    }

    @Override
    public TzggDao getDao() {
        return tzggDao;
    }    
    
}
