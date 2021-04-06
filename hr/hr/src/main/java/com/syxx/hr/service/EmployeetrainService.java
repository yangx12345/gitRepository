package com.syxx.hr.service;

import com.syxx.hr.entity.Employeetrain;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;


public interface EmployeetrainService extends IService<Employeetrain> {

    /**
     * 根据id查询实体列表
     */
    public Employeetrain getEntityById(Integer id);

    /**
     * 根据条件查询实体列表
     */
    public List<Employeetrain> getListByCondition(Employeetrain condition);

    /**
     *  保存实体
     */
    public boolean saveEntity(Employeetrain entity);

    /**
     *  更新实体
     */
    public boolean updateEntity(Employeetrain entity);

    /**
     *  根据id删除实体
     */
    public boolean deleteById(Integer id);

    /**
     *  根据id列表删除
     */
    public boolean deleteByIds(String ids);

	Boolean batchSave(Employeetrain employeetrain);
}
