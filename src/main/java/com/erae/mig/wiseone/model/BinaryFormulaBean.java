package com.erae.mig.wiseone.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "binaryFormula")
public class BinaryFormulaBean extends FormulaBean {

	private String termField2;

	public String getTermField2() {
		return termField2;
	}

	public void setTermField2(String termField2) {
		this.termField2 = termField2;
	}

}
