//package com.ereport.master.config;
//
//import com.zaxxer.hikari.HikariDataSource;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//        entityManagerFactoryRef = "kenmlEntityManagerFactory",
//        basePackages = {"com.ereport.master.kenML"},
//        transactionManagerRef = "twTransactionManager"
//)
//public class KenMLDBConfig {
//
//    @Bean(name = "kenmlDataSourceProperties")
//    @ConfigurationProperties("kenml.datasource")
//    public DataSourceProperties dataSourceProperties() {
//        return new DataSourceProperties();
//    }
//
//    @Bean(name = "kenmlDataSource")
//    @ConfigurationProperties("kenml.datasource.configuration")
//    public DataSource dataSource(@Qualifier("kenmlDataSourceProperties") DataSourceProperties db1DataSourceProperties) {
//        return db1DataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class)
//                .build();
//    }
//
//    @Bean(name = "kenmlEntityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean rwEntityManagerFactory(
//            EntityManagerFactoryBuilder builder, @Qualifier("kenmlDataSource") DataSource rwDataSource) {
//        return builder
//                .dataSource(rwDataSource)
//                .packages("com.ereport.master.kenML")
//                .persistenceUnit("kenml")
//                .build();
//    }
//
//    @Bean(name = "twTransactionManager")
//    public PlatformTransactionManager transactionManager(
//            @Qualifier("kenmlEntityManagerFactory") EntityManagerFactory rwEntityManagerFactory) {
//        return new JpaTransactionManager(rwEntityManagerFactory);
//    }
//}
