//package com.ereport.master.config;
//
//import com.zaxxer.hikari.HikariDataSource;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
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
//        entityManagerFactoryRef = "reportEntityManagerFactory",
//        basePackages = {"com.ereport.master.repository"},
//        transactionManagerRef = "reportTransactionManager"
//)
//public class ReportDBConfig {
//
//    @Primary
//    @Bean(name = "reportDataSourceProperties")
//    @ConfigurationProperties("spring.datasource")
//    public DataSourceProperties dataSourceProperties() {
//        return new DataSourceProperties();
//    }
//
//    @Primary
//    @Bean(name = "reportDataSource")
//    @ConfigurationProperties("spring.datasource.configuration")
//    public DataSource dataSource(@Qualifier("reportDataSourceProperties") DataSourceProperties db1DataSourceProperties) {
//        return db1DataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class)
//                .build();
//    }
//
//    @Primary
//    @Bean(name = "reportEntityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean doEntityManagerFactory(
//            EntityManagerFactoryBuilder builder, @Qualifier("reportDataSource") DataSource docDataSource) {
//        return builder
//                .dataSource(docDataSource)
//                .packages("com.ereport.master.domain")
//                .persistenceUnit("report")
//                .build();
//    }
//
//    @Primary
//    @Bean(name = "reportTransactionManager")
//    public PlatformTransactionManager docTransactionManager(
//            @Qualifier("reportEntityManagerFactory") EntityManagerFactory docEntityManagerFactory) {
//        return new JpaTransactionManager(docEntityManagerFactory);
//    }
//}
