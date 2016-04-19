package com.erae.mig.wiseone.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "formula")
public class FormulaBean extends BaseModelBean {

	private int priority;

	private String termField;

	private String resultField;

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getTermField() {
		return termField;
	}

	public void setTermField(String termField) {
		this.termField = termField;
	}

	public String getResultField() {
		return resultField;
	}

	public void setResultField(String resultField) {
		this.resultField = resultField;
	}

}
