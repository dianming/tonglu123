package com.zht.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zht.dao.impl.UsersImpl;

public class UsersTest {

//	@Before
//	public void start() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
//	}

	@Test
	public void queryByUserId() {
		UsersImpl usersImpl = (UsersImpl) applicationContext
				.getBean("usersImpl");
		List<Integer> list=usersImpl.queryByUserId(3);
		for (Integer integer : list) {
			System.out.println("--->"+integer);
		}
	}
}
