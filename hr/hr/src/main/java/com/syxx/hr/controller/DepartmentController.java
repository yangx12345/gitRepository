package com.syxx.hr.controller;


import com.syxx.hr.common.enums.RoleEnum;
import com.syxx.hr.common.pojo.TreeSelect;
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

import com.syxx.hr.service.DepartmentService;
import com.syxx.hr.entity.Department;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("getById/{id}")
    public Result getById(@PathVariable Integer id)
    {
        if(id==null)
        {
            return ResultUtil.parameterError();
        }
        return ResultUtil.success(departmentService.getEntityById(id));
    }

    @PostMapping("getListByCondition")
    public Result getListByCondition(@RequestBody Department condition,@RequestParam Integer role)
    {
        if(condition==null || condition.getId() == null || role == null){
		  return ResultUtil.parameterError();
        }
        if (RoleEnum.EMPLOYEE.getCode().equals(role))
        {
            List<Department> list = new ArrayList<>();
            list.add(departmentService.getEntityById(condition.getId()));
            return ResultUtil.success(list);
        }
        List<Department> departments= departmentService.getListByCondition(condition);
        if (departments.isEmpty())
        {
            return ResultUtil.success(departments);
        }
        List<Department> list = departmentService.creatTreeById(departments,condition.getId(), true);
        return ResultUtil.success(list);
    }

    @PostMapping("add")
    public Result add(@RequestBody Department entity)
    {
        if(entity==null){
		return ResultUtil.parameterError();
        }
        return ResultUtil.judgmentResult(departmentService.saveEntity(entity));
    }


    @PutMapping("update")
    public Result update(@RequestBody Department entity)
    {
        if(entity==null){
		return ResultUtil.parameterError();
        }
        return ResultUtil.judgmentResult(departmentService.updateEntity(entity));
    }


    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id)
    {
        if(id==null){
		 return ResultUtil.parameterError();
        }
        return ResultUtil.judgmentResult(departmentService.deleteById(id));
    }

    @DeleteMapping("batchDelete")
    public Result batchDelete(@RequestParam String ids)
    {
        if(StringUtils.isBlank(ids))
        {
		  return ResultUtil.parameterError();
        }
        return ResultUtil.judgmentResult(departmentService.deleteByIds(ids));
    }

    @GetMapping("getSelectTree")
    public Result getSelectTree(Department department,@RequestParam Integer id) {
        List<Department> departments = departmentService.getListByCondition(department);
        List<Department> departmentTrees = departmentService.creatTreeById(departments,id, true);
        List<TreeSelect> departmentSelect = departmentService.buildTreeSelect(departmentTrees);
        return ResultUtil.success(departmentSelect);
    }

    @GetMapping("getList")
    public Result getList()
    {
        return ResultUtil.success(departmentService.list());
    }
}

