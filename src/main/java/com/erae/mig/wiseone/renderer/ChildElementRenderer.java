package com.erae.mig.wiseone.renderer;

import java.util.HashMap;
import java.util.Map;

import com.erae.mig.wiseone.model.ChildElementBean;
import com.erae.mig.wiseone.model.FontDataBean;
import com.erae.mig.wiseone.model.Position;
import com.erae.mig.wiseone.model.RectangleBean;

public abstract class ChildElementRenderer implements IElementRenderer {

	protected static final String[] ALIGN_VALUES = new String[] {
			"center", "left", "right", "justify"
	};
	
	protected static final String[] VALIGN_VALUES = new String[] {
			"middle", "top", "bottom"
	};
	
	protected static final String[] FONTSTYLE_VALUES = new String[] {
			"normal", "bold"
	};
	
	protected static final String DEFAULT_FONT = "normal 10pt dotum;";
	
	/**
	 * 기존 좌표와의 차이
	 * 
	 */
	protected Position differencePosition;

	/**
	 * 좌표를 style 로 변환합니다.
	 * @param layout
	 * @return
	 */
	protected String convertHTMLStyle(RectangleBean layout) {
		StringBuffer buffer = new StringBuffer();
		buffer
			.append("left:").append(layout.getX()+"px")
			.append(";top:").append(layout.getY()+"px")
			.append(";width:").append(layout.getWidth()+"px")
			.append(";height:").append(layout.getHeight()+"px");
		
		return buffer.toString();
	}
	
	/**
	 * 폰트를 style 로 변환합니다.
	 * @param font
	 * @return
	 */
	protected String convertHTMLStyle(FontDataBean font) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("font: ").append(convertHTMLAttributeValue(font));
		return buffer.toString();
	}
	
	/**
	 * 폰트를 style 로 변환합니다.
	 * @param font
	 * @return
	 */
	protected String convertHTMLAttributeValue(FontDataBean font) {
		StringBuffer buffer = new StringBuffer();
		try{
		if (font != null)
			buffer.append(FONTSTYLE_VALUES[font.getStyle()]).append(" ").append(font.getHeight()).append("pt ").append(font.getName());
		else
			buffer.append(DEFAULT_FONT);
		} catch(Exception e) {
		    buffer.append(DEFAULT_FONT);
		}
		return buffer.toString();
	}
	
	/**
	 * FontData 스타일을 반환합니다.
	 * @param font
	 * @return
	 */
	protected String getStyleFontData(FontDataBean font) {
		StringBuffer buffer = new StringBuffer();
		buffer
			.append(FONTSTYLE_VALUES[font.getStyle()]).append(" ").append(font.getHeight()).append("pt ").append(font.getName());	
		return buffer.toString();
	}

	public String renderElement(ChildElementBean elem, Object data) {
		return "" + elem.getId();
	}
	
	/**
	 * 버퍼에 속성을 추가합니다.
	 */
	protected void appendAttributes(StringBuffer buffer, Map<String, Object> attrMap) {
		for (String key : attrMap.keySet()) {
			buffer.append(" ").append(key).append("=\"").append(attrMap.get(key)).append("\"");
		}
	}
	
	/**
	 * 버퍼에 스타일을 추가합니다.
	 * @param buffer
	 * @param attrMap
	 */
	protected void appendStyleAttribute(StringBuffer buffer, Map<String, Object> attrMap) {
		buffer.append(" style=\"");
		for (String key : attrMap.keySet()) {
			buffer.append(key).append(":").append(attrMap.get(key)).append(";");
		}
		buffer.append("\"");
	}

	public void setDifferencePosition(Position differencePosition) {
		this.differencePosition = differencePosition;
	}
	
	/**
	 * layout 관련 스타일 속성을 map 에 담아 반환합니다.
	 * @param elem
	 * @return
	 */
	public Map<String, Object> getLayoutStyleAttribute(ChildElementBean elem) {
		Map<String, Object> styleAttrMap = new HashMap<String, Object>();
		
		int left = elem.getLayout().getX();
		int top = elem.getLayout().getY();
		if (differencePosition != null) {
			left += differencePosition.getX();
			top += differencePosition.getY();
		}

		styleAttrMap.put("position", "absolute");
		styleAttrMap.put("left", left+"px");
		styleAttrMap.put("top", top+"px");
		styleAttrMap.put("width", elem.getLayout().getWidth()+"px");
		styleAttrMap.put("height", elem.getLayout().getHeight()+"px");
		
		return styleAttrMap;
	}
	
}
