package com.zht.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zht.dao.impl.MessagesImpl;
import com.zht.entity.Message;
import com.zht.service.impl.UsersServiceImpl;

public class SpringTest {

//	@Before
//	public void start() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
//	}

	@Test
	public void updateUserPwd() {
		UsersServiceImpl usersService = (UsersServiceImpl) applicationContext
				.getBean("usersService");
		Integer influenceRows = null;
		try {
			influenceRows = usersService.putUsersPwd("xxx", "aaa");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("--->"+influenceRows);

	}
	
	@Test
	public void delTalk() throws Exception{
		MessagesImpl talkImpl = (MessagesImpl) applicationContext.getBean("talkImpl");
		System.out.println("--->"+talkImpl.del(5));
	}
	@Test
	public void addTalk() throws Exception {
		MessagesImpl talkImpl = (MessagesImpl) applicationContext.getBean("messagesImpl");
		Message talk = new Message();
		talk.setAccessAuthority(0);
		talk.setContent("fff");
		talk.setStatus(0);
		Integer result = null;
		try {
			result = talkImpl.add(talk);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("------>" + result);
		System.out.println("==========>" + talk.getMessageId());
		
		// SolutionService si=(SolutionService)
		// applicationContext.getBean("SolutionService");
		// Solution solution=new Solution();
		// solution.setTitle("x2");
		// solution.setContent("x2");
		// solution.setCreateDate("x2");
		// si.add(solution);
		// List<Solution> list=si.queryAll();
		// for (Solution solution : list) {
		// System.out.println(solution.getCreateDate());
		// }
		// ObjectMapper mapper = new ObjectMapper();
		// Map<String, String> map = new HashMap<String, String>();
		// map.put("studentList", "xxxx");
		// map.put("class", "ClassName");
		// String jsonfromMap = mapper.writeValueAsString(map);
		// System.out.println(jsonfromMap);
		// String url = "/resource/js/xxx.js";
		// System.out.println(url.endsWith(".js"));
	}
}
