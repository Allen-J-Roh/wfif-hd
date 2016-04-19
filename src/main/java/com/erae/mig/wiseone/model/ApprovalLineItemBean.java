package com.erae.mig.wiseone.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.erae.mig.wiseone.ModelConvertable;

@XmlRootElement(name = "lineItem")
@ModelConvertable("com.idstrust.wiseone.formwizard.models.ApprovalLineElement")
public class ApprovalLineItemBean extends BaseModelBean {
	
	private String id;
	
	private int height;
	
	private String type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
