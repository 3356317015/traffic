package com.zhima.traffic.action.epd.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.traffic.business.epd.INetEpdPlanservice;
import com.service.traffic.business.epd.impl.ImpNetEpdPlanservice;
import com.zhima.basic.CommFinal;
import com.zhima.basic.EpdFinal;
import com.zhima.basic.ServiceClient;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.action.epd.IEpdPlanservice;
import com.zhima.traffic.model.EpdPlanservice;
import com.zhima.util.MapUtil;

public class ImpEpdPlanservice implements IEpdPlanservice {

	@SuppressWarnings("rawtypes")
	@Override
	public EpdPlanservice insert(EpdPlanservice epdPlanservice,Map<String, Object> config)throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdPlanservice iNetEpdPlanservice = new ImpNetEpdPlanservice();
				return iNetEpdPlanservice.insert(epdPlanservice, config);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("epdPlanservice", epdPlanservice);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdPlanservice_Insert,
						EpdFinal.ServerNamespace,EpdFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (EpdPlanservice)JsonUtil.convertObject(
			    			mapResult.get("epdPlanservice").toString(), EpdPlanservice.class);
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
	public void update(EpdPlanservice epdPlanservice,Map<String, Object> config)throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdPlanservice iNetEpdPlanservice = new ImpNetEpdPlanservice();
				iNetEpdPlanservice.update(epdPlanservice, config);
				return;
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("epdPlanservice", epdPlanservice);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdPlanservice_Update,
						EpdFinal.ServerNamespace,EpdFinal.ServerAddres);
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

	@SuppressWarnings("rawtypes")
	@Override
	public void delete(List<EpdPlanservice> planservices,Map<String, Object> config)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdPlanservice iNetEpdPlanservice = new ImpNetEpdPlanservice();
				iNetEpdPlanservice.delete(planservices, config);
				return;
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("epdPlanservices", planservices);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdPlanservice_Delete,
						EpdFinal.ServerNamespace,EpdFinal.ServerAddres);
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
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<EpdPlanservice> queryByPK(String planserviceSeq) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdPlanservice iNetEpdPlanservice = new ImpNetEpdPlanservice();
				return iNetEpdPlanservice.queryByPK(planserviceSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("planserviceSeq", planserviceSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdPlanservice_QueryByPK,
						EpdFinal.ServerNamespace,EpdFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<EpdPlanservice>)JsonUtil.convertArray(
			    			mapResult.get("epdPlanservices").toString(), new EpdPlanservice());
			    }else{
			    	throw new UserBusinessException(String.valueOf(mapResult.get("Message")));
			    }
			}
			return null;
		} catch (Exception e) {
			throw new UserBusinessException(e.getMessage());
		}
	}
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<EpdPlanservice> queryByPlanSeq(String planSeq)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdPlanservice iNetEpdPlanservice = new ImpNetEpdPlanservice();
				return iNetEpdPlanservice.queryByPlanSeq(planSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("planSeq", planSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdPlanservice_QueryByPlanSeq,
						EpdFinal.ServerNamespace,EpdFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<EpdPlanservice>)JsonUtil.convertArray(
			    			mapResult.get("epdPlanservices").toString(), new EpdPlanservice());
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