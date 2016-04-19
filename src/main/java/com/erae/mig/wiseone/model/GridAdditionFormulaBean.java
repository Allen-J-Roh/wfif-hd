package com.erae.mig.wiseone.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.erae.mig.wiseone.ModelConvertable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "gridAdditionFormula")
@ModelConvertable("com.idstrust.wiseone.formwizard.models.GridAdditionFormula")
public class GridAdditionFormulaBean extends BinaryFormulaBean implements IGridSupport {

	private String gridId;

	public String getGridId() {
		return gridId;
	}

	public void setGridId(String gridId) {
		this.gridId = gridId;
	}
	
}
