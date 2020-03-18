package com.yulin.library.init.filter;

import com.yulin.library.init.authentication.JwtAuthentication;
import net.minidev.json.JSONArray;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;
import org.springframework.security.oauth2.server.resource.web.DefaultBearerTokenResolver;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.Assert;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationManagerResolver<HttpServletRequest> authenticationManagerResolver;

    private final AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource =
            new WebAuthenticationDetailsSource();

    private BearerTokenResolver bearerTokenResolver = new DefaultBearerTokenResolver();

    private AuthenticationEntryPoint authenticationEntryPoint = new BearerTokenAuthenticationEntryPoint();

    private AuthenticationFailureHandler authenticationFailureHandler = (request, response, exception) ->
            authenticationEntryPoint.commence(request, response, exception);

    /**
     * Construct a {@code BearerTokenAuthenticationFilter} using the provided parameter(s)
     * @param authenticationManager
     */
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        Assert.notNull(authenticationManager, "authenticationManager cannot be null");
        this.authenticationManagerResolver = request -> authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final boolean debug = this.logger.isDebugEnabled();

        String token;

        try {
            token = this.bearerTokenResolver.resolve(request);
        } catch ( OAuth2AuthenticationException invalid ) {
            this.authenticationEntryPoint.commence(request, response, invalid);
            return;
        }

        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        BearerTokenAuthenticationToken authenticationRequest = new BearerTokenAuthenticationToken(token);

        authenticationRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));

        try {
            AuthenticationManager authenticationManager = this.authenticationManagerResolver.resolve(request);
            Authentication authenticationResult = authenticationManager.authenticate(authenticationRequest);

            SecurityContext context = SecurityContextHolder.createEmptyContext();

            JwtAuthentication jwtAuthentication = createJwtAuthentication(authenticationResult);

            context.setAuthentication(jwtAuthentication);
            SecurityContextHolder.setContext(context);

            filterChain.doFilter(request, response);
        } catch (AuthenticationException failed) {
            SecurityContextHolder.clearContext();

            if (debug) {
                this.logger.debug("Authentication request for failed!", failed);
            }

            this.authenticationFailureHandler.onAuthenticationFailure(request, response, failed);
        }
    }

    private JwtAuthentication createJwtAuthentication(Authentication authenticationResult) {
        Jwt principal = (Jwt) authenticationResult.getPrincipal();
        Map<String, Object> claims = principal.getClaims();
        JSONArray authoritiesTemp = (JSONArray) claims.get("authorities");
        StringBuffer sb=new StringBuffer();
        for (int i=0,size=authoritiesTemp.size();i<size;i++){
            sb.append(authoritiesTemp.get(i).toString()).append(",");
        }
        String authorities="";
        if(sb.length()>0){
            authorities=sb.substring(0,sb.length()-1);
        }
        return new JwtAuthentication(principal,authorities);
    }

    /**
     * Set the {@link BearerTokenResolver} to use. Defaults to {@link DefaultBearerTokenResolver}.
     * @param bearerTokenResolver the {@code BearerTokenResolver} to use
     */
    public void setBearerTokenResolver(BearerTokenResolver bearerTokenResolver) {
        Assert.notNull(bearerTokenResolver, "bearerTokenResolver cannot be null");
        this.bearerTokenResolver = bearerTokenResolver;
    }

    /**
     * Set the {@link AuthenticationEntryPoint} to use. Defaults to {@link BearerTokenAuthenticationEntryPoint}.
     * @param authenticationEntryPoint the {@code AuthenticationEntryPoint} to use
     */
    public void setAuthenticationEntryPoint(final AuthenticationEntryPoint authenticationEntryPoint) {
        Assert.notNull(authenticationEntryPoint, "authenticationEntryPoint cannot be null");
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    /**
     * Set the {@link AuthenticationFailureHandler} to use. Default implementation invokes {@link AuthenticationEntryPoint}.
     * @param authenticationFailureHandler the {@code AuthenticationFailureHandler} to use
     * @since 5.2
     */
    public void setAuthenticationFailureHandler(final AuthenticationFailureHandler authenticationFailureHandler) {
        Assert.notNull(authenticationFailureHandler, "authenticationFailureHandler cannot be null");
        this.authenticationFailureHandler = authenticationFailureHandler;
    }
}
