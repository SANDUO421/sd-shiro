package com.yulin.library.feign;

import com.yulin.library.util.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("library-cas")
public interface SysUserService {
    @GetMapping("/sysUsers")
    ApiResponse get(@RequestHeader(name = "Authorization") String authorization);
}
