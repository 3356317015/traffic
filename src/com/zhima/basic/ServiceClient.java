package com.zhima.basic;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;

import net.sf.json.JSONObject;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.axis2.transport.http.HTTPConstants;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.util.DateUtils;
import com.zhima.util.DesUtils;

public class ServiceClient {
	public static String RequestService(String parameter,String methodName,
			String nameSpace,String serverAddress) throws UserBusinessException {
		DesUtils desUtils = new DesUtils();
		try {
			/*if (urlIsReach(SamFinal.ServerAddres)==false){
				throw new UserBusinessException("无法连接服务\r\n"+SamFinal.ServerAddres);
			}*/
			// 使用RPC方式调用WebService
			RPCServiceClient serviceClient = new RPCServiceClient();
	        Options options = serviceClient.getOptions(); 
	        //client.addHeader(new OME)
	        // 指定调用WebService的URL
	        EndpointReference end = new EndpointReference(serverAddress);  
	        options.setTo(end);
	        // 解决多次调用webservice后连接超时异常
	        options.setManageSession(true);
            options.setProperty(HTTPConstants.REUSE_HTTP_CLIENT,true);
            // 设置超时
            options.setTimeOutInMilliSeconds(60000L);
	        // 设置安全认证参数
	        Map<String, String> map = new HashMap<String, String>();
	        map.put("ClientUrl", CommFinal.CLIENT_ADDRESS);
	        map.put("ClientMac", CommFinal.CLIENT_MAC);
	        map.put("UserCode", CommFinal.CLIENT_USERCODE);
	        map.put("PassWord", CommFinal.CLIENT_PASSWORD);
	        map.put("Organize", CommFinal.organize.getOrganizeSeq());
	        map.put("OperUser", CommFinal.user.getUserCode());		
	        map.put("MethodName", methodName);
	        String security = JSONObject.fromObject(map).toString();
	        
	        if (null == parameter || parameter.length()<0){
	        	// 指定方法传进的参数值(加密)
	        	Object[] obj = new Object[1];
	        	obj[0] = desUtils.getEncString(security);
	        	// 指定方法返回值的数据类型的Class对象
	        	Class<?>[] classes = new Class[] { String.class };
	        	// 指定要调用的方法及WSDL文件的命名空间
	            QName qname = new QName(nameSpace, methodName); 
	            String result = (String) serviceClient.invokeBlocking(qname, obj, classes)[0];
		        // 解决多次调用webservice后连接超时异常
		        serviceClient.cleanupTransport(); 
		        return result;
	            //return desUtils.getDesString(result);
	        }else{
	        	// 指定方法传进的参数值(加密)
	        	Object[] obj = new Object[2]; 
	        	obj[0] = desUtils.getEncString(security);
	        	obj[1] = desUtils.getEncString(parameter);
	        	// 指定方法返回值的数据类型的Class对象
	        	Class<?>[] classes = new Class[] { String.class };    
	        	// 指定要调用的法及WSDL文件的命名空间
	            QName qname = new QName(nameSpace, methodName);
	            // 调用方法并输出该方法的返回值
	            System.out.println(methodName);
	            String result = (String) serviceClient.invokeBlocking(qname, obj, classes)[0];
		        // 解决多次调用webservice后连接超时异常
		        serviceClient.cleanupTransport(); 
		        return result;
	            //return desUtils.getDesString(result);
	        } 
	    } catch (AxisFault e) {
			e.printStackTrace();
			throw new UserBusinessException(e.getMessage());
	    } catch (Exception e) {
			e.printStackTrace();
			throw new UserBusinessException(e.getMessage());
		}
	}
	
	/**
	 * 判断URL是否可达
	 * @param url 待判断的URL
	 * @return true: 可达 false: 不可达
	 */
	public static boolean urlIsReach(String url) {
		if (url==null) {
			return false;
		}
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setUseCaches(true);
			if (HttpURLConnection.HTTP_OK==connection.getResponseCode()) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}
	
