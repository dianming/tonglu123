package com.zht.entity;

/**
 * 临时存储消息，用户读取之后删除
 * 
 * @author admin
 * 
 */
public class TempMessage {
	private Integer tempMessageId;
	private Integer userId;
	private Integer readStatus;
	private Integer messageId;

	public Integer getTempMessageId() {
		return tempMessageId;
	}

	public void setTempMessageId(Integer tempMessageId) {
		this.tempMessageId = tempMessageId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(Integer readStatus) {
		this.readStatus = readStatus;
	}

	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	public TempMessage(Integer tempMessageId, Integer userId,
			Integer readStatus, Integer messageId) {
		super();
		this.tempMessageId = tempMessageId;
		this.userId = userId;
		this.readStatus = readStatus;
		this.messageId = messageId;
	}

	public TempMessage() {
		super();
	}

	/**
	 * 
	 * @param userId 用户ID
	 * @param readStatus 消息状态
	 * @param messageId 消息ID
	 */
	public TempMessage(Integer userId, Integer messageId, Integer readStatus) {
		super();
		this.userId = userId;
		this.messageId = messageId;
		this.readStatus = readStatus;
	}
	
}
