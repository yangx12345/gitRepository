package com.syxx.hr.mapper;

import com.syxx.hr.entity.Employeetrain;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeetrainMapper extends BaseMapper<Employeetrain> {

    /**
     * 根据查询条件查询实体列表
     */
    List<Employeetrain> getListByCondition(Employeetrain condition);

}
