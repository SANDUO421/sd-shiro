package com.module.authority.app.data.strategy;

import com.module.authority.app.data.enmu.DataScopeTypeEnum;
import com.module.authority.app.domain.RoleDTO;
import com.module.authority.app.entity.SysDept;
import com.module.authority.app.service.ISysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  所有
 * @author 三多
 * @Time 2020/3/5
 */
@Component("1")
public class AllDataScope implements AbstractDataScopeHandler {

    @Autowired
    private ISysDeptService deptService;

    /**
     * @param roleDTO
     * @param dataScopeTypeEnum
     * @return
     */
    @Override
    public List<Integer> getDeptIds(RoleDTO roleDTO, DataScopeTypeEnum dataScopeTypeEnum) {
        List<SysDept> deptList = deptService.list();
        return deptList.stream().map(SysDept::getDeptId).collect(Collectors.toList());
    }
}
