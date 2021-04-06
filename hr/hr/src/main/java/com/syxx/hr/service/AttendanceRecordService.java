package com.syxx.hr.service;

import com.syxx.hr.entity.AttendanceRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;


public interface AttendanceRecordService extends IService<AttendanceRecord> {

    /**
     * 根据id查询实体列表
     */
    public AttendanceRecord getEntityById(Integer id);

    /**
     * 根据条件查询实体列表
     */
    public List<AttendanceRecord> getListByCondition(AttendanceRecord condition);

    /**
     *  保存实体
     */
    public boolean saveEntity(AttendanceRecord entity);

    /**
     *  更新实体
     */
    public boolean updateEntity(AttendanceRecord entity);

    /**
     *  根据id删除实体
     */
    public boolean deleteById(Integer id);

    /**
     *  根据id列表删除
     */
    public boolean deleteByIds(String ids);

}
