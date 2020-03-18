package com.yulin.library.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yulin.library.entity.SysUser;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

public interface SysUserMapper extends BaseMapper<SysUser> {

    @Select("select p.permission_expression from sys_user_permission up inner join sys_permission p on up.permission_id=p.id where up.user_id=#{id} union all select p.permission_expression from sys_user_role ur inner join sys_role_permission rp on ur.role_id=rp.role_id inner join sys_permission p on rp.permission_id=p.id where ur.user_id=#{id}")
    Set<String> getPermission(Long id);

}
