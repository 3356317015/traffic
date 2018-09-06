package com.zhima.traffic.action.epd.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.traffic.business.epd.INetEpdRouteService;
import com.service.traffic.business.epd.impl.ImpNetEpdRouteService;
import com.zhima.basic.CommFinal;
import com.zhima.basic.EpdFinal;
import com.zhima.basic.ServiceClient;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.action.epd.IEpdRouteService;
import com.zhima.traffic.model.EpdRouteservice;
import com.zhima.util.MapUtil;

/**
 * ImpEpdRouteStation概要说明：线路站点
 * @author lcy
 */
public class ImpEpdRouteService implements IEpdRouteService {

	@SuppressWarnings("rawtypes")
	@Override
	public EpdRouteservice insert(EpdRouteservice epdRouteservice,Map<String, Object> config)throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdRouteService iNetEpdRouteService = new ImpNetEpdRouteService();
				return iNetEpdRouteService.insert(epdRouteservice, config);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("epdRouteservice", epdRouteservice);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdRouteService_Insert,
						EpdFinal.ServerNamespace,EpdFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (EpdRouteservice)JsonUtil.convertObject(
			    			mapResult.get("epdRouteservice").toString(), EpdRouteservice.class);
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
	public void update(EpdRouteservice epdRouteservice,Map<String, Object> config)throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdRouteService iNetEpdRouteService = new ImpNetEpdRouteService();
				iNetEpdRouteService.update(epdRouteservice, config);
				return;
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("epdRouteservice", epdRouteservice);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdRouteService_Update,
						EpdFinal.ServerNamespace,EpdFinal.ServerAddres);
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
	public void delete(List<EpdRouteservice> epdRouteservices,Map<String, Object> config)throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdRouteService iNetEpdRouteService = new ImpNetEpdRouteService();
				iNetEpdRouteService.delete(epdRouteservices, config);
				return;
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("epdRouteservices", epdRouteservices);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdRouteService_Delete,
						EpdFinal.ServerNamespace,EpdFinal.ServerAddres);
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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<EpdRouteservice> queryByPK(String routeserviceSeq) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdRouteService iNetEpdRouteService = new ImpNetEpdRouteService();
				return iNetEpdRouteService.queryByPK(routeserviceSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("routeserviceSeq", routeserviceSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdRouteService_QueryByPK,
						EpdFinal.ServerNamespace,EpdFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<EpdRouteservice>)JsonUtil.convertArray(
			    			mapResult.get("epdRouteservices").toString(), new EpdRouteservice());
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
	public List<EpdRouteservice> queryByRouteSeq(String routeSeq) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdRouteService iNetEpdRouteService = new ImpNetEpdRouteService();
				return iNetEpdRouteService.queryByRouteSeq(routeSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("routeSeq", routeSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdRouteService_QueryByRouteSeq,
						EpdFinal.ServerNamespace,EpdFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<EpdRouteservice>)JsonUtil.convertArray(
			    			mapResult.get("epdRouteservices").toString(), new EpdRouteservice());
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