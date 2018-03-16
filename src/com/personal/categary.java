package com.personal;

import java.io.Serializable;
//This class represents a category
@SuppressWarnings("serial")
public class categary implements Serializable {
	
//generate unique ID Using time(MilliSeconds)
	private long categaryID = System.currentTimeMillis();
	private String name;
	
	public categary(int categaryID) {
		super();
		this.categaryID = categaryID;
	}
	public categary(String name) {
		super();
		this.name = name;
	}
	public long getCategaryID() {
		return categaryID;
	}
	public void setCategaryID(int categaryID) {
		this.categaryID = categaryID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
