package com.zht.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zht.dao.AttentionDao;
import com.zht.entity.Attention;
import com.zht.mapper.AttentionMapper;

@Repository
public class AttentionImpl implements AttentionDao {

	@Autowired
	private SqlSession sqlSession;
	
	public AttentionMapper attentionMapper;
	
	public AttentionMapper getAttentionMapper() {
		return this.attentionMapper=sqlSession.getMapper(AttentionMapper.class);
	}
	
	public Integer add(Attention attention) throws NullPointerException {
		if(attention !=null && attention.getUserId()!=null && attention.getUserById()!=null){
			return getAttentionMapper().addAttention(attention);
		}return 0;
	}
	
	public Integer del(Integer userId,Integer userById) {
		return getAttentionMapper().delAttention(userId, userById);
	}
	
	public Integer put() {
		return null;
	}
	
	public Attention query() {
		// TODO Auto-generated method stub
		return null;
	}

	public Attention checkRelation(int userId, int userById) {
		return getAttentionMapper().checkRelation(userId, userById);
	}

}
