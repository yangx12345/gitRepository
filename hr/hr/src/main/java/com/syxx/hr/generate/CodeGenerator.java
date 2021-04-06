package com.syxx.hr.generate;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;
public class CodeGenerator {
	/**
	 * 项目地址
	 **/
	private static final String PROJECTPATH = System.getProperty("user.dir");

	/**
	 * 数据库类型
	 **/
	private static final DbType DB_TYPE = DbType.MYSQL;
	/**
	 * 数据库连接信息
	 **/
	private static final String DB_URL = "jdbc:mysql://localhost:3306/personnel?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "123456";

	/**
	 * 包名
	 **/
	private static final String PACKAGE_NAME = "com.syxx.hr";
	/**
	 * 基础controller
	 **/
	private static final String BASE_CONTROLLER = PACKAGE_NAME + ".common.BaseController";
	/**
	 * 基础实体
	 **/
	private static final String BASE_ENTITY = PACKAGE_NAME + ".common.BaseEntity";
	/**
	 * 作者
	 **/
	private static final String AUTHOR = "syxx";

	public static void main(String[] args) {
		// 代码生成器
		AutoGenerator mpg = new AutoGenerator();

		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		//生成文件的输出目录
		gc.setOutputDir(PROJECTPATH + "/src/main/java");
		//作者
		gc.setAuthor(AUTHOR);
		//是否覆盖
		gc.setOpen(true);
		//controller名字
		gc.setControllerName("%sController");
		//Mapper名字
		gc.setMapperName("%sMapper");
		// 是否生成基础映射
		gc.setBaseResultMap(true);
		//service 命名方式。默认值：null 例如：%sBusiness 生成 UserBusiness
		gc.setServiceName("%sService");
		//service impl 命名方式。默认值：null 例如：%sBusinessImpl 生成 UserBusinessImpl
		gc.setServiceImplName("%sServiceImpl");
		//指定生成的主键的ID类型
		gc.setIdType(IdType.AUTO);
		//时间类型对应策略
		gc.setDateType(DateType.TIME_PACK);
		gc.setBaseColumnList(true);
		mpg.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		//url
		dsc.setUrl(DB_URL);
		//数据库驱动
		dsc.setDriverName(DRIVER);
		//用户名
		dsc.setUsername(USER_NAME);
		//密码
		dsc.setPassword(PASSWORD);
		//数据库类型
		dsc.setDbType(DB_TYPE);
		mpg.setDataSource(dsc);

		// 包配置
		PackageConfig pc = new PackageConfig();
		//父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
		pc.setParent("com.syxx.hr");
		//pojo或entity包名
		pc.setEntity("entity");
		//mapper包名
		pc.setMapper("mapper");
		//service包名
		pc.setService("service");
		//service实现包名
		pc.setServiceImpl("service.impl");
		//controller 包名
		pc.setController("controller");
		mpg.setPackageInfo(pc);

		// 自定义配置    主要是为了下面输出Mapper位置
		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {
				// to do nothing
			}
		};

		String mapperXmlTemplatePath = "/templates/mapper.xml.vm";
		String controllerTemplatePath = "/templates/controller.java.vm";
		String serviceTemplatePath = "/templates/service.java.vm";
		String serviceImplTemplatePath = "/templates/serviceImpl.java.vm";
		String mapperTemplatePath = "/templates/mapper.java.vm";
		String entityTemplatePath = "/templates/entity.java.vm";
		// 自定义输出配置
		List<FileOutConfig> focList = new ArrayList<>();
		// 自定义配置会被优先输出
		focList.add(new FileOutConfig(mapperXmlTemplatePath) {
			@Override
			public String outputFile(TableInfo tableInfo) {
				// 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
				return PROJECTPATH + "/src/main/resources/mapper/"
						+ tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
			}
		});
		cfg.setFileOutConfigList(focList);
		mpg.setCfg(cfg);

		// 配置模板
		TemplateConfig templateConfig = new TemplateConfig();
		// 配置自定义输出模板
		//指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
		templateConfig.setEntity(entityTemplatePath);
		templateConfig.setController(controllerTemplatePath);
		templateConfig.setService(serviceTemplatePath);
		templateConfig.setServiceImpl(serviceImplTemplatePath);
		templateConfig.setMapper(mapperTemplatePath);
		templateConfig.setXml(null);
		mpg.setTemplate(templateConfig);


		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		//设置要映射的表名，可传多个
		strategy.setInclude(
				"salary",
//				"employee",
//				"adjustsalary",
//				"appraise",
//				"department",
//				"employeeec",
//				"employeeremove",
//				"employeetrain"
				"empsalary");
//				"joblevel",
//				"nation",
//				"politicsstatus",
//				"position",
//				"attendance_record"
		//去掉表前缀
		//strategy.setTablePrefix("t_");
		//字段名下划线转驼峰命名
		strategy.setNaming(NamingStrategy.underline_to_camel);
		//表名下划线转驼峰命名
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
		//开启lombok
		strategy.setEntityLombokModel(true);
		//控制器类上加上@RestController
		strategy.setRestControllerStyle(true);
		//驼峰转连字符
		strategy.setControllerMappingHyphenStyle(true);
		//逻辑删除
		strategy.setLogicDeleteFieldName("deleted");

		strategy.setEntityTableFieldAnnotationEnable(true);
		mpg.setStrategy(strategy);
		mpg.execute();
	}


}
