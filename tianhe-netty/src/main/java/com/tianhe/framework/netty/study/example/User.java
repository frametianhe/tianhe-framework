package com.tianhe.framework.netty.study.example;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: he.tian
 * @time: 2018-08-02 14:51
 */
@Data
public class User implements Serializable{

//    这里要实现序列化
    private static final long serialVersionUID = 5621607886328950072L;

    private String userName;
}
