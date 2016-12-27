package com.productMonitor.config;

import org.dom4j.DocumentException;
import org.dom4j.Element;

/**
 * 配置产品与店铺信息数据
 * @author DengPan
 *
 */
public class RegexConfig {

	private String configFileName;
	XmlConfig xmlConfig;
	
	/**构造函数，传入配置文件名作为参数
	 * @param fileName
	 */
	public RegexConfig(String fileName){
		this.configFileName=fileName;
		xmlConfig=new XmlConfig(this.configFileName);
	}
	//创建一个XmlConfig对象
	

	/**
	 * 进入网站website结点
	 * @param website
	 * @return
	 * @throws DocumentException
	 */
	public Element intoWebsiteNode(String website) throws Exception{
		Element rootEle=xmlConfig.getRootEle();
		Element websiteEle=xmlConfig.getNextElement(rootEle, website);
		return websiteEle;
	}
	

	/**
	 * 进入某网站下的某个子结点元素
	 * @param website
	 * @return
	 * @throws Exception
	 */
	public Element intoSubNode(String website,String subNode) throws Exception{
		Element websiteEle=intoWebsiteNode(website);
		Element subNodeEle=xmlConfig.getNextElement(websiteEle, subNode);
		return subNodeEle;
	}
	
	/**
	 * 获取网站下某一子结点中的某个具体结点元素
	 * @param website
	 * @param subNode
	 * @param concreteNode
	 * @return
	 * @throws Exception
	 */
	public Element getConcreteNode (String website,String subNode,String concreteNode) throws Exception{
		return xmlConfig.getNextElement(this.intoSubNode(website,subNode),concreteNode);
	}
	
