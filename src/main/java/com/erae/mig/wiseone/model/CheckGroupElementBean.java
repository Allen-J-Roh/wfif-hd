package com.erae.mig.wiseone.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.erae.mig.wiseone.ModelConvertable;

@XmlRootElement(name = "checkGroupElement")
@ModelConvertable("com.idstrust.wiseone.formwizard.models.CheckGroupElement")
public class CheckGroupElementBean extends FieldElementBean {

	private FontDataBean fontData;
	
	private int colSize;

	public FontDataBean getFontData() {
		return fontData;
	}

	public void setFontData(FontDataBean fontData) {
		this.fontData = fontData;
	}

	public int getColSize() {
		return colSize;
	}

	public void setColSize(int colSize) {
		this.colSize = colSize;
	}

}
