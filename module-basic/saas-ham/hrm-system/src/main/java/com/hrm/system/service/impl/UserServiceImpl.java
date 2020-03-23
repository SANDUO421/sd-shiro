package com.hrm.system.service.impl;

import com.hrm.common.service.BaseService;
import com.hrm.domain.system.entity.User;
import com.hrm.system.dao.UserDao;
import com.hrm.system.service.IUserService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 三多
 * @Time 2020/3/23
 */
@Service
public class UserServiceImpl extends BaseService implements IUserService {
    @Autowired
    private UserDao userDao;

    /**
     * 根据公司ID查询部门列表
     * 1. 构造查询条件
     * 2. 查询
     * 3. 参数:map集合的形式
     *          hasDept
     *          departmentId
     *          companyId
     *
     * @param map 参数
     * @param page 当前页
     * @param pageSize 页数
     * @return 用户列表
     */
    @Override
    public Page findAll(Map map,int page,int pageSize) {
        //1. 查询条件
        Specification spec = new Specification() {
            /**
             * 动态拼接条件
             * @param root
             * @param query
             * @param criteriaBuilder
             * @return
             */
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList();
                //companyId
                if(ObjectUtils.isNotEmpty(map.get("companyId"))){
                    list.add(criteriaBuilder.equal(root.get("companyId").as(String.class),String.valueOf(map.get("companyId"))));
                }
                //部门Id
                if(ObjectUtils.isNotEmpty(map.get("departmentId"))){
                    list.add(criteriaBuilder.equal(root.get("departmentId").as(String.class),String.valueOf(map.get("departmentId"))));
                }
                //hasDept 是否分配部门 0 未分配(departmentId = null), 1 分配(departmentId != null)
                if(ObjectUtils.isEmpty(map.get("hasDept"))||"0".equals(String.valueOf(map.get("hasDept")))){
                    list.add(criteriaBuilder.isNull(root.get("departmentId")));
                }else{
                    list.add(criteriaBuilder.isNotNull(root.get("departmentId")));
                }
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
        //2. 分页
        Page<User> pageUser = userDao.findAll(spec, PageRequest.of(page, pageSize));
        return pageUser;
    }

    /**
     * 保存
     *
     * @param user 用户
     * @return int
     */
    @Override
    public User save(User user) {
        User entity = userDao.save(user);
        //设置用户基本信息
        user.setPassword("123456");
        user.setEnableState(1);
        return entity;
    }

    /**
     * 查询
     *
     * @param id 部门id
     * @return 用户
     */
    @Override
    public User findById(String id) {
        return userDao.findById(id).get();
    }

    /**
     * 修改
     *
     * @param user
     */
    @Override
    public void update(User user) {
        // 1.根据Id查找用户
        User target = findById(user.getId());
        //2.根据Id查找用户
        target.setUsername(user.getUsername());
        target.setPassword(user.getPassword());
        target.setDepartmentId(user.getDepartmentId());
        target.setDepartmentName(user.getDepartmentName());
        //3. 更新用户
        userDao.save(target);
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void delete(String id) {
        userDao.deleteById(id);
    }
}
