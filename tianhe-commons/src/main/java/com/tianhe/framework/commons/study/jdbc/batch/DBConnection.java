package com.tianhe.framework.commons.study.jdbc.batch;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
public class DBConnection {

  private static String url;
  private static String user;
  private static String password;
  static {
    Properties props = new Properties();
    // 注册驱动
    try {
      InputStream is  = new FileInputStream("src/db.properties");
      props.load(is);
//     System.out.println(props.getProperty("dirver"));
       url=props.getProperty("url");
       user = props.getProperty("user");
       password = props.getProperty("password");
       Class.forName(props.getProperty("dirver"));
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      // 计写日志log4j
      throw new RuntimeException("注册驱动有误!");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      throw new RuntimeException("配置文件没找到");
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException("读取配置文件失败");
    }
  }

  /*
   * 获取连接
   */
  public static Connection getConnection() throws Exception {
    Connection conn = null;
    try {
      conn = DriverManager.getConnection(
          url,user,password);
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException("连接失败");
    }
    return conn;
  }

  /*
   * 关闭数据库
   * null  ""
   */
  // 关闭有结果集(查询)      PreparedStatement
   public static void close(ResultSet rs,Statement stat,Connection conn)throws Exception{
       try {
        if(rs !=null){
           rs.close();
         }
         if(stat != null){
           stat.close();
         }
         if(conn != null){
            conn.close();
         }
      } catch (SQLException e) {
        e.printStackTrace();
        throw new RuntimeException("关闭失败");
      }
     
     
   }
  
  // 关闭无结果集(添加、修改、删除)
   public static void close(Statement stat,Connection conn)throws Exception{
     try {
      if(stat != null){
           stat.close();
       }
       if(conn!=null){
         conn.close();
       }
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException("关闭失败");
    }
   }
  
  
  public static void main(String[] args)throws Exception {
    System.out.println(DBConnection.getConnection());

  }

}
