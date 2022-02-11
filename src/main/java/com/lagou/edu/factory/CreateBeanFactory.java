package com.lagou.edu.factory;

import com.lagou.edu.utils.ConnectionUtilsSelf;

public class CreateBeanFactory {

    public static ConnectionUtilsSelf getIntanceStatic(){
        return new ConnectionUtilsSelf();
    }

    public ConnectionUtilsSelf getIntance(){
        return new ConnectionUtilsSelf();
    }
}
