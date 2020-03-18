package com.module.authority.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.module.authority.app.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @description:    用户角色表 Mapper 接口
 * @author:         sanduo
 * @date:           2020/3/1 16:23
 * @version:        1.0
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
    /**
     * 根据用户Id查询角色列表
     * @param userId
     * @return
     */
    @Select("SELECT r.role_name,ur.role_id FROM " +
            "(sys_role r LEFT JOIN sys_user_role ur ON r.role_id = ur.role_id) " +
            "LEFT JOIN sys_user u ON u.user_id = ur.user_id WHERE u.user_id = #{userId}")
    List<SysUserRole> selectUserRoleListByUserId(Integer userId);
}
