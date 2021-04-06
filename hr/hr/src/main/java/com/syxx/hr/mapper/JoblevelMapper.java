package com.syxx.hr.mapper;

import com.syxx.hr.entity.Joblevel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JoblevelMapper extends BaseMapper<Joblevel> {

    /**
     * 根据查询条件查询实体列表
     */
    List<Joblevel> getListByCondition(Joblevel condition);

}
