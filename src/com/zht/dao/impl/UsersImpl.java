package com.zht.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.zht.dao.UsersDao;
import com.zht.entity.UserInfo;
import com.zht.entity.Users;
import com.zht.mapper.UsersMapper;

@Repository(value="usersImpl")
@Scope(value="prototype")
public class UsersImpl implements UsersDao{

	@Autowired
	private SqlSession sqlSession;
	
	UsersMapper getUsersMapper() {
		return sqlSession.getMapper(UsersMapper.class);
	}
	
	/**
	 * 根据账号查询
	 */
	@Override
	public Users queryByName(String username) {
		return getUsersMapper().LoginByNamePwd(username);
	}
	
	/**
	 * 修改密码
	 */
	@Override
	public Integer putUsersPwd(String pwd,int id) {
		return getUsersMapper().updateUsersPwd(pwd, id);
	}
	
	/**
	 * 更具ID查询
	 */
	@Override
	public Users queryUsersByUserId(int userId) {
		return getUsersMapper().queryUsersByUserId(userId);
	}
	
	/**
	 * 查询发布说说条数
	 */
	@Override
	public int queryMessageCountByUserId(int userId) {
		return getUsersMapper().queryByAttentionCount(userId);
	}

	/**
	 * 关注人数
	 */
	@Override
	public int queryAttentionCountByUserId(int userId) {
		return getUsersMapper().queryAttentionCount(userId);
	}

	/**
	 * 被关注人数
	 */
	@Override
	public int queryByAttentionCountByUserId(int userId) {
		return getUsersMapper().queryMessageCount(userId);
	}
	/**
	 * 朋友推荐查询
	 */
	@Override
	public List<UserInfo> queryRecommendFriend(int userId) {
		return getUsersMapper().queryRecommendFriend(userId);
	}

	/**
	 * 查询关注我的人
	 */
	@Override
	public List<Integer> queryByUserId(int userId) {
		return getUsersMapper().queryByUserId(userId);
	}
}
