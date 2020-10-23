package com.frido.hd.utils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HAOHAO on 2019/4/25.
 */
public class Generatortest {
    @Test
    public void generateTest(){
        //全局配置
        GlobalConfig config = new GlobalConfig();
        //设置是否支持AR模式
        config.setActiveRecord(true)
                //设置生成代码的作者
                .setAuthor("ldhao")
                //设置输出代码的位置
                .setOutputDir("C:output")
                .setFileOverride(true);
        //数据库连接url
        String dbUrl = "jdbc:mysql://localhost:3306/company_frame?serverTimezone=UTC";
        //数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        //数据库类型 枚举
        dataSourceConfig.setDbType(DbType.MYSQL)
                //设置url
                .setUrl(dbUrl)
                //设置用户名
                .setUsername("root")
                //设置密码
                .setPassword("123456")
                .setDriverName("com.mysql.cj.jdbc.Driver")
                .setTypeConvert(new MySqlTypeConvert() {
                    @Override
                    public DbColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                        System.out.println("转换类型：" + fieldType);
                         if ( fieldType.toLowerCase().contains( "tinyint" ) ) {
                            return DbColumnType.BOOLEAN;
                         }
                        if ( fieldType.toLowerCase().contains( "datetime" ) ) {
                            return DbColumnType.DATE;
                        }
                        return (DbColumnType) super.processTypeConvert(globalConfig, fieldType);
                    }
                });
        List<TableFill> tableFillList = new ArrayList<>();
        tableFillList.add(new TableFill("gmt_modified", FieldFill.INSERT_UPDATE));
        tableFillList.add(new TableFill("modifier_id", FieldFill.INSERT_UPDATE));
        tableFillList.add(new TableFill("creator_id", FieldFill.INSERT));
        tableFillList.add(new TableFill("gmt_creat", FieldFill.INSERT));
        tableFillList.add(new TableFill("available_flag", FieldFill.INSERT));
        tableFillList.add(new TableFill("deleted_flag", FieldFill.INSERT));
        tableFillList.add(new TableFill("sync_flag", FieldFill.INSERT));
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)
                .setEntityLombokModel(true)
                .setNaming(NamingStrategy.underline_to_camel)
                .setTableFillList(tableFillList)
                .setInclude("sys_user");//输入表名
        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent("com.frido.hd.sys")
                                .setController("controller")
                                .setEntity("entity")
                )
                .setTemplate(
                        new TemplateConfig()
                                 .setServiceImpl("templates/serviceImpl.java")
                )
                .execute();
    }
}
