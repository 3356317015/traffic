package test;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 
 * 在XML文档中实现增、删、改、查
 * 
 * @author TigerLee
 * 
 */
public class HandleXML {

	/**
	 * 采用DOM方式
	 * 
	 * @author TigerLee
	 * 
	 */
	public class DOMForXml {
		/**
		 * 获得doc对象
		 * 
		 * @param fileName
		 * @return
		 */
		public Document getDocument(String fileName) {
			Document document = null;
			try {
				DocumentBuilderFactory factory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				document = builder.parse(new File(fileName));

			} catch (Exception e) {
				e.printStackTrace();
			}

			return document;
		}

		/**
		 * 将改动持久到文件
		 * 
		 * @param doc
		 * @param distFileName
		 */
		public void modifyFile(Document doc, String distFileName) {
			try {
				TransformerFactory tf = TransformerFactory.newInstance();
				Transformer tfer = tf.newTransformer();
				DOMSource dsource = new DOMSource(doc);
				StreamResult sr = new StreamResult(new File("student.xml"));
				tfer.transform(dsource, sr);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		/**
		 * 解析
		 * 
		 * @param fileName
		 */
		public void paseXml(String fileName) {
			Document document = getDocument(fileName);
			NodeList nodeList = document.getElementsByTagName("student");
			for (int i = 0; i < nodeList.getLength(); i++) {
				StringBuilder sb = new StringBuilder();
				sb.append("姓名："
						+ document.getElementsByTagName("name").item(i)
								.getFirstChild().getNodeValue());
				sb.append(" , ");
				sb.append("年龄："
						+ document.getElementsByTagName("age").item(i)
								.getFirstChild().getNodeValue());
				sb.append(" , ");
				sb.append("性别："
						+ document.getElementsByTagName("sex").item(i)
								.getFirstChild().getNodeValue());
				sb.append(" , ");
				sb.append("地址："
						+ document.getElementsByTagName("address").item(i)
								.getFirstChild().getNodeValue());

				System.out.println(sb.toString());
			}
		}

		/**
		 * 创建一个新的学生
		 * 
		 * @param name
		 * @param age
		 * @param sex
		 * @param address
		 */
		public void addNewNode(String name, String age, String sex,
				String address) {
			try {
				Document document = getDocument("student.xml");
				NodeList nodeList = document.getElementsByTagName("students");
				// 创建新的节点
				Node studentNode = document.createElement("student");
				Node nameNode = document.createElement("name");
				nameNode.appendChild(document.createTextNode(name));
				Node ageNode = document.createElement("age");
				ageNode.appendChild(document.createTextNode(age));
				Node sexNode = document.createElement("sex");
				sexNode.appendChild(document.createTextNode(sex));
				Node addressNode = document.createElement("address");
				addressNode.appendChild(document.createTextNode(address));
				// 添加节点
				studentNode.appendChild(nameNode);
				studentNode.appendChild(ageNode);
				studentNode.appendChild(sexNode);
				studentNode.appendChild(addressNode);
				nodeList.item(0).appendChild(studentNode);
				// 此时真正的处理将新数据添加到文件中（磁盘）
				TransformerFactory tf = TransformerFactory.newInstance();
				Transformer tfer = tf.newTransformer();
				DOMSource dsource = new DOMSource(document);
				StreamResult sr = new StreamResult(new File("student.xml"));
				tfer.transform(dsource, sr);
			} catch (Exception e) {
				e.printStackTrace();
			}

			paseXml("student.xml");
		}

		/**
		 * 删除一个节点
		 * 
		 * @param name
		 */
		public void deleteNode(String name) {
			Document document = getDocument("student.xml");

			NodeList nodeList = document.getElementsByTagName("name");
			for (int i = 0; i < nodeList.getLength(); i++) {
				String value = nodeList.item(i).getFirstChild()
						.getTextContent();
				if (name != null && name.equalsIgnoreCase(value)) {
					Node parentNode = nodeList.item(i).getParentNode();
					document.getFirstChild().removeChild(parentNode);
				}
			}
			modifyFile(document, "student.xml");
		}

		/**
		 * 根据name修改某个节点的内容
		 * 
		 * @param name
		 */
		public void updateNode(String name) {

			Document document = getDocument("student.xml");
			NodeList nodeList = document.getElementsByTagName("name");
			for (int i = 0; i < nodeList.getLength(); i++) {
				String value = nodeList.item(i).getFirstChild()
						.getTextContent();
				if (name != null && name.equalsIgnoreCase(value)) {
					Node parentNode = nodeList.item(i).getParentNode();
					NodeList nl = parentNode.getChildNodes();
					for (int j = 0; j < nl.getLength(); j++) {
						String modifyNode = nl.item(j).getNodeName();
						if (modifyNode.equalsIgnoreCase("age")) {
							nl.item(j).getFirstChild().setTextContent("25");
						}
					}
				}
			}
			modifyFile(document, "student.xml");
		}

	}

	/**
	 * 采用SAX方式
	 * 
	 * @author TigerLee
	 * 
	 */
	public class SAXForXml extends DefaultHandler {
		@SuppressWarnings("rawtypes")
		private Stack tags = new Stack();
		@SuppressWarnings("rawtypes")
		private Map contents = new LinkedHashMap();
		public int count = 0;

		@SuppressWarnings("unchecked")
		@Override
		public void characters(char[] ch, int start, int length)
				throws SAXException {
			String tag = (String) tags.peek();

			if ("name".equals(tag)) {
				String name = new String(ch, start, length);
				contents.put("name" + count, name);
			}
			if ("age".equals(tag)) {
				contents.put("age" + count, new String(ch, start, length));
			}
			if ("sex".equals(tag)) {
				contents.put("sex" + count, new String(ch, start, length));
			}
			if ("address".equals(tag)) {
				contents.put("address" + count, new String(ch, start, length));
			}
		}

		@SuppressWarnings("unchecked")
		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			if ("student".equals(qName)) {
				count++;
			}
			tags.push(qName);
		}

		@Override
		public void endElement(String uri, String localName, String qName)
				throws SAXException {
			tags.pop();
		}

		@SuppressWarnings("rawtypes")
		public Map getContents() {
			return contents;
		}
	}

