package com.tianhe.framework.springboot.test;

import com.tianhe.AppTest;
import com.tianhe.framework.springboot.prototype.lookup.User;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by tianhe on 2019/11/28.
 */
public class UserTest extends AppTest{

    @Resource
    private User user;

    @Test
    public void testUser(){
        for(int i=0;i<10;i++){
           user.getAddress();
        }
    }
}
