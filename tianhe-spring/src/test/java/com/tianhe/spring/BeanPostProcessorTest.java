package com.tianhe.spring;

import com.tianhe.AppTest;
import com.tianhe.integration.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by tianhe on 2019/12/11.
 */
public class BeanPostProcessorTest extends AppTest{

    @Autowired
    User user;

    @Test
    public void testAfterInit(){
        System.out.println(user);
    }
}
