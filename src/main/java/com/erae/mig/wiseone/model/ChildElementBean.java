package com.erae.mig.wiseone.model;


public class ChildElementBean extends BaseModelBean implements Comparable<ChildElementBean> {

	private RectangleBean layout;

	private String id;
	
	private ColorBean backgroundColor;
	
	private ColorBean borderColor;
	
	private int borderWidth;
	
	private String text;
	
	private InsetsBean padding;

	public RectangleBean getLayout() {
		return layout;
	}

	public void setLayout(RectangleBean layout) {
		this.layout = layout;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ColorBean getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(ColorBean backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public ColorBean getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(ColorBean borderColor) {
		this.borderColor = borderColor;
	}

	public int getBorderWidth() {
		return borderWidth;
	}

	public void setBorderWidth(int borderWidth) {
		this.borderWidth = borderWidth;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public InsetsBean getPadding() {
		return padding;
	}

	public void setPadding(InsetsBean padding) {
		this.padding = padding;
	}

	public int compareTo(ChildElementBean o) {
		if (o == null)
			return 1;
		return layout.compareTo(o.layout);
	}
}
