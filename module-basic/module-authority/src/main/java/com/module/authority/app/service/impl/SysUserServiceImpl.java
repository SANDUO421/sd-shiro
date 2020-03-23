package com.module.authority.app.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.module.authority.app.data.datascope.DataScope;
import com.module.authority.app.domain.UserDTO;
import com.module.authority.app.entity.SysUser;
import com.module.authority.app.entity.SysUserRole;
import com.module.authority.app.mapper.SysUserMapper;
import com.module.authority.app.security.utils.JwtUtil;
import com.module.authority.app.service.ISysDeptService;
import com.module.authority.app.service.ISysUserRoleService;
import com.module.authority.app.service.ISysUserService;
import com.module.authority.app.social.SocialRedisHelper;
import com.module.authority.app.utils.PreUtil;
import com.module.authority.app.utils.UserUtils;
import com.module.common.exception.PreBaseException;
import com.module.common.security.domain.PreSecurityUser;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.UserDataHandler;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 三多
 * @Time 2020/3/1
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    private static final String RAW_PASSWORD = "123456";
    @Autowired
    private ISysUserRoleService userRoleService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private SocialRedisHelper socialRedisHelper;
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private ISysDeptService sysDeptService;


    /**
     * 注册用户
     *
     * @param userDTO 用户实体
     * @return 返回
     */
    @Override
    public boolean register(UserDTO userDTO) {
        //查看用户名是否存在(用户名、手机号)
        SysUser sysUserByUsername = findSecurityUser(userDTO.getUsername());
        if (ObjectUtil.isNotNull(sysUserByUsername)) {
            throw new PreBaseException("账户名已被注册");
        }
        SysUser sysUserByPhone = findSecurityUser(userDTO.getPhone());
        if (ObjectUtil.isNotNull(sysUserByPhone)) {
            throw new PreBaseException("手机号已被注册");
        }
        userDTO.setDeptId(6);
        userDTO.setLockFlag("0");
        SysUser sysUser = new SysUser();
        //对象拷贝
        BeanUtil.copyProperties(userDTO, sysUser);
        //加密后的密码
        sysUser.setPassword(UserUtils.encode(sysUser.getPassword()));
        baseMapper.insertUser(sysUser);
        SysUserRole userRole = new SysUserRole();
        userRole.setRoleId(14);
        userRole.setUserId(sysUser.getUserId());
        return userRoleService.save(userRole);
    }

    /**
     * 第三方账号信息与系统账号进行绑定
     *
     * @param sysUser 系统账户
     * @return boolean
     */
    @Override
    public boolean doPostSignUp(SysUser sysUser) {
        //进行账号校验
        SysUser user = findSecurityUserByUser(new SysUser().setUsername(sysUser.getUsername()));
        if (ObjectUtil.isNull(user)) {
            throw new PreBaseException("账号不存在");
        }
        Integer userId = user.getUserId();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(sysUser.getUsername(), sysUser.getPassword()));
        //将业务系统的用户与社交用户绑定
        socialRedisHelper.doPostSignUp(user.getKey(), userId);
        return true;
    }

    /**
     * 通过用户名称查询用户
     *
     * @param sysUser 用户
     * @return SysUser
     */
    @Override
    public SysUser findSecurityUserByUser(SysUser sysUser) {
        LambdaQueryWrapper<SysUser> queryWrapper = new QueryWrapper<SysUser>().lambda()
                .select(SysUser::getUserId, SysUser::getUsername, SysUser::getPassword)
                .eq(StringUtils.isNotEmpty(sysUser.getUsername()), SysUser::getUsername, sysUser.getUsername())
                .eq(StringUtils.isNotEmpty(sysUser.getPhone()), SysUser::getUsername, sysUser.getPhone())
                .eq(ObjectUtil.isNotNull(sysUser.getUserId()), SysUser::getUsername, sysUser.getUserId());

        return baseMapper.selectOne(queryWrapper);
    }

    /**
     * 根据userID 、userName、phone用户查看用户
     *
     * @param userIdOrUserNameOrPhone
     * @return
     */
    private SysUser findSecurityUser(String userIdOrUserNameOrPhone) {
        LambdaQueryWrapper<SysUser> queryWrapper = new QueryWrapper<SysUser>().lambda()
                .select(SysUser::getUserId, SysUser::getUsername, SysUser::getPassword)
                .eq(StringUtils.isNotEmpty(userIdOrUserNameOrPhone), SysUser::getUsername, userIdOrUserNameOrPhone)
                .or()
                .eq(StringUtils.isNotEmpty(userIdOrUserNameOrPhone), SysUser::getPhone, userIdOrUserNameOrPhone)
                .or()
                .eq(StringUtils.isNotEmpty(userIdOrUserNameOrPhone), SysUser::getUserId, userIdOrUserNameOrPhone);
        return baseMapper.selectOne(queryWrapper);
    }

    /**
     * 账户密码登录
     *
     * @param username 用户名
     * @param password 密码
     * @return String
     */
    @Override
    public String login(String username, String password) {
        //用户验证
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        //存储认证信息
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //生成token
        PreSecurityUser userDetail = (PreSecurityUser) authentication.getPrincipal();
        return JwtUtil.generateToken(userDetail);
    }

    /**
     * 保存用户以及角色部门等信息
     *
     * @param userDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insertUser(UserDTO userDto) {
        /********保存用户********/
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(userDto, sysUser);
        // 默认密码 123456
        sysUser.setPassword(PreUtil.encode(RAW_PASSWORD));
        baseMapper.insertUser(sysUser);
        /**********关联用户和角色********/
        List<SysUserRole> sysUserRoles = userDto.getRoleList().stream().map(item -> {
            SysUserRole userRole = new SysUserRole();
            userRole.setUserId(sysUser.getUserId());
            userRole.setRoleId(item);
            return userRole;
        }).collect(Collectors.toList());
        return userRoleService.saveBatch(sysUserRoles);
    }

    /**
     * 查询用户集合
     *
     * @param page    分页对象
     * @param userDto 参数列表
     * @return
     */
    @Override
    public IPage<SysUser> getUsersWithRolePage(Page page, UserDTO userDto) {
        if (ObjectUtils.anyNotNull(userDto) && ObjectUtil.isNotNull(userDto.getDeptId())) {
            userDto.setDeptList(sysDeptService.selectDeptIds(userDto.getDeptId()));
        }
        return baseMapper.getUserVosPage(page,userDto,new DataScope());
    }

    /**
     * 更新用户以及角色部门等信息
     *
     * @param userDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateUser(UserDTO userDto) {
        /********修改用户********/
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(userDto,sysUser);
        baseMapper.updateById(sysUser);
        /********移除用户角色关系********/
        userRoleService.remove(new QueryWrapper<SysUserRole>().lambda().eq(SysUserRole::getUserId,sysUser.getUserId()));
        /********添加用户角色关系********/
        List<SysUserRole> userRoleList = userDto.getRoleList().stream().map(item -> {
            SysUserRole userRole = new SysUserRole();
            userRole.setRoleId(item);
            userRole.setUserId(sysUser.getUserId());
            return userRole;
        }).collect(Collectors.toList());
        return userRoleService.saveBatch(userRoleList);
    }

    /**
     * 通过用户名查找用户个人信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    @Override
    public SysUser findByUserInfoName(String username) {
        SysUser sysUser = baseMapper.selectOne(new QueryWrapper<SysUser>().lambda()
                .select(SysUser::getUserId, SysUser::getUsername, SysUser::getPhone, SysUser::getEmail, SysUser::getPassword, SysUser::getDeptId, SysUser::getJobId, SysUser::getAvatar)
                .eq(SysUser::getUsername, username));
        //获取部门
        sysUser.setDeptName(sysDeptService.selectDeptNameByDeptId(sysUser.getDeptId()));
        //获取岗位
       // sysUser.setJobName(jobService.selectJobNameByJobId(sysUser.getJobId()));
        return sysUser;
    }

    /**
     * 根据用户id查询权限
     *
     * @param userId
     * @return
     */
    @Override
    public Set<String> findPermsByUserId(Integer userId) {
        return userRoleService
                .selectUserRoleListByUserId(userId)
                .stream()
                .map(sysUserRole -> "ROLE_" + sysUserRole.getRoleId())
                .collect(Collectors.toSet());
    }

    /**
     * 通过用户id查询角色集合
     *
     * @param userId
     * @return
     */
    @Override
    public Set<String> findRoleIdByUserId(Integer userId) {
        return userRoleService
                .selectUserRoleListByUserId(userId)
                .stream()
                .map(sysUserRole -> "ROLE_" + sysUserRole.getRoleId())
                .collect(Collectors.toSet());
    }
}
