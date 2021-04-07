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

import com.syxx.hr.service.AttendanceRecordService;
import com.syxx.hr.entity.AttendanceRecord;

import java.util.List;

/**
 *
 * 考勤记录控制器4
 * test6
 * 123456
 * 我正在工作1111  工作完成(dev测试)
 * Rebase test 1
 * Rebase test 2
 * origin test
 * @author yangxiao
 * @date 2021-04-06 10:24
 **/
@Slf4j
@RestController
@RequestMapping("/attendancerecord")
public class AttendanceRecordController {
    @Autowired
    private AttendanceRecordService attendanceRecordService;

    //bug06
    @GetMapping("getById/{id}")
    public Result getById(@PathVariable Integer id)
    {
        if(id==null)
        {
            return ResultUtil.parameterError();
        }
        return ResultUtil.success(attendanceRecordService.getEntityById(id));
    }

    @PostMapping("getListByCondition")
    public Result getListByCondition(@RequestBody AttendanceRecord condition,@RequestParam("pageIndex") Integer pageIndex,@RequestParam("pageSize") Integer pageSize)
    {
        if(condition==null || pageIndex==null || pageIndex< 1||pageSize==null||pageSize< 1){
		 return ResultUtil.parameterError();
        }
		PageHelper.startPage(pageIndex, pageSize);
        List<AttendanceRecord> list= attendanceRecordService.getListByCondition(condition);
		PageInfo<AttendanceRecord> result = new PageInfo<>(list);
        return ResultUtil.success(result);
    }

    @PostMapping("add")
    public Result add(@RequestBody AttendanceRecord entity)
    {
        if(entity==null){
		return ResultUtil.parameterError();
        }
        return ResultUtil.judgmentResult(attendanceRecordService.saveEntity(entity));
    }


    @PutMapping("update")
    public Result update(@RequestBody AttendanceRecord entity)
    {
        if(entity==null){
		return ResultUtil.parameterError();
        }
        return ResultUtil.judgmentResult(attendanceRecordService.updateEntity(entity));
    }


    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id)
    {
        if(id==null){
		 return ResultUtil.parameterError();
        }
        return ResultUtil.judgmentResult(attendanceRecordService.deleteById(id));
    }

    @DeleteMapping("batchDelete")
    public Result batchDelete(@RequestParam String ids)
    {
        if(StringUtils.isBlank(ids))
        {
		  return ResultUtil.parameterError();
        }
        return ResultUtil.judgmentResult(attendanceRecordService.deleteByIds(ids));
    }
}

