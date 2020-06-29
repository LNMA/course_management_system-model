package com.louay.model.factory;

import com.louay.model.util.pool.ConnectionWrapper;
import com.louay.model.util.pool.DBConnectionConfig;
import com.louay.model.util.queue.MyList;
import com.louay.model.util.queue.MyQueue;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

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
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(db.getUrl(), db.getUsername(), db.getPassword());
        } catch (ClassNotFoundException | SQLException e) {
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
        p.setMaxWait(10000);
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
        p.setLogAbandoned(true);
        p.setJmxEnabled(true);
        p.setValidationInterval(3000);
        p.setMaxAge(7200000);
        p.setJdbcInterceptors(
                "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"+
                        "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
        dataSource.setPoolProperties(p);

        return dataSource;
    }

    @Bean(name = "buildMapParameter")
    public MapSqlParameterSource buildMapSqlParameter(){
        return new MapSqlParameterSource();
    }

    @Bean(name = "buildKeyHolder")
    public KeyHolder buildGeneratedKeyHolder(){
        return new GeneratedKeyHolder();
    }

}
