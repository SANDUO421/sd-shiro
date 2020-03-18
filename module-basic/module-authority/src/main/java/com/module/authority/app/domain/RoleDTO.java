package com.module.authority.app.domain;

import com.module.authority.app.entity.SysRoleMenu;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @description:    角色Dto
 * @author:         sanduo
 * @date:           2020/3/4 12:21
 * @version:        1.0
 */
@Setter
@Getter
public class RoleDTO {

    private static final long serialVersionUID = 1L;

    private Integer roleId;
    private String roleName;
    private String roleCode;
    private String roleDesc;
    private String delFlag;
    private int dsType;
    List<SysRoleMenu> roleMenus;
    List<Integer> roleDepts;



}
