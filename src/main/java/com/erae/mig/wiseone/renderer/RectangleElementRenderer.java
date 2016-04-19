package com.erae.mig.wiseone.renderer;

import java.util.HashMap;
import java.util.Map;

import com.erae.mig.util.IDataMapCode;
import com.erae.mig.wiseone.model.ChildElementBean;
import com.erae.mig.wiseone.model.HTMLElementBean;
import com.erae.mig.wiseone.model.RectangleElementBean;

public class RectangleElementRenderer extends ChildElementRenderer {

    public String renderElement(ChildElementBean elem, Object data) {
        if (elem instanceof RectangleElementBean)
            return renderElement((RectangleElementBean) elem, data);
        return super.renderElement(elem, data);
    }

    public String renderElement(RectangleElementBean elem, Object data) {

        StringBuffer buffer = new StringBuffer("<div");

        Map<String, Object> attrMap = new HashMap<String, Object>();
        String elementId = elem.getId();
        attrMap.put("wo_elem_id", elementId);

        boolean bFlexible = elementId.startsWith("flexible");
        if (bFlexible) {
            attrMap.put("wo_flexible", "true");
            attrMap.put("wo_affected", "");
            attrMap.put("wo_height", elem.getLayout().getHeight() + "px" );
        }
        appendAttributes(buffer, attrMap);

        Map<String, Object> styleAttrMap = new HashMap<String, Object>();

        styleAttrMap.putAll(getLayoutStyleAttribute(elem));

        styleAttrMap.put("background-color", elem.getBackgroundColor().toRGBString());
        styleAttrMap.put("border", elem.getBorderWidth() + "px solid " + elem.getBorderColor().toRGBString());

        if (elem instanceof HTMLElementBean && !bFlexible) {
            styleAttrMap.put("overflow", "auto");
        }

        appendStyleAttribute(buffer, styleAttrMap);
        buffer.append(">");

        if (bFlexible) {
            buffer.append("<div id=\"wo_html_element_inner\">");
        }

        appendTable(elem, buffer, data);

        if (bFlexible) {
            buffer.append("</div>");
        }
        buffer.append("</div>");
        return buffer.toString();
    }

    private void appendTable(RectangleElementBean elem, StringBuffer buffer, Object data) {
        String html = getMappingData(elem, data);
        if (html == null) {
            html = elem.getText().replaceAll("\\p{Space}", "&nbsp;");
        }
        html = html.replaceAll("img&nbsp;src", "img src");
        html = html.replaceAll("a&nbsp;href", "img src");
        if (elem.getAlign() == 3) {

            buffer.append("<div style=\"width:100%; text-align:justify; text-justify:distribute-all-lines; color:").append(
                    elem.getColor().toRGBString()).append(";").append(convertHTMLStyle(elem.getFontData())).append(";\">").append(html).append(
                    "</div>");
        } else {
            buffer.append("<table width=100% height=100% cellspacing=0 cellpadding=0>").append("<tr valign=").append(VALIGN_VALUES[elem.getVAlign()]).append(
                    ">").append("<td class=wiseone_rectangle align=").append(ALIGN_VALUES[elem.getAlign()]).append(" style=\"color=").append(
                    elem.getColor().toRGBString()).append(";").append(convertHTMLStyle(elem.getFontData())).append(";\">").append(html).append(
                    "</td>").append("</tr>").append("</table>");
        }
    }

    /**
     * Mapping Data 를 조사합니다.
     * @param elem
     * @param data
     * @return
     */
    private String getMappingData(RectangleElementBean elem, Object data) {
        String mappingData = null;
        if (data instanceof Map) {
            String mappingKey = elem.getName();
            if (mappingKey != null) {
                Map dataMap = (Map) data;
                Map contentMap = (Map) dataMap.get(IDataMapCode.SANCTION_CONTENT);
                mappingData = (String) contentMap.get(mappingKey);
            }
        }
        return mappingData;
    }

}
