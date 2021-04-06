package com.syxx.hr.service.impl;

import com.syxx.hr.entity.Politicsstatus;
import com.syxx.hr.mapper.PoliticsstatusMapper;
import com.syxx.hr.service.PoliticsstatusService;
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
public class PoliticsstatusServiceImpl extends ServiceImpl<PoliticsstatusMapper, Politicsstatus>implements PoliticsstatusService {

    @Override
    public Politicsstatus getEntityById(Integer id){
        return this.getById(id);
    }

    @Override
    public List<Politicsstatus>getListByCondition(Politicsstatus condition){
		return baseMapper.getListByCondition(condition);
    }

    @Override
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public boolean saveEntity(Politicsstatus entity){
        return this.save(entity);
    }

    @Override
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public boolean updateEntity(Politicsstatus entity){
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
}
