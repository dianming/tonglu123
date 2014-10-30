package com.zht.index;

import org.apache.solr.client.solrj.beans.Field;

public class Index {
	@Field
	private String id;
	
	@Field
	private String wechat_name;
	
	@Field
	private String regdate_time;
	
	@Field
	private String phone;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWechat_name() {
		return wechat_name;
	}

	public void setWechat_name(String wechat_name) {
		this.wechat_name = wechat_name;
	}

	public String getRegdate_time() {
		return regdate_time;
	}

	public void setRegdate_time(String regdate_time) {
		this.regdate_time = regdate_time;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
