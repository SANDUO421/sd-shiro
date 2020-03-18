package com.yulin.library.util.propertie.tencent;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "tencent")
public class TencentProperties implements java.io.Serializable {
    private static final long serialVersionUID = 3309210693328692595L;

    private CosProperties cos;

}
