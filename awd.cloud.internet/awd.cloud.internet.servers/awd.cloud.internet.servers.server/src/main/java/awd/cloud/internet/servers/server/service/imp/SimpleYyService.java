/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.servers.server.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import awd.cloud.internet.servers.server.dao.YyDao;
import awd.cloud.internet.servers.server.entity.YyEntity;
import awd.cloud.internet.servers.server.service.YyService;
import awd.framework.common.core.IDGenerator;
import awd.framework.common.service.simple.GenericEntityService;

@Service("yyService")
public class SimpleYyService extends GenericEntityService<YyEntity, String> implements YyService {

	@Autowired
    private YyDao yyDao;

    @Override
    protected IDGenerator<String> getIDGenerator(YyEntity entity) {
    	String jsbh=entity.getJsbh();
        return () -> {
            return getSEQUID(jsbh);
        };
    }

    @Override
    public YyDao getDao() {
        return yyDao;
    }    
    
}
