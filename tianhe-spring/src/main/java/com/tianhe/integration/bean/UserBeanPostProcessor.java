package com.tianhe.integration.bean;

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;

/**
 * Created by tianhe on 2019/12/11.
 */
public class UserBeanPostProcessor implements BeanPostProcessor{

//    @Override
//    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
////        在bean初始化完毕之后可以修改属性值
//        Class<?> beanClass = bean.getClass();
//        User user = new User();
//        if(beanClass.isAssignableFrom(User.class)){
//            user = (User) bean;
//            user.setUserName("userName");
//        }
//        return user;
//    }


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        Data data = AnnotationUtils.findAnnotation(beanClass, Data.class);
        if(data != null){
//            可以判断bean类或属性、方法上是不是有定义的注解
        }
        return bean;
    }
}
