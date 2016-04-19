package com.erae.mig.wiseone.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.erae.mig.wiseone.ModelConvertable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "currencyFieldElement")
@ModelConvertable("com.idstrust.wiseone.formwizard.models.CurrencyFieldElement")
public class CurrencyFieldElementBean extends FieldElementBean {

	private String currency;

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
}
