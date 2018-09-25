package com.bluehonour.test;

import java.util.List;

import org.junit.Test;

import com.bluehonour.bean.User;
import com.bluehonour.dao.IUserDao;
import com.bluehonour.dao.impl.IUserDaoImpl;

public class IUserDaoImplTest {
	

	@Test
	public void loginTest() {
		IUserDao userDao = new IUserDaoImpl();
		User user = new User("杰哥", "123456");
		int i = userDao.login(user);
		System.out.println(i);
	}

	@Test
	public void insertTest() {
		IUserDao userDao = new IUserDaoImpl();
		User user = new User();
		user.setUsername("a");
		user.setPassword("123456");
		user.setIsManager(2);
		boolean b = userDao.insert(user);
		System.out.println(b);
	}

	@Test
	public void deleteTest() {
		IUserDao userDao = new IUserDaoImpl();
		boolean b = userDao.delete("a");
		System.out.println(b);
	}

	@Test
	public void studentUpdateTest() {
		IUserDao userDao = new IUserDaoImpl();
		User oldUser = new User();
		oldUser.setUsername("a");
		User newUser = new User();
		newUser.setPassword("8522");
		boolean b = userDao.studentUpdate(oldUser, newUser);
		System.out.println(b);
	}

	@Test
	public void findAllTest() {
		IUserDao userDao = new IUserDaoImpl();
		List<User> list = userDao.findAll();
		for (User user : list) {
			System.out.println(user);
		}
	}

	@Test
	public void findUserByNameTest() {
		IUserDao userDao = new IUserDaoImpl();
		User user = userDao.findUserByName("杰哥");
		System.out.println(user);
	}

}
