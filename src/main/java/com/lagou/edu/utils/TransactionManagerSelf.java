package com.lagou.edu.utils;

import java.sql.SQLException;

/**
 * @author 应癫
 *
 * 事务管理器类：负责手动事务的开启、提交、回滚
 */
public class TransactionManagerSelf {

    private TransactionManagerSelf(){}

    private static TransactionManagerSelf transactionManagerSelf = new TransactionManagerSelf();

    public static TransactionManagerSelf getInstance(){
        return transactionManagerSelf;
    }

    // 开启手动事务控制
    public void beginTransaction() throws SQLException {
        ConnectionUtilsSelf.getConnectionUtilsSelf().getConnection().setAutoCommit(false);
    }


    // 提交事务
    public void commit() throws SQLException {
        ConnectionUtilsSelf.getConnectionUtilsSelf().getConnection().commit();
    }


    // 回滚事务
    public void rollback() throws SQLException {
        ConnectionUtilsSelf.getConnectionUtilsSelf().getConnection().rollback();
    }
}
