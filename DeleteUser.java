package com.cn.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class DeleteUser {
	public void deleteUser(int id){
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = JDBC_Connection.getConnection();
			String sql = "DELETE FROM users where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeLargeUpdate();
			System.out.println("ɾ���ɹ����ϳ���id�ǣ�"+id);
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			JDBC_Connection.free(null, conn, pstmt);
		}
	}
	
	
}
