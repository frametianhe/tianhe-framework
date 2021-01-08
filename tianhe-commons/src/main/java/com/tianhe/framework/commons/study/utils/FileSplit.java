package com.tianhe.framework.commons.study.utils;

import java.io.*;

/**
 * 大小文件拆分小文件并去掉换行符
 * @author: he.tian
 * @time: 2018-09-18 17:04
 */
public class FileSplit {

    public static void main(String[] args) throws Exception{
        FileInputStream in = new FileInputStream("/Users/jiangweifeng/Documents/tb_detail_transaction_proccess_201809.sql");
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader bufferedReader = new BufferedReader(reader);

        BufferedWriter bufferedWriter = getWriter(0);
        String line = "";
        int count = 0;
        int fileNum = 0;
        while ((line=bufferedReader.readLine()) != null){
            bufferedWriter.write(line);
            line = "";
            count = count+1;
            if(count == 10000){
                bufferedWriter.close();
                fileNum = fileNum+1;
                bufferedWriter = getWriter(fileNum);
                count=0;
            }
        }
        bufferedReader.close();

    }

    public static BufferedWriter getWriter(int fileNum) throws Exception{
        FileOutputStream out = new FileOutputStream("/Users/jiangweifeng/Documents/tb_detail_transaction_proccess_201809_execute_"+fileNum+".sql");
        OutputStreamWriter writer = new OutputStreamWriter(out);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        return bufferedWriter;
    }
}
