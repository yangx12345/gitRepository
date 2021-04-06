package com.syxx.hr.controller;


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

import com.syxx.hr.service.EmployeetrainService;
import com.syxx.hr.entity.Employeetrain;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/employeetrain")
public class EmployeetrainController {
    @Autowired
    private EmployeetrainService employeetrainService;

    @GetMapping("getById/{id}")
    public Result getById(@PathVariable Integer id)
    {
        if(id==null)
        {
            return ResultUtil.parameterError();
        }
        return ResultUtil.success(employeetrainService.getEntityById(id));
    }

    @PostMapping("getListByCondition")
    public Result getListByCondition(@RequestBody Employeetrain condition,@RequestParam("pageIndex") Integer pageIndex,@RequestParam("pageSize") Integer pageSize)
    {
        if(condition==null || pageIndex==null || pageIndex< 1||pageSize==null||pageSize< 1){
		 return ResultUtil.parameterError();
        }
		PageHelper.startPage(pageIndex, pageSize);
        List<Employeetrain> list= employeetrainService.getListByCondition(condition);
		PageInfo<Employeetrain> result = new PageInfo<>(list);
        return ResultUtil.success(result);
    }

    @PostMapping("add")
    public Result add(@RequestBody Employeetrain entity)
    {
        if(entity==null){
		return ResultUtil.parameterError();
        }
        return ResultUtil.judgmentResult(employeetrainService.saveEntity(entity));
    }


    @PutMapping("update")
    public Result update(@RequestBody Employeetrain entity)
    {
        if(entity==null){
		return ResultUtil.parameterError();
        }
        return ResultUtil.judgmentResult(employeetrainService.updateEntity(entity));
    }


    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id)
    {
        if(id==null){
		 return ResultUtil.parameterError();
        }
        return ResultUtil.judgmentResult(employeetrainService.deleteById(id));
    }

    @DeleteMapping("batchDelete")
    public Result batchDelete(@RequestParam String ids)
    {
        if(StringUtils.isBlank(ids))
        {
		  return ResultUtil.parameterError();
        }
        return ResultUtil.judgmentResult(employeetrainService.deleteByIds(ids));
    }

    @PostMapping("batchSave")
    public Result batchSave(@RequestBody Employeetrain employeetrain)
    {
        if(employeetrain == null)
        {
            return ResultUtil.parameterError();
        }
        return ResultUtil.judgmentResult(employeetrainService
                .batchSave(employeetrain));
    }
}

