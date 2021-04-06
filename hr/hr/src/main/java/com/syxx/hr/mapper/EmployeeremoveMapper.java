package com.syxx.hr.mapper;

import com.syxx.hr.entity.Employeeremove;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeeremoveMapper extends BaseMapper<Employeeremove> {

    /**
     * 根据查询条件查询实体列表
     */
    List<Employeeremove> getListByCondition(Employeeremove condition);

}
