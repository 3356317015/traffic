package test; 



public class TestXml{ 
	public static void main(String[] args) {
		testDomForXml();
	}

	public static void testDomForXml(){ 

		HandleXML handleXml = new HandleXML(); 
		HandleXML.DOMForXml domForXml = handleXml.new DOMForXml(); 
		//解析 
		//domForXml.paseXml("student.xml");
		// 新增 
		//domForXml.addNewNode("Sun", "20", "Man", "USA"); 
		//删除 
		//domForXml.deleteNode("26"); 
		domForXml.updateNode("TigerLee"); 
		} 
}


