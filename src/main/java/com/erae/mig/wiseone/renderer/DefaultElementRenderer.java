package com.erae.mig.wiseone.renderer;

import com.erae.mig.wiseone.model.ChildElementBean;

public class DefaultElementRenderer extends ChildElementRenderer {
	
	public String renderElement(ChildElementBean elem, Object data) {
		StringBuffer buffer = new StringBuffer("<div style=\"position=absolute;");	
		buffer
			.append(convertHTMLStyle(elem.getLayout()))
			.append(";background-color:").append(elem.getBackgroundColor().toRGBString())
			.append(";border:").append(elem.getBorderWidth()).append("px solid ").append(elem.getBorderColor().toRGBString())
			.append("\">")
			.append(elem.getClass().getSimpleName() + " " + elem.getId())
			.append("</div>");
		return buffer.toString();
	}

}
