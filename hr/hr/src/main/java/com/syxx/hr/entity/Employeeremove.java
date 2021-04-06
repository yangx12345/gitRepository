package com.syxx.hr.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;


@Data
@EqualsAndHashCode(callSuper = false)
@TableName("employeeremove")
public class Employeeremove implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("eid")
    private Integer eid;

    /**
     * 员工姓名
     */
    @TableField("name")
    private String name;

    /**
     * 调动前部门
     */
    @TableField("beforeDepId")
    private Integer beforeDepId;

    /**
     * 调动前职位
     */
    @TableField("beforeJobId")
    private Integer beforeJobId;

    /**
     * 调动后部门
     */
    @TableField("afterDepId")
    private Integer afterDepId;

    /**
     * 调动后职位
     */
    @TableField("afterJobId")
    private Integer afterJobId;

    /**
     * 调动日期
     */
    @TableField("removeDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private LocalDate removeDate;

    /**
     * 调动原因
     */
    @TableField("reason")
    private String reason;

    @TableField("workId")
    private String workId;

    @TableField("remark")
    private String remark;


}
