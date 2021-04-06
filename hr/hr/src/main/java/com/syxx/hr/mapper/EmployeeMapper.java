package com.syxx.hr.mapper;

import com.syxx.hr.entity.Department;
import com.syxx.hr.entity.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

    /**
     * 根据查询条件查询实体列表
     */
    List<Employee> getListByCondition(@Param("condition") Employee condition, @Param("ids") List<Integer> childrenIds);

	List<Employee> getEmployeeByDeptId(@Param("ids") List<Integer> childrenIds);

	List<Employee> getEmployeeByCurrentDeptId(Integer deptId);

	Employee getEmployeeInfoByWordId(String workId);

	List<String> getTiptopDegreeChartData();
}
