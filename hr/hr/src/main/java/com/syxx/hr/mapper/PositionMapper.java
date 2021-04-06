package com.syxx.hr.mapper;

import com.syxx.hr.entity.Position;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PositionMapper extends BaseMapper<Position> {

    /**
     * 根据查询条件查询实体列表
     */
    List<Position> getListByCondition(Position condition);

}
