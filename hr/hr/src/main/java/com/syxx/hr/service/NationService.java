package com.syxx.hr.service;

import com.syxx.hr.entity.Nation;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;


public interface NationService extends IService<Nation> {

    /**
     * 根据id查询实体列表
     */
    public Nation getEntityById(Integer id);

    /**
     * 根据条件查询实体列表
     */
    public List<Nation> getListByCondition(Nation condition);

    /**
     *  保存实体
     */
    public boolean saveEntity(Nation entity);

    /**
     *  更新实体
     */
    public boolean updateEntity(Nation entity);

    /**
     *  根据id删除实体
     */
    public boolean deleteById(Integer id);

    /**
     *  根据id列表删除
     */
    public boolean deleteByIds(String ids);

}
