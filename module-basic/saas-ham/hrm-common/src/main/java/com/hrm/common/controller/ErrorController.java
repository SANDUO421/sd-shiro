package com.hrm.common.controller;

import com.hrm.common.entity.Result;
import com.hrm.common.entity.ResultCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 处理多个微服务错误跳转页面
 * @author 三多
 * @Time 2020/4/15
 */
@RestController
public class ErrorController {
    /**
     * 公共错误跳转
     * @param code
     * @return
     */
    @GetMapping("/authError")
    public Result authError(int code){
        return code==1?new Result(ResultCode.UN_AUTHENTICATED):new Result(ResultCode.UN_AUTHORISE);
    }
}
