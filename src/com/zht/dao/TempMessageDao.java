package com.zht.dao;

import com.zht.entity.TempMessage;



public interface TempMessageDao {
	public Integer add(TempMessage tempMessage);
	public Integer del(int userId);
	public Integer queryUserUnreadMessageCount(int userId);
}
