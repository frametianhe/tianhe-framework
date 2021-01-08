package com.tianhe.framework.java.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author jiangweifeng
 * @date 2020/05/17
 * @description
 */
public class StreamTest {

    //user不为空的用userName分组
    @Test
    public void testListToMap(){
        User user = new User("userName","pwd");
        User user2 = new User("userName2","pwd2");
        List list = new ArrayList();
        list.add(user);
        list.add(user2);
        Map<String,User> map = (Map<String, User>) list.stream().filter(Objects::nonNull).collect(Collectors.groupingBy(new Function<Object, Object>() {
            @Override
            public Object apply(Object o) {
                User u = (User) o;
                return u.getUserName();
            }
        }));
        System.out.println(map);
    }

//    找出list中user对象的userName组成list
    @Test
    public void testListToList(){
        User user = new User("userName","pwd");
        User user2 = new User("userName2","pwd2");
        List list = new ArrayList();
        list.add(user);
        list.add(user2);
        Object collect = list.stream().filter(Objects::nonNull).map(new Function() {
            @Override
            public Object apply(Object o) {
                User u = (User) o;
                return u.getUserName();
            }
        }).collect(Collectors.toList());
        System.out.println(collect);

    }
}
