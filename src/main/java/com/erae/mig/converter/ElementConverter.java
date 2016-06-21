package com.erae.mig.converter;

import java.util.List;
import java.util.Map;

import com.erae.mig.wiseone.model.ChildElementBean;
import com.erae.mig.wiseone.model.FormInfoBean;
import com.erae.mig.wiseone.model.Position;
import com.erae.mig.wiseone.renderer.IElementRenderer;
import com.erae.mig.wiseone.renderer.IElementRendererFactory;

public class ElementConverter {
    
    private IElementRendererFactory factory;
    
    private int bottomPosition = 0;
    
    private int maxRightPosition = 0;
    
    public ElementConverter() { }

    public ElementConverter(IElementRendererFactory factory) {
        setFactory(factory);
    }

    public void setFactory(IElementRendererFactory factory) {
        this.factory = factory;
    }
    
    public int getBottomPosition() {
		return bottomPosition;
	}

	public int getMaxRightPosition() {
		return maxRightPosition;
	}
	
	@SuppressWarnings("rawtypes")
	public String convertHTMLTag(Map documentMap) {
        Position position = new Position(0, 0);
        
        int beforeBottomPosition = 0;
        if (beforeBottomPosition != 0) {
            beforeBottomPosition += 50;
        }
        
        FormInfoBean formInfo = (FormInfoBean) documentMap.get("formInfo");
        Map dataMap = (Map) documentMap.get("docInfo");
        List<ChildElementBean> childElements = formInfo.getBodyElement().getChildren();
        
        StringBuffer buffer = new StringBuffer();
        for (ChildElementBean childElement : childElements) {
            IElementRenderer renderer = factory.getRenderer(childElement);
            if (renderer != null) {
                childElement.getLayout().setY(childElement.getLayout().getY() + beforeBottomPosition);
                
                renderer.setDifferencePosition(position);
                buffer.append(renderer.renderElement(childElement, dataMap)).append("\n");
                
                calculateMaxPosition(childElement);
            }
        }
        return buffer.toString();      
    }
	
	/**
	 * 화면의 양 끝 위치를 계산합니다.
	 * @param chilElement
	 */
	private void calculateMaxPosition(ChildElementBean childElement) {
		int rightPosition = childElement.getLayout().getX() + childElement.getLayout().getWidth();
		if (maxRightPosition < rightPosition) {
			maxRightPosition = rightPosition;
		}

        bottomPosition = childElement.getLayout().getY() + childElement.getLayout().getHeight();
	}
}
