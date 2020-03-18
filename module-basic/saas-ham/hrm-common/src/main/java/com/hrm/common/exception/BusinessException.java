package com.hrm.common.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * @author 三多
 * @Time 2020/3/13
 */
public class BusinessException extends RuntimeException implements Serializable {

    @Setter
    @Getter
    private int code=HttpStatus.INTERNAL_SERVER_ERROR.value();

    @Setter
    @Getter
    private String message;
    public BusinessException(String message) {
        super(message);
        this.message = message;
    }
    public BusinessException(String message,Throwable throwable) {
        super(message,throwable);
        this.message = message;
    }

    /**
     *
     * @param message 消息
     * @param code 状态码
     */
    public BusinessException(String message, int code) {
        super(message);
        this.code = code;
        this.message = message;
    }
    public  BusinessException(int code,String message ,Throwable throwable){
        super(message,throwable);
        this.code = code;
    }
}
