package com.zht.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zht.dao.impl.MessagesImpl;
import com.zht.dao.impl.TempMessageImpl;
import com.zht.dao.impl.UsersImpl;
import com.zht.entity.Message;
import com.zht.entity.TempMessage;
import com.zht.entity.UserInfo;
import com.zht.entity.Users;
import com.zht.service.UsersService;

@Service(value="usersServiceImpl")
@Scope(value="prototype")
public class UsersServiceImpl implements UsersService {
	Logger logs = Logger.getLogger(this.getClass().getPackage().getName());
	
	@Autowired
	private UsersImpl usersImpl;
	
	@Autowired
	private MessagesImpl messagesImpl;
	
	@Autowired
	private TempMessageImpl tempMessageImpl;
	
	public Users queryByName(String userName) {
		return usersImpl.queryByName(userName);
	}
	
	/**
	 * 
	 * @param username
	 *            用户账号
	 * @param pwd
	 *            用户密码
	 * @param id
	 *            用户id
	 * @return
	 */
	public Integer putUsersPwd(String username, String pwd) {
		Users users = usersImpl.queryByName(username);
		// 这里需要旧密码验证 ????
		Integer influenceRows = null;
		if (users != null) {
			influenceRows = usersImpl.putUsersPwd(pwd, users.getUserId());
		}
		return influenceRows;
	}
	
	/**
	 * 查询用户，关注人数，被关注人数，说说发布条数
	 */
	@Override
	public Map<String, Integer> queryProfile(Integer userId) {
		if(userId!=null){
			Map<String, Integer> mapCount = new HashMap<String, Integer>();
			mapCount.put("attentionCount",usersImpl.queryAttentionCountByUserId(userId));
			mapCount.put("byAttentionCount",usersImpl.queryByAttentionCountByUserId(userId));
			mapCount.put("messageCount",usersImpl.queryMessageCountByUserId(userId));
			return mapCount;
		}return null;
	}
	
	/**
	 * 发布心情
	 */
	@Transactional
	public Integer addMessage(Message message,String pwd) {
		message.setAccessAuthority(0);//0所有人访问
		message.setStatus(0);//0没有冻结
		Message prototypeMessage=null;
		if(message !=null){
			prototypeMessage=message;
		}
		try {
			if(usersImpl.putUsersPwd(pwd,message.getUserId())!=1){
				return 0;
			};
			List<Integer> byUsers = usersImpl.queryByUserId(message.getUserId());
			if(messagesImpl.add(prototypeMessage)!=0){
				for (Integer byUserId : byUsers) {
					tempMessageImpl.add(new TempMessage(byUserId,message.getMessageId(),0));
				}
			};
			return 1;
		} catch (Exception e) {
			logs.warn("添加说说失败！", e);
		}
		return 0;
	}
	
	/**
	 * 刷新未读消息，短链接？
	 */
	@Override
	public Integer refreshTempMessage(Integer userId) {
		if(userId!=null){
			return tempMessageImpl.queryUserUnreadMessageCount(userId);
		}return 0;
	}

	/**
	 * 推荐朋友查询
	 */
	@Override
	public List<UserInfo> queryRecommendFriend(int userId) {
		return usersImpl.queryRecommendFriend(userId);
	}
}