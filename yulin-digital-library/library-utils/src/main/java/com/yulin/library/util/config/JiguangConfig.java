package com.yulin.library.util.config;

import cn.jiguang.common.ClientConfig;
import cn.jpush.api.JPushClient;
import com.yulin.library.util.propertie.jiguang.JiguangProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class JiguangConfig {

    @Autowired
    private JiguangProperties jiguang;

    @ConditionalOnMissingBean(JPushClient.class)
    @Bean
    public JPushClient jpushClient(){
        if(Objects.nonNull(jiguang.getPush())){
            ClientConfig clientConfig = ClientConfig.getInstance();
            return new JPushClient(jiguang.getPush().getMasterSecret(), jiguang.getPush().getAppKey(), null, clientConfig);
        }
        return new JPushClient("123456789012345678901234","123456789012345678901234");
    }

}
