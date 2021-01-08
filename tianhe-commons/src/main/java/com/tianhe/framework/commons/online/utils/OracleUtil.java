package com.tianhe.framework.commons.online.utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * oracle工具类
 * @author:weifeng.jiang
 * @DATE:2016年12月9日 @TIME: 下午5:53:13
 */
public class OracleUtil {
	public static Connection getConnection() throws Exception {
		Connection conn = null;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(
				"jdbc:oracle:thin:@127.0.0.1:1521:tianhe", "mengli", "mengli");
		return conn;
	}

	public static void closeConnection(Connection conn) throws Exception {
		if (conn != null) {
			conn.close();
		}
	}
}