package com.syxx.hr.service;

import com.syxx.hr.entity.Joblevel;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;


public interface JoblevelService extends IService<Joblevel> {

    /**
     * 根据id查询实体列表
     */
    public Joblevel getEntityById(Integer id);

    /**
     * 根据条件查询实体列表
     */
    public List<Joblevel> getListByCondition(Joblevel condition);

    /**
     *  保存实体
     */
    public boolean saveEntity(Joblevel entity);

    /**
     *  更新实体
     */
    public boolean updateEntity(Joblevel entity);

    /**
     *  根据id删除实体
     */
    public boolean deleteById(Integer id);

    /**
     *  根据id列表删除
     */
    public boolean deleteByIds(String ids);

    List<Joblevel> getList();
}
