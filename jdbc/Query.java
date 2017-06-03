package com.cn.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Query {
	public List<UseVo> showUser(){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<UseVo> list = new ArrayList<UseVo>();
		try{
			conn = JDBC_Connection.getConnection();
			stmt = (Statement) conn.createStatement();
			rs = stmt.executeQuery("select * from users");
			while(rs.next()){
				UseVo userVo = new UseVo();
				userVo.setId(rs.getInt("id"));
				userVo.setName(rs.getString("name"));
				userVo.setAge(rs.getInt("age"));
				userVo.setAddress(rs.getString("address"));
				list.add(userVo);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			
			JDBC_Connection.free(rs, conn, stmt);
		}
		return list;
	}
}
