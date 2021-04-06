package com.syxx.hr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.syxx.hr.entity.Joblevel;
import com.syxx.hr.mapper.JoblevelMapper;
import com.syxx.hr.service.JoblevelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.hutool.core.convert.Convert;



import java.util.Arrays;
import java.util.List;

@Service
@Transactional(readOnly = true)
@Slf4j
public class JoblevelServiceImpl extends ServiceImpl<JoblevelMapper, Joblevel>implements JoblevelService {

    @Override
    public Joblevel getEntityById(Integer id){
        return this.getById(id);
    }

    @Override
    public List<Joblevel>getListByCondition(Joblevel condition){
		return baseMapper.getListByCondition(condition);
    }

    @Override
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public boolean saveEntity(Joblevel entity){
        return this.save(entity);
    }

    @Override
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public boolean updateEntity(Joblevel entity){
        return this.updateById(entity);
    }

    @Override
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public boolean deleteById(Integer id){
        return this.removeById(id);
    }

    @Override
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public boolean deleteByIds(String ids){
        Integer[]Ids=Convert.toIntArray(ids);
        List<Integer>idList=Arrays.asList(Ids);
        return this.removeByIds(idList);
    }

    @Override
    public List<Joblevel> getList() {
        QueryWrapper<Joblevel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("enabled",1);
        return baseMapper.selectList(queryWrapper);
    }
}
