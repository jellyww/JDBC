package com.cn.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class UpdateUser {
	public void update(UseVo userVo){
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE usres set id=?,name=?,age=?,address=?WHERE id = ?";
		
		try{
			conn = JDBC_Connection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userVo.getId());
			pstmt.setString(2, userVo.getName());
			pstmt.setInt(3, userVo.getAge());
			pstmt.setString(4, userVo.getAddress());
			pstmt.setInt(5, userVo.getId());
			pstmt.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			JDBC_Connection.free(null, conn, pstmt);
		}
	}
	
	
}
