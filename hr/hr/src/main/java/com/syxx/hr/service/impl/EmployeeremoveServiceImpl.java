package com.syxx.hr.service.impl;

import com.syxx.hr.entity.Employeeremove;
import com.syxx.hr.mapper.EmployeeremoveMapper;
import com.syxx.hr.service.EmployeeremoveService;
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
public class EmployeeremoveServiceImpl extends ServiceImpl<EmployeeremoveMapper, Employeeremove>implements EmployeeremoveService {

    @Override
    public Employeeremove getEntityById(Integer id){
        return this.getById(id);
    }

    @Override
    public List<Employeeremove>getListByCondition(Employeeremove condition){
		return baseMapper.getListByCondition(condition);
    }

    @Override
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public boolean saveEntity(Employeeremove entity){
        return this.save(entity);
    }

    @Override
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public boolean updateEntity(Employeeremove entity){
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
