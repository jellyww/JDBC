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
			System.out.println("���������ɹ�");
		}catch(Exception e){
			System.out.println("��������ʧ��");
		}
		try{
			Connection conn;
			Statement stmt;
			PreparedStatement pstmt;
			ResultSet rs;
			conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","0729");
			System.out.println("���ݿ����ӳɹ�");
			conn.setAutoCommit(false);//���������Զ��ύ��
			//�������ݱ�
			pstmt = (PreparedStatement) conn.prepareStatement("CREATE TABLE useBatch(id INTEGER,name VARCHAR(50));");
			pstmt.execute();
			//ʹ��Statement�����addBatch()������������������¼
			stmt = (Statement) conn.createStatement();
			
			stmt.addBatch("INSERT INTO useBatch VALUES(1,'jack')");
			stmt.addBatch("INSERT INTO useBatch VALUES(2,'make')");
			stmt.addBatch("INSERT INTO useBatch VALUES(3,'curry')");
			//�ύ����������
			int[] commitSQL1 = stmt.executeBatch();
			stmt.clearBatch();
			System.out.println("������������֮��");
			rs = stmt.executeQuery("SELECT * FROM useBatch");
			PrintResult(rs);
			//ʹ��PreparedStatement�����������±�������
			pstmt = (PreparedStatement) conn.prepareStatement("UPDATE useBatch SET name=? WHERE id=?");
			pstmt.setString(1, "����");
			pstmt.setInt(2, 1);
			pstmt.addBatch();
			pstmt.setString(1, "����");
			pstmt.setInt(2, 2);
			pstmt.addBatch();
			int[] commitSQL2 = pstmt.executeBatch();
			pstmt.clearBatch();
			System.out.println("�������º�����ݣ�");
			rs = stmt.executeQuery("SELECT * FROM useBatch");
			PrintResult(rs);
			//ɾ����ʱ��
			pstmt.executeUpdate("DROP TABLE useBatch");
			conn.commit();
			System.out.println("��useBatch��ɾ��");
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
