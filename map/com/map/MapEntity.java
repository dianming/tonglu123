package com.map;

import java.io.Serializable;
import java.util.List;

public class MapEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String status;
	public String message;
	public List<MapInfo> results;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<MapInfo> getResults() {
		return results;
	}
	public void setResults(List<MapInfo> results) {
		this.results = results;
	}
}
