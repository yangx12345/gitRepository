package com.syxx.hr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.syxx.hr.common.exception.BusinessException;
import com.syxx.hr.common.pojo.TreeSelect;
import com.syxx.hr.entity.Department;
import com.syxx.hr.mapper.DepartmentMapper;
import com.syxx.hr.service.DepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.hutool.core.convert.Convert;


import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@Slf4j
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department>implements DepartmentService {

    @Override
    public Department getEntityById(Integer id){
        return this.getById(id);
    }

    @Override
    public List<Department>getListByCondition(Department condition){
        List<Integer> childrenIds = this.getDeptChildernIds(condition.getId(),true);
		return baseMapper.getListByCondition(condition,childrenIds);
    }

    @Override
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public boolean saveEntity(Department entity){
        boolean flag = this.save(entity);
        if (flag) {
            entity.setDepPath(entity.getDepPath()+"."+entity.getId());
            this.updateEntity(entity);
            Department parentDepartment = this.getEntityById(entity.getParentId());
            if (!parentDepartment.getIsParent())
            {
                parentDepartment.setIsParent(true);
                return this.updateEntity(parentDepartment);
            }
            return true;
        }
        return false;
    }

    @Override
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public boolean updateEntity(Department entity){
        Department findDepartment = this.getEntityById(entity.getId());
        if (!findDepartment.getEnabled().equals(entity.getEnabled()))
        {
            List<Integer> childrenIds = this.getDeptChildernIds(entity.getId(),false);
            if (!childrenIds.isEmpty())
            {
                this.baseMapper.batchUpdateStatus(entity.getEnabled(),childrenIds);
            }
        }
        return this.updateById(entity);
    }

    @Override
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public boolean deleteById(Integer id){
        if (id==1)
        {
            throw new BusinessException("顶级部门不可删除");
        }
        LambdaQueryWrapper<Department> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Department::getParentId,id);
        int count  = this.count(lambdaQueryWrapper);
        if (count>0)
        {
            throw new BusinessException("该部门下含有子部门,不可删除");
        }
        Department findDepartment = this.getEntityById(id);
        boolean flag = this.removeById(id);
        Department parentDepartment = this.getEntityById(findDepartment.getParentId());
        LambdaQueryWrapper<Department> lambdaQueryWrapper1 = new LambdaQueryWrapper<>();
        lambdaQueryWrapper1.eq(Department::getParentId,parentDepartment.getId());
        int count1  = this.count(lambdaQueryWrapper1);
        if (count1<1)
        {
            parentDepartment.setIsParent(false);
            return this.updateEntity(parentDepartment);
        }
        return flag;
    }

    @Override
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public boolean deleteByIds(String ids){
        Integer[]Ids=Convert.toIntArray(ids);
        List<Integer>idList=Arrays.asList(Ids);
        return this.removeByIds(idList);
    }

    @Override
    public List<Department> creatTreeById(List<Department> departments, Integer id,Boolean isIncludeParentNode) {
        List<Department> returnList = new ArrayList<Department>();
        for (Iterator<Department> iterator = departments.iterator(); iterator.hasNext(); ) {
            Department department = iterator.next();
            if (isIncludeParentNode) {
                if (department.getId().equals(id)) {
                    recursion(departments, department);
                    returnList.add(department);
                }
            } else {
                if (department.getParentId().equals(id)) {
                    recursion(departments, department);
                    returnList.add(department);
                }
            }
        }
        if (returnList.isEmpty()) {
            returnList = departments;
        }
        return returnList;

    }

    private void recursion(List<Department> list, Department department) {
        List<Department> childList = getChildList(list, department);
        department.setChildren(childList);
        for (Department tChild : childList) {
            if (hasChild(list, tChild)) {
                recursion(list, tChild);
            }
        }
    }

    private List<Department> getChildList(List<Department> list, Department department) {
        List<Department> departmentList = new ArrayList<Department>();
        Iterator<Department> it = list.iterator();
        while (it.hasNext()) {
            Department d = it.next();
            if (d.getParentId().longValue() == department.getId().longValue()) {
                departmentList.add(d);
            }
        }
        return departmentList;
    }

    private boolean hasChild(List<Department> list, Department department) {
        return getChildList(list, department).size() > 0;
    }


    @Override
    public List<Integer> getDeptChildernIds(Integer deptId,Boolean includdeptId) {
        String depPath = baseMapper.getDepPathById(deptId);
        List<Integer> childrenIds = baseMapper.getDeptChildernIds(depPath);
        if (includdeptId) {
           childrenIds.add(deptId);
            return childrenIds;
        }
        return childrenIds;
    }

    @Override
    public List<TreeSelect> buildTreeSelect(List<Department> departmentTrees) {
        return departmentTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }
}
