package com.shu.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean(name = "operatorDataSource")
    @ConfigurationProperties("spring.datasource.operator")
    public DataSource operatorDataSource() {
        return DataSourceBuilder.create().build();
    }

//    @Bean(name = "booksDataSource")
//    @ConfigurationProperties("spring.datasource.books")
//    public DataSource booksDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "resourcesDataSource")
//    @ConfigurationProperties("spring.datasource.resources")
//    public DataSource resourcesDataSource() {
//        return DataSourceBuilder.create().build();
//    }


}




