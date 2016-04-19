package com.erae.mig.wiseone.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.erae.mig.wiseone.ModelConvertable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "htmlElement")
@ModelConvertable("com.idstrust.wiseone.formwizard.models.HTMLElement")
public class HTMLElementBean extends RectangleElementBean {
	
	private String htmlTag;

	public String getHtmlTag() {
		return htmlTag;
	}

	public void setHtmlTag(String htmlTag) {
		this.htmlTag = htmlTag;
	}

}
