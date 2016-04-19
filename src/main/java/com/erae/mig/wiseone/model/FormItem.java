package com.erae.mig.wiseone.model;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

public class FormItem implements IsSerializable {

	private int index;
	
	private String name;
	
	private Date createDate;
	
	private String createUser;
	
	private Date updateDate;
	
	private String updateUser;
	
	private Double sortOrder;

	public FormItem() {
		index = -1;
		sortOrder = (double) -1;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Double getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Double sortOrder) {
		this.sortOrder = sortOrder;
	}

}
