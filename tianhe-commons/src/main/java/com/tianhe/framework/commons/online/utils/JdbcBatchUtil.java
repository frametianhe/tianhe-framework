package com.tianhe.framework.commons.online.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


/**
 * 功能说明：jdbc批量操作类
 *
 * @author:weifeng.jiang
 * @DATE:2017/6/28 @TIME:16:42
 */
public  class JdbcBatchUtil {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 功能说明：实现
     * @author:weifeng.jiang
     */
    public void batchSaveTransactionProccess(List<User> userList){
        String sql = "insert into user values(?,?)";
        Connection connection = null;//获取数据库连接 TODO
        PreparedStatement preparedStatement = null;
        try{
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            for (User user : userList) {
                preparedStatement.setString(1,user.getName());
                preparedStatement.setString(2,user.getAge());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
            preparedStatement.clearBatch();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
    }
    }
}
