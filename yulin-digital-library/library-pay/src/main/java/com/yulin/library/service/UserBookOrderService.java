package com.yulin.library.service;

import com.yulin.library.entity.UserBookOrder;

public interface UserBookOrderService extends BaseUserOrderService<UserBookOrder> {

    UserBookOrder getByBookId(String bookId,String userId,Integer payState);

}
