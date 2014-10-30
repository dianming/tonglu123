package com.zht.service;

import com.zht.entity.Attention;

public interface AttentionService {

	/**
	 * 添加关注
	 * @param attention
	 * @return
	 */
	public Integer addAttention(Attention attention);
	
	/**
	 * 取消关注
	 * @param userId 关注人
	 * @param userById 被关注人
	 * @return
	 */
	public Integer cancelAttention(int userId,int userById);
	
	/**
	 * 验证是否已经存在关系
	 * @param userId
	 * @param userById
	 * @return
	 */
//	public Attention checkRelation(int userId,int userById);
	
}
