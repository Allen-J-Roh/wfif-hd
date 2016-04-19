package com.erae.mig.wiseone.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.erae.mig.wiseone.ModelConvertable;

@XmlRootElement(name = "gridElement")
@ModelConvertable("com.idstrust.wiseone.formwizard.models.GridElement")
public class GridElementBean extends ChildElementBean {
	
	private List<GridColumnBean> columns;

	private int rows;
	
	private int headerHeight;
	
	private ColorBean headerBackgroundColor;
	
	private FontDataBean headerFontData;
	
	private ColorBean headerFontColor;
	
	private boolean headerDisable;

	public GridElementBean() {
		columns = new ArrayList<GridColumnBean>();
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public List<GridColumnBean> getColumns() {
		return columns;
	}

	public void setColumns(List<GridColumnBean> columns) {
		this.columns = columns;
	}
	
	public void addColumn(GridColumnBean column) {
		columns.add(column);
	}

	public int getHeaderHeight() {
		return headerHeight;
	}

	public void setHeaderHeight(int headerHeight) {
		this.headerHeight = headerHeight;
	}

	public ColorBean getHeaderBackgroundColor() {
		return headerBackgroundColor;
	}

	public void setHeaderBackgroundColor(ColorBean headerBackgroundColor) {
		this.headerBackgroundColor = headerBackgroundColor;
	}

	public FontDataBean getHeaderFontData() {
		return headerFontData;
	}

	public void setHeaderFontData(FontDataBean headerFontData) {
		this.headerFontData = headerFontData;
	}

	public ColorBean getHeaderFontColor() {
		return headerFontColor;
	}

	public void setHeaderFontColor(ColorBean headerFontColor) {
		this.headerFontColor = headerFontColor;
	}

	public boolean isHeaderDisable() {
		return headerDisable;
	}

	public void setHeaderDisable(boolean headerDisable) {
		this.headerDisable = headerDisable;
	}
}
