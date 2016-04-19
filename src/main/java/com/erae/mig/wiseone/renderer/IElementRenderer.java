package com.erae.mig.wiseone.renderer;


import com.erae.mig.wiseone.model.ChildElementBean;
import com.erae.mig.wiseone.model.Position;

public interface IElementRenderer {

	/**
	 * 엘리먼트의 렌더링 HTML 을 반환합니다.
	 * @param elem
	 * @param data
	 * @return
	 */
	public String renderElement(ChildElementBean elem, Object data);

	/**
	 * 포지션 차이를 설정합니다.
	 * @param differencePosition
	 */
	public void setDifferencePosition(Position differencePosition);
	
}
