package com.hrm.system.service;

import com.hrm.domain.system.entity.Permission;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 三多
 * @Time 2020/3/26
 */
public interface IPermissionService {
    /**
     * 查询所有
     * type: 查询全部权限列表type： 0：菜单+按钮，1:菜单，2：API接口
     * enVisible ：0 查询所有saasp平台的最高权限，1查询企业的权限
     * pid 父ID
     * @param map 参数
     * @return 权限列表 List<Permission>
     */
    List<Permission> findAll(Map map);
}
