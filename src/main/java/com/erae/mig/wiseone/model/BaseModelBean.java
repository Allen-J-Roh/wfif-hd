package com.erae.mig.wiseone.model;

import com.google.gwt.user.client.rpc.IsSerializable;

public class BaseModelBean implements IsSerializable {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
