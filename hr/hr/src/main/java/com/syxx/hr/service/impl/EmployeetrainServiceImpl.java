package com.syxx.hr.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syxx.hr.entity.Employee;
import com.syxx.hr.entity.Employeetrain;
import com.syxx.hr.mapper.EmployeetrainMapper;
import com.syxx.hr.service.EmployeeService;
import com.syxx.hr.service.EmployeetrainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional(readOnly = true)
@Slf4j
public class EmployeetrainServiceImpl extends ServiceImpl<EmployeetrainMapper, Employeetrain>implements EmployeetrainService {

    @Autowired
    private EmployeeService employeeService;


    @Override
    public Employeetrain getEntityById(Integer id){
        return this.getById(id);
    }

    @Override
    public List<Employeetrain>getListByCondition(Employeetrain condition){
		return baseMapper.getListByCondition(condition);
    }

    @Override
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public boolean saveEntity(Employeetrain entity){
        return this.save(entity);
    }

    @Override
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public boolean updateEntity(Employeetrain entity){
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
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public Boolean batchSave(Employeetrain employeetrain) {
        if (employeetrain.getWorkIds().isEmpty())
        {
            boolean flag = false;
            List<Employee> employees = employeeService.getEmployeeByDeptId(employeetrain.getDepartmentId());
            for(Employee employee:employees)
            {
                Employeetrain employeetrain1 = new Employeetrain();
                employeetrain1.setEid(employee.getId());
                employeetrain1.setWorkId(employee.getWorkID());
                employeetrain1.setName(employee.getName());
                employeetrain1.setTrainDate(employeetrain.getTrainDate());
                employeetrain1.setTrainContent(employeetrain.getTrainContent());
                employeetrain1.setRemark(employeetrain.getRemark());
                flag = this.save(employeetrain1);
            }
            return flag;
        }
        else
        {
            boolean flag = false;
            List<String> workIds = employeetrain.getWorkIds();
            for (String workId:workIds) {
              Employee employee = employeeService.getEmployeeInfoByWordId(workId);
              Employeetrain employeetrain1 = new Employeetrain();
              employeetrain1.setEid(employee.getId());
              employeetrain1.setWorkId(employee.getWorkID());
              employeetrain1.setName(employee.getName());
              employeetrain1.setTrainDate(employeetrain.getTrainDate());
              employeetrain1.setTrainContent(employeetrain.getTrainContent());
              employeetrain1.setRemark(employeetrain.getRemark());
              flag = this.save(employeetrain1);
            }
            return flag;
        }
    }
}
