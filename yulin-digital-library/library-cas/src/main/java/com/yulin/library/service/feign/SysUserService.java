package com.yulin.library.service.feign;

import com.yulin.library.authentication.TempSysUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("library-system")
public interface SysUserService {
    @GetMapping("/loginSelect")
    TempSysUser loginSelect(@RequestParam("username") String username);
}
