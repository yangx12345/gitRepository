package com.syxx.hr.mapper;

import com.syxx.hr.entity.Empsalary;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpsalaryMapper extends BaseMapper<Empsalary> {

    /**
     * 根据查询条件查询实体列表
     */
    List<Empsalary> getListByCondition(Empsalary condition);

}
