package com.map;

import java.io.Serializable;
import java.util.Map;

public class MapInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String name;
	public Map<String,String> location;
	public String address;
	public String street_id;
	public String uid;

	public Map<String,String> getLocation() {
		return location;
	}

	public void setLocation(Map<String,String> location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStreet_id() {
		return street_id;
	}

	public void setStreet_id(String street_id) {
		this.street_id = street_id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
}
