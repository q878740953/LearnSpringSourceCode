package com.lagou.edu.factory;

import com.lagou.edu.utils.TransactionManagerSelf;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理生成工程，由于是生成工厂单例模式即可
 */
public class ProxyFactorySelf {

//    private static ProxyFactorySelf proxyFactorySelf = new ProxyFactorySelf();
//
//    private ProxyFactorySelf() {
//    }
//
//    public static ProxyFactorySelf getInstance(){
//        return proxyFactorySelf;
//    }

    // 使用自己写的IOC容器去获取TransactionManagerSelf实例化对象
    private TransactionManagerSelf transactionManagerSelf;

    public void setTransactionManagerSelf(TransactionManagerSelf transactionManagerSelf) {
        this.transactionManagerSelf = transactionManagerSelf;
    }

    /**
     * 实现事务控制的动态代理
     */
    public Object getTransactionControl(Object object){
        return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object result = null;
                try {
                    // 获取事务控制管理类
                    // 开启事务控制
                    transactionManagerSelf.beginTransaction();
                    // 调用委托对象原有方法逻辑
                    result = method.invoke(object, args);
                    // 提交
                    transactionManagerSelf.commit();
                }catch (Exception e){
                    e.printStackTrace();
                    // 出现异常 回滚
                    transactionManagerSelf.rollback();
                    // 抛出异常
                    throw e;
                }
                return result;
            }
        });
    }
}
