package com.cn.jdbc;

import java.sql.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class AddUser {
	public void add(UseVo userVo){
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try{
//			JDBC_Connection jcon = new JDBC_Connection();
			conn = JDBC_Connection.getConnection();
			String sql = "INSERT INTO users(id,name,age,adress) values(?,?,?,?,)";
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setInt(1, userVo.getId());
			pstm.setString(2, userVo.getName());
			pstm.setInt(3, userVo.getAge());
//			pstm.setString(4,userVo.getTel());
			pstm.setString(4, userVo.getAddress());
//			pstm.executeUpdate();
			System.out.println("添加成功！添加内容如下：");
			System.out.println("id:"+userVo.getId()+"name:"+userVo.getName()+"age:"+userVo.getAge()+"adress:"+userVo.getAddress());
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBC_Connection.free(rs, conn, pstm);
		}
			
		}
}
