package com.yulin.library.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yulin.library.entity.BaseUserOrder;
import com.yulin.library.entity.UserBookOrder;
import com.yulin.library.entity.UserOrderDetail;
import com.yulin.library.feign.BookService;
import com.yulin.library.mapper.UserBookOrderMapper;
import com.yulin.library.service.UserBookOrderService;
import com.yulin.library.util.ApiResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

@Service
public class UserBookOrderServiceImpl extends AbstractUserOrderService<UserBookOrderMapper, UserBookOrder> implements UserBookOrderService {

    @Autowired
    private BookService bookService;

    @Autowired
    private HttpServletRequest request;

    @Override
    protected void addListCondition(QueryWrapper<UserBookOrder> queryWrapper, Object[] data) {
        if(Objects.nonNull(data[0]) && StringUtils.isNoneBlank(data[0].toString())){
            queryWrapper.eq("book_id",data[0]);
        }
        if(Objects.nonNull(data[1])){
            queryWrapper.eq("pay_state",data[1]);
        }
    }

    @Override
    protected String getOrderType(UserBookOrder order) {
        return "userBookOrder";
    }

    @Override
    protected BigDecimal getPrice(UserBookOrder order) {
        ApiResponse apiResponse = bookService.get(request.getHeader("Authorization"),order.getBookId());
        Map data = (Map) apiResponse.getData();
        return new BigDecimal(data.get("price").toString());
    }

    @Override
    public void afterPay(UserOrderDetail userOrderDetail, BaseUserOrder entity) {}

    @Override
    public UserBookOrder getByBookId(String bookId,String userId,Integer payState) {
        UserBookOrder query=new UserBookOrder();
        query.setBookId(bookId);
        query.setPayState(payState);
        query.setUserId(userId);
        QueryWrapper<UserBookOrder> queryWrapper=new QueryWrapper<>(query);
        return baseMapper.selectOne(queryWrapper);
    }
}
