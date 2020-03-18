package com.yulin.library.util;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import java.util.Objects;

/**
 * @author lingyu
 */
@JsonView(ApiResponse.BaseJsonView.class)
@Data
public class ApiResponse<T> implements java.io.Serializable {

    public interface BaseJsonView{}

    private static final long serialVersionUID = 1679695741003656805L;

    private String code;
    private String msg;
    private Long length;
    private T data;

    public ApiResponse(){}

    public ApiResponse(String code, String msg, T data){
        this.code=code;
        this.msg=msg;
        this.data=data;
        if(Objects.nonNull(data) && data instanceof String){
            this.length=(long)((String) data).length();
        }
    }

    public static<T> ApiResponse<T> success(){
        return success(null);
    }

    public static<T> ApiResponse<T> success(T data){
        return new ApiResponse(Constants.ReturnValue.SUCCESS_CODE,Constants.ReturnValue.SUCCESS_MSG,data);
    }

    public static<T> ApiResponse<T> error(String msg){
        return error(Constants.ReturnValue.ERROR_CODE,msg);
    }

    public static<T> ApiResponse<T> error(String code,String msg){
        return error(code,msg,null);
    }

    public static<T> ApiResponse<T> error(String code,String msg,T data){
        return new ApiResponse<>(code,msg,data);
    }
}
