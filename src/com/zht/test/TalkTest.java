package com.zht.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zht.dao.impl.TempMessageImpl;
import com.zht.entity.Message;
import com.zht.entity.TempMessage;
import com.zht.service.UsersService;
import com.zht.service.impl.TalkServiceImpl;
import com.zht.service.impl.UsersServiceImpl;


public class TalkTest {

//	@Before
//	public void start() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
//	}

	@Test
	public void updateUserPwd() {
		TalkServiceImpl talkServiceImpl = (TalkServiceImpl) applicationContext
				.getBean("talkService");
		Integer influenceRows = null;
		try {
			influenceRows = talkServiceImpl.addTalk(new Message(0,0),"xxx");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("--->"+influenceRows);

	}
	
	@Test
	public void addTalk() throws Exception {
		TempMessageImpl tempMessageImpl = (TempMessageImpl) applicationContext.getBean("tempMessageImpl");
		Integer result = null;
		TempMessage tempMessage= new TempMessage(1,1,1);
		try {
			result = tempMessageImpl.add(tempMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("------>" + result);
		System.out.println(new TempMessage().getTempMessageId());
		System.out.println("==========>" + tempMessage.getTempMessageId());
	}
	@Test
	public void addTempMessage() throws Exception {
		UsersService usersServiceImpl = (UsersService) applicationContext.getBean("usersServiceImpl");
		Integer result = null;
		try {
			result = usersServiceImpl.addMessage(new Message("xxxxx",3,3), "xxx");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
