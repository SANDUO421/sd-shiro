package com.hrm.system.service;

import com.hrm.common.entity.PermissionEnum;
import com.hrm.domain.system.entity.Permission;
import com.hrm.system.dao.PermissionDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author 三多
 * @Time 2020/3/26
 */
@Service
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    private PermissionDao permissionDao;

    /**
     * 查询所有
     * type: 查询全部权限列表type： 0：菜单+按钮，1:菜单，2：API接口
     * enVisible ：0 查询所有saasp平台的最高权限，1查询企业的权限
     * pid 父ID
     *
     * @param map 参数
     * @return 权限列表 List<Permission>
     */
    @Override
    public List<Permission> findAll(Map map) {
        //需要查询条件
        Specification<Permission> spec = (root, criteriaQuery,criteriaBuilder)->{
            List<Predicate> list = new ArrayList<>();
            //根据父id查询
            if(Objects.nonNull(root.get("pid"))){
                list.add(criteriaBuilder.equal(root.get("pid").as(String.class),(String)map.get("pid")));
            }
            //根据enVisible查询
            if(Objects.nonNull(root.get("enVisible"))){
                list.add(criteriaBuilder.equal(root.get("enVisible").as(String.class),(String)map.get("enVisible")));
            }
            //根据type查询
            if(Objects.nonNull(root.get("type"))){
                String ty = String.valueOf(root.get("type"));
                CriteriaBuilder.In<Object> in = criteriaBuilder.in(root.get("type"));
                if(String.valueOf(PermissionEnum.MENU_AND_POINT.getCode()).equals(ty)){
                    //菜单+按钮
                    in.value(PermissionEnum.MENU.getCode()).value(PermissionEnum.POINT.getCode());
                }else{
                    in.value(Integer.parseInt(ty));
                }
                list.add(in);
            }
            return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
        };

        return permissionDao.findAll(spec);
    }
}
