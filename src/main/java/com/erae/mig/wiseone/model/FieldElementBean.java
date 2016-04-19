package com.erae.mig.wiseone.model;



public class FieldElementBean extends ChildElementBean {
	
	private boolean readOnly;
	
	private String mappingKey;

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public String getMappingKey() {
		return mappingKey;
	}

	public void setMappingKey(String mappingKey) {
		this.mappingKey = mappingKey;
	}
	
}
