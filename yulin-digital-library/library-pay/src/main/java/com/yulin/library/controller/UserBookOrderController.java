package com.yulin.library.controller;

import com.google.common.base.Splitter;
import com.yulin.library.entity.UserBookOrder;
import com.yulin.library.service.UserBookOrderService;
import com.yulin.library.util.ApiResponse;
import com.yulin.library.util.model.page.QueryCondition;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserBookOrderController extends AbstractUserOrderController<UserBookOrderService> {
    @GetMapping("/userBookOrders")
    public ApiResponse get(
            QueryCondition queryCondition,
            @RequestParam(value = "bookId",required = false) String bookId,
            @RequestParam(value = "payState",required = false) Integer payState
    ){
        return ApiResponse.success(service.listPage(queryCondition,bookId,payState));
    }

    @GetMapping("/userBookOrder")
    public ApiResponse getByBookId(String bookId,String userId,Integer payState){
        return ApiResponse.success(service.getByBookId(bookId,userId,payState));
    }

    @PostMapping("/userBookOrder")
    public ApiResponse add(@RequestBody UserBookOrder userBookOrder){
        return ApiResponse.success(service.createOrder(userBookOrder));
    }

    @DeleteMapping("/userBookOrder/{id}")
    public ApiResponse delete(@PathVariable Long id){
        return ApiResponse.success(service.removeById(id));
    }

    @DeleteMapping("/userBookOrders/{ids}")
    public ApiResponse deletes(@PathVariable String ids){
        return ApiResponse.success(service.removeByIds(Splitter.on(",").trimResults().splitToList(ids)));
    }
}
