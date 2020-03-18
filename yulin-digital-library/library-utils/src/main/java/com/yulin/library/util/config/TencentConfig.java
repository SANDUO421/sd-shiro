package com.yulin.library.util.config;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.AnonymousCOSCredentials;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;
import com.yulin.library.util.propertie.tencent.TencentProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class TencentConfig {

    @Autowired
    private TencentProperties tencent;

    @ConditionalOnMissingBean(COSClient.class)
    @Bean
    public COSClient cosClient(){
        if(Objects.nonNull(tencent.getCos())){
            COSCredentials cred = new BasicCOSCredentials(tencent.getCos().getSecretId(), tencent.getCos().getSecretKey());
            // 2 设置 bucket 的区域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
            // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
            Region region = new Region(tencent.getCos().getRegionName());
            ClientConfig clientConfig = new ClientConfig(region);
            // 3 生成 cos 客户端。
            return new COSClient(cred, clientConfig);
        }
        return new COSClient(new AnonymousCOSCredentials(),new ClientConfig());
    }

}
