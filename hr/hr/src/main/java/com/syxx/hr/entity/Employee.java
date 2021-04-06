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
@TableName("employee")
public class Employee implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 员工编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 登录名
     */
    @TableField("username")
    private String username;

    /**
     * 登录密码
     */
    @TableField("password")
    private String password;

    /**
     * 员工姓名
     */
    @TableField("name")
    private String name;

    /**
     * 性别
     */
    @TableField("gender")
    private String gender;

    /**
     * 出生日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @TableField("birthday")
    private LocalDate birthday;

    /**
     * 角色 0 为系统管理员 1 为hr 2为普通员工
     */
    @TableField("role")
    private Integer role;

    /**
     * 身份证号
     */
    @TableField("idCard")
    private String idCard;

    /**
     * 婚姻状况
     */
    @TableField("wedlock")
    private String wedlock;

    /**
     * 民族
     */
    @TableField("nationId")
    private Integer nationId;

    /**
     * 籍贯
     */
    @TableField("nativePlace")
    private String nativePlace;

    /**
     * 政治面貌
     */
    @TableField("politicId")
    private Integer politicId;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 电话号码
     */
    @TableField("phone")
    private String phone;

    /**
     * 联系地址
     */
    @TableField("address")
    private String address;

    /**
     * 所属部门
     */
    @TableField("departmentId")
    private Integer departmentId;

    /**
     * 职称ID
     */
    @TableField("jobLevelId")
    private Integer jobLevelId;

    /**
     * 职位ID
     */
    @TableField("posId")
    private Integer posId;

    /**
     * 聘用形式
     */
    @TableField("engageForm")
    private String engageForm;

    /**
     * 最高学历
     */
    @TableField("tiptopDegree")
    private String tiptopDegree;

    /**
     * 所属专业
     */
    @TableField("specialty")
    private String specialty;

    /**
     * 毕业院校
     */
    @TableField("school")
    private String school;

    /**
     * 入职日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @TableField("beginDate")
    private LocalDate beginDate;

    /**
     * 在职状态
     */
    @TableField("workState")
    private String workState;

    /**
     * 工号
     */
    @TableField("workID")
    private String workID;

    /**
     * 合同期限
     */
    @TableField("contractTerm")
    private Double contractTerm;

    /**
     * 转正日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @TableField("conversionTime")
    private LocalDate conversionTime;

    /**
     * 离职日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @TableField("notWorkDate")
    private LocalDate notWorkDate;

    /**
     * 合同起始日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @TableField("beginContract")
    private LocalDate beginContract;

    /**
     * 合同终止日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @TableField("endContract")
    private LocalDate endContract;

    /**
     * 工龄
     */
    @TableField("workAge")
    private Integer workAge;

    /**
     * 注册状态 0待审核 1审核通过 2审核拒绝
     */
    @TableField("registerStatus")
    private String registerStatus;


}
