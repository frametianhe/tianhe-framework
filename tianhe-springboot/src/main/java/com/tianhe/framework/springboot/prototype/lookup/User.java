package com.tianhe.framework.springboot.prototype.lookup;

import com.tianhe.framework.springboot.prototype.Address;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

/**
 * Created by tianhe on 2019/11/28.
 */
@Component
public abstract class User {

    @Lookup
    public abstract Address address();

    public void getAddress(){
//        从运行结果看，每次获取的address对象都是不同的，底层是cglib实现
        System.out.println(address());
    }
}
