package com.yulin.library.init.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Log4j2
@Configuration
public class TokenStoreConfig {

    /**
     * redis连接工厂
     */
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * 使用redis存储token的配置，只有在imooc.security.oauth2.tokenStore配置为redis时生效
     * @return
     */
    @Bean
    @ConditionalOnProperty(prefix = "com.yulin.cas.oauth2",
            name = "store-type",
            havingValue = "redis")
    public TokenStore redisTokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }


    /**
     * jwt的配置
     *
     * 使用jwt时的配置，默认生效
     */
    @Configuration
    @ConditionalOnProperty(prefix = "com.yulin.cas.oauth2",
            name = "store-type",
            havingValue = "jwt",
            matchIfMissing = true)
    public static class JwtTokenConfig {

        @Bean
        public TokenStore jwtTokenStore(JwtAccessTokenConverter accessTokenConverter) {
            return new JwtTokenStore(accessTokenConverter);
        }

        @Bean
        public JwtAccessTokenConverter accessTokenConverter() {
            JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
            converter.setSigningKey("yulin-tu-shu-guan");
            return converter;
        }
    }
}
