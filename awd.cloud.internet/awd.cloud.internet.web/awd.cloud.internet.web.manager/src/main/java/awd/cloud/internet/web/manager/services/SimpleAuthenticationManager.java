package awd.cloud.internet.web.manager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import awd.framework.expands.authorization.Authentication;
import awd.framework.expands.authorization.AuthenticationInitializeService;
import awd.framework.expands.authorization.AuthenticationManager;


@Service("authenticationManager")
public class SimpleAuthenticationManager implements AuthenticationManager {

    private AuthenticationInitializeService authenticationInitializeService;

    public SimpleAuthenticationManager() {
    }

    public SimpleAuthenticationManager(AuthenticationInitializeService authenticationInitializeService) {
        this.authenticationInitializeService = authenticationInitializeService;
    }

    @Autowired
    public void setAuthenticationInitializeService(AuthenticationInitializeService authenticationInitializeService) {
        this.authenticationInitializeService = authenticationInitializeService;
    }

    @Override
    public Authentication getByUserId(String userId) {    
        return authenticationInitializeService.initUserAuthorization(userId);
    }

    @Override
    public Authentication sync(Authentication authentication) {
        return authentication;
    }
    
    
}
