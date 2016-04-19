package com.erae.mig.wiseone.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;

import com.erae.mig.wiseone.ModelConvertable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "layerElement")
@ModelConvertable("com.idstrust.wiseone.formwizard.models.LayerElement")
public class LayerElementBean extends ChildElementBean {
	
	@XmlElements ({
		@XmlElement(name="rectangleElement", type=RectangleElementBean.class),
		@XmlElement(name="htmlElement", type=HTMLElementBean.class),
		@XmlElement(name="imageElement", type=ImageElementBean.class),
		@XmlElement(name="textFieldElement", type=TextFieldElementBean.class),
		@XmlElement(name="textAreaElement", type=TextAreaElementBean.class),
		@XmlElement(name="numberFieldElement", type=NumberFieldElementBean.class),
		@XmlElement(name="currencyFieldElement", type=CurrencyFieldElementBean.class),
		@XmlElement(name="dateFieldElement", type=DateFieldElementBean.class),
		@XmlElement(name="formatFieldElement", type=FormatFieldElementBean.class),
		@XmlElement(name="gridElement", type=GridElementBean.class),
		@XmlElement(name="approvalLineElement", type=ApprovalLineElementBean.class),
		@XmlElement(name="checkGroupElement", type=CheckGroupElementBean.class),
		@XmlElement(name="radioGroupElement", type=RadioGroupElementBean.class),
		@XmlElement(name="comboboxElement", type=ComboboxElementBean.class),
		@XmlElement(name="richEditorElement", type=RichEditorElementBean.class),
		@XmlElement(name="activeXEditorElement", type=ActiveXEditorElementBean.class),
		@XmlElement(name="layerElement", type=LayerElementBean.class)
	})
	private List<ChildElementBean> children;
	
	public LayerElementBean() {
		children = new ArrayList<ChildElementBean>();
	}
	
	public List<ChildElementBean> getChildren() {
		return children;
	}

	public void setChildren(List<ChildElementBean> children) {
		this.children = children;
	}
	
	public void addChild(ChildElementBean child) {
		children.add(child);
	}
	
}
