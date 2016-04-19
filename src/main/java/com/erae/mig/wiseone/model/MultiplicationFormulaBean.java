package com.erae.mig.wiseone.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.erae.mig.wiseone.ModelConvertable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "multiplicationFormula")
@ModelConvertable("com.idstrust.wiseone.formwizard.models.MultiplicationFormula")
public class MultiplicationFormulaBean extends BinaryFormulaBean {

}
