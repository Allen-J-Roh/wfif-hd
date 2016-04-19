package com.erae.mig.wiseone.model;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "fileList")
public class FileList {
	
	@XmlElement(name = "file", type = FileInfo.class)
	private List<FileInfo> fileInfoList;
	
	
	public List<FileInfo> getFileInfoList() {
		if (fileInfoList == null) {
			fileInfoList = new ArrayList<FileInfo>();
		}
		return fileInfoList;
	}

	private static ThreadLocal<Unmarshaller> unmarshaller;
	
	static {		
		unmarshaller = new ThreadLocal<Unmarshaller>() {
			protected Unmarshaller initialValue() {
				Unmarshaller unmarshaller = null;
				try {
					unmarshaller = JAXBContext.newInstance(FileList.class).createUnmarshaller();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return unmarshaller;
			}
		};
	}

	public static FileList unmarshalling(String data) {
		FileList fileList = null;
		StringReader reader = new StringReader(data);		
		try {
			fileList = (FileList) unmarshaller.get().unmarshal(reader);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return fileList;
	}
}
