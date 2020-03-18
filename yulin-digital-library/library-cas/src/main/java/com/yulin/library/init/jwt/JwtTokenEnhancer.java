package com.yulin.library.init.jwt;

import com.yulin.library.entity.SysUser;
import com.yulin.library.util.model.entity.CurrentUser;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Data
@Log4j2
@Component
public class JwtTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        SysUser sysUser = (SysUser)authentication.getPrincipal();

        CurrentUser currentUser=getCurrentUser(sysUser);
        Map<String, Object> info = new HashMap<>();
        info.put("currentUser",currentUser);
        info.put("code","200");

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
        return accessToken;
    }

    private CurrentUser getCurrentUser(SysUser sysUser) {
        CurrentUser currentUser=new CurrentUser();
        currentUser.setId(sysUser.getId());
        currentUser.setUsername(sysUser.getUsername());
        currentUser.setPhone(sysUser.getPhone());
        currentUser.setHeadPortraits(sysUser.getHeadPortraits());
        currentUser.setSex(sysUser.getSex());
        return currentUser;
    }

}
