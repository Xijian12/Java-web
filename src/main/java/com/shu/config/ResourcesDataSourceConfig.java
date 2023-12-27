//package com.shu.config;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//
///**
// * @author 85131
// */
//@Configuration
//@EnableTransactionManagement
//public class ResourcesDataSourceConfig {
//
//    @Bean(name = "resourcesDataSource")
//    @ConfigurationProperties("spring.datasource.resources")
//    public DataSource resourcesDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "resourcesSqlSessionFactory")
//    public SqlSessionFactory resourcesSqlSessionFactory(@Qualifier("resourcesDataSource") DataSource dataSource, ApplicationContext applicationContext) throws Exception {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(dataSource);
//        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath*:com/shu/mapper/resources/*.xml"));
//        return sqlSessionFactoryBean.getObject();
//    }
//
//    @Bean(name = "resourcesTransactionManager")
//    public DataSourceTransactionManager resourcesTransactionManager(@Qualifier("resourcesDataSource") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//}
//
