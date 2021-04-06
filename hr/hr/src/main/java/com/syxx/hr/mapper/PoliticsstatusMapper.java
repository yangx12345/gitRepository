package com.syxx.hr.mapper;

import com.syxx.hr.entity.Politicsstatus;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PoliticsstatusMapper extends BaseMapper<Politicsstatus> {

    /**
     * 根据查询条件查询实体列表
     */
    List<Politicsstatus> getListByCondition(Politicsstatus condition);

}
