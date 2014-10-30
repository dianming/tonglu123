package com.zht.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.zht.dao.TempMessageDao;
import com.zht.entity.TempMessage;
import com.zht.mapper.TempMessageMapper;

@Repository(value="tempMessageImpl")
@Scope("prototype")
public class TempMessageImpl implements TempMessageDao {

	@Autowired
	private SqlSession sqlSession;

//	TempMessageMapper tempMessageMapper = null;
	TempMessageMapper getTempMessageMapper() {
		return sqlSession.getMapper(TempMessageMapper.class);
	}

	/**
	 * 添加消息
	 */
	public Integer add(TempMessage tempMessage) {
		return getTempMessageMapper().add(tempMessage);
	}

	/**
	 * 删除消息
	 */
	@Override
	public Integer del(int userId) {
		return getTempMessageMapper().del(userId);
	}

	/**
	 * 查询未读消息
	 */
	@Override
	public Integer queryUserUnreadMessageCount(int userId) {
		return getTempMessageMapper().queryUserMessageCount(userId);
	}

}
