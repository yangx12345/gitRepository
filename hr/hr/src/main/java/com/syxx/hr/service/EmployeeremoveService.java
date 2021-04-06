package com.syxx.hr.service;

import com.syxx.hr.entity.Employeeremove;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;


public interface EmployeeremoveService extends IService<Employeeremove> {

    /**
     * 根据id查询实体列表
     */
    public Employeeremove getEntityById(Integer id);

    /**
     * 根据条件查询实体列表
     */
    public List<Employeeremove> getListByCondition(Employeeremove condition);

    /**
     *  保存实体
     */
    public boolean saveEntity(Employeeremove entity);

    /**
     *  更新实体
     */
    public boolean updateEntity(Employeeremove entity);

    /**
     *  根据id删除实体
     */
    public boolean deleteById(Integer id);

    /**
     *  根据id列表删除
     */
    public boolean deleteByIds(String ids);

}
