package com.module.common.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @description:    ValidateCodeException 验证码校验
 * @author:         sanduo
 * @date:           2020/3/1 12:03
 * @version:        1.0
 */
public class ValidateCodeException extends AuthenticationException {

    private static final long serialVersionUID = 5022575393500654459L;

    public ValidateCodeException(String message) {
        super(message);
    }
}
