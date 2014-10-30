package com.zht.dao;

import java.util.List;

import com.zht.entity.UserInfo;
import com.zht.entity.Users;

public interface UsersDao {

	public Users queryByName(String username);

	public Integer putUsersPwd(String pwd, int id);
	
	public Users queryUsersByUserId(int userId);
	
	public int queryMessageCountByUserId(int userId);
	
	public int queryAttentionCountByUserId(int userId);
	
	public int queryByAttentionCountByUserId(int userId);
	
	public List<UserInfo> queryRecommendFriend(int userId);
	
	public List<Integer> queryByUserId(int userId);
}
