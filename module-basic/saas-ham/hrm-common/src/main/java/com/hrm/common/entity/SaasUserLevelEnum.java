package com.hrm.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 三多
 * @Time 2020/3/26
 */
@Getter
@AllArgsConstructor
public enum SaasUserLevelEnum {
    SAAS_ADMIN("saasAdmin","saas平台管理员"),
    CO_ADMIN("coAdmin","企业管理员"),
    USER("user","用户");


    /**
     * 代号
     */
    String code;
    /**
     * 描述
     */
    String desc;
}
