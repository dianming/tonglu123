package com.zht.entity;

/**
 * 文本编辑器
 * 2014-06-07
 * @author admin
 *
 */
public class Solution {
	private Integer id;
	private String title;
	private String content;
	private String createDate;
	private int status;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Solution(Integer id, String title, String content,
			String createDate, int status) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.createDate = createDate;
		this.status = status;
	}
	public Solution() {
		super();
	}
}
