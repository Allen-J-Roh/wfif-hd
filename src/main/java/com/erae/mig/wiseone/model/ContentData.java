package com.erae.mig.wiseone.model;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "content")
public class ContentData {

	@XmlJavaTypeAdapter(value=HashMapAdapter.class)
	private Map contentMap;

	public ContentData() {
		super();
	}

	public ContentData(Map contentMap) {
		super();
		setContentMap(contentMap);
	}
	
	private void refineContentMap(Map contentMap) {
	    Map changedMap = new HashMap();
	    List nullValueList = new ArrayList();
	    
	    for (Object key : contentMap.keySet()) {
	        Object value = contentMap.get(key);
	        if (value instanceof List) {
	            changedMap.put(key, JSONArray.fromObject(value).toString());
	        } else if (value instanceof JSONNull) {
	            nullValueList.add(key);
	        }
	    }
	    
	    contentMap.putAll(changedMap);
	    
	    for (Object key : nullValueList) {
	        contentMap.remove(key);
	    }
	}

	public Map getContentMap() {
		return contentMap;
	}

	public void setContentMap(Map contentMap) {
		this.contentMap = contentMap;
		refineContentMap(this.contentMap);
	}
	
	private static ThreadLocal<Marshaller> marshaller;
	
	private static ThreadLocal<Unmarshaller> unmarshaller;
	
	static {

		marshaller = new ThreadLocal<Marshaller>(){
			protected Marshaller initialValue() {
				Marshaller marshaller = null;
				try {
					marshaller = JAXBContext.newInstance(ContentData.class).createMarshaller();
					marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
					marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				} catch (JAXBException e) {
					e.printStackTrace();
				}
				return marshaller;
			}
		};
		
		unmarshaller = new ThreadLocal<Unmarshaller>() {
			protected Unmarshaller initialValue() {
				Unmarshaller unmarshaller = null;
				try {
					unmarshaller = JAXBContext.newInstance(ContentData.class).createUnmarshaller();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return unmarshaller;
			}
		};
	}

	public static String marshalling(ContentData contentData) {
		StringWriter writer = new StringWriter();
		try {
			marshaller.get().marshal(contentData, writer);
		} catch (JAXBException e) {
			e.printStackTrace();
		}		
		return writer.toString();
	}

	public static ContentData unmarshalling(String data) {
		ContentData contentData = null;
		StringReader reader = new StringReader(data);		
		try {
			contentData = (ContentData) unmarshaller.get().unmarshal(reader);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return contentData;
	}

}
