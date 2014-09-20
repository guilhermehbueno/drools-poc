package com.walmart.search.rules.model;

import java.io.Serializable;


public class EntityBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String id;

	
	public EntityBean(String id) {
		super();
		this.id = id;
	}
	
	public EntityBean() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
