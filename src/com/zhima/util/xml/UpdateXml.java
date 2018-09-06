package com.zhima.util.xml;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class UpdateXml {
	public static String strData;
	
	@SuppressWarnings("rawtypes")
	public static int getNodesIndex(String fileName, String queryNodes,String string) throws Exception {
		SAXReader sr = new SAXReader();  
		sr.setEncoding("utf-8");
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
	
	@SuppressWarnings("rawtypes")
	public void updateXml(String fileName, String queryNodes, String strValue) throws IOException, DocumentException {  
		SAXReader sr = new SAXReader();  
		sr.setEncoding("utf-8");
		String file = fileName;  
		Document doc = sr.read(file);  
		List list = doc.selectNodes(queryNodes);  
		for (Object obj : list) { 
			Element ele = (Element) obj;  
			ele.setText(strValue);  
		}
		OutputFormat opf = OutputFormat.createPrettyPrint();
		opf.setEncoding("utf-8");
		opf.setSuppressDeclaration(true);
		//opf.setIndent("    "); //以空格方式实现缩进
		opf.setNewlines(true); //是否换行
		opf.setLineSeparator("\r\n");
		
		//Writer w = new FileWriter(file); 
		XMLWriter xw = new XMLWriter(new FileOutputStream(fileName), opf);
		xw.setEscapeText(false);
		xw.write(doc);  
		xw.close();
		/*// 输出XML文档
		try
		{
		    OutputFormat outFmt = new OutputFormat("\t", true);
		    outFmt.setEncoding("UTF-8");
		    outFmt.setNewlines(true); //是否换行
		    outFmt.setLineSeparator("\r\n");
		    XMLWriter xw = new XMLWriter(new FileOutputStream(fileName), outFmt); 
		    xw.setEscapeText(false);
		    xw.write(doc);
		    xw.flush();
		    xw.close();
		}
		catch (IOException e)
		{
		    System.out.println(e.getMessage());
		}*/

	}
	
	@SuppressWarnings({"rawtypes" })
	public static void writeProperty(String fileName, String queryNodes, String Property,
			String tagName,String value) throws Exception{
	 	SAXReader sr = new SAXReader();  
	 	sr.setEncoding("utf-8");
		Document doc = sr.read(fileName);
		List list = doc.selectNodes(queryNodes);
		for (Object obj : list) {  
			Element el = (Element) obj;
			List propertys = el.elements(Property);
			for(Object node : propertys){
				Element nodeElem = (Element) node;
				if(nodeElem.attributeValue("name").equals(tagName)){
					Attribute attValue = nodeElem.attribute("value");
					attValue.setValue(value);
				}
			}
		}
		OutputFormat opf = OutputFormat.createPrettyPrint();  
		opf.setEncoding("utf-8");
		opf.setSuppressDeclaration(true);
		//opf.setIndent("    "); //以空格方式实现缩进
		opf.setNewlines(true); //是否换行
		opf.setLineSeparator("\r\n");
        XMLWriter writer = new XMLWriter(new FileOutputStream(fileName), opf);
        writer.write(doc);
        writer.flush();
        writer.close();
		try
		{
		    OutputFormat outFmt = new OutputFormat("\t", true);
		    outFmt.setEncoding("UTF-8");
		    outFmt.setNewlines(true); //是否换行
		    outFmt.setLineSeparator("\r\n");
		    XMLWriter xw = new XMLWriter(new FileOutputStream(fileName), outFmt); 
		    xw.setEscapeText(false);
		    xw.write(doc);
		    xw.flush();
		    xw.close();
		}
		catch (IOException e)
		{
		    System.out.println(e.getMessage());
		}
	 }
	
	@SuppressWarnings("rawtypes")
	public void updateXml(String fileName, String queryNodes, String strValue, int index) throws IOException, DocumentException {  
		SAXReader sr = new SAXReader();  
		sr.setEncoding("utf-8");
		String file = fileName;  
		Document doc = sr.read(file);  
		List list = doc.selectNodes(queryNodes);  
		int i = 0;
		for (Object obj : list) {
			if (i==index){
				Element ele = (Element) obj;  
				ele.setText(strValue);  
			}
			i++;
		}  
		OutputFormat opf = OutputFormat.createPrettyPrint();
		opf.setEncoding("utf-8");
		opf.setSuppressDeclaration(true);
		//opf.setIndent("    "); //以空格方式实现缩进
		//opf.setNewlines(true); //是否换行
		opf.setLineSeparator("\r\n");
		XMLWriter xw = new XMLWriter(new FileOutputStream(fileName), opf);  
		xw.write(doc);  
		xw.flush();
		xw.close();  
	}
	
	@SuppressWarnings({"rawtypes" })
	public static void writeProperty(String fileName, String queryNodes, String Property,
			String tagName,String value, int index) throws Exception{
	 	SAXReader sr = new SAXReader();
	 	sr.setEncoding("utf-8");
		Document doc = sr.read(fileName);
		List list = doc.selectNodes(queryNodes);
		int i = 0;
		for (Object obj : list) {  
			Element el = (Element) obj;
			List propertys = el.elements(Property);
			for(Object node : propertys){
				if (i==index){
					Element nodeElem = (Element) node;
					if(nodeElem.attributeValue("name").equals(tagName)){
						Attribute attValue = nodeElem.attribute("value");
						attValue.setValue(value);
					}
				}
				i++;
			}
		}
		OutputFormat opf = OutputFormat.createPrettyPrint();  
		opf.setEncoding("utf-8");
		opf.setSuppressDeclaration(true);
		//opf.setIndent("    "); //以空格方式实现缩进
		//opf.setNewlines(true); //是否换行
		opf.setLineSeparator("\r\n");
        XMLWriter writer = new XMLWriter(new FileOutputStream(fileName), opf);
        writer.write(doc);
        writer.flush();
        writer.close();
	 }
	 
}  
