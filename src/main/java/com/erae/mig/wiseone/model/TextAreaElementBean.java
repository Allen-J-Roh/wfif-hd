package com.erae.mig.wiseone.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.erae.mig.wiseone.ModelConvertable;

@XmlRootElement(name = "textAreaElement")
@ModelConvertable("com.idstrust.wiseone.formwizard.models.TextAreaElement")
public class TextAreaElementBean extends FieldElementBean {

	private boolean flexible;

	private String affectedElements;
	
	public boolean isFlexible() {
		return flexible;
	}

	public void setFlexible(boolean flexible) {
		this.flexible = flexible;
	}

	public String getAffectedElements() {
		return affectedElements;
	}

	public void setAffectedElements(String affectedElements) {
		this.affectedElements = affectedElements;
	}
	
}
