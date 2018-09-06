package com.service.traffic.action.sam;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.service.comm.SecurityFinal;
import com.service.traffic.business.login.INetLogin;
import com.service.traffic.business.login.impl.ImpNetLogin;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.model.SamUser;

/**
 * ImpEpdCar概要说明：车辆接口实现
 * @author lcy
 */
public class SamLoginAction {
	INetLogin iNetLogin = new ImpNetLogin();
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String login(String security,String parameter){
		Map<String, String> result = new HashMap<String, String>();
		result.put("ResultCode", "0");
		result.put("Message", "操作成功");
		try {
			//1.安全认证
			SecurityFinal.certification(security);
			//2.解析parameter
			String strParameter = JSONObject.fromObject(parameter).toString();
	        JSONObject jsonParameter = JSONObject.fromObject(strParameter);
	        Map mapParameter = (Map)jsonParameter;
	        String organizeSeq = mapParameter.get("organizeSeq").toString();
	        String userCode = mapParameter.get("userCode").toString();
	        String password = mapParameter.get("password").toString();
	        String sysVer = mapParameter.get("sysVer").toString();
	        String loginIp = mapParameter.get("loginIp").toString();

	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
	        SamUser samUser = iNetLogin.login(organizeSeq, userCode, password, sysVer, loginIp, mapConfig);
	        result.put("user", JSONObject.fromObject(samUser).toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String close(String security,String parameter){
		Map<String, String> result = new HashMap<String, String>();
		result.put("ResultCode", "0");
		result.put("Message", "操作成功");
		try {
			//1.安全认证
			SecurityFinal.certification(security);
			//2.解析parameter
			String strParameter = JSONObject.fromObject(parameter).toString();
	        JSONObject jsonParameter = JSONObject.fromObject(strParameter);
	        Map mapParameter = (Map)jsonParameter;
	        String onlineSeq = mapParameter.get("onlineSeq").toString();
	        String organizeSeq = mapParameter.get("organizeSeq").toString();
	        String userCode = mapParameter.get("userCode").toString();
	        String userName = mapParameter.get("userName").toString();
	        
	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
	        iNetLogin.close(onlineSeq, organizeSeq, userCode, userName, mapConfig);
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}

	@SuppressWarnings("rawtypes")
	public String update(String security,String parameter){
		Map<String, String> result = new HashMap<String, String>();
		result.put("ResultCode", "0");
		result.put("Message", "操作成功");
		try {
			//1.安全认证
			SecurityFinal.certification(security);
			//2.解析parameter
			String strParameter = JSONObject.fromObject(parameter).toString();
	        JSONObject jsonParameter = JSONObject.fromObject(strParameter);
	        Map mapParameter = (Map)jsonParameter;
	        String onlineSeq = mapParameter.get("onlineSeq").toString();
	        //3.调用业务
	        iNetLogin.update(onlineSeq);
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}

	public String getServerTime(String security){
		Map<String, String> result = new HashMap<String, String>();
		result.put("ResultCode", "0");
		result.put("Message", "操作成功");
		try {
			//1.安全认证
			SecurityFinal.certification(security);
			//2.解析parameter
	        //3.调用业务
	        String serverTime = iNetLogin.getServerTime();
	        result.put("serverTime", serverTime);
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
}