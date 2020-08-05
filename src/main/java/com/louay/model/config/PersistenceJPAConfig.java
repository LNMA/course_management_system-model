package com.louay.model.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@EnableAutoConfiguration
@Configuration
@EnableTransactionManagement
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "com.louay.model.dao")
@EntityScan(basePackages = "com.louay.model.entity")
@ComponentScan(basePackages = "com.louay.model")
public class PersistenceJPAConfig {

    @Bean(name = "pool")
    @Order(1)
    public DataSource mysqlDataSource() {
        org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
        PoolProperties p = new PoolProperties();
        p.setDriverClassName("com.mysql.cj.jdbc.Driver");
        p.setUrl("jdbc:mysql://localhost:3306/course_management_system?useSSL=false");
        p.setUsername("root");
        p.setPassword("1729384#General");
        p.setMaxActive(100);
        p.setMaxIdle(100);
        p.setMinIdle(10);
        p.setInitialSize(10);
        p.setMaxWait(20000);
        p.setTestOnBorrow(true);
        p.setTestOnConnect(false);
        p.setTestOnReturn(false);
        p.setTestWhileIdle(false);
        p.setValidationQuery("SELECT 1");
        p.setValidationQueryTimeout(-1);
        p.setValidatorClassName(null);
        p.setTimeBetweenEvictionRunsMillis(5000);
        p.setMinEvictableIdleTimeMillis(30000);
        p.setRemoveAbandoned(true);
        p.setRemoveAbandonedTimeout(60);
        p.setSuspectTimeout(60);
        p.setLogAbandoned(true);
        p.setJmxEnabled(true);
        p.setValidationInterval(3000);
        p.setMaxAge(7200000);
        p.setDefaultAutoCommit(true);
        p.setJdbcInterceptors(
                "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;" +
                        "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer;"+
                "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
        dataSource.setPoolProperties(p);
        /*
        StackTraceElement[] st = Thread.currentThread().getStackTrace();
        System.out.println("create connection called from " + st[2]);
         */

        return dataSource;
    }

    @Bean
    @Order(2)
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        JpaVendorAdapter vendorAdapter = getHibernateJpaVendorAdapter();
        LocalContainerEntityManagerFactoryBean entityManagerBean = new LocalContainerEntityManagerFactoryBean();

        entityManagerBean.setDataSource(mysqlDataSource());
        entityManagerBean.setPackagesToScan("com.louay.model");
        entityManagerBean.setJpaVendorAdapter(vendorAdapter);
        entityManagerBean.setJpaProperties(additionalProperties());

        return entityManagerBean;
    }

    @Bean
    public HibernateJpaVendorAdapter getHibernateJpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Bean
    @Order(3)
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update"); //for developer none | validate | update | create-only |create | create-drop with every SessionFactory close
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        properties.setProperty("hibernate.naming-strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
        //properties.setProperty("hibernate.envers.audit_table_suffix", "_AUDIT_LOG");
        //properties.setProperty("hibernate.globally_quoted_identifiers", "true");
        //properties.setProperty("hibernate.enhancer.enableLazyInitialization", "true");
        //properties.setProperty("hibernate.ejb.use_class_enhancer", "true");
        //properties.setProperty("hibernate.discriminator.ignore_explicit_for_joined", "true");
        //properties.setProperty("hibernate.current_session_context_class", "thread");
        //properties.setProperty("spring.jpa.open-in-view", "true");
        properties.setProperty("hibernate.format_sql", "false"); //to show query
        properties.setProperty("hibernate.generate_statistics", "false"); // to show the time in nano second to prepare the batch

        //for batch
        properties.setProperty("hibernate.jdbc.batch_size", "4");
        properties.setProperty("hibernate.order_inserts", "true");
        properties.setProperty("hibernate.order_updates", "true");
        properties.setProperty("hibernate.batch_versioned_data", "true");

        return properties;
    }
}
