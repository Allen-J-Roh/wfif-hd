package com.erae.mig.wiseone.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.google.gwt.user.client.rpc.IsSerializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "color")
public class ColorBean implements IsSerializable {
	
	private int red;
	
	private int green;
	
	private int blue;

	public ColorBean() {
		
	}

	public ColorBean(int red, int green, int blue) {
		super();
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public int getRed() {
		return red;
	}

	public void setRed(int red) {
		this.red = red;
	}

	public int getGreen() {
		return green;
	}

	public void setGreen(int green) {
		this.green = green;
	}

	public int getBlue() {
		return blue;
	}

	public void setBlue(int blue) {
		this.blue = blue;
	}
	
	public String toRGBString() {
		return new StringBuffer("rgb(").append(red).append(",").append(green)
			.append(",").append(blue).append(")").toString();
	}
}
