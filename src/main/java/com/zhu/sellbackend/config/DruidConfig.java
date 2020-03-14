package com.zhu.sellbackend.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @ClassName DruidConfig
 * @Description
 * @Author qgh
 * @Date 2020-01-23 12:22
 **/
@Configuration
@MapperScan({"com.zhu.sellbackend.mapper"})
@ConfigurationProperties(prefix = "spring.datasource.druid")
public class DruidConfig extends DruidDataSourceProperties{

    @Bean
    public DruidDataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.configFromPropety(super.buildProperties());
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource,
                                               @Qualifier("globalConfig") GlobalConfig globalConfig,
                                               @Qualifier("paginationInterceptor") PaginationInterceptor paginationInterceptor) throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage("com.zhu.sellbackend.orm");
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{paginationInterceptor});     //分页插件
        sqlSessionFactoryBean.setGlobalConfig(globalConfig);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public GlobalConfig globalConfig() {
        GlobalConfig config = new GlobalConfig();
        GlobalConfig.DbConfig dbConfig = new GlobalConfig.DbConfig();
        //使用delete时使用逻辑删除  D删除  N正常
        dbConfig.setLogicDeleteValue("D");
        dbConfig.setLogicNotDeleteValue("N");
        config.setDbConfig(dbConfig);
//        config.setMetaObjectHandler(metaObjectHandler);
        return config;
    }

    //分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return paginationInterceptor;
    }

    //全局插入默认值
    /*@Bean
    public MetaObjectHandler metaObjectHandler() {
        return new MetaObjectHandler() {
            @Override
            public void insertFill(MetaObject metaObject) {
                Object status = getFieldValByName("status", metaObject);
                if(status == null) {
                    setFieldValByName("status",Constant.STATUS_NORMAL,metaObject);
                }
            }

            @Override
            public void updateFill(MetaObject metaObject) {

            }
        };
    }*/

}
