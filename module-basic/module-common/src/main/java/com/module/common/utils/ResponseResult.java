package com.module.common.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @description:    统一响应类。
 * @author:         sanduo
 * @date:           2020/2/20 12:02
 * @version:        1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseResult<T> {
    /**
     * 状态码
     */
    private int code;

    /**
     * 消息
     */
    private String message;
    /**
     * 返回的数据
     */
    private T data;

    /**
     * 响应成功
     * @param data 返回数据
     * @param <T> 数据类型
     * @return 返回
     */
    public static <T> ResponseResult ofSuccess(  T data) {
        return new ResponseResult<T>(Status.SUCCESS.getCode(), Status.SUCCESS.getStandardMessage(), data);
    }

    public static <T> ResponseResult ofSuccess() {
        return new ResponseResult<T>(Status.SUCCESS.getCode(), Status.SUCCESS.getStandardMessage(), null);
    }

    public static <T> ResponseResult ofError() {
        return new ResponseResult<T>(Status.ERROR.getCode(), Status.ERROR.getStandardMessage(), null);
    }

    public static <T> ResponseResult ofError(T data) {
        return new ResponseResult<T>(Status.ERROR.getCode(), Status.ERROR.getStandardMessage(), data);
    }

    public static <T> ResponseResult ofMessage(int code, String message) {
        return new ResponseResult<T>(code, message, null);
    }

    public static <T> ResponseResult ofStatus(Status status) {
        return new ResponseResult<T>(status.getCode(), status.getStandardMessage(), null);
    }

    /**
     * 定义状态枚举
     */
    public enum Status {
        SUCCESS(200, "OK"),
        ERROR(1, "fail"),
        DEFINE(-1,"数据信息不全"),
        USERNAME_EXIST(-2,"用户名已存在"),
        DELETE(-2,"数据删除失败"),
        BAD_REQUEST(400, "Bad Request"),
        NOT_FOUND(404, "page not found"),
        INTERNAL_SERVER_ERROR(500, "server internal error"),
        NOT_LOGIN(40005, "not login"),
        INVALID_PARAM(40006, "invalid parameter"),
            PASSERROR(-1,"用户名或密码错误");
        private int code;
        private String standardMessage;

        Status(int code, String standardMessage) {
            this.code = code;
            this.standardMessage = standardMessage;
        }

        public int getCode() {
            return code;
        }

        public String getStandardMessage() {
            return standardMessage;
        }

    }
}
