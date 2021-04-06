package com.syxx.hr.controller;


import com.syxx.hr.entity.Employee;
import com.syxx.hr.service.EmployeeService;
import org.springframework.web.bind.annotation.RequestMapping;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.syxx.hr.common.result.Result;
import com.syxx.hr.common.result.ResultUtil;
import com.syxx.hr.common.utils.StringUtils;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.syxx.hr.service.EmployeeremoveService;
import com.syxx.hr.entity.Employeeremove;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/employeeremove")
public class EmployeeremoveController {
    @Autowired
    private EmployeeremoveService employeeremoveService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("getById/{id}")
    public Result getById(@PathVariable Integer id)
    {
        if(id==null)
        {
            return ResultUtil.parameterError();
        }
        return ResultUtil.success(employeeremoveService.getEntityById(id));
    }

    @PostMapping("getListByCondition")
    public Result getListByCondition(@RequestBody Employeeremove condition,@RequestParam("pageIndex") Integer pageIndex,@RequestParam("pageSize") Integer pageSize)
    {
        if(condition==null || pageIndex==null || pageIndex< 1||pageSize==null||pageSize< 1){
		 return ResultUtil.parameterError();
        }
		PageHelper.startPage(pageIndex, pageSize);
        List<Employeeremove> list= employeeremoveService.getListByCondition(condition);
		PageInfo<Employeeremove> result = new PageInfo<>(list);
        return ResultUtil.success(result);
    }

    @PostMapping("add")
    public Result add(@RequestBody Employeeremove entity)
    {
        if(entity==null){
		return ResultUtil.parameterError();
        }
        Employee employee = employeeService.getEntityById(entity.getEid());
        employee.setDepartmentId(entity.getAfterDepId());
        employee.setPosId(entity.getAfterJobId());
        employeeService.updateEntity(employee);
        return ResultUtil.judgmentResult(employeeremoveService.saveEntity(entity));
    }


    @PutMapping("update")
    public Result update(@RequestBody Employeeremove entity)
    {
        if(entity==null){
		return ResultUtil.parameterError();
        }
        Employee employee = employeeService.getEntityById(entity.getEid());
        employee.setDepartmentId(entity.getAfterDepId());
        employee.setPosId(entity.getAfterJobId());
        employeeService.updateEntity(employee);
        return ResultUtil.judgmentResult(employeeremoveService.updateEntity(entity));
    }


    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id)
    {
        if(id==null){
		 return ResultUtil.parameterError();
        }
        return ResultUtil.judgmentResult(employeeremoveService.deleteById(id));
    }

    @DeleteMapping("batchDelete")
    public Result batchDelete(@RequestParam String ids)
    {
        if(StringUtils.isBlank(ids))
        {
		  return ResultUtil.parameterError();
        }
        return ResultUtil.judgmentResult(employeeremoveService.deleteByIds(ids));
    }
}

