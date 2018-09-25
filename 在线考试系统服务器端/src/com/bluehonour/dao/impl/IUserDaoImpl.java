package com.bluehonour.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.bluehonour.bean.User;
import com.bluehonour.dao.IUserDao;
import com.bluehonour.utils.DBUtils;

public class IUserDaoImpl implements IUserDao{

	
	@Override
	public int findId(User user) {
		Connection conn = DBUtils.getConn();
		QueryRunner qr = new QueryRunner();
		String sql = "select id from euser where username='"+user.getUsername()+"'";
		int id = -1;
		try {
			User u = qr.query(conn, sql, new BeanHandler<>(User.class));
			id = u.getId();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	@Override
	public int login(User user) {
		String username = user.getUsername();
		String password = user.getPassword();
		Connection conn = DBUtils.getConn();
		QueryRunner qr = new QueryRunner();
		String sql = "select ismanager from euser where username='"+username+"' and password"
				+ "='"+password+"'";
		int i = -1;
		try {
			List<User> userList = qr.query(conn, sql, new BeanListHandler<>(User.class));
//			System.out.println(userList);
			if(userList.size()>0) {
				User user2 = userList.get(0);
				i = user2.getIsManager();
			}
		} catch (SQLException e) {
			System.out.println("登录失败");
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
		return i;
	}

	@Override
	public boolean insert(User user) {
		QueryRunner qr = new QueryRunner();
		Connection conn = DBUtils.getConn();
		String sql = "insert into euser(id,username,password) values(euser_id_seq.nextval, '"+user.getUsername()+"','"+
		user.getPassword()+"')";
		boolean flag = false;
		try {
			int i = qr.update(conn, sql);
			if(i>0) {
				flag = true;
			}
		} catch (SQLException e) {
			System.out.println("名字有重名哇，违反唯一约束了");
		}
		return flag;
	}

	@Override
	public boolean delete(String username) {
		QueryRunner qr = new QueryRunner();
		String sql = "delete from euser where username='"+username+"'";
		Connection conn = DBUtils.getConn();
		try {
			int i = qr.update(conn, sql);
			System.out.println();
			if(i>0)
				return true;
			else
				System.out.println("删除失败");
		} catch (SQLException e) {
			System.out.println("删除失败");
		}
		return false;
	}

	@Override
	public boolean studentUpdate(User oldUser, User newUser) {
		QueryRunner qr = new QueryRunner();
		String sql = "update euser set password ='"+newUser.getPassword()+
				"' where username='"+oldUser.getUsername()+"' and password='"+oldUser.getPassword()+"'";
		Connection conn = DBUtils.getConn();
		try {
			int i = qr.update(conn, sql);
			if(i>0) 
				return true;
		} catch (SQLException e) {
			System.out.println("修改密码失败");
		}
		return false;
	}

	@Override
	public boolean managerUpdate(User user) {
		QueryRunner qr = new QueryRunner();
		String sql = "update euser set username='"+user.getUsername()+"' ,password ='"+user.getPassword()+
				"' where username='"+user.getUsername()+"'";
		Connection conn = DBUtils.getConn();
		try {
			int i = qr.update(conn, sql);
			if(i>0) 
				return true;
		} catch (SQLException e) {
			System.err.println("管理员修改学员信息失败");
		}
		return false;
	}

	@Override
	public User findUserByName(String username) {
		QueryRunner qr = new QueryRunner();
		Connection conn = DBUtils.getConn();
		String sql = "select * from euser where username='"+username+"'";
		try {
			List<User> list = qr.query(conn, sql, new BeanListHandler<>(User.class));
			if(!list.isEmpty()) {
				return list.get(0);
			}
		} catch (SQLException e) {
			System.out.println("用户名不存在");
		}
		
		return null;
	}

	@Override
	public List<User> findAll() {
		QueryRunner qr = new QueryRunner();
		Connection conn = DBUtils.getConn();
		String sql = "select * from euser";
		List<User> list = null;
		try {
			list = qr.query(conn, sql, new BeanListHandler<>(User.class));
		} catch (SQLException e) {
			System.out.println("用户名不存在");
		}
		return list;
	}



}
