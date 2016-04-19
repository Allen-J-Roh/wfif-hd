package com.erae.mig.wiseone.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.erae.mig.wiseone.ModelConvertable;

@XmlRootElement(name = "richEditorElement")
@ModelConvertable("com.idstrust.wiseone.formwizard.models.RichEditorElement")
public class RichEditorElementBean extends TextAreaElementBean {

}
