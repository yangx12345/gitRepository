package com.syxx.hr.mapper;

import com.syxx.hr.entity.Salary;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SalaryMapper extends BaseMapper<Salary> {

    /**
     * 根据查询条件查询实体列表
     */
    List<Salary> getListByCondition(Salary condition);

	List<Salary> getList();
}
