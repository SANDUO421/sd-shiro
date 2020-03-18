package com.yulin.library.init.service;

import com.yulin.library.authentication.IntegrationAuthenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private List<IntegrationAuthenticator> integrationAuthenticators;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        for (IntegrationAuthenticator integrationAuthenticator : integrationAuthenticators) {
            if (integrationAuthenticator.support()) {
                return integrationAuthenticator.authenticate(username);
            }
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }
}
