package com.syxx.hr.service;

import com.syxx.hr.entity.Politicsstatus;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;


public interface PoliticsstatusService extends IService<Politicsstatus> {

    /**
     * 根据id查询实体列表
     */
    public Politicsstatus getEntityById(Integer id);

    /**
     * 根据条件查询实体列表
     */
    public List<Politicsstatus> getListByCondition(Politicsstatus condition);

    /**
     *  保存实体
     */
    public boolean saveEntity(Politicsstatus entity);

    /**
     *  更新实体
     */
    public boolean updateEntity(Politicsstatus entity);

    /**
     *  根据id删除实体
     */
    public boolean deleteById(Integer id);

    /**
     *  根据id列表删除
     */
    public boolean deleteByIds(String ids);

}
