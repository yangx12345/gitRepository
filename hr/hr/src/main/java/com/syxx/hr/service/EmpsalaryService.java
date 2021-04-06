package com.syxx.hr.service;

import com.syxx.hr.entity.Empsalary;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;


public interface EmpsalaryService extends IService<Empsalary> {

    /**
     * 根据id查询实体列表
     */
    public Empsalary getEntityById(Integer id);

    /**
     * 根据条件查询实体列表
     */
    public List<Empsalary> getListByCondition(Empsalary condition);

    /**
     *  保存实体
     */
    public boolean saveEntity(Empsalary entity);

    /**
     *  更新实体
     */
    public boolean updateEntity(Empsalary entity);

    /**
     *  根据id删除实体
     */
    public boolean deleteById(Integer id);

    /**
     *  根据id列表删除
     */
    public boolean deleteByIds(String ids);

}
