package com.erae.mig.wiseone.renderer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.erae.mig.util.IDataMapCode;
import com.erae.mig.wiseone.model.ChildElementBean;
import com.erae.mig.wiseone.model.ElementCode;
import com.erae.mig.wiseone.model.GridColumnBean;
import com.erae.mig.wiseone.model.GridElementBean;
import com.erae.mig.wiseone.model.RectangleBean;

public class GridElementRenderer extends ChildElementRenderer {

	private static final int DEFAULT_GRID_CELL_HEIGHT = 23;

	public String renderElement(ChildElementBean elem, Object data) {
		if (elem instanceof GridElementBean)
			return renderElement((GridElementBean) elem, data);
		return super.renderElement(elem, data);
	}

	public String renderElement(GridElementBean elem, Object data) {

		int diffHeight = 0;

		if (data instanceof Map) {
			Map dataMap = (Map) data;
			Map contentMap = (Map) dataMap.get(IDataMapCode.SANCTION_CONTENT);
			String fluid_grid_id = (String) contentMap.get("wowf_fluid_grid_id");
			String fluid_grid_row = (String) contentMap.get("wowf_fluid_grid_row");

			// 기본 유동 그리드의 경우 화면 재정의
			if (fluid_grid_id != null && fluid_grid_id.equals(elem.getId())) {
				try {
					int fluidRows = Integer.parseInt(fluid_grid_row);
					int originRows = elem.getRows();

					diffHeight = refineGridPositionAndRow(elem, fluidRows, originRows);
				} catch (NumberFormatException e) {
					// ...
				}
			}

			// 다중 유동 그리드 처리
			String fluidGridSizeId = elem.getId() + "_size";
			if (contentMap.containsKey(fluidGridSizeId)) {
				String strFluidGridSize = (String) contentMap.get(fluidGridSizeId);
				if (strFluidGridSize != null && strFluidGridSize.matches("[0-9]+")) {
					int nFluidGridSize = Integer.parseInt(strFluidGridSize);
					int nOriginSize = elem.getRows();

					// 여기서는 diffHeight 가 위의 wowf_fluid_grid_id 에 의해 변경될 수 있으니
					// 화면이 깨진다면 확인해 볼 것. Vicki Yi.
					diffHeight = refineGridPositionAndRow(elem, nFluidGridSize, nOriginSize);
				}
			}

		}

		StringBuffer buffer = new StringBuffer("<div");

		Map<String, Object> attrMap = new HashMap<String, Object>();
		attrMap.put("wo_elem_id", elem.getId());
		appendAttributes(buffer, attrMap);

		Map<String, Object> styleAttrMap = new HashMap<String, Object>();

		styleAttrMap.putAll(getLayoutStyleAttribute(elem));

		styleAttrMap.put("background-color", elem.getBackgroundColor().toRGBString());
		styleAttrMap.put("border-top", elem.getBorderWidth() + "px solid " + elem.getBorderColor().toRGBString());
		appendStyleAttribute(buffer, styleAttrMap);

		buffer.append(">");

		renderTable(buffer, elem, data);

		buffer.append("</div>");

		if (diffHeight != 0) {
			differencePosition.addY(-diffHeight);
		}

		return buffer.toString();
	}

	/**
	 * 그리드의 포지션과 행의 수를 재조정 합니다.
	 * 
	 * @param elem
	 * @param fluidRows
	 * @param originRows
	 * @return
	 */
	private int refineGridPositionAndRow(GridElementBean elem, int fluidRows, int originRows) {
		int diffHeight = (originRows - fluidRows) * DEFAULT_GRID_CELL_HEIGHT;

		RectangleBean layout = elem.getLayout();
		layout.setHeight(layout.getHeight() - diffHeight);

		elem.setRows(fluidRows);
		return diffHeight;
	}

