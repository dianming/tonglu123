package com.zht.entity;

public class UserInfo {
	private Integer userInfoId;
	private Integer userId;
	private String trueName;

	public Integer getUserInfoId() {
		return userInfoId;
	}

	public void setUserInfoId(Integer userInfoId) {
		this.userInfoId = userInfoId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public UserInfo(Integer userInfoId, Integer userId, String trueName) {
		super();
		this.userInfoId = userInfoId;
		this.userId = userId;
		this.trueName = trueName;
	}

	public UserInfo() {
		super();
	}

}
