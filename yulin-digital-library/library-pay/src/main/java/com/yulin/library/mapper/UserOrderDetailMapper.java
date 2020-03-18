package com.yulin.library.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yulin.library.entity.SysUserInfo;
import com.yulin.library.entity.UserOrderDetail;
import org.apache.ibatis.annotations.Select;

public interface UserOrderDetailMapper extends BaseMapper<UserOrderDetail> {

    @Select("select * from user_order_detail where order_type=#{orderType} and order_id=#{orderId}")
    UserOrderDetail findByOrderTypeAndOrderId(String orderType,Long orderId);

}
