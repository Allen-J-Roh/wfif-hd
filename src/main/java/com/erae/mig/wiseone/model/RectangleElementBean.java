package com.erae.mig.wiseone.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rectangleElement")
//@ModelConvertable("com.idstrust.wiseone.formwizard.models.RectangleElement")
public class RectangleElementBean extends ChildElementBean {

	private FontDataBean fontData;
	
	private ColorBean color;
	
	private int align;
	
	private int vAlign;

	public FontDataBean getFontData() {
		return fontData;
	}

	public void setFontData(FontDataBean fontData) {
		this.fontData = fontData;
	}

	public ColorBean getColor() {
		return color;
	}

	public void setColor(ColorBean color) {
		this.color = color;
	}

	public int getAlign() {
		return align;
	}

	public void setAlign(int align) {
		this.align = align;
	}

	public int getVAlign() {
		return vAlign;
	}

	public void setVAlign(int align) {
		vAlign = align;
	}
}
