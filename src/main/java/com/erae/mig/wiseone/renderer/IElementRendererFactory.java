package com.erae.mig.wiseone.renderer;

import com.erae.mig.wiseone.model.ChildElementBean;

public interface IElementRendererFactory {
	
	/**
	 * 엘리먼트에 해당하는 렌더러를 반환합니다.
	 * @param childElement
	 * @return
	 */
	public IElementRenderer getRenderer(ChildElementBean childElement);

}
