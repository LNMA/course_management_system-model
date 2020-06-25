package com.louay.model.factory;

import com.louay.model.util.pool.ConnectionWrapper;
import com.louay.model.util.pool.DBConnectionConfig;
import com.louay.model.util.queue.MyList;
import com.louay.model.util.queue.MyQueue;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
@ComponentScan(basePackages = {"com.louay.model"})
public class BeansFactory {

    @Bean(name = "queue")
    @Scope("singleton")
    public MyList<ConnectionWrapper> getMyQueue() {
        return new MyQueue<>(10);
    }

    @Bean(name = "dbConnectionWrapper")
    @Scope("prototype")
    public ConnectionWrapper getConnectionWrapper() {
        DBConnectionConfig db = new DBConnectionConfig();
        db.setDriver("jdbc:mysql");
        db.setHost("localhost");
        db.setPort("3306");
        db.setSchema("course_management_system?useSSL=false");
        db.setUsername("root");
        db.setPassword("1729384#General");

        Connection connection = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(db.getUrl(), db.getUsername(), db.getPassword());
        } catch (ClassNotFoundException | SQLException | IllegalAccessException | InstantiationException e) {
            System.out.println(e.getMessage());
        }

        return new ConnectionWrapper(connection);
    }

    @Bean(name = "buildAnnotationContextModel")
    @Scope("prototype")
    public ApplicationContext buildContext(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.louay.model");
        context.refresh();
        return context;
    }

    @Bean
    public DataSource mysqlDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/course_management_system?useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("1729384#General");

        return dataSource;
    }
}
