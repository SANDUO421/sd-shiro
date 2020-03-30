package com.sd.shiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;

/**
 * @author 三多
 * @Time 2020/3/30
 */
@SpringBootApplication
@EntityScan("com.sd.shiro.domain")
public class ShiroApp {
    public static void main(String[] args) {
        SpringApplication.run(ShiroApp.class,args);
    }

    /**
     * 解决共享session问题
     * @return
     */
    @Bean
    public OpenEntityManagerInViewFilter openEntityManagerInViewFilter(){
        return new OpenEntityManagerInViewFilter();
    }

}
