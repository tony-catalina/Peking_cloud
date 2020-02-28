package awd.cloud.internet.web.manager.config;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import awd.cloud.internet.web.manager.services.SimpleAuthenticationManager;
import awd.framework.common.utils.ClassUtils;
import awd.framework.expands.authorization.AuthenticationInitializeService;
import awd.framework.expands.authorization.AuthenticationManager;
import awd.framework.expands.authorization.listener.AuthorizationListener;
import awd.framework.expands.authorization.listener.AuthorizationListenerDispatcher;
import awd.framework.expands.authorization.listener.event.AuthorizationEvent;

@Configuration
public class AuthorizationAutoConfigration {

    @Autowired(required = false)
    private List<AuthorizationListener> listeners;

    @Bean
    @SuppressWarnings("unchecked")
    public <E extends AuthorizationEvent> AuthorizationListenerDispatcher authorizationListenerDispatcher() {
        AuthorizationListenerDispatcher dispatcher = new AuthorizationListenerDispatcher();
        if (listeners != null) {
            listeners.forEach(listener -> dispatcher.addListener((Class<E>) ClassUtils.getGenericType(listener.getClass()), listener));
        }
        return dispatcher;
    }

    @Bean
    @ConditionalOnMissingBean(AuthenticationManager.class)
    @ConditionalOnBean(AuthenticationInitializeService.class)
    public AuthenticationManager authenticationManager(AuthenticationInitializeService authenticationInitializeService) {
        return new SimpleAuthenticationManager(authenticationInitializeService);
    }
}
