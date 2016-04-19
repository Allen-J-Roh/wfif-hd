package com.erae.mig.wiseone.renderer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.erae.mig.util.IDataMapCode;
import com.erae.mig.wiseone.model.CheckGroupElementBean;
import com.erae.mig.wiseone.model.ChildElementBean;
import com.erae.mig.wiseone.model.FieldElementBean;
import com.erae.mig.wiseone.model.RadioGroupElementBean;

public class CheckGroupElementRenderer extends ChildElementRenderer {
	
	public String renderElement(ChildElementBean elem, Object data) {
		if (elem instanceof CheckGroupElementBean)
			return renderElement((CheckGroupElementBean) elem, data);
		return super.renderElement(elem, data);
	}

	public String renderElement(CheckGroupElementBean elem, Object data) {
		String mappingData = getMappingData(elem, data);
		
		StringBuffer buffer = new StringBuffer("<div");
		
		Map<String, Object> attrMap = new HashMap<String, Object>();
		attrMap.put("wo_elem_id", elem.getId());
		appendAttributes(buffer, attrMap);
				
		Map<String, Object> styleAttrMap = new HashMap<String, Object>();

		styleAttrMap.putAll(getLayoutStyleAttribute(elem));
		
		styleAttrMap.put("background-color", elem.getBackgroundColor().toRGBString());
		styleAttrMap.put("border", elem.getBorderWidth() + "px solid " + elem.getBorderColor().toRGBString());
		appendStyleAttribute(buffer, styleAttrMap);
		buffer.append(">");
		
		renderTable(elem, mappingData, buffer);
		
		buffer.append("</div>");
		return buffer.toString();
	}

	private void renderTable(CheckGroupElementBean elem, String mappingData,
			StringBuffer buffer) {
		buffer.append("<table width=100% height=100% cellspacing=0 cellpadding=0>")
			.append("<tr valign=middle>")
			.append("<td id=").append(elem.getId()).append(" class='wiseone_textfield")
			.append("'>");
		
		renderItems(buffer, elem, mappingData);
		
		buffer
			.append("</td>")
			.append("</tr>")
			.append("</table>");
	}
	
	/**
	 * 아이템들을 렌더링 합니다.
	 * @param buffer
	 * @param elem
	 */
	public void renderItems(StringBuffer buffer, CheckGroupElementBean elem, String mappingData) {
		String text = elem.getText();
		
		List dataList = new ArrayList();
		if (mappingData != null && mappingData.length() > 0) {
			String[] checkedValues = mappingData.split(",");
			for (String value : checkedValues) {
				dataList.add(value);
			}
		}

		if (text != null && text.trim().length() > 0) {
			String[] labels = text.split(",");
			
			buffer.append("<table cellspacing=0 cellpadding=0>");
			
			for (int i=0; i<labels.length; i++) {
				if (i % elem.getColSize() == 0) {
					if (i != 0)
						buffer.append("</tr>");
					buffer.append("<tr>");
				}
				buffer.append("<td>");
				buffer.append("<span style='");
				buffer.append(convertHTMLStyle(elem.getFontData()));
				buffer.append("'><input type=");
				
				if (elem instanceof RadioGroupElementBean)
					buffer.append("radio");
				else
					buffer.append("checkbox");
				
				buffer.append(" disabled=true");
				
				if (dataList.contains("" + i))
					buffer.append(" checked");
				
				buffer.append("/>").append(labels[i]);
				buffer.append("</span>");
				buffer.append("</td>");
			}
			buffer.append("</tr>");
			buffer.append("</table>");
		}
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
