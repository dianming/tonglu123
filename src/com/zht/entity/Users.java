package com.zht.entity;

public class Users {
	private Integer userId;
	private String userName;
	private String password;
	private Integer enabled;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public Users(Integer userId, String userName, String password,
			Integer enabled) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.enabled = enabled;
	}

	public Users() {
		super();
	}

}
