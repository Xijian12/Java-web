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
//public class OperatorDataSourceConfig {
//
//    @Bean(name = "operatorDataSource")
//    @ConfigurationProperties("spring.datasource.operator")
//    public DataSource operatorDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "operatorSqlSessionFactory")
//    public SqlSessionFactory operatorSqlSessionFactory(@Qualifier("operatorDataSource") DataSource dataSource, ApplicationContext applicationContext) throws Exception {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(dataSource);
//        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath*:com/shu/mapper/operator/*.xml"));
//        return sqlSessionFactoryBean.getObject();
//    }
//
//    @Bean(name = "operatorTransactionManager")
//    public DataSourceTransactionManager operatorTransactionManager(@Qualifier("operatorDataSource") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//}
//
