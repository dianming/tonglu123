package com.zht.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.zht.dao.MessagesDao;
import com.zht.entity.Message;
import com.zht.mapper.MessageMapper;


@Repository(value = "messagesImpl")
@Scope("prototype")
public class MessagesImpl implements MessagesDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	/**
	 * 添加消息
	 */
	@Override
	public Integer add(Message message) {
		return sqlSession.getMapper(MessageMapper.class).add(message);
//		return sqlSession.insert("inserttalk",talk);
	}

	/**
	 * 删除消息
	 */
	@Override
	public Integer del(int id) {
		return sqlSession.getMapper(MessageMapper.class).del(id);
	}

}
