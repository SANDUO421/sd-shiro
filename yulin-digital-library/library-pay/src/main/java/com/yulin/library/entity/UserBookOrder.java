package com.yulin.library.entity;

import lombok.Data;

/**
 * 购买图书
 * @author 星中月
 * @since 2018/7/6 0006
 */
@Data
public class UserBookOrder extends BaseUserOrder {
    private static final long serialVersionUID = -2351558032178689626L;

    private String bookId;

}
