package com.erae.mig.wiseone.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "gridColumn")
public class GridColumnBean extends BaseModelBean {
	
	private String id;
	
	private String title;
	
	private int width;
	
	private int type;
	
	private boolean readOnly;
	
	private String mappingKey;
	
	private String format;

	public GridColumnBean() {
		mappingKey = "";
		format = "";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

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

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

}
