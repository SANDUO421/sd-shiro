package com.module.authority.app.data.strategy;

import com.module.authority.app.data.enmu.DataScopeTypeEnum;
import com.module.authority.app.domain.RoleDTO;
import com.module.authority.app.service.ISysDeptService;
import com.module.authority.app.service.ISysUserService;
import com.module.common.security.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 三多
 * @Time 2020/3/5
 */
@Component("3")
public class ThisLevelChildenDataScope implements AbstractDataScopeHandler {
    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysDeptService deptService;

    /**
     * @param roleDTO
     * @param dataScopeTypeEnum
     * @return
     */
    @Override
    public List<Integer> getDeptIds(RoleDTO roleDTO, DataScopeTypeEnum dataScopeTypeEnum) {
        Integer deptId = userService.findByUserInfoName(SecurityUtil.getUser().getUsername()).getDeptId();
        return deptService.selectDeptIds(deptId);
    }
}
