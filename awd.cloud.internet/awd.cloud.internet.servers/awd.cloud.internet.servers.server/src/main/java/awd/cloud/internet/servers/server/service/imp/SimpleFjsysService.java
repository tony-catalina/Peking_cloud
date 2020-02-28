/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.servers.server.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import awd.cloud.internet.servers.server.dao.FjsysDao;
import awd.cloud.internet.servers.server.entity.FjsysEntity;
import awd.cloud.internet.servers.server.service.FjsysService;
import awd.framework.common.core.IDGenerator;
import awd.framework.common.service.simple.GenericEntityService;

@Service("fjsysService")
public class SimpleFjsysService extends GenericEntityService<FjsysEntity, String> implements FjsysService {

	@Autowired
    private FjsysDao fjsysDao;

    @Override
    protected IDGenerator<String> getIDGenerator(FjsysEntity entity) {
    	String jsbh="00000";
        return () -> {
            return getSEQUID(jsbh);
        };
    }

    @Override
    public FjsysDao getDao() {
        return fjsysDao;
    }
    
    

	@Override
	public void updateFjBysJsbh(String sysl, String jsbh) {
	
		fjsysDao.updateFjBysJsbh(sysl,jsbh);
	}

    
    
    
    
}
