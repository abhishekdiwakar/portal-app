package com.linneaus.portal.configuration;

import com.linneaus.portal.serviceintegrationlayer.SecurityService;
import com.linneaus.portal.serviceintegrationlayer.UserLayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class AuthProvider implements AuthenticationProvider {

    @Autowired
    SecurityService securityService;

    @Autowired
    UserLayerService userLayerService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
