package com.tianhe.framework.commons.study.jdbc.batch;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class BatchTest {
	
  public static void main(String[] args) {
    Connection conn = null;
    PreparedStatement ps = null;
    try {
      conn = DBConnection.getConnection();
      String sql= "insert into zzq_emp values(?,?,?)";
      ps =conn.prepareStatement(sql);
      for(int i =1;i<=102;i++){
        ps.setInt(1, i);
        ps.setString(2,"king"+i);
        ps.setDouble(3,8000+i);
        ps.addBatch();
        if(i%10==0){
          ps.executeBatch();
          ps.clearBatch();
        }
      }
      
     // ps.executeUpdate();
      ps.executeBatch();
      ps.clearBatch();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        DBConnection.close(ps, conn);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

  }
}
