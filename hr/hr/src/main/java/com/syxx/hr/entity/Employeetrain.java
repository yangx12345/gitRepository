package com.syxx.hr.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;


@Data
@EqualsAndHashCode(callSuper = false)
@TableName("employeetrain")
public class Employeetrain implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 员工编号
     */
    @TableField("eid")
    private Integer eid;

    /**
     * 员工姓名
     */
    @TableField("name")
    private String name;

    @TableField("workId")
    private String workId;

    /**
     * 培训日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @TableField("trainDate")
    private LocalDate trainDate;

    /**
     * 培训内容
     */
    @TableField("trainContent")
    private String trainContent;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    @TableField(exist = false)
    private List<String> workIds;

    @TableField(exist = false)
    private Integer departmentId;
}
