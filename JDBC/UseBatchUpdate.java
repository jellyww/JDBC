package com.cn.useBatchUpdate;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class UseBatchUpdate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("创建驱动成功");
		}catch(Exception e){
			System.out.println("创建驱动失败");
		}
		try{
			Connection conn;
			Statement stmt;
			PreparedStatement pstmt;
			ResultSet rs;
			conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","0729");
			System.out.println("数据库连接成功");
			conn.setAutoCommit(false);//设置事务不自动提交；
			//创建数据表
			pstmt = (PreparedStatement) conn.prepareStatement("CREATE TABLE useBatch(id INTEGER,name VARCHAR(50));");
			pstmt.execute();
			//使用Statement对象的addBatch()方法向表中批量插入记录
			stmt = (Statement) conn.createStatement();
			
			stmt.addBatch("INSERT INTO useBatch VALUES(1,'jack')");
			stmt.addBatch("INSERT INTO useBatch VALUES(2,'make')");
			stmt.addBatch("INSERT INTO useBatch VALUES(3,'curry')");
			//提交批处理命令
			int[] commitSQL1 = stmt.executeBatch();
			stmt.clearBatch();
			System.out.println("批量插入数据之后：");
			rs = stmt.executeQuery("SELECT * FROM useBatch");
			PrintResult(rs);
			//使用PreparedStatement对象批量更新表中数据
			pstmt = (PreparedStatement) conn.prepareStatement("UPDATE useBatch SET name=? WHERE id=?");
			pstmt.setString(1, "张三");
			pstmt.setInt(2, 1);
			pstmt.addBatch();
			pstmt.setString(1, "李四");
			pstmt.setInt(2, 2);
			pstmt.addBatch();
			int[] commitSQL2 = pstmt.executeBatch();
			pstmt.clearBatch();
			System.out.println("批量更新后的数据：");
			rs = stmt.executeQuery("SELECT * FROM useBatch");
			PrintResult(rs);
			//删除临时表
			pstmt.executeUpdate("DROP TABLE useBatch");
			conn.commit();
			System.out.println("表useBatch已删除");
			conn.close();
			stmt.close();
			pstmt.close();
			
		}catch(SQLException e){
			System.out.println(e);
		}finally{
		
		}
	}

	private static void PrintResult(ResultSet rs) {
		// TODO Auto-generated method stub
		System.out.println("id:"+"\tname:");
		try{
			while(rs.next()){
				System.out.println(rs.getString(1)+"\t"+rs.getString(2));
			}
			rs.close();
		}catch(SQLException e){
			System.out.println(e);
		}finally{
			
		}
	}

}
