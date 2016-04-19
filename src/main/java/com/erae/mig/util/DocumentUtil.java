package com.erae.mig.util;

import java.io.StringReader;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.erae.mig.common.SqlMapConfig;
import com.erae.mig.wiseone.model.ChildElementBean;
import com.erae.mig.wiseone.model.ColorBean;
import com.erae.mig.wiseone.model.ContentData;
import com.erae.mig.wiseone.model.FileInfo;
import com.erae.mig.wiseone.model.FileList;
import com.erae.mig.wiseone.model.FontDataBean;
import com.erae.mig.wiseone.model.FormData;
import com.erae.mig.wiseone.model.FormInfoBean;
import com.erae.mig.wiseone.model.RectangleBean;
import com.erae.mig.wiseone.model.RectangleElementBean;

public class DocumentUtil {

	private static ThreadLocal<Unmarshaller> unmarshaller;

	static {
		unmarshaller = new ThreadLocal<Unmarshaller>() {
			protected Unmarshaller initialValue() {
				Unmarshaller unmarshaller = null;
				try {
					unmarshaller = JAXBContext.newInstance(FormInfoBean.class).createUnmarshaller();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return unmarshaller;
			}
		};
	}

	private String sanc_id;
	private String form_seq;
	private List<Map<String,String>> refFiles = new ArrayList<Map<String,String>>();
	
	
	public List<Map<String,String>> getRefFiles() {
		return refFiles;
	}

	@SuppressWarnings("unused")
	private Map<String, String> dataMap = new HashMap<String, String>();

	public DocumentUtil() {}
	
	public void setSanc_id(String sanc_id) {
		this.sanc_id = sanc_id;
	}

	public void setForm_seq(String form_seq) {
		this.form_seq = form_seq;
	}

	public Map<String,Object> makeDocumentMap() {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
		    map.put("formInfo", getWorkflowFormInfo());
		    map.put("docInfo", getDocumentInfo());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}

	private FormInfoBean getWorkflowFormInfo() throws SQLException {
		FormData formData = (FormData) SqlMapConfig.getSqlMapInstance().queryForObject("getFormInfo", form_seq);
		StringReader reader = new StringReader(formData.getContent());
		FormInfoBean formInfo = null;
		try {
			formInfo = (FormInfoBean) unmarshaller.get().unmarshal(reader);
			appendBottomMargin(formInfo);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		reader.close();
		return formInfo;
	}

	private void appendBottomMargin(FormInfoBean formInfo) {
		if (formInfo == null)
			return;

		List<ChildElementBean> childList = formInfo.getBodyElement().getChildren();
		ChildElementBean lastElement = childList.get(childList.size() - 1);
		int bottomY = lastElement.getLayout().getY() + lastElement.getLayout().getHeight() + 5;

		RectangleElementBean marginElement = new RectangleElementBean();
		ColorBean color_white = new ColorBean(255, 255, 255);
		marginElement.setBackgroundColor(color_white);
		marginElement.setBorderColor(color_white);
		marginElement.setColor(color_white);
		marginElement.setId("________________margin");
		marginElement.setFontData(new FontDataBean());
		marginElement.setLayout(new RectangleBean(0, bottomY + 9, 1, 1));
		marginElement.setText("");
		marginElement.setName("");

		childList.add(marginElement);
	}

	@SuppressWarnings("unchecked")
	private Map<String, Object> getDocumentInfo() {

		URL url;
		refFiles.clear();
		try {
			List<Map<String, String>> sanclineList = new ArrayList<Map<String, String>>();
			List<Map<String, String>> agreelinelist = new ArrayList<Map<String, String>>();
			List<Map<String, String>> receiptlinelist = new ArrayList<Map<String, String>>();
			List<Map<String, String>> originlinelist = new ArrayList<Map<String, String>>();

			sanclineList = (List<Map<String, String>>) SqlMapConfig.getSqlMapInstance().queryForList("getSancLine",	sanc_id);
			agreelinelist = (List<Map<String, String>>) SqlMapConfig.getSqlMapInstance().queryForList("getAgreeLine", sanc_id);
			receiptlinelist = (List<Map<String, String>>) SqlMapConfig.getSqlMapInstance().queryForList("getReceiptLine", sanc_id);
			originlinelist = (List<Map<String, String>>) SqlMapConfig.getSqlMapInstance().queryForList("getOriginLine",	sanc_id);

			Map<String, String> writerMap = (Map<String, String>) SqlMapConfig.getSqlMapInstance().queryForObject("getWrierInfo", sanc_id);
			Map<String, String> contentMap = (Map<String, String>) SqlMapConfig.getSqlMapInstance().queryForObject("getDocumentInfo", sanc_id);
			Map<String, String> optionMap = (Map<String, String>) SqlMapConfig.getSqlMapInstance().queryForObject("getOptionInfo", sanc_id);
			
			
			String xmlBody = (String) contentMap.get(IDataMapCode.SANC_CONTENTS);
			Map<String,String> dummy = null;
			if ((xmlBody != null) && (!xmlBody.equals(""))) {
				dummy = ContentData.unmarshalling(xmlBody).getContentMap();
			}
			contentMap.putAll(dummy);
			
			List<Map<String,String>> opinionlist = (List<Map<String, String>>) SqlMapConfig.getSqlMapInstance().queryForList("getOpinionList",	sanc_id);
			
			String opinion = "";
			for (Map<String,String> map : opinionlist) {
				opinion += map.get("infos");
			}
			
			contentMap.put("wowf_opinion",opinion);
			
			try {
                if (contentMap.containsKey("refFileinfo")) {
                    String refFileinfo = (String) contentMap.get("refFileinfo") + "\r\n";

                    if (!refFileinfo.trim().endsWith("</fileList>")) {
                        refFileinfo += "<fileList></fileList>";
                    }

                    FileList refFileList = FileList.unmarshalling(refFileinfo);

                    StringBuffer fileLink = new StringBuffer();
                    for (int fIndex = 0; refFileList.getFileInfoList() != null && fIndex < refFileList.getFileInfoList().size(); fIndex++) {
                        if (fIndex == 0) {
                        }
                        FileInfo fileInfo = refFileList.getFileInfoList().get(fIndex);
                        if (fileInfo.getUrl() != null) {
                            fileLink.append("<a href=\"javascript:void(window.open('").append(fileInfo.getUrl().replace(" ", "%20")).append(
                                    "'))\" >").append(fileInfo.getName()).append("</a>");
                            fileLink.append("<br>");
                            
                            url = new URL(fileInfo.getUrl().replace(" ", "%20"));
                            
                            refFiles.add(refSanc_id(url));
                        }
                    }

                    contentMap.put("_ref_attach_link", fileLink.toString());
                }

            } catch (Exception e) {

            }
			
			
			Map<String, Object> lineMap = new HashMap<String, Object>();
			WFLineInfoSetUtil lineUtil = new WFLineInfoSetUtil();
			lineMap.put(IDataMapCode.SANC_LINE_LIST, lineUtil.getLineData(sanclineList));
			lineMap.put(IDataMapCode.AGREE_LINE_LIST, lineUtil.getLineData(agreelinelist));
			lineMap.put(IDataMapCode.RECEIPT_LINE_LIST, lineUtil.getLineData(receiptlinelist));
			lineMap.put(IDataMapCode.ORIGIN_LINE_LIST, lineUtil.getLineData(originlinelist));

			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put(IDataMapCode.SANCTION_CONTENT, contentMap);
			dataMap.put(IDataMapCode.SANCTION_WRITER, writerMap);
			dataMap.put(IDataMapCode.SANCTION_LINE, lineMap);
			dataMap.put(IDataMapCode.SANCTION_OPTION, optionMap);
			return dataMap;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	private Map<String,String> refSanc_id(URL url) {
		String urlQuery = url.getQuery();
		String[] queries = urlQuery.split("&");
		Map<String,String> map = new HashMap<String,String>();
		for (int i=0; i < queries.length; i++) {
			if (queries[i].indexOf("sanc_id") > -1 ) {
				map.put("sourceId", queries[i].replaceAll("sanc_id=", ""));
			}	
		}
		
		return map;
	}
}