	public static void main(String[] args) {
		System.out.println(DateUtils.getNow());
		System.out.println(ServiceClient.urlIsReach("http://127.0.0.1:8080/axis2/services/StationSamServer?wsdl"));
		System.out.println(DateUtils.getNow());
		
		
		
//		List<String> strings = new ArrayList<String>();
//		strings.add(DriverFinal.OPER_CODE);
//		strings.add(DriverFinal.OPER_ADDRESS);
//		strings.add("渝A1G219");
//		SelfServer selfServer = new SelfServer();
//		try {
//			selfServer.driverSelfHelp(strings, "QuerySafecarInfo");
//			XMLResult result = XMLParser.getInstance().parseXML(strings.toString(), new NDSafecar().getClass());
//	        System.out.println("aaaaa");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
		
		/*try {
			RPCServiceClient client = new RPCServiceClient();  
	        Options options = client.getOptions(); 
	        String url = DriverFinal.SELF_SERVER_ADDRESS;
	        EndpointReference end = new EndpointReference(url);  
	        options.setTo(end);  
	        	Object[] obj = new Object[]{DriverFinal.OPER_CODE,DriverFinal.OPER_ADDRESS,"渝A1G219","car"};
		        Class<?>[] classes = new Class[] { String.class };
		        QName qname = new QName(DriverFinal.URL, "QueryImage"); 
		        String string = (String) client.invokeBlocking(qname, obj, classes)[0];
		        System.out.println(string);
		        byte[] bs = ImageUtil.decode(string);
				File file = new File(DriverFinal.CAR_CER_REG_IMAGE);
				//建立输出字节流
				FileOutputStream fos = new FileOutputStream(file);
	
				//用FileOutputStream 的write方法写入字节数组
				fos.write(bs);
				System.out.println("写入成功");
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		
		
//		if(ModelStatic.DRIVER != null){
//			NDDriver ndDriver = ModelStatic.DRIVER;
//			 String jsonString = JSON.toJSONString(ndDriver);
//			 strings.add(jsonString);
//			 strings.add(ModelStatic.DRIVER.getDriverSeq());
//			 strings.add(ModelStatic.DRIVER.getIdentityId());
//			 strings.add(ModelStatic.DRIVER.getDriverName());
//			 strings.add("1");
//		}
//			 
//		if(ModelStatic.GUOQI_NDCARINFOS != null && ModelStatic.GUOQI_NDCARINFOS.size()>0){
//			 strings.add("不合格");
//		}else{
//			strings.add("合格");
//		}
//			 strings.add("");
//		try {
//					String retString = selfServer.driverSelfHelp(strings, "CheckCer");
		
//		try {
//			RPCServiceClient client = new RPCServiceClient();  
//	        Options options = client.getOptions(); 
//	        String url = "http://127.0.0.1:8080/axis2/services/SelfHelpService?wsdl";
//	        EndpointReference end = new EndpointReference(url);  
//	        options.setTo(end);  
//	        
//	        Object[] obj = new Object[]{"500115","192.168.1.118","51052519941004116X","dirver"};
//	        Class<?>[] classes = new Class[] { String.class };
//	        QName qname = new QName("http://selfhelp.webservice.netSale.com", "QueryImage"); 
//	        //XMLResult result = XMLParser.getInstance().parseXML((String)client.invokeBlocking(qname, obj, classes)[0], new ModelUtil().getClass());
//	        
//	        
//	        //XMLResult result = XMLParser.getInstance().parseXML(client.invokeBlocking(qname, obj, classes)[0], null);
//	        String string = (String) client.invokeBlocking(qname, obj, classes)[0];
//	        System.out.println(string);
//	        byte[] bs = ImageUtil.decode(string);
//			File file = new File("E:\\ddddd\\zzzzz.jpg");
//			//建立输出字节流
//			FileOutputStream fos = new FileOutputStream(file);
//
//			//用FileOutputStream 的write方法写入字节数组
//			fos.write(bs);
//			System.out.println("写入成功");
//
//			//为了节省IO流的开销，需要关闭
//			fos.close();
//	        //XMLResult result = XMLParser.getInstance().parseXML(string, new NDDriver().getClass());
//	        System.out.println("aaaaa");
//	        
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		///9j/4AAQSkZJRgABAQEA1gDWAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0a HBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIy MjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAeYCiADASIA AhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQA AAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3 ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWm p6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEA AwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSEx BhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElK U1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3 uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDx/cFb ockdKXGFxgA46+1KH2vkcEjpUbli3OBxwc9sV7F9Djcrgw+6VACkcgGoePu8896czcHD+1NbGB0+ hqb6jQjYHPXnBpoDDOO54xyacWU4HI75z/SkAV+MkE9DngVPQaGvnlWQknrS/wDLPGMZ6AmkYbCE BzxQWLqCV5HU4pegxoIBBz19qX5SR8ozjqKQHcxOOp5FJgA4x1HBz3oYbC5xIMZweuT7UEbUYKNw 6nvR1GX7jinYwhyTgD0+lT6DWwfNkDA9KeF+VhuGcYzmmfICdvAPIGOlOLfLtUdxyKEtCXYbKEI4 XaOvWsxiC5I6E1qOrFc57YHNZR61hiNkaUncKKKK5TYUEjp3pKX0pKACiiigAooooAKKKKACiiig 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...
		///9j/4AAQSkZJRgABAQEA1gDWAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0a HBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIy MjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAeYCiADASIA AhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQA AAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3 ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWm p6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEA AwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSEx BhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElK U1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3 uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDx/cFb ockdKXGFxgA46+1KH2vkcEjpUbli3OBxwc9sV7F9Djcrgw+6VACkcgGoePu8896czcHD+1NbGB0+ hqb6jQjYHPXnBpoDDOO54xyacWU4HI75z/SkAV+MkE9DngVPQaGvnlWQknrS/wDLPGMZ6AmkYbCE BzxQWLqCV5HU4pegxoIBBz19qX5SR8ozjqKQHcxOOp5FJgA4x1HBz3oYbC5xIMZweuT7UEbUYKNw 6nvR1GX7jinYwhyTgD0+lT6DWwfNkDA9KeF+VhuGcYzmmfICdvAPIGOlOLfLtUdxyKEtCXYbKEI4 XaOvWsxiC5I6E1qOrFc57YHNZR61hiNkaUncKKKK5TYUEjp3pKX0pKACiiigAooooAKKKKACiiig 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...
		
		
//		String jsonstring = "{\"driverCer\":\"1\",\"driverName\":\"lsm\",\"driverSeq\":\"50011500000000000221\",\"idNumber\":\"51052519941004116X\"}";
//		List<SafeDriver> group2 = (List<SafeDriver>) JSON.parseArray(jsonstring, SafeDriver.class); 
//		 ArrayList<Student> list2 = JSON.parseObject(jsonText, new TypeReference<ArrayList<Student>>(){}); 
//	     System.out.println("users2:" + group2);
	}
	

}
