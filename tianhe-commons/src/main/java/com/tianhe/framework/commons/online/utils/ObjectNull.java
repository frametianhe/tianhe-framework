package com.tianhe.framework.commons.online.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 验证把对象=null会加速垃圾回收
 * Created by weifeng.jiang on 2017-08-15 下午 5:50.
 */
public class ObjectNull {


    public static void main(String[] args) throws Exception{
        List<String> list= new ArrayList<String>();
        for (int i = 0; i < 10000000; i++) {
            String a = new String("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            list.add(a);
        }
        list =null;
//        Thread.sleep(1000);

        List<String> list2= new ArrayList<String>();
        for (int i = 0; i<10000000; i++) {
            String a = new String("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
            list2.add(a);
        }
        list2 =null;
//        Thread.sleep(1000);

        List<String> list3= new ArrayList<String>();
        for (int i = 0; i < 10000000; i++) {
            String a = new String("cccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc");
            list3.add(a);
        }
        list3 =null;
//        Thread.sleep(1000);

        List<String> list4= new ArrayList<String>();
        for (int i = 0; i<10000000; i++) {
            String a = new String("dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");
            list4.add(a);
        }
        list4 =null;
//        Thread.sleep(1000);

        List<String> list5= new ArrayList<String>();
        for (int i = 0; i < 10000000; i++) {
            String a = new String("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
            list5.add(a);
        }
        list5 =null;
//        Thread.sleep(1000);

        List<String> list6= new ArrayList<String>();
        for (int i = 0; i<10000000; i++) {
            String a = new String("fffffffffffffffffffffffffffffffffffffffffffffffffffffffff");
            list6.add(a);
        }
        list6= null;
        System.out.println("运行成功。");
    }

}
