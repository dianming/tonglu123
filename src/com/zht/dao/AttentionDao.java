package com.zht.dao;

import com.zht.entity.Attention;
import com.zht.entity.Users;

public interface AttentionDao {
	
	public Integer add(Attention attention);
	
	public Integer del(Integer userId,Integer UserById);
	
	public Integer put();
	
	public Attention query();
	
	public Attention checkRelation(int userId,int userById);
}
