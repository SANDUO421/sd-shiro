package com.module.authority.app.data.strategy;

import com.module.authority.app.data.enmu.DataScopeTypeEnum;
import com.module.authority.app.domain.RoleDTO;
import com.module.authority.app.service.ISysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author 三多
 * @Time 2020/3/5
 */

@Component("4")
public class CustomizeDataScope implements AbstractDataScopeHandler {

    @Autowired
    private ISysDeptService deptService;
    /**
     * @param roleDTO
     * @param dataScopeTypeEnum
     * @return
     */
    @Override
    public List<Integer> getDeptIds(RoleDTO roleDTO, DataScopeTypeEnum dataScopeTypeEnum) {
        List<Integer> roleDeptIds = roleDTO.getRoleDepts();
        List<Integer> ids = new ArrayList<>();
        for (Integer roleDeptId : roleDeptIds) {
            ids.addAll(deptService.selectDeptIds(roleDeptId));
        }
        Set<Integer> set = new HashSet<>(ids);
        ids.clear();
        ids.addAll(set);
        return ids;
    }
}
