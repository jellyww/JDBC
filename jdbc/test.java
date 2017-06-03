package com.cn.jdbc;

import java.util.List;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * 连接数据库
		 * */
		connection_test();
		/*
		 * 插入数据
		 * */
		add_test();
		/*
		 * 查询数据库表数据
		 * */
		query_test();
		/*
		 * 按id查询数据库
		 * */
		queryById_test();
		/*
		 * 删除数据
		 * */
		delete_test();
		
		/* 
		 * 修改表数据；
		 * */
		update_test();
		
		
		
	}
	/**
	 * 连接数据库
	 */
	private static void connection_test() {
		JDBC_Connection.getConnection();
	}
	/**
	 * 添加表数据
	 */
	private static void add_test() {
		AddUser addUser = new AddUser();
		UseVo userVo = new UseVo();
		int id= 207;
		String name = "小强";
		int age = 22;
		String tel = "123456789";
		String address = "中国";
		userVo.setId(id);
		userVo.setName(name);
		userVo.setAge(age);
		userVo.setTel(tel);
		userVo.setAddress(address);
		addUser.add(userVo);
	}
	/**
	 * 查询表数据
	 */
	private static void query_test() {
		Query query = new Query();
		List<UseVo> list = query.showUser();
		if(list != null){
			System.out.print("id        ");
			System.out.print("name      ");
			System.out.print("age       ");
			System.out.print("address   ");
			for (int i = 0; i < list.size(); i++) {
				System.out.print(list.get(i).getId());
				System.out.print(list.get(i).getName());
				System.out.print(list.get(i).getAge());
				System.out.print(list.get(i).getAddress());
				System.out.println();
			}
		}
	}
	/**
	 * 按ID查询数据表
	 */
	private static void queryById_test() {
		QueryById byId = new QueryById();
		int id = 207;
		UseVo vo = byId.queryById(id);
		if(vo != null){
			System.out.print("id        ");
			System.out.print("name      ");
			System.out.print("age       ");
			System.out.print("address   ");
			System.out.println();
			System.out.print(vo.getId());
			System.out.print(vo.getName());
			System.out.print(vo.getAge());
			System.out.print(vo.getAddress());
			System.out.println();
		}else{
			System.out.println("该用户不存在");
		}
	}
	/**
	 * 删除数据
	 */
	private static void delete_test() {
		DeleteUser delelteUser = new DeleteUser();
		int id = 1;
		UseVo userVo = new UseVo();
		QueryById queryById = new QueryById();
		userVo = queryById.queryById(id);
		if(userVo != null){
			delelteUser.deleteUser(id);
		}else{
			System.out.println("删除失败");
		}
	}
	/**
	 * 修改表数据
	 */
	private static void update_test() {
		UpdateUser updateUser = new UpdateUser();
		int id = 107;
		String name = "Jack";
		int age = 18;
		String address = "北京";
		QueryById queryById = new QueryById();
		UseVo vo = new UseVo();
		vo = queryById.queryById(id);
		if (vo != null){
			UseVo userVo = new UseVo();
			userVo.setId(id);
			userVo.setName(name);
			userVo.setAge(age);
			userVo.setAddress(address);
			updateUser.update(userVo);
			System.out.println("修改数据成功");
		}else{
			System.out.println("修改数据失败");
		}
	}

}
