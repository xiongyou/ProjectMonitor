package com.productMonitor.config;



import java.io.File;
import java.io.IOException;   
import java.io.Writer;   
import java.util.Iterator;   
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;   
import org.dom4j.DocumentException;   
import org.dom4j.DocumentHelper;   
import org.dom4j.Element;   
import org.dom4j.io.SAXReader;   
import org.dom4j.io.XMLWriter; 

/***********************************************************************
 * Module:  ProductConfig.java
 * Author:  Administrator
 * Purpose: Defines the Class ProductConfig
 ***********************************************************************/

/** 配置检索数据（关键字，网站，正则表达式）
 * 
 * @pdOid 2c5f83a3-55c0-4253-bd11-4e0d3caebf21 */
public class DataSourceConfig {
	
	private String configFileName;
	public DataSourceConfig(String fileName){
		this.configFileName=fileName;
	}
	XmlConfig xmlConfig=new XmlConfig(this.configFileName);
	

	/**
	 * 进入网站website结点
	 * @param website
	 * @return
	 * @throws DocumentException
	 */
	public Element intoWebsiteNode(String website) throws Exception{
		Element rootEle=xmlConfig.getRootEle();
		Element nextEle=xmlConfig.getNextElement(rootEle, "website");
		Element websiteEle=xmlConfig.getNextElement(nextEle, website);
		return websiteEle;
	}
	
	/**
	 * 获取网站的编码
	 * @param website
	 * @return
	 * @throws Exception
	 */
	public String getCharset(String website) throws Exception 
	{
		Element charset=xmlConfig.getNextElement(intoWebsiteNode(website), "charset");
		return xmlConfig.getNodeContent(charset);		
	}


	/**
	 * 获取网站构造搜索的URL
	 * @param website 网站
	 * @return 执行搜索时的URL
	 * @throws Exception 
	 */
	public  String getSearchURL(String website) throws Exception 
	{
		Element searchURL=xmlConfig.getNextElement(intoWebsiteNode(website), "searchURL");
		return xmlConfig.getNodeContent(searchURL);		
	}
	
	/**
	 * 获取某网站总页数的正则表达式
	 * @param website
	 * @return
	 * @throws Exception
	 */
	public String totalPageRegex(String website) throws Exception{
		Element totalPageEle=xmlConfig.getNextElement(intoWebsiteNode(website), "totalPage");
		return xmlConfig.getNodeContent(totalPageEle);
	}
	
	
	/**
	 * 获取某网站分页的分组
	 * @param website
	 * @return
	 * @throws Exception
	 */
	public int getTotalGroup(String website) throws Exception{
		Element totalPageEle=xmlConfig.getNextElement(intoWebsiteNode(website), "totalPage");
		return Integer.parseInt(xmlConfig.attrInElement(totalPageEle,"targetgroup"));	
		}
	
	
	/**
	 * 获取网站用来构造下一页URL的字符串
	 * @param website
	 * @return
	 * @throws Exception
	 */
	public String nextPageURL(String website) throws Exception{
		Element nextPageURL=xmlConfig.getNextElement(intoWebsiteNode(website), "nextPageURL");
		return xmlConfig.getNodeContent(nextPageURL);
	}
	
	
	
	/**
	 * 获取某网站搜索结果页面中产品的正则表达式
	 * @param website
	 * @return
	 * @throws Exception
	 */
	public String productURLRegex(String website) throws Exception{
		Element productURL=xmlConfig.getNextElement(intoWebsiteNode(website), "productURL");
		return xmlConfig.getNodeContent(productURL);
	}
		
    
	/**
	 * 获取所有的关键字
	 * @return 关键字字符串
	 * @throws Exception 
	 */
	public  String[] getAllProductsKeyword() throws Exception 
	{
		Element rootEle=xmlConfig.getRootEle();
		Element keywordsEle=xmlConfig.getNextElement(rootEle, "keywords");
		
		String[] keys=xmlConfig.getNodeContent(keywordsEle).split(";");
		return keys;
	}
	
	

	/** @pdOid e6c5ea3a-4a79-4117-8fa7-1a5b774bc5d8 */
	public String[] getCategory() {
		// TODO: implement
		return null;
	}

	/** @param category
	 * @pdOid dc771c1d-fc30-4174-b960-afda3155f6e8 */
	public void addCategory(String category) {
		// TODO: implement
	}

	/** @param productKeyWord
	 * @pdOid 85cb9537-1fb3-4f76-8ae6-ff6ab312ed31 */
	public void addProductKeyword(String productKeyWord) {
		// TODO: implement
	}
	

	

}