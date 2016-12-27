package com.productMonitor.config;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XmlConfig {
	private String configFileName;
	public XmlConfig(String fileName){
		this.configFileName=fileName;
	}
	
	/**
	 * 加载XML文件
	 *  @param fileName
	 * @throws DocumentException 
	 * @pdOid 226ee484-6c27-4695-8906-d699d66fc5fd */
	public Document loadConfigFile() throws Exception {
		// TODO: implement
		// 创建saxreader对象  
        SAXReader reader = new SAXReader();  
        // 读取一个文件，把这个文件转换成Document对象  
        Document document = reader.read(new File(configFileName));
        return document;
     
	}
	/**
	 * 把文档转换字符串 
	 * @param document
	 * @return
	 */
	public String docXmlToString(Document document){
		String docXmlText = document.asXML();  
		return docXmlText;
	}
	/**
	 *  获取根元素
	 * @param fileName
	 * @return
	 * @throws DocumentException
	 */
	public Element   getRootEle() throws Exception{
		
		// 获取根元素  
        Element root = loadConfigFile().getRootElement();        
        return root;
	}
	
	/**将元素转换为String
	 * @param e
	 * @return
	 */
	public String elementToString(Element e){
		String elemmentXmlText = e.asXML();  
		return elemmentXmlText;
	}
	
	/**
	 * 获取某个结点下的具体内容
	 * @param node
	 */
	public String getNodeContent(Element node){
		return node.getTextTrim();
	            
	}
	/**
	 * 取得某节点下的某属性,并将其转成文本
	 * @param ele
	 * @param attr
	 * @return
	 * @throws Exception
	 */
	public String attrInElement(Element ele,String attr) throws Exception{
		Attribute attribute=ele.attribute(attr);
		return attribute.getText(); 
	}
	
	 /** 
     * 遍历当前节点元素下面的所有(元素的)子节点 
     *  
     * @param node 
     */  
    public void listNodes(Element node) {  
        System.out.println("当前节点的名称：：" + node.getName());  
        // 获取当前节点的所有属性节点  
        List<Attribute> list = node.attributes();  
        // 遍历属性节点  
        for (Attribute attr : list) {  
            System.out.println(attr.getText() + "-----" + attr.getName()  
                    + "---" + attr.getValue());  
        }  
  
        if (!(node.getTextTrim().equals(""))) {  
            System.out.println("文本内容：：：：" + node.getText());  
        }  
  
        // 当前节点下面子节点迭代器  
        Iterator<Element> it = node.elementIterator();  
        // 遍历  
        while (it.hasNext()) {  
            // 获取某个子节点对象  
            Element e = it.next();  
            // 对子节点进行遍历  
            listNodes(e);  
        }  
    }

	// 获取某个元素下标签 (strLabel)代表的内容  
	public Element getNextElement(Element priorEle,String strLabel){
		return priorEle.element(strLabel);  
	}
	

}
