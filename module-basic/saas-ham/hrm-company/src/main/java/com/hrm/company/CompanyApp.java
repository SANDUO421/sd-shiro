package com.hrm.company;

import com.hrm.common.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

/**
 * @author 三多
 * @Time 2020/3/10
 */
//1. 配置springboot 包扫描
@SpringBootApplication(scanBasePackages = "com.hrm")
//2. 配置jpa注解的扫描
@EntityScan(value = "com.hrm.domain.company")
public class CompanyApp {
    /**
     * 启动方法
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(CompanyApp.class,args);
    }


}
