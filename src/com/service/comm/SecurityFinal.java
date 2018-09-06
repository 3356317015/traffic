package com.service.comm;

import java.util.Map;

import net.sf.json.JSONObject;


/**
 * ConstantFinal概要说明：参数
 * @author lcy
 */
public class SecurityFinal {

	
	@SuppressWarnings({ "rawtypes" })
	public static String certification(String security){
		String strSecurity = JSONObject.fromObject(security).toString();
		JSONObject jsonSecurity = JSONObject.fromObject(strSecurity);
		Map mapSecurity = (Map)jsonSecurity;
		System.out.println("MethodName:" + mapSecurity.get("MethodName"));
		System.out.println("ClientUrl:" + mapSecurity.get("ClientUrl"));
		System.out.println("ClientMac:" + mapSecurity.get("ClientMac"));
		System.out.println("UserCode:" + mapSecurity.get("UserCode"));
		System.out.println("PassWord:" + mapSecurity.get("PassWord"));
		System.out.println("Organize:" + mapSecurity.get("Organize"));
		System.out.println("OperUser:" + mapSecurity.get("OperUser"));
		return "";
	}

}
