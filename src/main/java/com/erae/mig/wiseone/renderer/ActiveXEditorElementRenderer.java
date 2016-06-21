package com.erae.mig.wiseone.renderer;

import java.util.HashMap;
import java.util.Map;

import com.erae.mig.util.IDataMapCode;
import com.erae.mig.wiseone.model.ChildElementBean;
import com.erae.mig.wiseone.model.FieldElementBean;
import com.erae.mig.wiseone.model.TextAreaElementBean;

public class ActiveXEditorElementRenderer extends ChildElementRenderer {
	
	public String renderElement(ChildElementBean elem, Object data) {
		if (elem instanceof TextAreaElementBean)
			return renderElement((TextAreaElementBean) elem, data);
		return super.renderElement(elem, data);
	}

	public String renderElement(TextAreaElementBean elem, Object data) {
		String mappingData = getMappingData(elem, data);
		
		StringBuffer buffer = new StringBuffer("<div");
		
		Map<String, Object> attrMap = new HashMap<String, Object>();
		attrMap.put("wo_elem_id", elem.getId());
		
		if (elem.isFlexible()) {
			attrMap.put("wo_flexible", "true");
			attrMap.put("wo_affected", elem.getAffectedElements());
			attrMap.put("wo_height", elem.getLayout().getHeight() +"px");
		} else {
			attrMap.put("class", "wo_html_element");
		}
		appendAttributes(buffer, attrMap);
		
		Map<String, Object> styleAttrMap = new HashMap<String, Object>();

		styleAttrMap.putAll(getLayoutStyleAttribute(elem));
		
		styleAttrMap.put("background-color", elem.getBackgroundColor().toRGBString());
		styleAttrMap.put("border", elem.getBorderWidth() + "px solid " + elem.getBorderColor().toRGBString());
		styleAttrMap.put("overflow", "auto");
		
		appendStyleAttribute(buffer, styleAttrMap);
		buffer.append("><div class=\"wo_ax_html_element_inner\">");
			
		buffer.append(mappingData);
		
		buffer.append("</div></div>");
		return buffer.toString();
	}

	/**
	 * Mapping Data 를 조사합니다.
	 * @param elem
	 * @param data
	 * @return
	 */
	private String getMappingData(FieldElementBean elem, Object data) {
		String mappingData = null;
		if (data instanceof Map) {
			String mappingKey = elem.getMappingKey();
			if (mappingKey == null || mappingKey.length() == 0)
				mappingKey = elem.getId();
			
			Map dataMap = (Map) data;
			Map contentMap = (Map) dataMap.get(IDataMapCode.SANCTION_CONTENT);
			mappingData = (String) contentMap.get(mappingKey);
			if (mappingData == null)
				mappingData = "";
		}
		return mappingData;
	}

}
