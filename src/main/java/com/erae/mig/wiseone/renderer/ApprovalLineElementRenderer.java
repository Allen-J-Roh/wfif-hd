package com.erae.mig.wiseone.renderer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.erae.mig.util.IDataMapCode;
import com.erae.mig.wiseone.model.ApprovalLineElementBean;
import com.erae.mig.wiseone.model.ApprovalLineItemBean;
import com.erae.mig.wiseone.model.ChildElementBean;

public class ApprovalLineElementRenderer extends ChildElementRenderer {

	public String renderElement(ChildElementBean elem, Object data) {
		if (elem instanceof ApprovalLineElementBean)
			return renderElement((ApprovalLineElementBean) elem, data);
		return super.renderElement(elem, data);
	}

	public String renderElement(ApprovalLineElementBean elem, Object data) {
		StringBuffer buffer = new StringBuffer("<div");

		Map<String, Object> attrMap = new HashMap<String, Object>();
		attrMap.put("wo_elem_id", elem.getId());
		appendAttributes(buffer, attrMap);

		Map<String, Object> styleAttrMap = new HashMap<String, Object>();

		styleAttrMap.putAll(getLayoutStyleAttribute(elem));

		styleAttrMap.put("background-color", elem.getBackgroundColor()
				.toRGBString());
		styleAttrMap.put("border", elem.getBorderWidth() + "px solid "
				+ elem.getBorderColor().toRGBString());
		appendStyleAttribute(buffer, styleAttrMap);

		buffer.append(">");

		renderTable(buffer, elem, data);

		buffer.append("</div>");
		return buffer.toString();
	}

	protected void renderTable(StringBuffer buffer,
			ApprovalLineElementBean elem, Object data) {
		List<Map> mappingData = getMappingData(elem, data);
		if (mappingData == null)
			return;

		buffer
				.append("<table width=100% height=100% cellspacing=0 cellpadding=0><tr valign=middle>");

		// 결재 라인을 확인
		int lineCount = mappingData.size();
		for (int i = 0; i < lineCount; i++) {
			Map lineData = mappingData.get(i);

			buffer.append("<td>");
			renderLine(buffer, elem, lineData, lineCount, i == 0);
			buffer.append("</td>");
		}

		buffer.append("</tr></table>");
	}

	protected void renderLine(StringBuffer buffer,
			ApprovalLineElementBean elem, Map lineData, int lineCount,
			boolean first) {
		List<ApprovalLineItemBean> lineItems = elem.getItemList();

		buffer
				.append("<table width=100% height=100% cellspacing=0 cellpadding=0>");

		for (int i = 0; i < lineItems.size(); i++) {
			ApprovalLineItemBean lineItem = lineItems.get(i);
			buffer.append("<tr valign=middle>").append(
					"<td class=wiseone_lineitem");

			Map<String, Object> attrMap = new HashMap<String, Object>();
			attrMap.put("width", getLineWidth(elem, lineItems, lineCount, i)+"px");
			attrMap.put("text-align", "center");

			if (i != 0) {
				attrMap.put("border-top", "1px solid black");
			}
			if (!first) {
				attrMap.put("border-left", "1px solid black");
			}
			if (lineItem.getHeight() > -1) {
				attrMap.put("height", lineItem.getHeight()+"px");
			}

			appendStyleAttribute(buffer, attrMap);

			String lineRowData = (String) lineData.get(lineItem.getType());
			lineRowData = lineRowData == null ? "&nbsp" : lineRowData;

			buffer.append(">");
			if ("1".equals("" + lineData.get("wowf_sanc_auth"))
					&& "0".equals(lineData.get("wowf_sancyn"))
					&& "wowf_line_sign".equals(lineItem.getType())) {
				if (!lineData.containsKey("mobile_mode")){
					buffer.append("<button onclick=\"onWorkflowSign('").append(
							lineData.get("wowf_sanc_type")).append(
							"')\">Sign</button>");
				} else {
					lineData.put("mobile_process", "true");
				}
			} else {
				buffer.append(lineRowData);
			}

			buffer.append("</td>").append("</tr>");
		}

		buffer.append("</table>");
	}

	private int getLineWidth(ApprovalLineElementBean elem,
			List<ApprovalLineItemBean> lineItems, int lineCount, int index) {
		int totalWidth = elem.getLayout().getWidth()
				- (elem.getBorderWidth() * 2);
		int eachWidth = totalWidth / lineCount;
		if (index == (lineCount - 1)) {
			return totalWidth - ((lineCount - 1) * eachWidth);
		}
		return eachWidth;
	}

	/**
	 * Mapping Data 를 조사합니다.
	 * 
	 * @param elem
	 * @param data
	 * @return
	 */
	private List<Map> getMappingData(ApprovalLineElementBean elem, Object data) {
		List<Map> mappingData = null;
		if (data instanceof Map) {
			String mappingKey = elem.getMappingKey();
			if (mappingKey == null || mappingKey.length() == 0)
				mappingKey = elem.getId();

			Map dataMap = (Map) data;
			Map approvalMap = (Map) dataMap.get(IDataMapCode.SANCTION_LINE);
			if (approvalMap != null) {
			    mappingData = (List<Map>) approvalMap.get(mappingKey);
			} 
		}
		return mappingData;
	}

}
