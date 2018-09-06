package com.zhima.frame.action.sam.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.traffic.business.sam.INetSamUserSecret;
import com.service.traffic.business.sam.impl.ImpNetSamUserSecret;
import com.zhima.basic.CommFinal;
import com.zhima.basic.SamFinal;
import com.zhima.basic.ServiceClient;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.action.sam.ISamUserSecret;
import com.zhima.frame.model.SamUserSecret;
import com.zhima.util.MapUtil;

public class ImpSamUserSecret implements ISamUserSecret {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<SamUserSecret> queryByGridSecret(String className,
			String gridName) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamUserSecret iNetSamUserSecret = new ImpNetSamUserSecret();
				return iNetSamUserSecret.queryByGridSecret(className, gridName);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("className", className);
				map.put("gridName", gridName);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.SamUserSecret_QueryByGridSecret,
						SamFinal.ServerNamespace,SamFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
		    		return (List<SamUserSecret>)JsonUtil.convertArray(
		    				mapResult.get("samUserSecrets").toString(), new SamUserSecret());
			    }else{
			    	throw new UserBusinessException(String.valueOf(mapResult.get("Message")));
			    }
			}
			return null; 
		} catch (Exception e) {
			throw new UserBusinessException(e.getMessage());
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void updateUserSecret(List<SamUserSecret> secrets,String className,String gridName,
			Map<String, Object> config) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamUserSecret iNetSamUserSecret = new ImpNetSamUserSecret();
				iNetSamUserSecret.updateUserSecret(secrets, className, gridName, config);
				return;
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("samUserSecrets", secrets);
				map.put("className", className);
				map.put("gridName", gridName);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.SamUserSecret_UpdateUserSecret,
						SamFinal.ServerNamespace,SamFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return;
			    }else{
			    	throw new UserBusinessException(String.valueOf(mapResult.get("Message")));
			    }
			}
			return; 
		} catch (Exception e) {
			throw new UserBusinessException(e.getMessage());
		}
	}

}
