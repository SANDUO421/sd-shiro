package com.hrm.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Entity;

/**
 * @author 三多
 * @Time 2020/3/20
 */
@SpringBootApplication(scanBasePackages = "com.hrm")
@EntityScan("com.hrm.domain.system")
public class SystemApp {
    public static void main(String[] args) {
        SpringApplication.run(SystemApp.class,args);
    }
}
