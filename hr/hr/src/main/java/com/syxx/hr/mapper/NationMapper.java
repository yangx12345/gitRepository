package com.syxx.hr.mapper;

import com.syxx.hr.entity.Nation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NationMapper extends BaseMapper<Nation> {

    /**
     * 根据查询条件查询实体列表
     */
    List<Nation> getListByCondition(Nation condition);

}
