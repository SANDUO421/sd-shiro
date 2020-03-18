package com.yulin.library.service;

import com.yulin.library.entity.UserOrderDetail;
import com.yulin.library.mybatis.service.BaseService;

public interface UserOrderDetailService extends BaseService<UserOrderDetail> {
    /**
     * 付钱
     * @param id
     * @param payId
     * @param isSuccess
     * @param payMessage
     * @param payMode
     * @return
     * @throws Exception
     */
    UserOrderDetail pay(Long id, String payId, Integer isSuccess, String payMessage, Integer payMode) throws Exception;
}
