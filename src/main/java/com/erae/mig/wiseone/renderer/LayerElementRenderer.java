package com.erae.mig.wiseone.renderer;

import java.util.HashMap;
import java.util.Map;

import com.erae.mig.wiseone.model.ChildElementBean;
import com.erae.mig.wiseone.model.LayerElementBean;

public class LayerElementRenderer extends ChildElementRenderer {
    
    private IElementRendererFactory elementRendererFactory;
	
	public void setElementRendererFactory(IElementRendererFactory elementRendererFactory) {
		this.elementRendererFactory = elementRendererFactory;
	}

	public String renderElement(ChildElementBean elem, Object data) {
		if (elem instanceof LayerElementBean)
			return renderElement((LayerElementBean) elem, data);
		return super.renderElement(elem, data);
	}

	public String renderElement(LayerElementBean elem, Object data) {

		
		StringBuffer buffer = new StringBuffer("<div");
		
		Map<String, Object> attrMap = new HashMap<String, Object>();
		attrMap.put("wo_elem_id", elem.getId());
		appendAttributes(buffer, attrMap);
				
		Map<String, Object> styleAttrMap = new HashMap<String, Object>();

		styleAttrMap.putAll(getLayoutStyleAttribute(elem));
		
		styleAttrMap.put("background-color", elem.getBackgroundColor().toRGBString());
		styleAttrMap.put("border", elem.getBorderWidth() + "px solid " + elem.getBorderColor().toRGBString());
		
		// 레이어 내부에서 벗어나는 부분은 숨겨지도록 설정
		styleAttrMap.put("overflow", "hidden");
		
		appendStyleAttribute(buffer, styleAttrMap);
		buffer.append(">");
		
		appendChildElements(elem, buffer, data);
		
		buffer.append("</div>");
		return buffer.toString();
	}

	private void appendChildElements(LayerElementBean elem, StringBuffer buffer, Object data) {
		if (elementRendererFactory == null) {
			System.err.println("elementRendererFactory 이 정의되어 있지 않습니다.");
			return;
		}
		
        for (ChildElementBean childElement : elem.getChildren()) {
            IElementRenderer renderer = elementRendererFactory.getRenderer(childElement);
            if (renderer != null) {                
              //  buffer.append(renderer.renderElement(childElement, data)).append("\n");
            	  buffer.append(renderer.renderElement(childElement, data));
            }
        }
		
	}


}
