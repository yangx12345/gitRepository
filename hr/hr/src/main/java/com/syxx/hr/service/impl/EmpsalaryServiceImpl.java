package com.syxx.hr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.syxx.hr.common.exception.BusinessException;
import com.syxx.hr.entity.Empsalary;
import com.syxx.hr.mapper.EmpsalaryMapper;
import com.syxx.hr.service.EmpsalaryService;
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
public class EmpsalaryServiceImpl extends ServiceImpl<EmpsalaryMapper, Empsalary>implements EmpsalaryService {

    @Override
    public Empsalary getEntityById(Integer id){
        return this.getById(id);
    }

    @Override
    public List<Empsalary>getListByCondition(Empsalary condition){
		return baseMapper.getListByCondition(condition);
    }

    @Override
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public boolean saveEntity(Empsalary entity){
        LambdaQueryWrapper<Empsalary> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Empsalary::getEid,entity.getEid()).eq(Empsalary::getSalaryDate,entity.getSalaryDate());
        Empsalary empsalary = this.baseMapper.selectOne(lambdaQueryWrapper);
        if (empsalary!=null)
        {
            throw new BusinessException("本月份该员工已有工资记录");
        }
        return this.save(entity);
    }

    @Override
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public boolean updateEntity(Empsalary entity){
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
