package com.erae.mig.wiseone.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.erae.mig.wiseone.ModelConvertable;

@XmlRootElement(name = "textFieldElement")
@ModelConvertable("com.idstrust.wiseone.formwizard.models.TextFieldElement")
public class TextFieldElementBean extends FieldElementBean {

	private boolean validation;

	private boolean checkEmpty;

	private int maxLength;
	
	private String validFormat;
	
	private boolean enabledFontStyle;

	private FontDataBean fontData;
	
	private ColorBean color;
	
	private int align;

	public boolean isValidation() {
		return validation;
	}

	public void setValidation(boolean validation) {
		this.validation = validation;
	}

	public boolean isCheckEmpty() {
		return checkEmpty;
	}

	public void setCheckEmpty(boolean checkEmpty) {
		this.checkEmpty = checkEmpty;
	}

	public int getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	public String getValidFormat() {
		return validFormat;
	}

	public void setValidFormat(String validFormat) {
		this.validFormat = validFormat;
	}

	public boolean isEnabledFontStyle() {
		return enabledFontStyle;
	}

	public void setEnabledFontStyle(boolean enabledFontStyle) {
		this.enabledFontStyle = enabledFontStyle;
	}

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

}
