package com.tianhe.framework.commons.online.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author: he.tian
 * @date: 2017/6/10 14:27
 */
public class JdbcUtils {

    private Logger logger = LoggerFactory.getLogger(JdbcUtils.class);

    private ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    @Resource(name="finance")
    private DataSource dataSource;

    public Connection getConnection(){
        Connection conn = threadLocal.get();
        if(conn==null){
            try {
                conn = dataSource.getConnection();
                threadLocal.set(conn);
            } catch (SQLException e) {
                logger.error("获取jdbc连接异常,原因={}", e);
                throw new FrameException(e.getMessage());
            }
        }
        return conn;
    }

    public void releaseConnection(Connection conn){
        try {
            if(conn!=null)
                conn.close();

            threadLocal.remove();
        } catch (SQLException e) {
            logger.error("释放jdbc连接异常,原因={}", e);
            throw new FrameException(e.getMessage());
        }
    }
}
