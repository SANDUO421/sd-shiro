package com.module.common.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * 自定义异常
 *
 * @author 三多
 * @Time 2020/3/1
 */
public class PreBaseException extends RuntimeException implements Serializable {

    /**消息提醒*/
    @Setter
    @Getter
    private String msg;
    /**默认状态码*/
    @Setter
    @Getter
    private int code = HttpStatus.INTERNAL_SERVER_ERROR.value();

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public PreBaseException(String msg) {
        super(msg);
        this.msg = msg;
    }

    /**
     * Constructs a new runtime exception with the specified detail message and
     * cause.  <p>Note that the detail message associated with
     * {@code cause} is <i>not</i> automatically incorporated in
     * this runtime exception's detail message.
     *
     * @param msg the detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method).
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link #getCause()} method).  (A <tt>null</tt> value is
     *                permitted, and indicates that the cause is nonexistent or
     *                unknown.)
     * @since 1.4
     */
    public PreBaseException(String msg, Throwable cause) {
        super(msg, cause);
        this.msg = msg;
    }

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public PreBaseException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }
    public  PreBaseException(String msg,int code, Throwable throwable){
        super(msg,throwable);
        this.msg = msg;
        this.code = code;
    }
}
