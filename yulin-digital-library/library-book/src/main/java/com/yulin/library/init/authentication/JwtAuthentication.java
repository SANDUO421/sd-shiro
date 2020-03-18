package com.yulin.library.init.authentication;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.Map;

public class JwtAuthentication extends AbstractAuthenticationToken {

    private Object credentials;

    private Object principal;

    public JwtAuthentication(Jwt jwt, String authorities) {
        this(AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
        Map<String, Object> claims = jwt.getClaims();
        this.credentials = claims.get("user_name");
        this.principal = claims.get("currentUser");
        this.setAuthenticated(true);
    }

    public JwtAuthentication(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}
