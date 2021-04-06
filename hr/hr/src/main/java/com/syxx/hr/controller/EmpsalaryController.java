package com.syxx.hr.controller;


import com.alibaba.excel.EasyExcel;
import com.syxx.hr.common.pojo.EmpsalaryExcel;
import com.syxx.hr.common.pojo.StatisticalSalaryExcel;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.BeanUtils;
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

import com.syxx.hr.service.EmpsalaryService;
import com.syxx.hr.entity.Empsalary;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/empsalary")
public class EmpsalaryController {
    @Autowired
    private EmpsalaryService empsalaryService;

    @GetMapping("getById/{id}")
    public Result getById(@PathVariable Integer id)
    {
        if(id==null)
        {
            return ResultUtil.parameterError();
        }
        return ResultUtil.success(empsalaryService.getEntityById(id));
    }

    @PostMapping("getListByCondition")
    public Result getListByCondition(@RequestBody Empsalary condition,@RequestParam("pageIndex") Integer pageIndex,@RequestParam("pageSize") Integer pageSize)
    {
        if(condition==null || pageIndex==null || pageIndex< 1||pageSize==null||pageSize< 1){
		 return ResultUtil.parameterError();
        }
		PageHelper.startPage(pageIndex, pageSize);
        List<Empsalary> list= empsalaryService.getListByCondition(condition);
		PageInfo<Empsalary> result = new PageInfo<>(list);
        return ResultUtil.success(result);
    }

    @PostMapping("add")
    public Result add(@RequestBody Empsalary entity)
    {
        if(entity==null){
		return ResultUtil.parameterError();
        }
        return ResultUtil.judgmentResult(empsalaryService.saveEntity(entity));
    }


    @PutMapping("update")
    public Result update(@RequestBody Empsalary entity)
    {
        if(entity==null){
		return ResultUtil.parameterError();
        }
        return ResultUtil.judgmentResult(empsalaryService.updateEntity(entity));
    }


    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id)
    {
        if(id==null){
		 return ResultUtil.parameterError();
        }
        return ResultUtil.judgmentResult(empsalaryService.deleteById(id));
    }

    @DeleteMapping("batchDelete")
    public Result batchDelete(@RequestParam String ids)
    {
        if(StringUtils.isBlank(ids))
        {
		  return ResultUtil.parameterError();
        }
        return ResultUtil.judgmentResult(empsalaryService.deleteByIds(ids));
    }

    @PostMapping(value = "/excel")
    public void exportExcel(@RequestBody Empsalary condition, HttpServletResponse response) {
        List<Empsalary> empsalaryList = empsalaryService.getListByCondition(condition);
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("员工工资报表", "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), EmpsalaryExcel.class).sheet("员工工资报表").doWrite(converExcelEmpSalary(empsalaryList));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private List<EmpsalaryExcel> converExcelEmpSalary(List<Empsalary> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        List<EmpsalaryExcel> empsalaryExcels = new ArrayList<>();
        for (Empsalary empsalary : list) {
            EmpsalaryExcel empsalaryExcel = new EmpsalaryExcel();
            BeanUtils.copyProperties(empsalary, empsalaryExcel);
            empsalaryExcels.add(empsalaryExcel);
        }
        return empsalaryExcels;
    }

    @PostMapping(value = "/StatisticalSalaryExcel")
    public void exportStatisticalSalaryExcel(@RequestBody Empsalary condition, HttpServletResponse response) {
        List<Empsalary> empsalaryList = empsalaryService.getListByCondition(condition);
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("工资报表", "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), StatisticalSalaryExcel.class).sheet("员工工资报表").doWrite(converStatisticalSalaryExcel(empsalaryList));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<StatisticalSalaryExcel> converStatisticalSalaryExcel(List<Empsalary> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        List<StatisticalSalaryExcel> statisticalSalaryExcels = new ArrayList<>();
        Integer allBasicSalary = 0;
        Integer allBonus = 0;
        Integer allLunchSalary = 0;
        Integer allTrafficSalary = 0;
        Integer allAllSalary = 0;
        Integer allPensionBase = 0;
        Float allPensionPer = (float) 0;
        Integer allMedicalBase = 0;
        Float allMedicalPer = (float) 0;
        Integer allAccumulationFundBase = 0;
        Float allAccumulationFundPer = (float) 0;
        Integer allAttendanceSalary = 0;
        Integer allRealSalary = 0;
        for (Empsalary empsalary : list) {
            StatisticalSalaryExcel statisticalSalaryExcel = new StatisticalSalaryExcel();
            BeanUtils.copyProperties(empsalary, statisticalSalaryExcel);
            statisticalSalaryExcel.setId(empsalary.getId().toString());
            statisticalSalaryExcels.add(statisticalSalaryExcel);
            allBasicSalary = allBasicSalary+empsalary.getBasicSalary();
            allBonus = allBonus+empsalary.getBonus();
            allLunchSalary = allLunchSalary+empsalary.getLunchSalary();
            allTrafficSalary = allTrafficSalary+empsalary.getTrafficSalary();
            allAllSalary = allAllSalary+empsalary.getAllSalary();
            allPensionBase = allPensionBase+empsalary.getPensionBase();
            allPensionPer = allPensionPer+empsalary.getPensionPer();
            allMedicalBase = allMedicalBase+empsalary.getMedicalBase();
            allMedicalPer = allMedicalPer+empsalary.getMedicalPer();
            allAccumulationFundBase = allAccumulationFundBase+empsalary.getAccumulationFundBase();
            allAccumulationFundPer = allAccumulationFundPer+empsalary.getAccumulationFundPer();
            allAttendanceSalary = allAttendanceSalary+empsalary.getAttendanceSalary();
            allRealSalary = allRealSalary+empsalary.getRealSalary();
        }
        StatisticalSalaryExcel statisticalSalaryExcel = new StatisticalSalaryExcel();
        statisticalSalaryExcel.setId("合计");
        DecimalFormat decimalFormat=new DecimalFormat(".00");
        statisticalSalaryExcel.setBasicSalary(allBasicSalary);
        statisticalSalaryExcel.setBonus(allBonus);
        statisticalSalaryExcel.setLunchSalary(allLunchSalary);
        statisticalSalaryExcel.setTrafficSalary(allTrafficSalary);
        statisticalSalaryExcel.setAllSalary(allAllSalary);
        statisticalSalaryExcel.setPensionBase(allPensionBase);
        statisticalSalaryExcel.setPensionPer(Float.valueOf(decimalFormat.format(allPensionPer)));
        statisticalSalaryExcel.setMedicalBase(allMedicalBase);
        statisticalSalaryExcel.setMedicalPer(Float.valueOf(decimalFormat.format(allMedicalPer)));
        statisticalSalaryExcel.setAccumulationFundBase(allAccumulationFundBase);
        statisticalSalaryExcel.setAccumulationFundPer(Float.valueOf(decimalFormat.format(allAccumulationFundPer)));
        statisticalSalaryExcel.setAttendanceSalary(allAttendanceSalary);
        statisticalSalaryExcel.setRealSalary(allRealSalary);
        statisticalSalaryExcels.add(statisticalSalaryExcel);
        return statisticalSalaryExcels;
    }
}

