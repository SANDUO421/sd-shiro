package com.hrm.common.exception;

import com.hrm.common.entity.Result;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

/**
 * @author 三多
 * @Time 2020/3/13
 */
@RestControllerAdvice(basePackages = {"com.hrm"})
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BusinessException.class)
    public Result handlerBusinessException(BusinessException ex){
        log.error(ex.getMessage(),ex);
        return new Result(ex.getCode(),ex.getMessage(),false);

    }
    @ExceptionHandler(value = Exception.class)
    public Result handlerException(Exception ex){
        log.error(ex.getMessage(),ex);
        if(ex instanceof MySQLIntegrityConstraintViolationException){
            log.error("业务日志", ex.getCause().getCause().getMessage());
            MySQLIntegrityConstraintViolationException e = (MySQLIntegrityConstraintViolationException)ex.getCause().getCause();
            return Result.FALL(e.getErrorCode(),e.getMessage());
        }
        return Result.FALL(ex.getMessage());
    }
    @ExceptionHandler(SQLException.class)
    public Result handlerSqlException(SQLException e) {
        log.error(e.getMessage(), e);
        return Result.FALL(e.getMessage());
    }
}
