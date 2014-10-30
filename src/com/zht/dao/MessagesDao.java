package com.zht.dao;

import com.zht.entity.Message;

public interface MessagesDao {
	public Integer add(Message message);
	
	public Integer del(int id);
}
