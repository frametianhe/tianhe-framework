package com.tianhe.framework.commons.online.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author: he.tian
 * @time: 2018-08-06 15:17
 */
public class ShellUtil {

    public static void main(String[] args) throws Exception{
//        可以执行本地系统的命令
        Process process = Runtime.getRuntime().exec("jps");
        InputStreamReader reader = new InputStreamReader(process.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = null;
        for (;(line=bufferedReader.readLine())!= null;){
            System.out.println(line);
        }
    }
}
