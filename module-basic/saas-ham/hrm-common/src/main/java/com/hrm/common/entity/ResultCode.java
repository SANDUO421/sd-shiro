package com.hrm.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 公共返回码
 *
 * @author 三多
 * @Time 2020/3/10
 */
@Getter
@AllArgsConstructor
public enum ResultCode {
    SUCCESS(true, 10000, "操作成功!"),
    /************************系统错误返回码:1XXX***************************/
    FAIL(false, 10001, "操作失败!"),
    UN_AUTHENTICATED(false, 10002, "您还未登录!"),
    UN_AUTHORISE(false, 10003, "没有权限!"),
    SERVER_ERROR(false, 99999, "抱歉系统繁忙，请稍后重试!");
    /************************用户操作返回码:2XXX***************************/

    /************************企业操作返回码：3XXX***************************/
    /************************权限操作返回码: 4XXX***************************/
    /************************其他操作返回码: 5XXX***************************/

    /**
     * 操作是否成功
     */
    boolean success;
    /**
     * 操作代码
     */
    int code;
    /**
     * 提示信息
     */
    String message;

}