	/**
	 * 采用JDOM方式
	 * 
	 * @author TigerLee
	 * 
	 */
	public class JDOMForXml {

	}

	/**
	 * 采用DOM4J方式
	 * 
	 * @author TigerLee
	 * 
	 */
	public class DOM4JForXml {
		/**
		 * 获取doc对象（org.dom4j.Document）
		 * 
		 * @param fileName
		 * @return
		 */
		public org.dom4j.Document getDocument(String fileName) {
			SAXReader sr = new SAXReader();
			org.dom4j.Document doc = null;
			try {

				doc = sr.read(new File(fileName));

			} catch (Exception e) {

				e.printStackTrace();
			}
			return doc;
		}

		/**
		 * 将文件保存到硬盘
		 * 
		 * @param doc
		 * @param fileName
		 */
		public void writeToFile(org.dom4j.Document doc, String fileName) {
			try {
				Writer writer = new FileWriter(fileName);
				OutputFormat format = OutputFormat.createPrettyPrint();
				format.setEncoding("UTF-8");
				XMLWriter xmlWriter = new XMLWriter(writer, format);
				xmlWriter.write(doc);
				xmlWriter.close();
				System.out.println(" 文件已经钝化！");
			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		/**
		 * 遍历xml文件输出节点值
		 * 
		 * @param fileName
		 */
		@SuppressWarnings("rawtypes")
		public void readXml(String fileName) {
			org.dom4j.Document doc = getDocument(fileName);
			List nameList = doc.selectNodes("/students/student/name");
			List ageList = doc.selectNodes("/students/student/age");
			List sexList = doc.selectNodes("/students/student/sex");
			List addressList = doc.selectNodes("/students/student/address");
			for (int i = 0; i < nameList.size(); i++) {
				StringBuilder sb = new StringBuilder();
				sb.append("name=" + ((Element) nameList.get(i)).getTextTrim());
				sb.append(",age=" + ((Element) ageList.get(i)).getTextTrim());
				sb.append(",sex=" + ((Element) sexList.get(i)).getTextTrim());
				sb.append(",address="
						+ ((Element) addressList.get(i)).getTextTrim());
				System.out.println(sb.toString());
			}

		}

		/**
		 * 根据姓名修改一个学生的信息
		 * 
		 * @param name
		 * @param newValue
		 * @param fileName
		 */
		@SuppressWarnings("rawtypes")
		public void updateXml(String name, Map newValue, String fileName) {
			org.dom4j.Document doc = getDocument(fileName);
			List nameList = doc.selectNodes("/students/student/name");
			Iterator iterator = nameList.iterator();

			while (iterator.hasNext()) {
				Element element = (Element) iterator.next();

				if (name != null && name.equals(element.getText())) {
					Element pe = element.getParent();
					List childList = pe.elements();
					for (int i = 0; i < childList.size(); i++) {
						Iterator valueSet = newValue.entrySet().iterator();
						while (valueSet.hasNext()) {
							Map.Entry entry = (Map.Entry) valueSet.next();
							String nodeName = ((Element) childList.get(i))
									.getName();
							String key = entry.getKey().toString();
							if (key != null && key.equals(nodeName)) {
								((Element) childList.get(i))
										.setText((String) entry.getValue());
							}
						}
					}
				}
			}
			writeToFile(doc, fileName);
		}

	}

}
