package com.syxx.hr.service;

import com.syxx.hr.entity.Position;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;


public interface PositionService extends IService<Position> {

    /**
     * 根据id查询实体列表
     */
    public Position getEntityById(Integer id);

    /**
     * 根据条件查询实体列表
     */
    public List<Position> getListByCondition(Position condition);

    /**
     *  保存实体
     */
    public boolean saveEntity(Position entity);

    /**
     *  更新实体
     */
    public boolean updateEntity(Position entity);

    /**
     *  根据id删除实体
     */
    public boolean deleteById(Integer id);

    /**
     *  根据id列表删除
     */
    public boolean deleteByIds(String ids);

	List<Position> getList();
}
