package com.zhima.traffic.action.operate.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.traffic.business.operate.INetItsLiner;
import com.service.traffic.business.operate.impl.ImpNetItsLiner;
import com.zhima.basic.CommFinal;
import com.zhima.basic.ItsFinal;
import com.zhima.basic.ServiceClient;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.action.operate.IItsLiner;
import com.zhima.traffic.model.ItsLiner;
import com.zhima.traffic.model.ItsLinercheck;
import com.zhima.traffic.model.ItsLinerdeal;
import com.zhima.traffic.model.ItsLinerfare;
import com.zhima.traffic.model.ItsLinerseat;
import com.zhima.traffic.model.ItsLinerservice;
import com.zhima.traffic.model.ItsLinerstation;
import com.zhima.util.MapUtil;

public class ImpItsLiner implements IItsLiner {

	@SuppressWarnings("rawtypes")
	@Override
	public ItsLiner insert(ItsLiner itsLiner,
			List<ItsLinerstation> itsLinerstations,
			List<ItsLinerseat> itsLinerseats,
			List<ItsLinerfare> itsLinerfares,
			List<ItsLinercheck> itsLinerchecks,
			List<ItsLinerdeal> itsLinerdeals,
			List<ItsLinerservice> itsLinerservices,
			Map<String, Object> config)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetItsLiner iNetItsLiner = new ImpNetItsLiner();
				return iNetItsLiner.insert(itsLiner, itsLinerstations, itsLinerseats,
						itsLinerfares, itsLinerchecks, itsLinerdeals,itsLinerservices, config);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("itsLiner", itsLiner);
				map.put("itsLinerstations", itsLinerstations);
				map.put("itsLinerseats", itsLinerseats);
				map.put("itsLinerfares", itsLinerfares);
				map.put("itsLinerchecks", itsLinerchecks);
				map.put("itsLinerdeals", itsLinerdeals);
				map.put("itsLinerservices", itsLinerservices);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, ItsFinal.ItsLiner_Insert,
						ItsFinal.ServerNamespace,ItsFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (ItsLiner)JsonUtil.convertObject(
			    			mapResult.get("itsLiner").toString(), ItsLiner.class);
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
	public void updateLiner(ItsLiner itsLiner, Map<String, Object> config)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetItsLiner iNetItsLiner = new ImpNetItsLiner();
				iNetItsLiner.updateLiner(itsLiner, config);
				return;
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("itsLiner", itsLiner);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, ItsFinal.ItsLiner_UpdateLiner,
						ItsFinal.ServerNamespace,ItsFinal.ServerAddres);
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
	public void delete(List<ItsLiner> itsLiners, Map<String, Object> config)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetItsLiner iNetItsLiner = new ImpNetItsLiner();
				iNetItsLiner.delete(itsLiners, config);
				return;
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("itsLiners", itsLiners);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, ItsFinal.ItsLiner_Delete,
						ItsFinal.ServerNamespace,ItsFinal.ServerAddres);
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
	public List<ItsLiner> queryByPK(String linerSeq)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetItsLiner iNetItsLiner = new ImpNetItsLiner();
				return iNetItsLiner.queryByPK(linerSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("linerSeq", linerSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, ItsFinal.ItsLiner_QueryByPK,
						ItsFinal.ServerNamespace,ItsFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<ItsLiner>)JsonUtil.convertArray(
			    			mapResult.get("itsLiners").toString(), new ItsLiner());
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
	public List<ItsLiner> queryPageByCustom(String organizeSeq,
			String routeSeq, String stationSeq, String linerId,
			String cargradeSeq, String linerStatus, String ifReport,
			String startDate, String limitDate, int start, int limit)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetItsLiner iNetItsLiner = new ImpNetItsLiner();
				return iNetItsLiner.queryPageByCustom(organizeSeq, routeSeq, stationSeq,
						linerId, cargradeSeq, linerStatus,
						ifReport, startDate, limitDate, start, limit);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				map.put("routeSeq", routeSeq);
				map.put("stationSeq", stationSeq);
				map.put("linerId", linerId);
				map.put("cargradeSeq", cargradeSeq);
				map.put("linerStatus", linerStatus);
				map.put("ifReport", ifReport);
				map.put("startDate", startDate);
				map.put("limitDate", limitDate);
				map.put("start", start);
				map.put("limit", limit);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, ItsFinal.ItsLiner_QueryPageByCustom,
						ItsFinal.ServerNamespace,ItsFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<ItsLiner>)JsonUtil.convertArray(
			    			mapResult.get("itsLiners").toString(), new ItsLiner());
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
	public int queryCountByCustom(String organizeSeq,
			String routeSeq, String stationSeq, String linerId,
			String cargradeSeq, String linerStatus, String ifReport,
			String startDate, String limitDate)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetItsLiner iNetItsLiner = new ImpNetItsLiner();
				return iNetItsLiner.queryCountByCustom(organizeSeq, routeSeq, stationSeq,
						linerId, cargradeSeq, linerStatus,
						ifReport, startDate, limitDate);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				map.put("routeSeq", routeSeq);
				map.put("stationSeq", stationSeq);
				map.put("linerId", linerId);
				map.put("cargradeSeq", cargradeSeq);
				map.put("linerStatus", linerStatus);
				map.put("ifReport", ifReport);
				map.put("startDate", startDate);
				map.put("limitDate", limitDate);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, ItsFinal.ItsLiner_QueryCountByCustom,
						ItsFinal.ServerNamespace,ItsFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return Integer.valueOf(mapResult.get("count").toString());
			    }else{
			    	throw new UserBusinessException(String.valueOf(mapResult.get("Message")));
			    }
			}
			return 0;
		} catch (Exception e) {
			throw new UserBusinessException(e.getMessage());
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void updateAttribute(ItsLiner itsLiner, Map<String, Object> config)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetItsLiner iNetItsLiner = new ImpNetItsLiner();
				iNetItsLiner.updateAttribute(itsLiner, config);
				return;
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("itsLiner", itsLiner);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, ItsFinal.ItsLiner_UpdateAttribute,
						ItsFinal.ServerNamespace,ItsFinal.ServerAddres);
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

	

}