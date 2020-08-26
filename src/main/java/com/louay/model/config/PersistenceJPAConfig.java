package com.louay.model.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@EnableAutoConfiguration
@Configuration
@EnableTransactionManagement
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "com.louay.model.dao")
@EntityScan(basePackages = "com.louay.model.entity")
@ComponentScan(basePackages = "com.louay.model")
public class PersistenceJPAConfig {

    @Bean("pool")
    @Order(1)
    public DataSource mysqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/course_management_system");
        dataSource.setUsername("root");
        dataSource.setPassword("1729384#General");
        /*
        StackTraceElement[] st = Thread.currentThread().getStackTrace();
        System.out.println("create connection called from " + st[2]);
         */
        return dataSource;
    }

    @Bean
    @Order(2)
    public PlatformTransactionManager transactionManager(@Autowired @Qualifier("pool") DataSource dataSource) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory(dataSource).getObject());

        return transactionManager;
    }

    @Bean
    @Order(3)
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        JpaVendorAdapter vendorAdapter = getHibernateJpaVendorAdapter();
        LocalContainerEntityManagerFactoryBean entityManagerBean = new LocalContainerEntityManagerFactoryBean();

        entityManagerBean.setDataSource(dataSource);
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
