package com.lagou.edu.utils;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 保证同一个线程拿到的connection是同一个
 */
@Component("connectionUtilsSelf")
public class ConnectionUtilsSelf {

    // 单例模式 - 饿汉 保证每次获取的对象是同一个
//    private ConnectionUtilsSelf(){}
//
//    private static final ConnectionUtilsSelf connectionUtilsSelf = new ConnectionUtilsSelf();
//
//    public static ConnectionUtilsSelf getConnectionUtilsSelf(){
//        return connectionUtilsSelf;
//    }
    @Autowired
    private DruidDataSource druidDataSource;

    private static final ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<>(); // 存储当前线程的连接

    public Connection getConnection() throws SQLException {
        Connection connection = connectionThreadLocal.get();
        if (connection == null){
            connection = druidDataSource.getConnection();
            connectionThreadLocal.set(connection);
        }

        return connection;
    }
}
