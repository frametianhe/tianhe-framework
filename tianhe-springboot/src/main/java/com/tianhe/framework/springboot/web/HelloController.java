package com.tianhe.framework.springboot.web;

import com.tianhe.framework.springboot.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tianhe on 2019/11/5.
 */
@RestController
public class HelloController {

    @RequestMapping(value = "/hello")
    public User hello(User user){
        return user;
    }
}
