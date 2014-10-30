package com.zht.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zht.dao.impl.MessagesImpl;
import com.zht.dao.impl.UsersImpl;
import com.zht.entity.Message;
import com.zht.service.TalkService;

@Service("talkServiceImpl")
@Scope(value="prototype")
public class TalkServiceImpl implements TalkService {

	Logger logs=Logger.getLogger(this.getClass().getName());
	@Autowired
	private MessagesImpl talkImpl;

	@Autowired
	private UsersImpl usersImpl;

	/**
	 * 添加说说
	 * @param pwd 密码
	 * @param talk 对象
	 * @return 0 失败，1成功
	 */
	public Integer addTalk(Message talk,String pwd) {
		talk.setAccessAuthority(0);//0所有人访问
		talk.setStatus(0);//0没有冻结
		try {
			if(usersImpl.putUsersPwd(pwd,talk.getUserId())!=1){
				return 0;
			};
			return talkImpl.add(talk);
		} catch (Exception e) {
			logs.warn("添加说说失败！", e);
		}
		return 0;
	}
}
