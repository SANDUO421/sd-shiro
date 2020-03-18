package com.hrm.common.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * 返回响应对象
 * 
 * @author 三多
 * @Time 2020/3/10
 */
@Data
@NoArgsConstructor
public class Result {
    /**是否成功*/
    private boolean success;
    /**操作码*/
    private Integer code;
    /**消息*/
    private String message;
    /**数据*/
    private Object data;

    /**
     * 构造
     * @param resultCode ResultCode对象
     */
    public Result(ResultCode resultCode){
        this.success = resultCode.success;
        this.code = resultCode.code;
        this.message = resultCode.message;
    }

    /**
     * 构造
     * @param resultCode ResultCode对象
     * @param data 数据
     */
    public Result(ResultCode resultCode,Object data){
        this.success = resultCode.success;
        this.code = resultCode.code;
        this.message = resultCode.message;
        this.data = data;
    }

    /**
     *
     * @param code  操作码
     * @param message 消息
     * @param success 成功标识
     */
    public Result(int code,String message,boolean success){
        this.success = success;
        this.code = code;
        this.message = message;
    }

    /**
     * 成功
     * @return
     */
    public  static  Result SUCCESS(){
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 服务器错误
     * @return Result
     */
    public  static  Result ERROR(){
        return new Result(ResultCode.SERVER_ERROR);
    }
    /**
     * 失败
     * @return Result
     */
    public  static  Result FAIL(){
        return new Result(ResultCode.FAIL);
    }
    public  static  Result FALL(String message){
        return FALL(HttpStatus.INTERNAL_SERVER_ERROR.value(),message);
    }
    public static Result FALL(int code,String message){
        return new Result(code,message,false);
    }
}
