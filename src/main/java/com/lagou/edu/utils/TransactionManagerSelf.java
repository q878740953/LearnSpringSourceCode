package com.lagou.edu.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * @author 应癫
 *
 * 事务管理器类：负责手动事务的开启、提交、回滚
 */
@Component("transactionManagerSelf")
public class TransactionManagerSelf {

    // 使用自己写的IOC容器去获取ConnectionUtilsSelf实例化对象
    @Autowired
    private ConnectionUtilsSelf connectionUtilsSelf;

    public void setConnectionUtilsSelf(ConnectionUtilsSelf connectionUtilsSelf) {
        this.connectionUtilsSelf = connectionUtilsSelf;
    }

    // 开启手动事务控制
    public void beginTransaction() throws SQLException {
        connectionUtilsSelf.getConnection().setAutoCommit(false);
    }


    // 提交事务
    public void commit() throws SQLException {
        connectionUtilsSelf.getConnection().commit();
    }


    // 回滚事务
    public void rollback() throws SQLException {
        connectionUtilsSelf.getConnection().rollback();
    }
}
