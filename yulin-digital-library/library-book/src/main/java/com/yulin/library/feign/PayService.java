package com.yulin.library.feign;

import com.yulin.library.util.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("library-pay")
public interface PayService {
    @GetMapping("/sysUserInfo/{id}")
    ApiResponse get(@RequestHeader(name = "Authorization") String authorization, @PathVariable("id") String id);

    @GetMapping("/userBookOrder")
    ApiResponse get(
            @RequestHeader(name = "Authorization") String authorization,
            @RequestParam("bookId") String bookId,
            @RequestParam("userId") String userId,
            @RequestParam("payState") Integer payState);
}
