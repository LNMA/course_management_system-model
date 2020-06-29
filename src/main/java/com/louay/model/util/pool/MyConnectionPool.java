package com.louay.model.util.pool;

import com.louay.model.util.constant.QueryType;
import com.louay.model.util.queue.MyList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@Scope("singleton")
@ComponentScan(basePackages = { "com.louay.model"})
public class MyConnectionPool {
    private final MyList<ConnectionWrapper> connection;
    private final ApplicationContext context;
    private ConnectionWrapper tempWrapper;

    @Autowired
    public MyConnectionPool(@Qualifier("buildAnnotationContextModel") ApplicationContext context, @Qualifier("queue") MyList<ConnectionWrapper> connection) {
        this.context = context;
        this.connection = connection;
    }


    public ConnectionWrapper getConnection() throws SQLException {
        if (this.connection.isEmpty()) {
            return (ConnectionWrapper) context.getBean("dbConnectionWrapper");
        } else {
            ConnectionWrapper connectionWrapper = this.connection.dequeue();
            if (connectionWrapper.isAlive()) {
                return connectionWrapper;
            } else {
                connectionWrapper.getConnection().close();
                return getConnection();
            }
        }
    }

    public void release(ConnectionWrapper connectionWrapper){
        this.connection.enqueue(connectionWrapper);
    }

    public ResultSet selectQuery(String query, Object...key) throws SQLException {
        this.tempWrapper = this.getConnection();
        PreparedStatement select = this.tempWrapper.getConnection().prepareStatement(query);
        return (ResultSet) queryAuxiliary(QueryType.SELECT, select, key);
    }

    public int updateQuery(String query,Object...objects) throws SQLException {
        this.tempWrapper = this.getConnection();
        PreparedStatement update = this.tempWrapper.getConnection().prepareStatement(query);
        return (int)queryAuxiliary(QueryType.UPDATE, update, objects);
    }

    private Object queryAuxiliary(QueryType queryType, PreparedStatement preparedStatement, Object...objects) throws SQLException {
        for (int i = 0; i < objects.length; i++) {
            if (objects[i] instanceof String) {
                preparedStatement.setString((i + 1), (String) objects[i]);
            } else if (objects[i] instanceof Integer) {
                preparedStatement.setInt((i + 1), (Integer) objects[i]);
            } else if (objects[i] instanceof java.sql.Date) {
                preparedStatement.setDate((i + 1), (java.sql.Date) objects[i]);
            } else if (objects[i] instanceof Long) {
                preparedStatement.setLong((i + 1), (Long) objects[i]);
            } else if (objects[i] instanceof java.sql.Timestamp) {
                preparedStatement.setTimestamp((i + 1), (java.sql.Timestamp) objects[i]);
            } else if (objects[i] instanceof java.sql.Time) {
                preparedStatement.setTime((i + 1), (java.sql.Time) objects[i]);
            } else if (objects[i] instanceof Double) {
                preparedStatement.setDouble((i + 1), (Double) objects[i]);
            } else if (objects[i] instanceof BigDecimal) {
                preparedStatement.setBigDecimal((i + 1), (BigDecimal) objects[i]);
            } else if (objects[i] instanceof Boolean) {
                preparedStatement.setBoolean((i + 1), (Boolean) objects[i]);
            } else if (objects[i] instanceof java.sql.Blob) {
                preparedStatement.setBlob((i + 1), (java.sql.Blob) objects[i]);
            }
        }

        if (queryType.compareTo(QueryType.UPDATE) == 0){
            int result = preparedStatement.executeUpdate();
            this.release(this.tempWrapper);
            return result;
        }else if (queryType.compareTo(QueryType.SELECT) == 0){
            ResultSet resultSet;
            resultSet = preparedStatement.executeQuery();
            this.release(this.tempWrapper);
            return resultSet;
        }else {
            throw new UnsupportedOperationException("there something wrong while execute the query.");
        }
    }

    public java.sql.Blob initBlob(){
        java.sql.Blob blob = null;
        try{
            this.tempWrapper = this.getConnection();
            blob = this.tempWrapper.getConnection().createBlob();
            this.release(tempWrapper);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return blob;
    }
}
