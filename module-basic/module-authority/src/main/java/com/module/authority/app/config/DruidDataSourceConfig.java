package com.module.authority.app.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.google.common.collect.Lists;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * @description:    Druid 配置
 * @author:         sanduo
 * @date:           2020/3/4 13:47
 * @version:        1.0
 */
@SpringBootConfiguration
public class DruidDataSourceConfig {

    @ConfigurationProperties(prefix = "spring.druid")
    @Bean
    public DruidDataSource dataSource(Filter statFilter) {
        DruidDataSource dataSource = new DruidDataSource();
        //  添加慢日志功能Lists.newArrayList添加guava工具集
        dataSource.setProxyFilters(Lists.newArrayList(statFilter));
        return dataSource;
    }

    @Bean
    public Filter statFilter(){
        StatFilter filter = new StatFilter();
        filter.setSlowSqlMillis(3000); // 设置慢sql时间
        filter.setLogSlowSql(true);
        filter.setMergeSql(true);
        return filter;
    }

    @Bean // 注册StatViewServlet
    public ServletRegistrationBean servletRegistrationBean(){
        return new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
    }
}