	protected void renderTable(StringBuffer buffer, GridElementBean elem, Object data) {
		List<GridColumnBean> gridColumns = elem.getColumns();

		buffer.append("<table width=100% cellspacing=0 cellpadding=0>");

		if (!elem.isHeaderDisable()) {
			// 그리드 헤더
			buffer.append("<tr valign=middle>");
			for (int i = 0; i < gridColumns.size(); i++) {
				GridColumnBean gridColumn = gridColumns.get(i);
	
				Map<String, Object> attrMap = new HashMap<String, Object>();
				attrMap.put("width", (gridColumn.getWidth() - 1) + "px") ;
				attrMap.put("height", (elem.getHeaderHeight() - 1) + "px");
				attrMap.put("color", elem.getHeaderFontColor().toRGBString());
				attrMap.put("background-color", elem.getHeaderBackgroundColor().toRGBString());
				attrMap.put("font", getStyleFontData(elem.getHeaderFontData()));
	
				attrMap.put("border-left", "1px solid black");
				if (i == gridColumns.size() - 1) {
					attrMap.put("border-right", "1px solid black");
				}
	
				buffer.append("<td align=center");
				appendStyleAttribute(buffer, attrMap);
				buffer.append(">").append(gridColumn.getTitle()).append("</td>");
			}
	
			buffer.append("</tr>");
		}

		// 그리드 셀
		for (int i = 0; i < elem.getRows(); i++) {
			buffer.append("<tr valign=middle>");
			for (int j = 0; j < gridColumns.size(); j++) {
				GridColumnBean gridColumn = gridColumns.get(j);

				Map<String, Object> attrMap = new HashMap<String, Object>();
				attrMap.put("width", gridColumn.getWidth() - 1);
				attrMap.put("background-color", elem.getBackgroundColor().toRGBString());
				if (elem.isHeaderDisable() && i == 0) {
					
				} else {
					attrMap.put("border-top", "1px solid black");					
				}

				attrMap.put("border-left", "1px solid black");
				if (j == gridColumns.size() - 1) {
					attrMap.put("border-right", "1px solid black");
				}

				// 선의 크기로 인해 사이즈가 맞지 않는 현상이 발생하여 높이를 조절
				if (i == elem.getRows() - 1) {
					attrMap.put("height", DEFAULT_GRID_CELL_HEIGHT + 1);
					attrMap.put("border-bottom", "1px solid black");
				} else {
					attrMap.put("height", DEFAULT_GRID_CELL_HEIGHT + "px" );
				}

				if (ElementCode.FIELD_CURRENCY == gridColumn.getType()) {
					attrMap.put("text-align", "right");
				}

				Map<String, Object> styleAttrMap = new HashMap<String, Object>();
				styleAttrMap.put("padding", "0 3 0 3");
				styleAttrMap.put("width", (gridColumn.getWidth() - 3)+ "px");
				styleAttrMap.put("overflow", "hidden");
				styleAttrMap.put("white-space", "nowrap	");

				String mappingKey = gridColumn.getMappingKey();
				if (mappingKey == null || mappingKey.length() == 0)
					mappingKey = gridColumn.getId();
				mappingKey += "_" + i;
				String mappingData = getMappingData(mappingKey, data);

				buffer.append("<td class=wiseone_gridcell");
				appendStyleAttribute(buffer, attrMap);
				buffer.append("><span class=wiseone_cellitem");
				appendStyleAttribute(buffer, styleAttrMap);
				buffer.append(">").append(mappingData).append("</span></td>");
			}
			buffer.append("</tr>");
		}

		buffer.append("</table>");
	}

	/**
	 * Mapping Data 를 조사합니다.
	 * 
	 * @param elem
	 * @param data
	 * @return
	 */
	private String getMappingData(String mappingKey, Object data) {
		String mappingData = null;
		if (data instanceof Map) {

			Map dataMap = (Map) data;
			//Map contentMap = (Map) dataMap.get(IDataMapCode.SANCTION_CONTENT);
			mappingData = (String) dataMap.get(mappingKey);
			if (mappingData == null || mappingData.length() == 0)
				mappingData = "&nbsp;";
		}
		return mappingData;
	}

}
