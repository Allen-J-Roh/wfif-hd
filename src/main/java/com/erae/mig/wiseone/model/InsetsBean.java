package com.erae.mig.wiseone.model;

import com.google.gwt.user.client.rpc.IsSerializable;

public class InsetsBean implements IsSerializable {

	private int top;
	
	private int left;
	
	private int bottom;
	
	private int right;

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public int getBottom() {
		return bottom;
	}

	public void setBottom(int bottom) {
		this.bottom = bottom;
	}

	public int getRight() {
		return right;
	}

	public void setRight(int right) {
		this.right = right;
	}
	
}
