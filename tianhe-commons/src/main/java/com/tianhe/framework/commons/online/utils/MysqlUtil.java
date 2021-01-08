package com.tianhe.framework.commons.online.utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * mysql工具类
 * @author:weifeng.jiang
 * @DATE:2016年12月9日 @TIME: 下午5:52:54
 */
public class MysqlUtil {
	public static Connection getConnection() throws Exception {
		Connection conn = null;
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/mengli", "mengli", "mengli");
		return conn;
	}

	public static void closeConnection(Connection conn) throws Exception {
		if (conn != null) {
			conn.close();
		}
	}
}