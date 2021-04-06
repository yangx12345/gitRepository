package com.syxx.hr.mapper;

import com.syxx.hr.entity.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DepartmentMapper extends BaseMapper<Department> {

    /**
     * 根据查询条件查询实体列表
     */
    List<Department> getListByCondition(@Param("condition") Department condition,@Param("ids") List<Integer> childrenIds);

	String getDepPathById(Integer deptId);

	List<Integer> getDeptChildernIds(String depPath);

	void batchUpdateStatus(@Param("enabled") Boolean enabled, @Param("childrenIds") List<Integer> childrenIds);
}
