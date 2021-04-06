package com.syxx.hr.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syxx.hr.common.pojo.PieChartData;
import com.syxx.hr.entity.Employee;
import com.syxx.hr.entity.Joblevel;
import com.syxx.hr.entity.Position;
import com.syxx.hr.mapper.EmployeeMapper;
import com.syxx.hr.service.DepartmentService;
import com.syxx.hr.service.EmployeeService;
import com.syxx.hr.service.JoblevelService;
import com.syxx.hr.service.PositionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
@Transactional(readOnly = true)
@Slf4j
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee>implements EmployeeService {


    @Autowired
    private JoblevelService joblevelService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private DepartmentService departmentService;

    @Override
    public Employee getEntityById(Integer id){
        return this.getById(id);
    }

    @Override
    public List<Employee>getListByCondition(Employee condition,List<Integer> childrenIds){
		return baseMapper.getListByCondition(condition,childrenIds);
    }

    @Override
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public boolean saveEntity(Employee entity){
        entity.setPassword(SecureUtil.md5(entity.getPassword()));
        return this.save(entity);
    }

    @Override
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public boolean updateEntity(Employee entity){
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
    public Employee getByUsernameAndPassword(String username, String password) {
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id","departmentId","registerStatus","role").eq("username",username).eq("password", SecureUtil.md5(password));
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public Employee getEmployeeByWordId(String workId) {
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("workID",workId);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public List<Employee> getEmployeeByDeptId(Integer deptId) {
        List<Integer> childrenIds = departmentService.getDeptChildernIds(deptId,true);
        return baseMapper.getEmployeeByDeptId(childrenIds);
    }

    @Override
    public List<Employee> getEmployeeByCurrentDeptId(Integer deptId) {
        return baseMapper.getEmployeeByCurrentDeptId(deptId);
    }

    @Override
    public Employee getEmployeeInfoByWordId(String workId) {
        return baseMapper.getEmployeeInfoByWordId(workId);
    }

    @Override
    public List<PieChartData> getWorkStateChartData() {
        List<PieChartData> pieChartDataList = new ArrayList<>();
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("workState","在职");
        PieChartData pieChartData = new PieChartData();
        pieChartData.setName("在职");
        pieChartData.setValue(baseMapper.selectCount(queryWrapper));
        pieChartDataList.add(pieChartData);
        QueryWrapper<Employee> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("workState","离职");
        PieChartData pieChartData1 = new PieChartData();
        pieChartData1.setName("离职");
        pieChartData1.setValue(baseMapper.selectCount(queryWrapper1));
        pieChartDataList.add(pieChartData1);
        return pieChartDataList;
    }

    @Override
    public List<PieChartData> getSexChartData() {
        List<PieChartData> pieChartDataList = new ArrayList<>();
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("gender","男");
        PieChartData pieChartData = new PieChartData();
        pieChartData.setName("男");
        pieChartData.setValue(baseMapper.selectCount(queryWrapper));
        pieChartDataList.add(pieChartData);
        QueryWrapper<Employee> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("gender","女");
        PieChartData pieChartData1 = new PieChartData();
        pieChartData1.setName("女");
        pieChartData1.setValue(baseMapper.selectCount(queryWrapper1));
        pieChartDataList.add(pieChartData1);
        return pieChartDataList;
    }

    @Override
    public List<String> getTiptopDegreeChartData() {
        return baseMapper.getTiptopDegreeChartData();
    }

    @Override
    public List<PieChartData> getJobLevelChartData() {
        List<Joblevel> joblevels = joblevelService.getList();
        List<PieChartData> pieChartDataList = new ArrayList<>();
        for (Joblevel j:joblevels
             ) {
            PieChartData pieChartData = new PieChartData();
            pieChartData.setName(j.getName());
            QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("jobLevelId",j.getId());
            pieChartData.setValue(baseMapper.selectCount(queryWrapper));
            pieChartDataList.add(pieChartData);
        }
        return pieChartDataList;
    }

    @Override
    public List<PieChartData> getPosChartData() {
        List<Position> positions = positionService.getList();
        List<PieChartData> pieChartDataList = new ArrayList<>();
        for (Position p:positions
        ) {
            PieChartData pieChartData = new PieChartData();
            pieChartData.setName(p.getName());
            QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("posId",p.getId());
            pieChartData.setValue(baseMapper.selectCount(queryWrapper));
            pieChartDataList.add(pieChartData);
        }
        return pieChartDataList;
    }
}
