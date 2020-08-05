package com.louay.model.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class, SpringDataWebAutoConfiguration.class})
@SpringBootApplication
public class MySpringBootJDBCApplication {
    public static void main(String[] args) {
        SpringApplication.run(MySpringBootJDBCApplication.class, args);
    }
}
