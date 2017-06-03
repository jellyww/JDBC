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
	 * ��ȡ����
	 * 
	 * */
	static{
		try{
			Class.forName(drivername);//��������
			System.out.println("���������ɹ���");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
	}
	/*
	 * �������ݿ�
	 * 
	 * */
	public static Connection getConnection(){
		Connection conn = null;
		try {
			conn =  (Connection) DriverManager.getConnection(url, username, password);
			System.out.println("�������ݿ�ɹ���");
		}catch(SQLException e){
			e.printStackTrace();
		}
		return conn;
	}
	/*
	 * �Ͽ����ݿ�
	 * 
	 * */
	public static void free(ResultSet rs,Connection conn,java.sql.Statement pstmt){
		try{
			if(rs != null)
				rs.close();//�رս����
		}catch(SQLException e){
			System.out.println("�ر����ݿ�ʧ�ܣ�");
			e.printStackTrace();
		}finally{
			try{
				if(conn != null){
					conn.close();//�ر�����
				}
			}catch(SQLException e){
				System.out.println("�ر�Connectionʧ�ܣ�");
				e.printStackTrace();
			}finally{
				try{
					if(pstmt != null){
						pstmt.close();//�ر�Statement����
					}
				}catch(SQLException e){
					System.out.println("�ر�Statementʧ�ܣ�");
					e.printStackTrace();
				}
			}
		}
	}
}
