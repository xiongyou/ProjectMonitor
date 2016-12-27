package com.productMonitor.config;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;



public interface iConfig {
	
	
	     
	    boolean load(String filePath);
	     
	    Element getElementObject(String elementPath) ;
	     
	    @SuppressWarnings("unchecked")
	    List<Element> getElementObjects(String elementPath) ;
	     
	    @SuppressWarnings("unchecked")
	     Map<String, String> getChildrenInfoByElement(Element element);
	     
	     boolean isExist(String elementPath);
	     String getElementText(String elementPath) ;
	    
	   /** @param fileName
	 * @throws XPathExpressionException 
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	    * @pdOid 249405cf-6cec-45c2-8dcd-3a6d5fea4ff8 */
	Document loadFromFile(String fileName) ;
	
	   /** 返回制定网站中需要解析的数据对象描述信息，包含是对象的名称
	    * 
	    * @param platform
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 * @throws XPathExpressionException 
	    * @pdOid 42455579-b572-4fcc-bea4-bb9d9d88b217 */
	   Map<String, String> getDataObjects(String fileName,String platform) ;
	   /** 返回某个平台下数据对象与字段属性的List
	    * 
	    * @param platform 
	    * @param dataObject
	    * @pdOid f3369800-2508-4644-a010-f89ac6a83935 */
	   List<String> getDataObjFieldNames(String platform, String dataObject);
	   /** 返回某个平台下某个数据对象的字段与正则表达式的Map
	    * 
	    * @param platform 
	    * @param object 
	    * @param fieldName
	    * @pdOid 4c6f4d7b-79ea-42bf-ad02-14c5463d99dc */
	   String getFieldRegex(String platform, String object, String fieldName);
	   /** @pdOid 613b8bd5-f55f-4dde-9738-56390c32a02f */
	   String getFieldSecondRegex();
	
}
