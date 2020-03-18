package com.yulin.library.authentication;

import com.yulin.library.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class SmsIntegrationAuthenticator extends AbstractIntegrationAuthenticator {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public SysUser authenticate(String username) throws UsernameNotFoundException {
        SysUser sysUser = getSysUser(username);
        String password=request.getParameter("password");
        sysUser.setPassword(passwordEncoder.encode(password));
        return sysUser;
    }

    @Override
    protected String getType() {
        return SMS;
    }
}