	/**
	 * 获取网站下某一子结点中的某个具体结点的内容
	 * @param website
	 * @param subNode
	 * @param concreteNode
	 * @return
	 * @throws Exception
	 */
	public String getConcreteNodeText(String website,String subNode,String concreteNode) throws Exception{
		return xmlConfig.getNodeContent(getConcreteNode (website,subNode,concreteNode) );
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
	 * 获取网站的任务量
	 * @param website
	 * @return
	 * @throws Exception
	 */
	public int getTaskCount(String website) throws Exception 
	{	
		
		Element charset=xmlConfig.getNextElement(intoWebsiteNode(website), "taskCount");
		return Integer.parseInt( xmlConfig.getNodeContent(charset));		
	}
	
	/**
	 * 获取网站的单个产品内容获取的间隔时间
	 * @param website
	 * @return
	 * @throws Exception
	 */
	public int getSingleInterval(String website) throws Exception 
	{	
		
		Element charset=xmlConfig.getNextElement(intoWebsiteNode(website), "singleInterval");
		return Integer.parseInt( xmlConfig.getNodeContent(charset));		
	}
	
	/**
	 * 获取网站的每批任务的间隔时间
	 * @param website
	 * @return
	 * @throws Exception
	 */
	public int getTaskInterval(String website) throws Exception 
	{	
		
		Element charset=xmlConfig.getNextElement(intoWebsiteNode(website), "taskInterval");
		return Integer.parseInt( xmlConfig.getNodeContent(charset));		
	}

	
	/**
	 * 获取某网站下产品的实际ID的正则表达式
	 * @return
	 * @throws Exception 
	 */
	public String getProductActualIDReg(String website) throws Exception{		
		return this.getConcreteNodeText(website, "productBaseInfo", "productActuallID");
	}
	
	/**
	 * 获取某网站下产品名称的正则表达式
	 * @param website
	 * @return
	 * @throws Exception
	 */
	public String getProductNameReg(String website) throws Exception{		
		return this.getConcreteNodeText(website, "productBaseInfo", "productName");
	}
	
	/**
	 * 获取某网站下产品描述信息的正则表达式
	 * @param website
	 * @return
	 * @throws Exception
	 */
	public String getProductDescriptionReg(String website) throws Exception{		
		return this.getConcreteNodeText(website, "productBaseInfo", "productDescription");
	}
	
	/**
	 * 获取某网站下产品上架时间的正则表达式
	 * @param website
	 * @return
	 * @throws Exception
	 */
	public String getProductShelveDateReg(String website) throws Exception{		
		return this.getConcreteNodeText(website, "productBaseInfo", "productShelveDate");
	}
	
	/**
	 * 获取某网站下产品毛重的正则表达式
	 * @param website
	 * @return
	 * @throws Exception
	 */
	public String getProductWeightReg(String website) throws Exception{		
		return this.getConcreteNodeText(website, "productBaseInfo", "productWeight");
	}
	
	/**
	 * 获取某网站下产品产地的正则表达式
	 * @param website
	 * @return
	 * @throws Exception
	 */
	public String getProductOriginReg(String website) throws Exception{		
		return this.getConcreteNodeText(website, "productBaseInfo", "productOrigin");
	}
	
	/**
	 * 获取某网站下产品类别的正则表达式
	 * @param website
	 * @return
	 * @throws Exception
	 */
	public String getProductCategoryReg(String website) throws Exception{		
		return this.getConcreteNodeText(website, "productBaseInfo", "productCategory");
	}
	
	/**
	 * 获取某网站下产品类别2的正则表达式
	 * @param website
	 * @return
	 * @throws Exception
	 */
	public String getProductCategory2Reg(String website) throws Exception{		
		return this.getConcreteNodeText(website, "productBaseInfo", "productCategory2");
	}
	
	/**
	 * 获取某网站下产品品牌的正则表达式
	 * @param website
	 * @return
	 * @throws Exception
	 */
	public String getProductBrandReg(String website) throws Exception{		
		return this.getConcreteNodeText(website, "productBaseInfo", "productBrand");
	}
	
	/**
	 * 获取产品价格的URL
	 * @param website
	 * @return
	 * @throws Exception
	 */
	public String getPriceUrl(String website) throws Exception{
		return xmlConfig.attrInElement(this.getConcreteNode(website, "proudctMonitor", "price"), "url");
	}
	
	/**
	 * 获取某网站下产品价格的正则表达式
	 * @param website
	 * @return
	 * @throws Exception
	 */
	public String getPriceReg(String website) throws Exception{
		return this.getConcreteNodeText(website,  "proudctMonitor", "price");
	}
	
	/**
	 * 获取产品评论数据的URL
	 * @param website
	 * @return
	 * @throws Exception
	 */
	public String getCommentUrl(String website) throws Exception{
		return xmlConfig.attrInElement(this.getConcreteNode(website, "proudctMonitor", "commentCount"), "url");
	}
	
	/**
	 * 获取产品评论数据是否是用星级表示
	 * @param website
	 * @return
	 * @throws Exception
	 */
	public String getCommentStar(String website) throws Exception{
		return xmlConfig.attrInElement(this.getConcreteNode(website, "proudctMonitor", "commentCount"), "star");
	}
	/**
	 * 获取某网站下产品评价总数量的正则表达式
	 * @param website
	 * @return
	 * @throws Exception
	 */
	public String getCommentCountReg(String website) throws Exception{
		return this.getConcreteNodeText(website,  "proudctMonitor", "commentCount");
	}
	
	/**
	 * 获取某网站下产品好评数量的正则表达式
	 * @param website
	 * @return
	 * @throws Exception
	 */
	public String getGoodCommentCountReg(String website) throws Exception{
		return this.getConcreteNodeText(website,  "proudctMonitor", "goodCommentCount");
	}
	
	/**
	 * 获取某网站下产品四星评论数量的正则表达式
	 * @param website
	 * @return
	 * @throws Exception
	 */
	public String getFourStarCommentCountReg(String website) throws Exception{
		return this.getConcreteNodeText(website,  "proudctMonitor", "fourStarCommentCount");
	}
	
	/**
	 * 获取某网站下产品中评数量的正则表达式
	 * @param website
	 * @return
	 * @throws Exception
	 */
	public String getMidCommentCountReg(String website) throws Exception{
		return this.getConcreteNodeText(website,  "proudctMonitor", "midCommentCount");
	}
	
	/**
	 * 获取某网站下产品二星评论数量的正则表达式
	 * @param website
	 * @return
	 * @throws Exception
	 */
	public String getTwoStarCommentCountReg(String website) throws Exception{
		return this.getConcreteNodeText(website,  "proudctMonitor", "twoStarCommentCount");
	}
	
	/**
	 * 获取某网站下产品差评数量的正则表达式
	 * @param website
	 * @return
	 * @throws Exception
	 */
	public String getBadCommentCountReg(String website) throws Exception{
		return this.getConcreteNodeText(website,  "proudctMonitor", "badCommentCount");
	}
	
	/**
	 * 获取某网站下产品带图评论数量的正则表达式
	 * @param website
	 * @return
	 * @throws Exception
	 */
	public String getPictureCommentCountReg(String website) throws Exception{
		return this.getConcreteNodeText(website,  "proudctMonitor", "pictureCommentCount");
	}
	
	/**
	 * 获取某网站下产品追加评论数量的正则表达式
	 * @param website
	 * @return
	 * @throws Exception
	 */
	public String getAdditionCommentCountReg(String website) throws Exception{
		return this.getConcreteNodeText(website,  "proudctMonitor", "additionCommentCount");
	}
	
	
	/**获取某网站下店铺URL构造方式。 mode=0：表示构造URL；mode=1：表示用正则表达式获取URL
	 * @param website
	 * @return
	 * @throws Exception
	 */
	public int getStoreURLMode(String website) throws Exception{
		return Integer.parseInt(xmlConfig.attrInElement(this.getConcreteNode(website, "storeBaseInfo", "storeURL"), "mode"));
	}
	
	/**
	 * 获取某网站下店铺URL的正则表达式
	 * @param website
	 * @return
	 * @throws Exception
	 */
	public String getStoreUrlReg(String website) throws Exception{
		return this.getConcreteNodeText(website,  "storeBaseInfo", "storeURL");
	}	

	/**
	 * 获取某网站下店铺实际ID的正则表达式
	 * @param website
	 * @return
	 * @throws Exception
	 */
	public String getStoreActualIDReg(String website) throws Exception{
		return this.getConcreteNodeText(website,  "storeBaseInfo", "storeActualID");
	}
	
	/**
	 * 获取某网站下店铺名称的正则表达式
	 * @param website
	 * @return
	 * @throws Exception
	 */
	public String getStoreNameReg(String website) throws Exception{
		return this.getConcreteNodeText(website,  "storeBaseInfo", "storeName");
	}
	
	/**
	 * 获取某网站下店铺公司名称的正则表达式
	 * @param website
	 * @return
	 * @throws Exception
	 */
	public String getStoreCompanyNameReg(String website) throws Exception{
		return this.getConcreteNodeText(website,  "storeBaseInfo", "storeCompanyName");
	}
	
	/**
	 * 获取某网站下店铺所在地的正则表达式
	 * @param website
	 * @return
	 * @throws Exception
	 */
	public String getStoreLocationReg(String website) throws Exception{
		return this.getConcreteNodeText(website,  "storeBaseInfo", "storeLocation");
	}

	/**
	 * 获取某网站下店铺综合评分的正则表达式
	 * @param website
	 * @return
	 * @throws Exception
	 */
	public String getComprehensiveScore(String website) throws Exception{
		return this.getConcreteNodeText(website,  "storeMonitor", "comprehensiveScore");
	}
	
	/**
	 * 获取某网站下店铺商品质量满意度评分的正则表达式
	 * @param website
	 * @return
	 * @throws Exception
	 */
	public String getProductQuality(String website) throws Exception{
		return this.getConcreteNodeText(website,  "storeMonitor", "productQuality");
	}
	
	
	/**
	 * 获取某网站下店铺退换货/返修率的正则表达式
	 * @param website
	 * @return
	 * @throws Exception
	 */
	public String getExchangeRate(String website) throws Exception{
		return this.getConcreteNodeText(website,  "storeMonitor", "exchangeRate");
	}
	
	/**
	 * 获取某网站下店铺退换货处理时长的正则表达式
	 * @param website
	 * @return
	 * @throws Exception
	 */
	public String getExchangeDuration(String website) throws Exception{
		return this.getConcreteNodeText(website,  "storeMonitor", "exchangeDuration");
	}
	
	/**
	 * 获取某网站下店铺在线客服响应时长的正则表达式
	 * @param website
	 * @return
	 * @throws Exception
	 */
	public String getOnlineResponseTime(String website) throws Exception{
		return this.getConcreteNodeText(website,  "storeMonitor", "onlineResponseTime");
	}
}
