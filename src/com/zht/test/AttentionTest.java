package com.zht.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zht.entity.Attention;
import com.zht.service.impl.AttentionServiceImpl;

public class AttentionTest {

	ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
			"applicationContext.xml");

	@Test
	public void addAttention() {
		AttentionServiceImpl attentionImpl = (AttentionServiceImpl)applicationContext.getBean("attentionServlet");
		System.out.println(attentionImpl.addAttention(new Attention(3,4)));
	}
	
	@Test
	public void delAttention() {
		AttentionServiceImpl attentionImpl = (AttentionServiceImpl)applicationContext.getBean("attentionServlet");
		System.out.println(attentionImpl.cancelAttention(3,4));
		
	}
}
