package com.module.authority.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.module.authority.app.data.datascope.DataScope;
import com.module.authority.app.domain.UserDTO;
import com.module.authority.app.entity.SysUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

/**
 * @author 三多
 * @Time 2020/3/1
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * 保存
     *
     * @param sysUser user对象
     * @return boolean
     */
    @Insert("insert into sys_user(username,password,dept_id,job_id,phone,email,avatar,lock_flag) " +
            "values (#{username},#{password},#{deptId},#{jobId},#{phone},#{email},#{avatar},#{lockFlag})")
    @Options(useGeneratedKeys = true, keyProperty = "userId", keyColumn = "user_id")
    boolean insertUser(SysUser sysUser);

    /**
     * 分页查询用户信息（含角色）
     *
     * @param page      分页
     * @param userDto   查询参数
     * @param dataScope 数据范围
     * @return 分页数据
     */
    IPage<SysUser> getUserVosPage(@Param("page") Page page, @Param("query") UserDTO userDto, @Param("dataScope") DataScope dataScope);
}
