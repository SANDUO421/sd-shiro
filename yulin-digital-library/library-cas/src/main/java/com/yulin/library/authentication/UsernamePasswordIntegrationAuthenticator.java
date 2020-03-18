package com.yulin.library.authentication;

import com.yulin.library.entity.SysUser;
import com.yulin.library.util.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UsernamePasswordIntegrationAuthenticator extends AbstractIntegrationAuthenticator {

    @Override
    public SysUser authenticate(String username) throws UsernameNotFoundException {
        SysUser sysUser = getSysUser(username);
        return sysUser;
    }

    @Override
    public boolean support() {
        String parameter = request.getParameter(Constants.AuthenticationMethod.AUTH_TYPE);
        if(StringUtils.isBlank(parameter)){
            return true;
        }
        return super.support();
    }

    @Override
    protected String getType() {
        return USERNAME_PASSWORD;
    }
}
