package com.yulin.library.util.propertie.jiguang;

import com.yulin.library.util.propertie.tencent.CosProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "jiguang")
public class JiguangProperties implements java.io.Serializable {
    private static final long serialVersionUID = 3309210693328692595L;

    private PushProperties push;

}
