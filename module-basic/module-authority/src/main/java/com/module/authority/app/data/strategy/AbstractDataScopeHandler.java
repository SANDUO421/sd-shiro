package com.module.authority.app.data.strategy;

import com.module.authority.app.data.enmu.DataScopeTypeEnum;
import com.module.authority.app.domain.RoleDTO;

import java.util.List;

/**
 * 创建抽象策略角色 主要作用 数据权限范围使用
 * @author 三多
 * @Time 2020/3/5
 */
public interface AbstractDataScopeHandler {
    /**
     *
     * @param roleDTO
     * @param dataScopeTypeEnum
     * @return
     */
    List<Integer> getDeptIds(RoleDTO roleDTO, DataScopeTypeEnum dataScopeTypeEnum);
}
