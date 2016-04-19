package com.erae.mig.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class JSONGernerator {
	public JSONGernerator() {}

	public static String getJSONString(Map<String, String> map) {
		JSONArray json_arr = new JSONArray();
		JSONObject json_obj = new JSONObject();
		if (map != null) {
			for (Map.Entry<String, String> entry : map.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();
				try {
					json_obj.put(key, value);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			json_arr.put(json_obj);
		}
		return json_arr.toString();
	}

	public static String getJSONString(List<Map<String, String>> list) {
		JSONArray json_arr = new JSONArray();
		for (Map<String, String> map : list) {
			JSONObject json_obj = new JSONObject();
			for (Map.Entry<String, String> entry : map.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();
			    try {
				    json_obj.put(key, value);
			    } catch (JSONException e) {
				    e.printStackTrace();
			    }
			}
			json_arr.put(json_obj);
		}
		return json_arr.toString();
	}
	
	@SuppressWarnings("unchecked")
	public static String getJSONStringObject(Map<String, Object> map) {
			JSONObject json_obj = new JSONObject();
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();
					if (value instanceof List) {
						try {
						    json_obj.put(key.toString(), toJSON2((List<Map<String, Object>>)value));
						} catch (JSONException e) {
						    e.printStackTrace();
					    }
					} else if (value instanceof Map) {
					    try {
						    json_obj.put(key.toString(), toJSON((Map<String,String>) value));
					    } catch (JSONException e) {
						    e.printStackTrace();
					    }
					} else {
					    try {
					    	json_obj.put(key, value);
					    } catch (JSONException e) {
						    e.printStackTrace();
					    }
					}
				}
		return json_obj.toString();
	}
	
	@SuppressWarnings("unchecked")
	public static String getJSONStringObject(List<Map<String, Object>> list) {
		JSONArray json_arr = new JSONArray();
		for (Map<String, Object> map : list) {
			JSONObject json_obj = new JSONObject();
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();
				if (key.equals("docBody")) {
				    try {
				    	json_obj.put(key, value);
				    } catch (JSONException e) {
					    e.printStackTrace();
				    }
				} else {
					if (value instanceof List) {
						try {
						    json_obj.put(key.toString(), toJSON2((List<Map<String, Object>>)value));
						} catch (JSONException e) {
						    e.printStackTrace();
					    }
					} else if (value instanceof Map) {
					    try {
						    json_obj.put(key.toString(), toJSON((Map<String,String>) value));
					    } catch (JSONException e) {
						    e.printStackTrace();
					    }
					} else {
					    try {
					    	json_obj.put(key, value);
					    } catch (JSONException e) {
						    e.printStackTrace();
					    }
					}
				}
			}
			json_arr.put(json_obj);
		}
		return json_arr.toString();
	}
	
	public static String getJSONStringForAssetList(List<Map<String, String>> list, List<Map<String, String>> collist) {
		JSONArray json_arr = new JSONArray();
		for (Map<String, String> map : list) {
			JSONObject json_obj = new JSONObject();
			for (Map.Entry<String, String> entry : map.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();
				try {
					json_obj.put(key, value);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			json_arr.put(json_obj);
		}
		
		for (Map<String, String> map : collist) {
			JSONObject json_obj = new JSONObject();
			for (Map.Entry<String, String> entry : map.entrySet()) {
				String key   = entry.getKey();
				Object value = entry.getValue();
				try {
					json_obj.put(key, value);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			json_arr.put(json_obj);
		}
		return json_arr.toString();
	}
	
	public static Object toJSON(Object object) throws JSONException {
        if (object instanceof Map) {
            JSONObject json = new JSONObject();
            Map map = (Map) object;
            for (Object key : map.keySet()) {
                json.put(key.toString(), toJSON(map.get(key)));
            }
            return json;
        } else if (object instanceof Iterable) {
            JSONArray json = new JSONArray();
            for (Object value : ((Iterable)object)) {
                json.put(value);
            }
            return json;
        } else {
            return object;
        }
    }

	@SuppressWarnings("unchecked")
	public static Object toJSON2(List<Map<String, Object>> list) throws JSONException {
		JSONArray json_arr = new JSONArray();
		for (Map<String, Object> map : list) {
			JSONObject json_obj = new JSONObject();
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();
				if (value instanceof List) {
					json_obj.put(key.toString(), toJSON2((List<Map<String, Object>>)value));
				} else if (value instanceof Map) {
				    try {
					    json_obj.put(key.toString(), toJSON((Map<String,String>) value));
				    } catch (JSONException e) {
					    e.printStackTrace();
				    }
				} else {
				    try {
				    	json_obj.put(key, value);
				    } catch (JSONException e) {
					    e.printStackTrace();
				    }
				}
			}
			json_arr.put(json_obj);
		}
		return json_arr;
    }
	
    public static boolean isEmptyObject(JSONObject object) {
        return object.names() == null;
    }
 
    public static Map<String, Object> getMap(JSONObject object, String key) throws JSONException {
        return toMap(object.getJSONObject(key));
    }
 
    public static Map<String, Object> toMap(JSONObject object) throws JSONException {
        Map<String, Object> map = new HashMap();
        Iterator keys = object.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            map.put(key, fromJson(object.get(key)));
        }
        return map;
    }
 
    public static List toList(JSONArray array) throws JSONException {
        List list = new ArrayList();
        for (int i = 0; i < array.length(); i++) {
        	Object obj = fromJson(array.get(i));
        	if (obj != null) {
                list.add(obj);
        	}
        }
        return list;
    }
 
    private static Object fromJson(Object json) throws JSONException {
        if (json == JSONObject.NULL) {
            return null;
        } else if (json instanceof JSONObject) {
            return toMap((JSONObject) json);
        } else if (json instanceof JSONArray) {
            return toList((JSONArray) json);
        } else {
            return json;
        }
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map<String, String> toMap(String jsonData) throws JSONException {
        Map<String, String> map = new HashMap();
        
		JSONObject obj = new JSONObject(jsonData);
		Iterator it = obj.keys();
		while(it.hasNext()){
			String key = (String) it.next();
			String value = (String) obj.get(key);
			map.put(key,value);
		}
		
        return map;
    }
    
    public static String resetKey(String key, List<Map<String,String>> colList) {
    	String result = key;
    	
    	for (Map<String, String> map : colList) {
    		if (result != null && result.toUpperCase().equals((map.get("asst_col_id")+"").toUpperCase())) {
    			result = map.get("asst_col_key")+"";
    			break;
    		}
    	}
    	
    	return result;
    }
}
