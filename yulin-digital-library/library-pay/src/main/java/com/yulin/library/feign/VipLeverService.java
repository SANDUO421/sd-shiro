package com.yulin.library.feign;

import com.yulin.library.util.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("library-setting")
public interface VipLeverService {
    @GetMapping("/vipLever/{id}")
    ApiResponse get(@RequestHeader(name = "Authorization") String authorization, @PathVariable(value = "id") String id);
}
