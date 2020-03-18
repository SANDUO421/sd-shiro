package com.module.authority.app.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.module.authority.app.domain.DeptDTO;
import com.module.authority.app.entity.SysDept;
import com.module.authority.app.mapper.SysDeptMapper;
import com.module.authority.app.service.ISysDeptService;
import com.module.authority.app.vo.DeptTreeVo;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description: 部门管理 服务实现类
 * @author: sanduo
 * @date: 2020/3/4 12:29
 * @version: 1.0
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements ISysDeptService {

    /**
     * 查询部门信息
     *
     * @return
     */
    @Override
    public List<SysDept> selectDeptList() {
        return null;
    }

    /**
     * 更新部门
     *
     * @param entity
     * @return
     */
    @Override
    public boolean updateDeptById(DeptDTO entity) {
        return false;
    }

    /**
     * 删除部门
     *
     * @param id
     * @return
     */
    @Override
    public boolean removeById(Serializable id) {
        return false;
    }

    /**
     * 根据部门id查询部门名称
     *
     * @param deptId
     * @return
     */
    @Override
    public String selectDeptNameByDeptId(int deptId) {
        return baseMapper.selectOne(new QueryWrapper<SysDept>().lambda().select(SysDept::getName).eq(SysDept::getDeptId,deptId)).getName();
    }

    /**
     * 根据部门名称查询
     *
     * @param deptName
     * @return
     */
    @Override
    public List<SysDept> selectDeptListBydeptName(String deptName) {
        return null;
    }

    /**
     * 通过此部门id查询于此相关的部门ids
     *
     * @param deptId
     * @return
     */
    @Override
    public List<Integer> selectDeptIds(int deptId) {
        SysDept sysDept = this.getDepartment(deptId);
        List<Integer> deptIdList = new ArrayList<>();
        if (ObjectUtil.isNotNull(sysDept)) {
            deptIdList.add(sysDept.getDeptId());
            addDeptIdList(deptIdList, sysDept);
        }
        return deptIdList;
    }

    /**
     * 根据部门ID获取该部门及其下属部门树
     *
     * @param deptId 部门Id
     * @return
     */
    private SysDept getDepartment(int deptId) {

        List<SysDept> departments = baseMapper.selectList(new QueryWrapper<SysDept>()
                .select("dept_id", "name", "parent_id", "sort", "create_time")
        );
        Map<Integer, SysDept> map = departments.stream().collect(Collectors.toMap(SysDept::getDeptId, department -> department));
        //TODO 有问题，测试时候需要调试
        for (SysDept dept : map.values()) {
            SysDept parent = map.get(dept.getParentId());
            if (parent != null) {
                List<SysDept> children = parent.getChildren() == null ? new ArrayList<>() : parent.getChildren();
                children.add(dept);
                parent.setChildren(children);
            }
        }
        return map.get(deptId);
    }

    /**
     * 构造部门树形结构
     *
     * @param deptIdList
     * @param sysDept
     */
    private void addDeptIdList(List<Integer> deptIdList, SysDept sysDept) {
        List<SysDept> children = sysDept.getChildren();
        if (ObjectUtil.isNotNull(children)) {
            for (SysDept child : children) {
                deptIdList.add(child.getDeptId());
                addDeptIdList(deptIdList, child);
            }
        }
    }

    /**
     * 获取部门树
     *
     * @return
     */
    @Override
    public List<DeptTreeVo> getDeptTree() {
        return null;
    }
}
