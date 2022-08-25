package com.xxbank.shop0825.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {

    //目的:建立連線資源
    @Bean
    @ConfigurationProperties (prefix = "spring.datasource.test1") //根據字首尋找建立連線資源的初始值
    public DataSource test1DataSource(){                          //方法名稱=Bean名稱
        return DataSourceBuilder.create().build();                //回傳所建立的連線資源
    }

    //目的:建立資料庫連線
    @Bean
    public NamedParameterJdbcTemplate test1JdbcTemplate (
            @Qualifier("test1DataSource") DataSource dataSource){  //指定連線資源
        return new NamedParameterJdbcTemplate(dataSource);         //建立與資料庫的連線
    }


    //同上
    @Bean
    @ConfigurationProperties ("spring.datasource.test2")
    public DataSource test2DataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    public NamedParameterJdbcTemplate test2JdbcTemplate(@Qualifier("test2DataSource") DataSource dataSource){
        return new NamedParameterJdbcTemplate(dataSource);
    }







}
