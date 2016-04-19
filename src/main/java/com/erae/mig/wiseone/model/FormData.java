package com.erae.mig.wiseone.model;


public class FormData extends FormItem {
	
	private int seq;
	
	private int version;
	
	private int categoryIndex;
	
	private String description;
	
	private String content;
	
	private String receipt;
	
	private boolean deleteState;
	
	private String schemaName;

	public FormData() {
		super();
		seq = -1;
		version = -1;
		categoryIndex = -1;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getCategoryIndex() {
		return categoryIndex;
	}

	public void setCategoryIndex(int categoryIndex) {
		this.categoryIndex = categoryIndex;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReceipt() {
		return receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	public boolean isDeleteState() {
		return deleteState;
	}

	public void setDeleteState(boolean deleteState) {
		this.deleteState = deleteState;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}
	
}
