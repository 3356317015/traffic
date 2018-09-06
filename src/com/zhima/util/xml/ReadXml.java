package com.zhima.util.xml;

import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ReadXml {
	
	/**
	 * 得到xml单个节点值
	 */
	public static String strData;
	
	@SuppressWarnings("rawtypes")
	public static int getNodesIndex(String fileName, String queryNodes,String string) throws Exception {
		SAXReader sr = new SAXReader();  
		Document doc = sr.read(fileName);
		List list = doc.selectNodes(queryNodes);
		strData = "";
		int i = 0;
		for (Object obj : list) {
			Element el = (Element) obj;
			strData = el.getText();
			if (string.equals(strData)){
				return i;
			}
			i++;
		}
		return -1;
	}
	
	
	
	/** 
	* 使用XPath语法来直接定位到某个节点 
	*/  
	@SuppressWarnings("rawtypes")
	public static void readElement(String fileName, String queryNodes) throws Exception {
		SAXReader sr = new SAXReader();  
		Document doc = sr.read(fileName);
		List list = doc.selectNodes(queryNodes);
		strData = "";
		for (Object obj : list) {
			Element el = (Element) obj;
			strData = el.getText();
		}  
	}
	
	/**
	 * readProperty方法描述：根据带属性的元素取值
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-12 上午11:31:12
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param fileName 文件地址
	 * @param queryNodes 节点
	 * @param Property 节点属性
	 * @param tagName 属性无素
	 * @throws Exception void
	 */
	@SuppressWarnings("rawtypes")
	public static void readProperty(String fileName, String queryNodes, String Property ,String tagName) throws Exception{
		SAXReader sr = new SAXReader();  
		Document doc = sr.read(fileName);
		List list = doc.selectNodes(queryNodes);
		strData = "";
		for (Object obj : list) {  
			Element el = (Element) obj;
			List propertys = el.elements(Property);
			for(Object node : propertys){
				Element nodeElem = (Element) node;
				if(nodeElem.attributeValue("name").equals(tagName)){
					strData = nodeElem.attributeValue("value");
				}
			}
		}  
	}
	
	/** 
	* 使用XPath语法直接定位到某个属性 
	*/  
	@SuppressWarnings("rawtypes")
	public static void readAttribute() throws Exception {  
		SAXReader sr = new SAXReader();  
		Document doc = sr.read("src/EMP.xml");//相对路径，可以换成自己的绝对路径  
		List list = doc.selectNodes("ROWDATA/ROW/ENAME/@firstname");  
		int i =0;
		for (Object obj : list) {  
			System.out.println("c"+ i++);
			Attribute el = (Attribute) obj;  
			System.out.println(el.getText());  
		}
	}
	
	/** 
	* 使用XPath语法来直接定位到某个节点 
	*/  
	@SuppressWarnings("rawtypes")
	public static void readElement(String fileName, String queryNodes,int index) throws Exception {
		SAXReader sr = new SAXReader();  
		Document doc = sr.read(fileName);
		List list = doc.selectNodes(queryNodes);
		strData = "";
		int i=0;
		for (Object obj : list) {
			if(i==index){
				Element el = (Element) obj;
				strData = el.getText();
			}
			i++;
		}  
	}
	
	/**
	 * readProperty方法描述：根据带属性的元素取值
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-12 上午11:31:12
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param fileName 文件地址
	 * @param queryNodes 节点
	 * @param Property 节点属性
	 * @param tagName 属性无素
	 * @throws Exception void
	 */
	@SuppressWarnings("rawtypes")
	public static void readProperty(String fileName, String queryNodes,
			String Property ,String tagName,int index) throws Exception{
		SAXReader sr = new SAXReader();  
		Document doc = sr.read(fileName);
		List list = doc.selectNodes(queryNodes);
		strData = "";
		int i=0;
		for (Object obj : list) {  
			Element el = (Element) obj;
			List propertys = el.elements(Property);
			for(Object node : propertys){
				Element nodeElem = (Element) node;
				if(nodeElem.attributeValue("name").equals(tagName)){
					if (i==index){
						strData = nodeElem.attributeValue("value");
					}
					i++;
				}
			}
		}  
	}
	
}  
