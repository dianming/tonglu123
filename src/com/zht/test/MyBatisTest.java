package com.zht.test;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.zht.entity.Message;

public class MyBatisTest {
	@Test
	public void test1() throws Exception {
		
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(inputStream);
		Message talk = new Message();
		talk.setAccessAuthority(0);
		talk.setContent("xxxxx");
		talk.setStatus(0);
		SqlSession session = sf.openSession();
		Integer u = session.selectOne("xxx.inserttalk",talk);
		System.out.println("------>" + u);
		System.out.println("==========>" + talk.getUserId());
	}
}
