package com.module.authority.app.data.tenant;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 三多
 * @Time 2020/3/5
 */
@Data
@Component
@ConfigurationProperties(prefix = "pre.tenant")
public class PreTenantConfigProperties {

    /**维护租户Id*/
    private String tenantIdColumn;
    /**多租户的数据表集合*/
    private List<String> ignoreTenantTables = new ArrayList<String>();
}
