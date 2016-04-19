package com.erae.mig.util;

import java.util.HashMap;
import java.util.Map;

import com.erae.mig.wiseone.model.ActiveXEditorElementBean;
import com.erae.mig.wiseone.model.ApprovalLineElementBean;
import com.erae.mig.wiseone.model.CheckGroupElementBean;
import com.erae.mig.wiseone.model.ChildElementBean;
import com.erae.mig.wiseone.model.ComboboxElementBean;
import com.erae.mig.wiseone.model.CurrencyFieldElementBean;
import com.erae.mig.wiseone.model.DateFieldElementBean;
import com.erae.mig.wiseone.model.FormatFieldElementBean;
import com.erae.mig.wiseone.model.GridElementBean;
import com.erae.mig.wiseone.model.ImageElementBean;
import com.erae.mig.wiseone.model.LayerElementBean;
import com.erae.mig.wiseone.model.NumberFieldElementBean;
import com.erae.mig.wiseone.model.RectangleElementBean;
import com.erae.mig.wiseone.model.RichEditorElementBean;
import com.erae.mig.wiseone.model.TextAreaElementBean;
import com.erae.mig.wiseone.model.TextFieldElementBean;
import com.erae.mig.wiseone.renderer.ActiveXEditorElementRenderer;
import com.erae.mig.wiseone.renderer.ApprovalLineElementRenderer;
import com.erae.mig.wiseone.renderer.CheckGroupElementRenderer;
import com.erae.mig.wiseone.renderer.DefaultElementRenderer;
import com.erae.mig.wiseone.renderer.FieldElementRenderer;
import com.erae.mig.wiseone.renderer.GridElementRenderer;
import com.erae.mig.wiseone.renderer.HTMLElementRenderer;
import com.erae.mig.wiseone.renderer.IElementRenderer;
import com.erae.mig.wiseone.renderer.IElementRendererFactory;
import com.erae.mig.wiseone.renderer.ImageElementRenderer;
import com.erae.mig.wiseone.renderer.LayerElementRenderer;
import com.erae.mig.wiseone.renderer.RectangleElementRenderer;

public class FastViewElementRendererFactory implements IElementRendererFactory {
	
	@SuppressWarnings("rawtypes")
	Map<Class, IElementRenderer> renderMap;

	@SuppressWarnings("rawtypes")
	public FastViewElementRendererFactory() {
		renderMap = new HashMap<Class, IElementRenderer>();
	}

	public IElementRenderer getRenderer(ChildElementBean childElement) {
		IElementRenderer renderer = null;
		if (childElement instanceof RectangleElementBean) {
			renderer = getRenderer(RectangleElementRenderer.class);
		} else if (childElement instanceof TextFieldElementBean ||
				childElement instanceof DateFieldElementBean ||
				childElement instanceof NumberFieldElementBean ||
				childElement instanceof FormatFieldElementBean ||
				childElement instanceof CurrencyFieldElementBean || 
				childElement instanceof ComboboxElementBean) {
			renderer = getRenderer(FieldElementRenderer.class);
        } else if (childElement instanceof ActiveXEditorElementBean) { 
            renderer = getRenderer(ActiveXEditorElementRenderer.class);
        }  else if (childElement instanceof TextAreaElementBean ||
                          childElement instanceof RichEditorElementBean) {
            renderer = getRenderer(HTMLElementRenderer.class);
		} else if (childElement instanceof ApprovalLineElementBean) {
			renderer = getRenderer(ApprovalLineElementRenderer.class);
		} else if (childElement instanceof GridElementBean) {
			renderer = getRenderer(GridElementRenderer.class);
		} else if (childElement instanceof CheckGroupElementBean) {
			renderer = getRenderer(CheckGroupElementRenderer.class);
		} else if (childElement instanceof ImageElementBean) {
			renderer = getRenderer(ImageElementRenderer.class);
		} else if (childElement instanceof LayerElementBean) {
			renderer = getRenderer(LayerElementRenderer.class);
			((LayerElementRenderer) renderer).setElementRendererFactory(this);
		} else {
			renderer = getRenderer(DefaultElementRenderer.class);
		}
		return renderer;
	}

	private IElementRenderer getRenderer(Class clazz) {
		IElementRenderer renderer = null;
		if (renderMap.containsKey(clazz)) {
			renderer = renderMap.get(clazz);
		} else {
			try {
				renderer = (IElementRenderer) clazz.newInstance();
				renderMap.put(clazz, renderer);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return renderer;
	}
	
}
