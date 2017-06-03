package com.cn.jdbc;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class JDBC_Connection {
	static String drivername = "com.mysql.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/test";
	static String username = "root";
	static String password = "0729";
	/*
	 * 获取驱动
	 * 
	 * */
	static{
		try{
			Class.forName(drivername);//创建驱动
			System.out.println("创建驱动成功！");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
	}
	/*
	 * 连接数据库
	 * 
	 * */
	public static Connection getConnection(){
		Connection conn = null;
		try {
			conn =  (Connection) DriverManager.getConnection(url, username, password);
			System.out.println("连接数据库成功！");
		}catch(SQLException e){
			e.printStackTrace();
		}
		return conn;
	}
	/*
	 * 断开数据库
	 * 
	 * */
	public static void free(ResultSet rs,Connection conn,java.sql.Statement pstmt){
		try{
			if(rs != null)
				rs.close();//关闭结果集
		}catch(SQLException e){
			System.out.println("关闭数据库失败！");
			e.printStackTrace();
		}finally{
			try{
				if(conn != null){
					conn.close();//关闭连接
				}
			}catch(SQLException e){
				System.out.println("关闭Connection失败！");
				e.printStackTrace();
			}finally{
				try{
					if(pstmt != null){
						pstmt.close();//关闭Statement对象
					}
				}catch(SQLException e){
					System.out.println("关闭Statement失败！");
					e.printStackTrace();
				}
			}
		}
	}
}
