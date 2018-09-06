package com.zhima.frame.action.sam.impl;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.traffic.business.sam.INetSamUpinfo;
import com.service.traffic.business.sam.impl.ImpNetSamUpinfo;
import com.zhima.basic.CommFinal;
import com.zhima.basic.SamFinal;
import com.zhima.basic.ServiceClient;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.action.sam.ISamUpinfo;
import com.zhima.frame.model.SamUpinfo;
import com.zhima.util.MapUtil;

public class ImpSamUpinfo implements ISamUpinfo {

	@SuppressWarnings("rawtypes")
	@Override
	public SamUpinfo insert(String fileVer,String remark,Map<String, Object> config)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamUpinfo iNetSamUpinfo = new ImpNetSamUpinfo();
				return iNetSamUpinfo.insert(fileVer, remark, config);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("fileVer", fileVer);
				map.put("remark", remark);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.SamUpinfo_Insert,
						SamFinal.ServerNamespace,SamFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	SamUpinfo newSamUpinfo =(SamUpinfo)JsonUtil.convertObject(
			    			mapResult.get("samUpinfo").toString(), SamUpinfo.class);
			    	return newSamUpinfo;
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
