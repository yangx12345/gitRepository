package com.syxx.hr.service;

import com.syxx.hr.common.pojo.TreeSelect;
import com.syxx.hr.entity.Department;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;


public interface DepartmentService extends IService<Department> {

    /**
     * 根据id查询实体列表
     */
    public Department getEntityById(Integer id);

    /**
     * 根据条件查询实体列表
     */
    public List<Department> getListByCondition(Department condition);

    /**
     *  保存实体
     */
    public boolean saveEntity(Department entity);

    /**
     *  更新实体
     */
    public boolean updateEntity(Department entity);

    /**
     *  根据id删除实体
     */
    public boolean deleteById(Integer id);

    /**
     *  根据id列表删除
     */
    public boolean deleteByIds(String ids);

    public List<Department> creatTreeById(List<Department> departments, Integer id,Boolean isIncludeParentNode);

    public List<TreeSelect> buildTreeSelect(List<Department> departmentTrees);

    public List<Integer> getDeptChildernIds(Integer deptId,Boolean includdeptId);

}
