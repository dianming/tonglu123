package com.zht.index;

import org.apache.solr.client.solrj.beans.Field;

public class News {

	@Field
	private String n_title;

	@Field
	private String n_content;

	public String getN_title() {
		return n_title;
	}

	public void setN_title(String n_title) {
		this.n_title = n_title;
	}

	public String getN_content() {
		return n_content;
	}

	public void setN_content(String n_content) {
		this.n_content = n_content;
	}

}
