package com.erae.mig.wiseone.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.google.gwt.user.client.rpc.IsSerializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rectangle")
public class RectangleBean implements IsSerializable, Comparable<RectangleBean> {
	
	private int x;
	
	private int y;
	
	private int width;
	
	private int height;

	public RectangleBean() {
		
	}

	public RectangleBean(int x, int y, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int compareTo(RectangleBean o) {
		if (o == null)
			return 1;
		return (y > o.y ? 1 : y < o.y ? -1 : x > o.x ? 1 : x < o.x ? -1 : 0);
	}
}
