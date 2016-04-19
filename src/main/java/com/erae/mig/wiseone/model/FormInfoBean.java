package com.erae.mig.wiseone.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gwt.user.client.rpc.IsSerializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "formInfo")
public class FormInfoBean implements IsSerializable {
	
	private BodyElementBean bodyElement;

	@XmlElements ({
		@XmlElement(name="additionFormula", type=AdditionFormulaBean.class),
		@XmlElement(name="multiAdditionFormula", type=MultiAdditionFormulaBean.class),
		@XmlElement(name="multiplicationFormula", type=MultiplicationFormulaBean.class),
		@XmlElement(name="subtractionFormula", type=SubtractionFormulaBean.class),
		@XmlElement(name="gridAdditionFormula", type=GridAdditionFormulaBean.class),
		@XmlElement(name="gridMultiplicationFormula", type=GridMultiplicationFormulaBean.class),
		@XmlElement(name="gridCellAdditionFormula", type=GridCellAdditionFormulaBean.class)
	})
	private List<FormulaBean> formulaList;

	public FormInfoBean() {
		formulaList = new ArrayList<FormulaBean>();
	}

	public BodyElementBean getBodyElement() {
		return bodyElement;
	}

	public void setBodyElement(BodyElementBean bodyElement) {
		this.bodyElement = bodyElement;
	}
	
	public List<FormulaBean> getFormulaList() {
		return formulaList;
	}

	public void setFormulaList(List<FormulaBean> formulaList) {
		this.formulaList = formulaList;
	}
}
