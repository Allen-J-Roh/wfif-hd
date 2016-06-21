package com.erae.mig.approval;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.erae.mig.common.Log;
import com.erae.mig.common.MigConfig;
import com.erae.mig.common.SqlMapConfig;
import com.erae.mig.converter.ElementConverter;
import com.erae.mig.util.CommonUtil;
import com.erae.mig.util.DocumentUtil;
import com.erae.mig.util.FastViewElementRendererFactory;
import com.erae.mig.util.JSONGernerator;

public class ApprovalBuilder {

	public void migrationDocuments(Map<String, String> params) {
		try {
			MigConfig migconfig = MigConfig.getInstance();
			
			List<Map<String,String>> sancList = getSancList(params, migconfig);
			int i = 0;
			for (Map<String,String> map : sancList) {
				if (CommonUtil.equals(map.get("isReceipt"),"false")) {
					writeMigDoc(map.get("str")+"", migconfig, "send" + File.separator + map.get("woYear") + File.separator + map.get("woMonth"), map.get("deptName") + ".cmds");
				} else {
					writeMigDoc(map.get("str")+"", migconfig, "recv" + File.separator + map.get("woYear") + File.separator + map.get("woMonth"), map.get("deptName") + ".cmds");
				}
				i++;
				Log.log(i + " / " + sancList.size() +" migration job completed. " + System.currentTimeMillis(), Log.INFO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private List<Map<String,String>> getSancList(Map<String, String> params, MigConfig migconfig) {
		try {
			List<Map<String,String>> sancList = new ArrayList<Map<String,String>>();
			String sanc_id;
			String form_seq;

			ElementConverter converter = new ElementConverter();
			converter.setFactory(new FastViewElementRendererFactory());

			List<Map<String, Object>> list = (List<Map<String, Object>>) SqlMapConfig.getSqlMapInstance().queryForList("getSancList", params);

			DocumentUtil util = new DocumentUtil();

			Map<String, Object> param = new HashMap<String, Object>();
			
//			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			Date date = new Date();
			
			
			Log.log("[ list.size() : " + list.size() + "  ]", Log.INFO);
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = (Map<String, Object>) list.get(i);
				Map<String,String> data = new HashMap<String,String>();
				sanc_id = map.get("sourceId") + "";
				form_seq = map.get("form_seq") + "";

				util.setForm_seq(form_seq);
				util.setSanc_id(sanc_id);

				if (!CommonUtil.isNull(sanc_id)) {
					data.put("woYear",map.get("woYear")+"");
					data.put("woMonth",map.get("woMonth")+"");
					data.put("isReceipt",map.get("isReceipt")+"");
					data.put("sourceId",map.get("sourceId")+"");
//System.out.println((0 + i) + "/ " + list.size() +" phase 1 : " + System.currentTimeMillis());
					if (!CommonUtil.isNull(map.get("createdAt"))) {
					    Date createAt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(map.get("createdAt")+"");
				        map.put("createdAt", createAt.getTime());
					}

//System.out.println( (0 + i) + "/ " + list.size() +" phase 2 : " + System.currentTimeMillis());
				    if (!CommonUtil.isNull(map.get("receivedAt"))) {
					    Date receivedAt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(map.get("receivedAt")+"");
				        map.put("receivedAt", receivedAt.getTime());
				    }
				    
//System.out.println( (0 + i) + "/ " + list.size() +" phase 3 : " + System.currentTimeMillis());
				    if (!CommonUtil.isNull(map.get("updatedAt"))) {
					    Date updatedAt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(map.get("updatedAt")+"");
				        map.put("updatedAt", updatedAt.getTime());
				    }

//System.out.println( (0 + i) + "/ " + list.size() +" phase 4 : " + System.currentTimeMillis());
				    if (!CommonUtil.isNull(map.get("completedAt"))) {
					    Date completedAt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(map.get("completedAt")+"");
				        map.put("completedAt", completedAt.getTime());
				    }

//System.out.println( (0 + i) + "/ " + list.size() +" phase 5 : " + System.currentTimeMillis());
					Map<String, Object> drafter = (Map<String, Object>) SqlMapConfig.getSqlMapInstance().queryForObject("getDrafter", sanc_id);
					map.put("drafter", drafter);
					data.put("deptName",drafter.get("deptName")+"");
					
//System.out.println( (0 + i) + "/ " + list.size() +" phase 6 : " + System.currentTimeMillis());
					List<Map<String, Object>> receiver = (List<Map<String, Object>>) SqlMapConfig.getSqlMapInstance().queryForList("getReceiver", sanc_id);
					map.put("receivers", receiver);

//System.out.println( (0 + i) + "/ " + list.size() +" phase 7 : " + System.currentTimeMillis());
					List<Map<String, Object>> activities = (List<Map<String, Object>>) SqlMapConfig.getSqlMapInstance().queryForList("getActivities", sanc_id);

					for (Map<String, Object> act : activities) {
						if (!CommonUtil.isNull(act.get("actionDate"))) {
						    Date actionDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(act.get("actionDate")+"");
						    act.put("actionDate", actionDate.getTime());
						}
						
						if (!CommonUtil.isNull(act.get("deputyUser"))) {
							param.put("wowf_sanc_id", sanc_id);
							param.put("deputyUser", act.get("deputyUser") + "");
							param.put("empNo", act.get("empNo") + "");
							Map<String, Object> deputyUser = (Map<String, Object>) SqlMapConfig.getSqlMapInstance().queryForObject("getDeputyUser", param);
							act.put("deputyUser", deputyUser);
						} else {
							act.put("deputyUser", new HashMap<String, Object>());
						}
					}

					map.put("activities", activities);

//System.out.println( (0 + i) + "/ " + list.size() +" phase 8 : " + System.currentTimeMillis());
					Map<String, Object> receptionOrigin = (Map<String, Object>) SqlMapConfig.getSqlMapInstance().queryForObject("getOrignDocumentId", sanc_id);
					map.put("receptionOrigin", receptionOrigin);

//System.out.println( (0 + i) + "/ " + list.size() +" phase 9 : " + System.currentTimeMillis());
					String str = converter.convertHTMLTag(util.makeDocumentMap());
					map.put("docBody", str);
					writeHTMLFile(map.get("docBody")+"", migconfig, map.get("woYear") + File.separator + map.get("woMonth") , map.get("sourceId") + ".html");

//System.out.println( (0 + i) + "/ " + list.size() +" phase 10 : " + System.currentTimeMillis());
					List<Map<String, String>> refList = util.getRefFiles();
					map.put("references", refList);

					map.put("readers", new ArrayList<Map<String, String>>());
					map.put("referrers", new ArrayList<Map<String, String>>());

//System.out.println( (0 + i) + "/ " + list.size() +" phase 11 : " + System.currentTimeMillis());
                    List<Map<String, Object>> attachList = new ArrayList<Map<String, Object>>();
                    Map<String,Object> amap = new HashMap<String,Object>();
                    amap.put("path", "file:///export/migration/html/"+map.get("woYear")+"/" +map.get("woMonth") + "/" + map.get("sourceId")+".html");
                    amap.put("name", "본문.html");
                    attachList.add(amap);
					List<Map<String, Object>> attaches = (List<Map<String, Object>>) SqlMapConfig.getSqlMapInstance().queryForList("getAttaches", sanc_id);
					attachList.addAll(attaches);
					map.put("attaches", attachList);
//System.out.println( (0 + i) + "/ " + list.size() +" phase 12 : " + System.currentTimeMillis());
					System.out.println( (0 + i) + "/ " + list.size() +" done.  " + System.currentTimeMillis());
				}
				
				data.put("str",JSONGernerator.getJSONStringObject(map));
				sancList.add(data);
			}
			return sancList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private void writeMigDoc(String str, MigConfig migconfig, String file_path, String file_name) {
		try {
			
			File file = new File(migconfig.getPropValues("repository") + File.separator + "document" + File.separator + file_path);
		    file.mkdirs();
			
			BufferedWriter out = new BufferedWriter(new FileWriter(file + File.separator + file_name, true));
			String s = "addDocument " + str;
			out.write(s, 0, s.length());
			out.newLine();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void writeHTMLFile(String str, MigConfig migconfig, String file_path, String file_name) {
		try {
			
	        String encode = "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>\n";
	        
	        StringBuffer html = new StringBuffer();
	        html.append("<html><head>\n").append(encode)
	            .append("<style type='text/css'>").append("p { margin: 0px; } td.wiseone_textfield { font: normal 10pt dotum; } td.wiseone_currencyfield { text-align: right; } "
	                                                      + "td.wiseone_lineitem { font: normal 10pt dotum; } td.wiseone_gridcell { font: normal 10pt dotum; } span.wiseone_cellitem { overflow:hidden; } "
	                                                      + " .wo_html_element { overflow:auto; } .wo_html_element_inner { font: normal 10pt dotum; padding:5px; } "
	                                                      + ".wiseone_rectangle p { margin:0px } .wo_html_static_panel { overflow:auto; } </style><body>")
	            .append(str).append("\n</body></html>");
	        
			File file = new File(migconfig.getPropValues("repository") + File.separator + "html" + File.separator + file_path);
		    file.mkdirs();
			
			BufferedWriter out = new BufferedWriter(new FileWriter(file + File.separator + file_name));
			out.write(html.toString(), 0, html.toString().length());
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
