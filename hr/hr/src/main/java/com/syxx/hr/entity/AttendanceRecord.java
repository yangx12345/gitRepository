package com.syxx.hr.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * 考勤记录
 *
 * @author yangxiao
 * @date 2021-04-06 10:21
 * @return
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("attendance_record")
public class AttendanceRecord implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 记录id
     */
    @TableId(value = "record_id", type = IdType.AUTO)
    private Integer recordId;

    /**
     * 用户编号
     */
    @TableField("eid")
    private Integer eid;

    @TableField("workId")
    private String workId;

    /**
     * 真实姓名
     */
    @TableField("real_name")
    private String realName;

    /**
     * 早退次数
     */
    @TableField("early_num")
    private Integer earlyNum;

    /**
     * 时间
     */
    @DateTimeFormat(pattern = "yyyy-MM")
    @JsonFormat(pattern = "yyyy-MM",timezone="GMT+8")
    @TableField("time")
    private Date time;

    /**
     * 缺勤天数
     */
    @TableField("absenteeism_num")
    private Integer absenteeismNum;

    /**
     * 出勤天数
     */
    @TableField("attendance_num")
    private Integer attendanceNum;

    /**
     * 迟到次数
     */
    @TableField("late_num")
    private Integer lateNum;

    /**
     * 出差天数
     */
    @TableField("evection_num")
    private Integer evectionNum;

    /**
     * 录入人
     */
    @TableField("entry_id")
    private Integer entryId;

    /**
     * 录入日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField("entry_date")
    private Date entryDate;

    /**
     * 更新人
     */
    @TableField("update_id")
    private Integer updateId;

    /**
     * 更新日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField("update_date")
    private Date updateDate;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;


}
