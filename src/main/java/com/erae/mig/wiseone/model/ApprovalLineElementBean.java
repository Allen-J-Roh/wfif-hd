package com.erae.mig.wiseone.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.erae.mig.wiseone.ModelConvertable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "approvalLineElement")
@ModelConvertable("com.idstrust.wiseone.formwizard.models.ApprovalLineElement")
public class ApprovalLineElementBean extends ChildElementBean {
	
	private String mappingKey;
	
	private List<ApprovalLineItemBean> itemList;
	
	public String getMappingKey() {
		return mappingKey;
	}

	public void setMappingKey(String mappingKey) {
		this.mappingKey = mappingKey;
	}

	public ApprovalLineElementBean() {
		itemList = new ArrayList<ApprovalLineItemBean>();
	}

	public void addItem(ApprovalLineItemBean item) {
		itemList.add(item);
	}
	
	public List<ApprovalLineItemBean> getItemList() {
		return itemList;
	}

	public void setItemList(List<ApprovalLineItemBean> itemList) {
		this.itemList = itemList;
	}
	
}
