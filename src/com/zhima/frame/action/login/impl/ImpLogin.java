package com.zhima.frame.action.login.impl;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.traffic.business.login.INetLogin;
import com.service.traffic.business.login.impl.ImpNetLogin;
import com.zhima.basic.CommFinal;
import com.zhima.basic.SamFinal;
import com.zhima.basic.ServiceClient;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.action.login.ILogin;
import com.zhima.frame.model.SamUser;
import com.zhima.util.MapUtil;

/**
 * ImpLogin概要说明：用户登录接口
 * @author lcy
 */
public class ImpLogin implements ILogin {	
	/**
	 * 用户登录
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void login(String organizeSeq, String userCode,String password, String sysVer, String loginIp,
			Map<String, Object> config) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetLogin iNetLogin = new ImpNetLogin();
				CommFinal.user = iNetLogin.login(organizeSeq, userCode, password, sysVer, loginIp, config);
				return;
			case 2:
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				map.put("userCode", userCode);
				map.put("password", password);
				map.put("sysVer", sysVer);
				map.put("loginIp", loginIp);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.Sam_Login,
						SamFinal.ServerNamespace,SamFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	CommFinal.user =(SamUser)JsonUtil.convertObject(mapResult.get("user").toString(), SamUser.class);
			    	return;
			    }else{
			    	throw new UserBusinessException(String.valueOf(mapResult.get("Message")));
			    }
			}
		} catch (Exception e) {
			throw new UserBusinessException(e.getMessage());
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void close(String onlineSeq, String organizeSeq, String userCode, String userName,
			Map<String, Object> config) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetLogin iNetLogin = new ImpNetLogin();
				iNetLogin.close(onlineSeq, organizeSeq, userCode, userName, config);
				return;
			case 2:
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("onlineSeq", onlineSeq);
				map.put("organizeSeq", organizeSeq);
				map.put("userCode", userCode);
				map.put("userName", userName);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.Sam_Close,
						SamFinal.ServerNamespace,SamFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return;
			    }else{
			    	throw new UserBusinessException(String.valueOf(mapResult.get("Message")));
			    }
			}
		} catch (Exception e) {
			throw new UserBusinessException(e.getMessage());
		} 
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void update(String onlineSeq)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetLogin iNetLogin = new ImpNetLogin();
				iNetLogin.update(onlineSeq);
				return;
			case 2:
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("onlineSeq", onlineSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.Sam_Update,
						SamFinal.ServerNamespace,SamFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return;
			    }else{
			    	throw new UserBusinessException(String.valueOf(mapResult.get("Message")));
			    }
			}
		} catch (Exception e) {
			throw new UserBusinessException(e.getMessage());
		} 
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String getServerTime() throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetLogin iNetLogin = new ImpNetLogin();
				return iNetLogin.getServerTime();
			case 2: 
				//1.参数对象为JSONObject
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(null, SamFinal.Sam_GetServerTime,
						SamFinal.ServerNamespace,SamFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return String.valueOf(mapResult.get("serverTime"));
			    }else{
			    	throw new UserBusinessException(String.valueOf(mapResult.get("Message")));
			    }
			}
			return null;
		} catch (Exception e) {
			throw new UserBusinessException(e.getMessage());
		}
	}

}