package com.cn.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class QueryById {
		public UseVo queryById(int id){
			UseVo userVo = null;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try{
				conn = JDBC_Connection.getConnection();
				pstmt = (PreparedStatement) conn.prepareStatement("select * from users while id = ?");
				pstmt.setInt(1, id);
				rs = pstmt.executeQuery();
				while(rs != null){
					userVo = new UseVo();
					userVo.setId(rs.getInt("id"));
					userVo.setName(rs.getString("name"));
					userVo.setAge(rs.getInt("age"));
					userVo.setAddress(rs.getString("address"));
					
				}
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				JDBC_Connection.free(rs, conn, pstmt);
			}
			return userVo;
		}
	
	
}
