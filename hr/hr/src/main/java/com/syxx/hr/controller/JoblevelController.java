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

import com.syxx.hr.service.JoblevelService;
import com.syxx.hr.entity.Joblevel;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/joblevel")
public class JoblevelController {
    @Autowired
    private JoblevelService joblevelService;

    @GetMapping("getById/{id}")
    public Result getById(@PathVariable Integer id)
    {
        if(id==null)
        {
            return ResultUtil.parameterError();
        }
        return ResultUtil.success(joblevelService.getEntityById(id));
    }

    @PostMapping("getListByCondition")
    public Result getListByCondition(@RequestBody Joblevel condition,@RequestParam("pageIndex") Integer pageIndex,@RequestParam("pageSize") Integer pageSize)
    {
        if(condition==null || pageIndex==null || pageIndex< 1||pageSize==null||pageSize< 1){
		 return ResultUtil.parameterError();
        }
		PageHelper.startPage(pageIndex, pageSize);
        List<Joblevel> list= joblevelService.getListByCondition(condition);
		PageInfo<Joblevel> result = new PageInfo<>(list);
        return ResultUtil.success(result);
    }

    @PostMapping("add")
    public Result add(@RequestBody Joblevel entity)
    {
        if(entity==null){
		return ResultUtil.parameterError();
        }
        return ResultUtil.judgmentResult(joblevelService.saveEntity(entity));
    }


    @PutMapping("update")
    public Result update(@RequestBody Joblevel entity)
    {
        if(entity==null){
		return ResultUtil.parameterError();
        }
        return ResultUtil.judgmentResult(joblevelService.updateEntity(entity));
    }


    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id)
    {
        if(id==null){
		 return ResultUtil.parameterError();
        }
        return ResultUtil.judgmentResult(joblevelService.deleteById(id));
    }

    @DeleteMapping("batchDelete")
    public Result batchDelete(@RequestParam String ids)
    {
        if(StringUtils.isBlank(ids))
        {
		  return ResultUtil.parameterError();
        }
        return ResultUtil.judgmentResult(joblevelService.deleteByIds(ids));
    }

    @GetMapping("getList")
    public Result getList()
    {
        List<Joblevel> joblevelList = joblevelService.getList();
        return ResultUtil.success(joblevelList);
    }
}

