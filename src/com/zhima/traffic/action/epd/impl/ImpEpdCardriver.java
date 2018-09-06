package com.zhima.traffic.action.epd.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.traffic.business.epd.INetEpdCardriver;
import com.service.traffic.business.epd.impl.ImpNetEpdCardriver;
import com.zhima.basic.CommFinal;
import com.zhima.basic.EpdFinal;
import com.zhima.basic.ServiceClient;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.action.epd.IEpdCardriver;
import com.zhima.traffic.model.EpdCardriver;
import com.zhima.util.MapUtil;

public class ImpEpdCardriver implements IEpdCardriver {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<EpdCardriver> queryByCarSeq(String carSeq) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdCardriver iNetEpdCardriver = new ImpNetEpdCardriver();
				return iNetEpdCardriver.queryByCarSeq(carSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("carSeq", carSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdCardriver_QueryByCarSeq,
						EpdFinal.ServerNamespace,EpdFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<EpdCardriver>)JsonUtil.convertArray(
			    			mapResult.get("epdCardrivers").toString(), new EpdCardriver());
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