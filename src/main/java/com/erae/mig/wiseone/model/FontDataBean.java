package com.erae.mig.wiseone.model;

import com.google.gwt.user.client.rpc.IsSerializable;

public class FontDataBean implements IsSerializable {
	
	private int height;
	
	private String name;
	
	private int style;

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStyle() {
		return style;
	}

	public void setStyle(int style) {
		this.style = style;
	}
}
