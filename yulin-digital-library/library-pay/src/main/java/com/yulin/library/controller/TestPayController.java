package com.yulin.library.controller;

import com.yulin.library.service.UserOrderDetailService;
import com.yulin.library.util.ApiResponse;
import com.yulin.library.util.pay.PayConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestPayController {

    @Autowired
    private UserOrderDetailService userOrderDetailService;

    @GetMapping("/testPay")
    public ApiResponse testPay(Long id,String payId) throws Exception {
        return ApiResponse.success(userOrderDetailService.pay(id,payId,1,"payMessage", PayConstants.PayModel.WEI_XIN));
    }

}
