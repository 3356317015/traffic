package com.zhima.traffic.action.operate.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.traffic.business.operate.INetItsLinerservice;
import com.service.traffic.business.operate.impl.ImpNetItsLinerservice;
import com.zhima.basic.CommFinal;
import com.zhima.basic.ItsFinal;
import com.zhima.basic.ServiceClient;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.action.operate.IItsLinerservice;
import com.zhima.traffic.model.ItsLiner;
import com.zhima.traffic.model.ItsLinerservice;
import com.zhima.util.MapUtil;

public class ImpItsLinerservice implements IItsLinerservice {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<ItsLinerservice> queryByLinerSeq(String linerSeq)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetItsLinerservice iNetItsLinerservice = new ImpNetItsLinerservice();
				return iNetItsLinerservice.queryByLinerSeq(linerSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("linerSeq", linerSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, ItsFinal.ItsLinerservice_QueryByLinerSeq,
						ItsFinal.ServerNamespace,ItsFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<ItsLinerservice>)JsonUtil.convertArray(
			    			mapResult.get("itsLinerservices").toString(), new ItsLinerservice());
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
	public void updateAttribute(ItsLiner itsLiner, List<ItsLinerservice> itsLinerservices,Map<String, Object> config)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetItsLinerservice iNetItsLinerservice = new ImpNetItsLinerservice();
				iNetItsLinerservice.updateAttribute(itsLiner, itsLinerservices, config);
				return;
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("itsLiner", itsLiner);
				map.put("itsLinerservices", itsLinerservices);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, ItsFinal.ItsLinerservice_UpdateAttribute,
						ItsFinal.ServerNamespace,ItsFinal.ServerAddres);
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