package com.yulin.library.authentication;

import com.yulin.library.entity.SysUser;
import com.yulin.library.service.feign.SysUserService;
import com.yulin.library.util.Constants;
import com.yulin.library.util.model.entity.BaseEntity;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

public abstract class AbstractIntegrationAuthenticator implements IntegrationAuthenticator {

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected SysUserService sysUserService;

    @Override
    public abstract SysUser authenticate(String username) throws UsernameNotFoundException;

    protected abstract String getType();

    @Override
    public void prepare() {

    }

    @Override
    public boolean support() {
        return getType().equals(request.getParameter(Constants.AuthenticationMethod.AUTH_TYPE));
    }

    @Override
    public void complete() {

    }

    protected SysUser getSysUser(String username) {
        TempSysUser tempSysUser = sysUserService.loginSelect(username);
        if(Objects.isNull(tempSysUser)){
            throw new UsernameNotFoundException("用户不存在或者密码错误");
        }
        SysUser sysUser=new SysUser();
        BeanUtils.copyProperties(tempSysUser,sysUser);
        return sysUser;
    }

}
