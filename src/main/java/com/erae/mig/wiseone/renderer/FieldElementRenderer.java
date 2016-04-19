package com.erae.mig.wiseone.renderer;

import java.util.HashMap;
import java.util.Map;

import com.erae.mig.common.Log;
import com.erae.mig.util.IDataMapCode;
import com.erae.mig.wiseone.model.ChildElementBean;
import com.erae.mig.wiseone.model.CurrencyFieldElementBean;
import com.erae.mig.wiseone.model.FieldElementBean;
import com.erae.mig.wiseone.model.TextFieldElementBean;

public class FieldElementRenderer extends ChildElementRenderer {
	public String renderElement(ChildElementBean elem, Object data) {
		if ((elem instanceof FieldElementBean))
			return renderElement((FieldElementBean) elem, data);
		return super.renderElement(elem, data);
	}

	public String renderElement(FieldElementBean elem, Object data) {
		String mappingData = getMappingData(elem, data);

		StringBuffer buffer = new StringBuffer("<div");

		Map attrMap = new HashMap();
		attrMap.put("wo_elem_id", elem.getId());
		appendAttributes(buffer, attrMap);

		Map styleAttrMap = new HashMap();

		styleAttrMap.putAll(getLayoutStyleAttribute(elem));

		styleAttrMap.put("background-color", elem.getBackgroundColor().toRGBString());
		styleAttrMap.put("border", elem.getBorderWidth() + "px solid " + elem.getBorderColor().toRGBString());
		appendStyleAttribute(buffer, styleAttrMap);
		buffer.append(">");

		appendTable(elem, mappingData, buffer);

		buffer.append("</div>");
		return buffer.toString();
	}

	private void appendTable(FieldElementBean elem, String mappingData, StringBuffer buffer) {
		String styleName = "wiseone_textfield";
		if ((elem instanceof CurrencyFieldElementBean)) {
			styleName = styleName + " wiseone_currencyfield";
		}
		Map attrMap = new HashMap();
		attrMap.put("id", "elem.getId()");
		attrMap.put("height", "100%");
		attrMap.put("class", styleName);

		Map styleAttrMap = new HashMap();
		if ((elem instanceof TextFieldElementBean)) {
			TextFieldElementBean textFieldElement = (TextFieldElementBean) elem;
			if (textFieldElement.isEnabledFontStyle()) {
				styleAttrMap.put("color", textFieldElement.getColor().toRGBString());
				styleAttrMap.put("text-align", ALIGN_VALUES[textFieldElement.getAlign()]);
				styleAttrMap.put("font", convertHTMLAttributeValue(textFieldElement.getFontData()));
			}
		}
		if ((elem instanceof CurrencyFieldElementBean)) {
			styleAttrMap.put("text-align", "right");
		}

		buffer.append("<table width=100% height=100%>").append("<tr valign=middle>").append("<td");
		appendAttributes(buffer, attrMap);
		appendStyleAttribute(buffer, styleAttrMap);
		buffer.append("'>").append(mappingData).append("</td>").append("</tr>").append("</table>");
	}

	private String getMappingData(FieldElementBean elem, Object data) {
		String mappingData = null;
		if ((data instanceof Map)) {
			String mappingKey = elem.getMappingKey();
			
			//String mappingKey = elem.getName();
			if ((mappingKey == null) || (mappingKey.length() == 0)) {
				mappingKey = elem.getId();
			}

			Map dataMap = (Map) data;
            Map contentMap = (Map) dataMap.get(IDataMapCode.SANCTION_CONTENT);
            
			mappingData = (String) contentMap.get(mappingKey);
			if (mappingData == null) {
				mappingData = "";
			}
		}
		return mappingData;
	}
}