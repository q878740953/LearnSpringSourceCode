package com.lagou.edu.utils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 保证同一个线程拿到的connection是同一个
 */
public class ConnectionUtilsSelf {

    // 单例模式 - 饿汉 保证每次获取的对象是同一个
    private ConnectionUtilsSelf(){}

    private static final ConnectionUtilsSelf connectionUtilsSelf = new ConnectionUtilsSelf();

    public static ConnectionUtilsSelf getConnectionUtilsSelf(){
        return connectionUtilsSelf;
    }

    private static final ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<>(); // 存储当前线程的连接

    public Connection getConnection() throws SQLException {
        Connection connection = connectionThreadLocal.get();
        if (connection == null){
            connection = DruidUtils.getInstance().getConnection();
            connectionThreadLocal.set(connection);
        }

        return connection;
    }
}
