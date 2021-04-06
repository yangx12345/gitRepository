package com.syxx.hr.common.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.*;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Data;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.util.Date;

@Data
@ContentRowHeight(20)
@HeadRowHeight(20)
@ColumnWidth(20)
// 头背景设置成红色 IndexedColors.RED.getIndex()
@HeadStyle(fillPatternType = FillPatternType.SOLID_FOREGROUND, fillForegroundColor = 13)
// 头字体设置成20
@HeadFontStyle(fontHeightInPoints = 16)
// 内容字体设置成15
@ContentFontStyle(fontHeightInPoints = 15)
public class EmpsalaryExcel {

	/**
	 * 主键
	 */
	@ExcelProperty({"员工工资报表", "编号"})
	@ColumnWidth(10)
	private Integer id;

	/**
	 * 工资时间
	 */
	@ExcelProperty({"员工工资报表", "工资时间"})
	@DateTimeFormat("yyyy年MM月")
	private Date salaryDate;

	/**
	 * 员工姓名
	 */
	@ExcelProperty({"员工工资报表", "员工姓名"})
	private String name;

	/**
	 * 员工工号
	 */
	@ExcelProperty({"员工工资报表", "员工工号"})
	private String workId;

	/**
	 * 基本工资
	 */
	@ExcelProperty({"员工工资报表", "基本工资"})
	private Integer basicSalary;

	/**
	 * 奖金
	 */
	@ExcelProperty({"员工工资报表", "奖金"})
	private Integer bonus;

	/**
	 * 午餐补助
	 */
	@ExcelProperty({"员工工资报表", "午餐补助"})
	private Integer lunchSalary;

	/**
	 * 交通补助
	 */
	@ExcelProperty({"员工工资报表", "交通补助"})
	private Integer trafficSalary;

	/**
	 * 应发工资
	 */
	@ExcelProperty({"员工工资报表", "应发工资"})
	private Integer allSalary;

	/**
	 * 养老金基数
	 */
	@ExcelProperty({"员工工资报表", "养老金基数"})
	private Integer pensionBase;

	/**
	 * 养老金比率
	 */
	@ExcelProperty({"员工工资报表", "养老金比率"})
	private Float pensionPer;


	/**
	 * 医疗基数
	 */
	@ExcelProperty({"员工工资报表", "医疗基数"})
	private Integer medicalBase;

	/**
	 * 医疗保险比率
	 */
	@ExcelProperty({"员工工资报表", "医疗保险比率"})
	private Float medicalPer;

	/**
	 * 公积金基数
	 */
	@ExcelProperty({"员工工资报表", "公积金基数"})
	private Integer accumulationFundBase;

	/**
	 * 公积金比率
	 */
	@ExcelProperty({"员工工资报表", "公积金比率"})
	private Float accumulationFundPer;



	/**
	 * 考勤工资
	 */
	@ExcelProperty({"员工工资报表", "考勤工资"})
	private Integer attendanceSalary;

	/**
	 * 实发工资
	 */
	@ExcelProperty({"员工工资报表", "实发工资"})
	private Integer realSalary;

	/**
	 * 备注
	 */
	@ExcelProperty({"员工工资报表", "备注"})
	private String remark;


}
