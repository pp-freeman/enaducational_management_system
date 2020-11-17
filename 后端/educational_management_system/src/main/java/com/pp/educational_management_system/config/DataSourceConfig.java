package com.pp.educational_management_system.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Base64;


/**
 * 对应spring 的 application-dao.xml
 */


@Configuration  //表示该配置是需要写入spring的IOC容器中

public class DataSourceConfig{
    //定义数据库的基本信息属性
    @Value("${db.driverClassName}")
    private String jdbcDriver;
    @Value("${db.url}")
    private String jdbcUrl;
    @Value("${db.username}")
    private String jdbcUsername;
    @Value("${db.password}")
    private String jdbcPassword;


    //创建一个dataSource对象
    @Bean(name = "dataSource")

    public ComboPooledDataSource createDataSourceBean() throws Exception {
        //创建dataSource对象 并设置属性
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(jdbcDriver);
        dataSource.setJdbcUrl(new String(Base64.getDecoder().decode(jdbcUrl)));
        dataSource.setUser(new String(Base64.getDecoder().decode(jdbcUsername)));  //需要先进行解密工作
        dataSource.setPassword(new String(Base64.getDecoder().decode(jdbcPassword)));
        return dataSource;
    }
}