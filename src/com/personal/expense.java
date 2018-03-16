package com.personal;

import java.io.Serializable;
import java.util.Date;
////This class represents a category
@SuppressWarnings("serial")
public class expense implements Serializable {
	
	private long expenseID = System.currentTimeMillis();
	private long categaryID;
	private String catName;
	private float amount;
	private Date date;
	private String description;
	
//Constructor to accept expense details while creating object
	public expense(long categaryID, float amount, Date date,String description,String catName) {
		super();
		this.categaryID = categaryID;
		this.catName = catName;
		this.amount = amount;
		this.date = date;
		this.description = description;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getExpenseID() {
		return expenseID;
	}

	public void setExpenseID(long expenseID) {
		this.expenseID = expenseID;
	}

	public long getCategaryID() {
		return categaryID;
	}

	public void setCategaryID(long categaryID) {
		this.categaryID = categaryID;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

	

}
