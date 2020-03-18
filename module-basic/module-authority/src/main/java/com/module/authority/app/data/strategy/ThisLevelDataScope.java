package com.module.authority.app.data.strategy;

import com.module.authority.app.data.enmu.DataScopeTypeEnum;
import com.module.authority.app.domain.RoleDTO;
import com.module.authority.app.service.ISysUserService;
import com.module.common.security.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 本级
 * @author 三多
 * @Time 2020/3/5
 */
@Component("2")
public class ThisLevelDataScope implements AbstractDataScopeHandler {

    @Autowired
    private ISysUserService userService;

    /**
     * @param roleDTO
     * @param dataScopeTypeEnum
     * @return
     */
    @Override
    public List<Integer> getDeptIds(RoleDTO roleDTO, DataScopeTypeEnum dataScopeTypeEnum) {
        // 用于存储部门id
        List<Integer> deptIds = new ArrayList<>();
        deptIds.add(userService.findByUserInfoName(SecurityUtil.getUser().getUsername()).getDeptId());
        return deptIds;
    }
}
