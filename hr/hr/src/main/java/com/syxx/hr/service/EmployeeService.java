package com.syxx.hr.service;

import com.syxx.hr.common.pojo.PieChartData;
import com.syxx.hr.entity.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;


public interface EmployeeService extends IService<Employee> {

    /**
     * 根据id查询实体列表
     */
    public Employee getEntityById(Integer id);

    /**
     * 根据条件查询实体列表
     */
    public List<Employee> getListByCondition(Employee condition,List<Integer> childrenIds);

    /**
     *  保存实体
     */
    public boolean saveEntity(Employee entity);

    /**
     *  更新实体
     */
    public boolean updateEntity(Employee entity);

    /**
     *  根据id删除实体
     */
    public boolean deleteById(Integer id);

    /**
     *  根据id列表删除
     */
    public boolean deleteByIds(String ids);

	Employee getByUsernameAndPassword(String username, String password);

    Employee getEmployeeByWordId(String workId);

	List<Employee> getEmployeeByDeptId(Integer deptId);

    List<Employee> getEmployeeByCurrentDeptId(Integer deptId);

    Employee getEmployeeInfoByWordId(String workId);

	List<PieChartData> getWorkStateChartData();

    List<PieChartData> getSexChartData();

    List<String> getTiptopDegreeChartData();

    List<PieChartData> getJobLevelChartData();

    List<PieChartData> getPosChartData();
}
