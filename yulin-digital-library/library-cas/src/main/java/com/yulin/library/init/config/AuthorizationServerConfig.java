package com.yulin.library.init.config;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.InMemoryClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.sql.DataSource;
import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@EnableAuthorizationServer
@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private static final String KEY_STORE_FILE = "cnsesan-jwt.jks";
    private static final String KEY_STORE_PASSWORD = "cnsesan123";
    private static final String KEY_ALIAS = "cnsesan-jwt";

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired(required = false)
    private DataSource dataSource;

    @Autowired
    private TokenStore tokenStore;

    @Autowired(required = false)
    private JwtAccessTokenConverter accessTokenConverter;

    @Autowired(required = false)
    @Qualifier("jwtTokenEnhancer")
    private TokenEnhancer tokenEnhancer;

    @Autowired(required = false)
    private WebResponseExceptionTranslator webResponseExceptionTranslator;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        DefaultOAuth2RequestFactory defaultOAuth2RequestFactory = new DefaultOAuth2RequestFactory(clientDetails());
        defaultOAuth2RequestFactory.setCheckUserScopes(true);
        endpoints
                .tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .requestFactory(defaultOAuth2RequestFactory);
        if(Objects.nonNull(accessTokenConverter) && Objects.nonNull(tokenEnhancer)){
            KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("cnsesan-jwt.jks"),
                    "cnsesan123".toCharArray());
            accessTokenConverter.setKeyPair(keyStoreKeyFactory.getKeyPair("cnsesan-jwt"));

            //拿到增强器链
            TokenEnhancerChain enhancerChain = new TokenEnhancerChain();

            List<TokenEnhancer> enhancers = new ArrayList<>(2);
            enhancers.add(tokenEnhancer);
            enhancers.add(accessTokenConverter);

            enhancerChain.setTokenEnhancers(enhancers);

            endpoints
                    .tokenEnhancer(enhancerChain)
                    .accessTokenConverter(accessTokenConverter);
        }
        // 添加自定义异常
        if(Objects.nonNull(webResponseExceptionTranslator)){
            endpoints.exceptionTranslator(webResponseExceptionTranslator);
        }
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 允许资源服务器验证 token
        security
                // 开启/oauth/token_key验证端口认证权限访问
                .tokenKeyAccess("isAuthenticated()")
                // 开启/oauth/check_token验证端口无权限访问
                .checkTokenAccess("isAuthenticated()");
    }

    /**
     * 客户端配置
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetails());
    }

    @Bean
    public ClientDetailsService clientDetails() {
        if(Objects.nonNull(dataSource)){
            return new JdbcClientDetailsService(dataSource);
        }
        return new InMemoryClientDetailsService();
    }

    @Bean
    public KeyPair keyPair() {
        ClassPathResource ksFile = new ClassPathResource(KEY_STORE_FILE);
        KeyStoreKeyFactory ksFactory = new KeyStoreKeyFactory(ksFile, KEY_STORE_PASSWORD.toCharArray());
        return ksFactory.getKeyPair(KEY_ALIAS);
    }

    @Bean
    public JWKSet jwkSet() {
        RSAKey.Builder builder = new RSAKey.Builder((RSAPublicKey) keyPair().getPublic())
                .keyUse(KeyUse.SIGNATURE)
                .algorithm(JWSAlgorithm.RS256)
                .keyID("bael-key-id");
        return new JWKSet(builder.build());
    }
}
