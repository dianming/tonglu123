package com.zht.entity;

public class Message {
	private int messageId;
	private String content;
	private int status;
	private int accessAuthority;
	private int userId;

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getAccessAuthority() {
		return accessAuthority;
	}

	public void setAccessAuthority(int accessAuthority) {
		this.accessAuthority = accessAuthority;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Message(int messageId, String content, int status,
			int accessAuthority, int userId) {
		super();
		this.messageId = messageId;
		this.content = content;
		this.status = status;
		this.accessAuthority = accessAuthority;
		this.userId = userId;
	}

	public Message() {
		super();
	}

	/**
	 * 
	 * @param status
	 *            是否冻结，default 0
	 * @param accessAuthority
	 *            开放访问权 default 0
	 */
	public Message(int status, int accessAuthority) {
		super();
		this.status = status;
		this.accessAuthority = accessAuthority;
	}

	/**
	 * 
	 * @param content 内容
	 * @param status 状态
	 * @param userId 用户ID
	 */
	public Message(String content, int status, int userId) {
		super();
		this.content = content;
		this.status = status;
		this.userId = userId;
	}
	
	

}
