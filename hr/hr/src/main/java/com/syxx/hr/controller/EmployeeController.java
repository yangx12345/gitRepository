package com.syxx.hr.controller;


import cn.hutool.crypto.SecureUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.syxx.hr.common.enums.RegisterStatusEnum;
import com.syxx.hr.common.pojo.PieChartData;
import com.syxx.hr.common.result.Result;
import com.syxx.hr.common.result.ResultUtil;
import com.syxx.hr.common.utils.StringUtils;
import com.syxx.hr.entity.Department;
import com.syxx.hr.entity.Employee;
import com.syxx.hr.service.DepartmentService;
import com.syxx.hr.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("getById/{id}")
    public Result getById(@PathVariable Integer id)
    {
        if(id==null)
        {
            return ResultUtil.parameterError();
        }
        return ResultUtil.success(employeeService.getEntityById(id));
    }

    @PostMapping("getListByCondition")
    public Result getListByCondition(@RequestBody Employee condition,@RequestParam("pageIndex") Integer pageIndex,@RequestParam("pageSize") Integer pageSize)
    {
        if(condition==null || pageIndex==null || pageIndex< 1||pageSize==null||pageSize< 1){
		 return ResultUtil.parameterError();
        }
        List<Integer> childrenIds = departmentService.getDeptChildernIds(condition.getDepartmentId(),true);
		PageHelper.startPage(pageIndex, pageSize);
        List<Employee> list= employeeService.getListByCondition(condition,childrenIds);
		PageInfo<Employee> result = new PageInfo<>(list);
        return ResultUtil.success(result);
    }

    @PostMapping("add")
    public Result add(@RequestBody Employee entity)
    {
        if(entity==null){
		return ResultUtil.parameterError();
        }
        Employee findEmployee  =  employeeService.getByUsernameAndPassword(entity.getUsername(),entity.getPassword());
        if (findEmployee != null)
        {
            return ResultUtil.error("用户名已存在");
        }
        Employee findEmployee1 = employeeService.getEmployeeByWordId(entity.getWorkID());
        if (findEmployee1!=null)
        {
            return ResultUtil.error("工号已存在");
        }
        entity.setRegisterStatus(RegisterStatusEnum.REVIEWED.getCode());
        return ResultUtil.judgmentResult(employeeService.saveEntity(entity));
    }


    @PutMapping("update")
    public Result update(@RequestBody Employee entity)
    {
        if(entity==null){
		return ResultUtil.parameterError();
        }
        if (entity.getWorkID()!=null)
        {
            Employee employee = employeeService.getEmployeeByWordId(entity.getWorkID());
            if (employee!=null && !entity.getId().equals(employee.getId()))
            {
                return ResultUtil.error("工号已存在");
            }
            else
            {
                return ResultUtil.judgmentResult(employeeService.updateEntity(entity));
            }
        }
        return ResultUtil.error("请填写工号");
    }


    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id)
    {
        if(id==null){
		 return ResultUtil.parameterError();
        }
        return ResultUtil.judgmentResult(employeeService.deleteById(id));
    }

    @DeleteMapping("batchDelete")
    public Result batchDelete(@RequestParam String ids)
    {
        if(StringUtils.isBlank(ids))
        {
		  return ResultUtil.parameterError();
        }
        return ResultUtil.judgmentResult(employeeService.deleteByIds(ids));
    }

    @PostMapping("login")
    public Result login(@RequestBody Employee employee)
    {
       if (employee == null || StringUtils.isBlank(employee.getUsername()) || StringUtils.isBlank(employee.getPassword()))
       {
           return ResultUtil.parameterError();
       }
       Employee findEmployee  =  employeeService.getByUsernameAndPassword(employee.getUsername(),employee.getPassword());
       if (findEmployee == null)
       {
           return ResultUtil.error("账号密码错误或用户不存在");
       }
       if (!RegisterStatusEnum.REVIEWED.getCode().equals(findEmployee.getRegisterStatus()))
       {
           return ResultUtil.error("账号正在审核中或审核未通过");
       }
       Department department = departmentService.getEntityById(findEmployee.getDepartmentId());
       if (department==null || !department.getEnabled())
       {
           return ResultUtil.error("部门不存在或者被禁用");
       }
       return ResultUtil.success(findEmployee);
    }

    @PostMapping("register")
    public Result register(@RequestBody Employee employee)
    {
        if (employee == null || StringUtils.isBlank(employee.getUsername()) || StringUtils.isBlank(employee.getPassword()))
        {
            return ResultUtil.parameterError();
        }
        Employee findEmployee  =  employeeService.getByUsernameAndPassword(employee.getUsername(),employee.getPassword());
        if (findEmployee != null)
        {
            return ResultUtil.error("用户名已存在");
        }
        employee.setRegisterStatus(RegisterStatusEnum.REVIEWING.getCode());
        return ResultUtil.judgmentResult(employeeService.saveEntity(employee));
    }

    @PostMapping("changePassword")
    public Result changePassword(@RequestParam String oldPassword,@RequestParam String newPassword,@RequestParam String userName)
    {
        if (StringUtils.isBlank(oldPassword) || StringUtils.isBlank(newPassword) || StringUtils.isBlank(userName))
        {
            return ResultUtil.parameterError();
        }
        Employee employee  =  employeeService.getByUsernameAndPassword(userName,oldPassword);
        if (employee == null)
        {
            return ResultUtil.error("用户不存在或密码错误");
        }
        employee.setPassword(SecureUtil.md5(newPassword));
        return ResultUtil.judgmentResult(employeeService.updateEntity(employee));
    }

    @PostMapping("restPassword")
    public Result changePassword(@RequestParam Integer eid)
    {
        if (eid == null)
        {
            return ResultUtil.parameterError();
        }
        Employee employee  =  employeeService.getEntityById(eid);
        if (employee == null)
        {
            return ResultUtil.error("用户不存在");
        }
        employee.setPassword(SecureUtil.md5("123456"));
        return ResultUtil.judgmentResult(employeeService.updateEntity(employee));
    }

    @GetMapping("getEmployeeByWordId/{workId}")
    public Result getEmployeeByWordId(@PathVariable("workId") String workId)
    {
          Employee employee = employeeService.getEmployeeByWordId(workId);
          if (employee == null)
          {
              return ResultUtil.error("该员工不存在");
          }
          return ResultUtil.success(employee);
    }

    @GetMapping("getEmployeesByDeptId/{deptId}")
    public Result getEmployeeByDeptId(@PathVariable("deptId") Integer deptId)
    {
        List<Employee> employees = employeeService.getEmployeeByDeptId(deptId);
        return ResultUtil.success(employees);
    }

    @GetMapping("getEmployeeByCurrentDeptId/{deptId}")
    public Result getEmployeeByCurrentDeptId(@PathVariable("deptId") Integer deptId)
    {
        List<Employee> employees = employeeService.getEmployeeByCurrentDeptId(deptId);
        return ResultUtil.success(employees);
    }

    @GetMapping("getWorkStateChartData")
    public Result getWorkStateChartData()
    {
        List<PieChartData> pieChartDataList = employeeService.getWorkStateChartData();
        return ResultUtil.success(pieChartDataList);
    }

    @GetMapping("getSexChartData")
    public Result getSexChartData()
    {
        List<PieChartData> pieChartDataList = employeeService.getSexChartData();
        return ResultUtil.success(pieChartDataList);
    }

    @GetMapping("getTiptopDegreeChartData")
    public Result getTiptopDegreeChartData()
    {
        List<String> list = employeeService.getTiptopDegreeChartData();
        return ResultUtil.success(list);
    }

    @GetMapping("getJobLevelChartData")
    public Result getJobLevelChartData()
    {
        List<PieChartData> list = employeeService.getJobLevelChartData();
        return ResultUtil.success(list);
    }

    @GetMapping("getPosChartData")
    public Result getPosChartData()
    {
        List<PieChartData> list = employeeService.getPosChartData();
        return ResultUtil.success(list);
    }
}

