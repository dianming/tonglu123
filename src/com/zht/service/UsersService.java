package com.zht.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.zht.entity.Message;
import com.zht.entity.UserInfo;
import com.zht.entity.Users;

public interface UsersService {
	
	public Users queryByName(String userName);
	
	public Integer putUsersPwd(String username,String pwd);
	
	public Map<String, Integer> queryProfile(Integer user);
	
	public Integer addMessage(Message talk,String pwd);
	
	public Integer refreshTempMessage(Integer userId);
	
	//随机查询非自己好友
	public List<UserInfo> queryRecommendFriend(int userId);
	
}