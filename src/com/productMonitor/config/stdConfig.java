package com.productMonitor.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class stdConfig implements iConfig {

	private String filePath;

	private Document document;

	public stdConfig(String filePath) {
		this.filePath = filePath;
		this.load(this.filePath);
	}

	public boolean load(String filePath) {
		File file = new File(filePath);
		if (file.exists()) {
			SAXReader saxReader = new SAXReader();
			try {
				document = saxReader.read(file);
			} catch (DocumentException e) {
				System.out.println("文件加载异常：" + filePath);
				return false;
			}
		} else {
			System.out.println("文件不存在 : " + filePath);
			return false;
		}
		return true;
	}

	public Element getElementObject(String elementPath) {
		return (Element) document.selectSingleNode(elementPath);
	}

	@SuppressWarnings("unchecked")
	public List<Element> getElementObjects(String elementPath) {
		return document.selectNodes(elementPath);
	}

	@SuppressWarnings("unchecked")
	public Map<String, String> getChildrenInfoByElement(Element element) {
		Map<String, String> map = new HashMap<String, String>();
		List<Element> children = element.elements();
		for (Element e : children) {
			map.put(e.getName(), e.getText());
		}
		return map;
	}

	public boolean isExist(String elementPath) {
		boolean flag = false;
		Element element = this.getElementObject(elementPath);
		if (element != null)
			flag = true;
		return flag;
	}

	 public String getElementText(String elementPath) {
	        Element element = this.getElementObject(elementPath);
	        if(element != null){
	            return element.getText().trim();
	        }else{
	            return null;
	        }      
	    }
	 
	@Override
	public Document loadFromFile(String fileName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getDataObjects(String fileName, String platform) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getDataObjFieldNames(String platform, String dataObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFieldRegex(String platform, String object, String fieldName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFieldSecondRegex() {
		// TODO Auto-generated method stub
		return null;
	}
	
	 public static void main(String[] args) {
         
	        stdConfig px = new stdConfig("config/TestBaidu.xml");
	        List<Element> elements = px.getElementObjects("/dataParseConfig/websites/website[@name='TaoBao']/dataobjects/*");
	        for(Element ele:elements){
	        	System.out.println(ele.getText());
	        }
	         
	    }
	
	

}
