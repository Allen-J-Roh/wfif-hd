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
    
    private int maxRightPosition = 0;
    
    public ElementConverter() { }

    public ElementConverter(IElementRendererFactory factory) {
        setFactory(factory);
    }

    public void setFactory(IElementRendererFactory factory) {
        this.factory = factory;
    }
    
	public int getMaxRightPosition() {
		return maxRightPosition;
	}
	
	/*
	private void calculateMaxPosition(ChildElementBean childElement) {
		int rightPosition = childElement.getLayout().getX() + childElement.getLayout().getWidth();
		if (maxRightPosition < rightPosition) {
			maxRightPosition = rightPosition;
		}

        bottomPosition = childElement.getLayout().getY() + childElement.getLayout().getHeight();
	}
*/
	/*
    public String convertHTMLTag(List<ChildElementBean> childElements, Map<String, String> contentMap) {
        Position position = new Position(0, 0);
        
        StringBuffer buffer = new StringBuffer();
        for (ChildElementBean childElement : childElements) {
            IElementRenderer renderer = factory.getRenderer(childElement);
            if (renderer != null) {
                renderer.setDifferencePosition(position);
                //buffer.append(renderer.renderElement(childElement, contentMap)).append("\n");
                buffer.append(renderer.renderElement(childElement, contentMap));
                calculateMaxPosition(childElement);
            }
        }
        
        //String encode = "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>\n";
        String encode = "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>";
        
        StringBuffer html = new StringBuffer();
        //html.append("<html><head>\n").append(encode)
        html.append("<html><head>").append(encode)
            .append("<style type='text/css'>").append("p { margin: 0px; } td.wiseone_textfield { font: normal 10pt dotum; } td.wiseone_currencyfield { text-align: right; } "
                                                      + "td.wiseone_lineitem { font: normal 10pt dotum; } td.wiseone_gridcell { font: normal 10pt dotum; } span.wiseone_cellitem { overflow:hidden; } "
                                                      + " .wo_html_element { overflow:auto; } .wo_html_element_inner { font: normal 10pt dotum; padding:5px; } "
                                                      + ".wiseone_rectangle p { margin:0px } .wo_html_static_panel { overflow:auto; } </style>")
        //    .append(buffer.toString()).append("\n</body></html>");
            .append(buffer.toString()).append("</body></html>");
        
        
        return html.toString();       
    }
    */
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
                //buffer.append(renderer.renderElement(childElement, dataMap)).append("\n");
                buffer.append(renderer.renderElement(childElement, dataMap));
                
                //calculateMaxPosition(childElement);
            }
        }
        
/*
        if (documentMap.containsKey("originDocument")) {
            Map originDocumentMap = (Map) documentMap.get("originDocument");
            buffer.append(convertHTMLTag(originDocumentMap));
        }
*/        
        //String encode = "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>\n";
     //   String encode = "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>";
        
     //   StringBuffer html = new StringBuffer();
       // html.append("<html><head>\n").append(encode)
      //  html.append("<html><head>").append(encode)
      //      .append("<style type='text/css'>").append("p { margin: 0px; } td.wiseone_textfield { font: normal 10pt dotum; } td.wiseone_currencyfield { text-align: right; } "
      //                                                + "td.wiseone_lineitem { font: normal 10pt dotum; } td.wiseone_gridcell { font: normal 10pt dotum; } span.wiseone_cellitem { overflow:hidden; } "
      //                                                + " .wo_html_element { overflow:auto; } .wo_html_element_inner { font: normal 10pt dotum; padding:5px; } "
      //                                                + ".wiseone_rectangle p { margin:0px } .wo_html_static_panel { overflow:auto; } </style><body>")
      //      .append(buffer.toString()).append("\n</body></html>");
      //      .append(buffer.toString()).append("</body></html>");
        
        
        return buffer.toString();      
    }
    
    /*
    public String convertAttachHTMLTag(List<ChildElementBean> childElements, Map<String, String> contentMap) {
        Position position = new Position(0, 0);
        
        int beforeBottomPosition = bottomPosition + 30;
        
        StringBuffer buffer = new StringBuffer();
        for (ChildElementBean childElement : childElements) {
            IElementRenderer renderer = factory.getRenderer(childElement);
            if (renderer != null) {
                childElement.getLayout().setY(childElement.getLayout().getY() + beforeBottomPosition);
                
                renderer.setDifferencePosition(position);
                //buffer.append(renderer.renderElement(childElement, contentMap)).append("\n");
                buffer.append(renderer.renderElement(childElement, contentMap));
                
                calculateMaxPosition(childElement);
            }
        }
        return buffer.toString();       
    }
    */
}
