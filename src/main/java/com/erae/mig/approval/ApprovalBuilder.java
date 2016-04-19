package com.erae.mig.approval;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
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
			List<Map<String,String>> sancList = getSancList(params);
			MigConfig migconfig = MigConfig.getInstance();

			for (Map<String,String> map : sancList) {
				if (CommonUtil.equals(map.get("isReceipt"),"false")) {
					writeMigDoc(map.get("str")+"", migconfig, map.get("woYear") + File.separator + map.get("woMonth") + File.separator + "send" , map.get("deptName") + ".txt");
				} else {
					writeMigDoc(map.get("str")+"", migconfig, map.get("woYear") + File.separator + map.get("woMonth") + File.separator + "recv" , map.get("deptName") + ".txt");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private List<Map<String,String>> getSancList(Map<String, String> params) {
		try {
			List<Map<String,String>> sancList = new ArrayList<Map<String,String>>();
			String sanc_id;
			String form_seq;

			ElementConverter converter = new ElementConverter();
			converter.setFactory(new FastViewElementRendererFactory());

			List<Map<String, Object>> list = (List<Map<String, Object>>) SqlMapConfig.getSqlMapInstance().queryForList("getSancList", params);

			DocumentUtil util = new DocumentUtil();

			Map<String, Object> param = new HashMap<String, Object>();
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			
			
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
System.out.println((0 + i) + "/ " + list.size() +" phase 1 : " + System.currentTimeMillis());
					if (!CommonUtil.isNull(map.get("createdAt"))) {
					    Date createAt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(map.get("createdAt")+"");
				        map.put("createdAt", createAt.getTime());
					}

System.out.println( (0 + i) + "/ " + list.size() +" phase 2 : " + System.currentTimeMillis());
				    if (!CommonUtil.isNull(map.get("receivedAt"))) {
					    Date receivedAt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(map.get("receivedAt")+"");
				        map.put("receivedAt", receivedAt.getTime());
				    }
				    
System.out.println( (0 + i) + "/ " + list.size() +" phase 3 : " + System.currentTimeMillis());
				    if (!CommonUtil.isNull(map.get("updatedAt"))) {
					    Date updatedAt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(map.get("updatedAt")+"");
				        map.put("updatedAt", updatedAt.getTime());
				    }

System.out.println( (0 + i) + "/ " + list.size() +" phase 4 : " + System.currentTimeMillis());
				    if (!CommonUtil.isNull(map.get("completedAt"))) {
					    Date completedAt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(map.get("completedAt")+"");
				        map.put("completedAt", completedAt.getTime());
				    }

System.out.println( (0 + i) + "/ " + list.size() +" phase 5 : " + System.currentTimeMillis());
					Map<String, Object> drafter = (Map<String, Object>) SqlMapConfig.getSqlMapInstance().queryForObject("getDrafter", sanc_id);
					map.put("drafter", drafter);
					data.put("deptName",drafter.get("deptName")+"");
					
System.out.println( (0 + i) + "/ " + list.size() +" phase 6 : " + System.currentTimeMillis());
					List<Map<String, Object>> receiver = (List<Map<String, Object>>) SqlMapConfig.getSqlMapInstance().queryForList("getReceiver", sanc_id);
					map.put("receivers", receiver);

System.out.println( (0 + i) + "/ " + list.size() +" phase 7 : " + System.currentTimeMillis());
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

System.out.println( (0 + i) + "/ " + list.size() +" phase 8 : " + System.currentTimeMillis());
					Map<String, Object> receptionOrigin = (Map<String, Object>) SqlMapConfig.getSqlMapInstance().queryForObject("getOrignDocumentId", sanc_id);
					map.put("receptionOrigin", receptionOrigin);

System.out.println( (0 + i) + "/ " + list.size() +" phase 9 : " + System.currentTimeMillis());
					String str = converter.convertHTMLTag(util.makeDocumentMap());
					map.put("docBody", str);

System.out.println( (0 + i) + "/ " + list.size() +" phase 10 : " + System.currentTimeMillis());
					List<Map<String, String>> refList = util.getRefFiles();
					map.put("references", refList);

					map.put("readers", new ArrayList<Map<String, String>>());
					map.put("referrers", new ArrayList<Map<String, String>>());

System.out.println( (0 + i) + "/ " + list.size() +" phase 11 : " + System.currentTimeMillis());
					List<Map<String, Object>> attaches = (List<Map<String, Object>>) SqlMapConfig.getSqlMapInstance().queryForList("getAttaches", sanc_id);
					map.put("attaches", attaches);
System.out.println( (0 + i) + "/ " + list.size() +" phase 12 : " + System.currentTimeMillis());
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
}
