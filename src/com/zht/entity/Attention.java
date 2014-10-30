package com.zht.entity;

/**
 * @see 用户关注与被关注 
 * @author admin
 */
public class Attention {
	private Integer attentionId;
	private Integer userId;
	private Integer userById;

	public Integer getAttentionId() {
		return attentionId;
	}

	public void setAttentionId(Integer attentionId) {
		this.attentionId = attentionId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserById() {
		return userById;
	}

	public void setUserById(Integer userById) {
		this.userById = userById;
	}

	public Attention(Integer attentionId, Integer userId, Integer userById) {
		super();
		this.attentionId = attentionId;
		this.userId = userId;
		this.userById = userById;
	}
	
	public Attention() {
		super();
	}

	public Attention(Integer userId, Integer userById) {
		super();
		this.userId = userId;
		this.userById = userById;
	}
}
