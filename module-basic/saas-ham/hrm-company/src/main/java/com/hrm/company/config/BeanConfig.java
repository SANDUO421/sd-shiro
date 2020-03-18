package com.hrm.company.config;

import com.hrm.common.utils.IdWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 三多
 * @Time 2020/3/11
 */
@Configuration
public class BeanConfig {

    @Bean
    public IdWorker idWorker(){
        return new IdWorker();
    }
}
