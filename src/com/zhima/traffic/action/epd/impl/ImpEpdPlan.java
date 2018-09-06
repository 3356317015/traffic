package com.zhima.traffic.action.epd.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.traffic.business.epd.INetEpdPlan;
import com.service.traffic.business.epd.impl.ImpNetEpdPlan;
import com.zhima.basic.CommFinal;
import com.zhima.basic.EpdFinal;
import com.zhima.basic.ServiceClient;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.action.epd.IEpdPlan;
import com.zhima.traffic.model.EpdCheckgate;
import com.zhima.traffic.model.EpdPlan;
import com.zhima.traffic.model.EpdPlandeal;
import com.zhima.traffic.model.EpdPlanservice;
import com.zhima.util.MapUtil;
import com.zhima.widget.seatBar.Seat;
import com.zhima.widget.stationBar.Station;

public class ImpEpdPlan implements IEpdPlan {

	@SuppressWarnings("rawtypes")
	@Override
	public EpdPlan insert(EpdPlan epdPlan,List<EpdCheckgate> checkgates,List<Station> stations,
			List<Seat> seats,List<EpdPlandeal> plandeals,List<EpdPlanservice> planservices,
			Map<String, Object> config)throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdPlan iNetEpdPlan = new ImpNetEpdPlan();
				return iNetEpdPlan.insert(epdPlan, checkgates, stations, seats, plandeals,
						planservices, config);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("epdPlan", epdPlan);
				map.put("epdCheckgates", checkgates);
				map.put("stations", stations);
				map.put("seats", seats);
				map.put("epdPlandeals", plandeals);
				map.put("epdPlanservices", planservices);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdPlan_Insert,
						EpdFinal.ServerNamespace,EpdFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (EpdPlan)JsonUtil.convertObject(
			    			mapResult.get("epdPlan").toString(), EpdPlan.class);
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
	public void update(EpdPlan epdPlan,List<EpdCheckgate> checkgates,List<Station> stations,
			List<Seat> seats,List<EpdPlandeal> plandeals,List<EpdPlanservice> planservices,
			Map<String, Object> config)throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdPlan iNetEpdPlan = new ImpNetEpdPlan();
				iNetEpdPlan.update(epdPlan, checkgates, stations, seats, plandeals,
						planservices, config);
				return;
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("epdPlan", epdPlan);
				map.put("epdCheckgates", checkgates);
				map.put("stations", stations);
				map.put("seats", seats);
				map.put("epdPlandeals", plandeals);
				map.put("epdPlanservices", planservices);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdPlan_Update,
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
	public void delete(List<EpdPlan> epdPlans,Map<String, Object> config)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdPlan iNetEpdPlan = new ImpNetEpdPlan();
				iNetEpdPlan.delete(epdPlans, config);
				return;
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("epdPlans", epdPlans);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdPlan_Delete,
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
	public List<EpdPlan> queryByPK(String planSeq) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdPlan iNetEpdPlan = new ImpNetEpdPlan();
				return iNetEpdPlan.queryByPK(planSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("planSeq", planSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdPlan_QueryByPK,
						EpdFinal.ServerNamespace,EpdFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<EpdPlan>)JsonUtil.convertArray(
			    			mapResult.get("epdPlans").toString(), new EpdPlan());
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
	public List<EpdPlan> queryByRouteSeq(String routeSeq)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdPlan iNetEpdPlan = new ImpNetEpdPlan();
				return iNetEpdPlan.queryByRouteSeq(routeSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("routeSeq", routeSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdPlan_QueryByRouteSeq,
						EpdFinal.ServerNamespace,EpdFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<EpdPlan>)JsonUtil.convertArray(
			    			mapResult.get("epdPlans").toString(), new EpdPlan());
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
	public List<EpdPlan> queryByAll() throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdPlan iNetEpdPlan = new ImpNetEpdPlan();
				return iNetEpdPlan.queryByAll();
			case 2: 
				//1.参数对象为JSONObject
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(null, EpdFinal.EpdPlan_QueryByAll,
						EpdFinal.ServerNamespace,EpdFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<EpdPlan>)JsonUtil.convertArray(
			    			mapResult.get("epdPlans").toString(), new EpdPlan());
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
	public List<EpdPlan> queryPageByCustom(String organizeSeq, String routeSeq,
			String stationSeq, String planId, String planType,
			String planStatus, int start, int limit)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdPlan iNetEpdPlan = new ImpNetEpdPlan();
				return iNetEpdPlan.queryPageByCustom(organizeSeq,
						routeSeq, stationSeq, planId, planType, planStatus, start, limit);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				map.put("routeSeq", routeSeq);
				map.put("stationSeq", stationSeq);
				map.put("planId", planId);
				map.put("planType", planType);
				map.put("planStatus", planStatus);
				map.put("start", start);
				map.put("limit", limit);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdPlan_QueryPageByCustom,
						EpdFinal.ServerNamespace,EpdFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<EpdPlan>)JsonUtil.convertArray(
			    			mapResult.get("epdPlans").toString(), new EpdPlan());
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
	public List<EpdPlan> queryAllByCustom(String organizeSeq, String routeSeq,
			String stationSeq, String planId, String planType,
			String planStatus) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdPlan iNetEpdPlan = new ImpNetEpdPlan();
				return iNetEpdPlan.queryAllByCustom(organizeSeq,
						routeSeq, stationSeq, planId, planType, planStatus);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				map.put("routeSeq", routeSeq);
				map.put("stationSeq", stationSeq);
				map.put("planId", planId);
				map.put("planType", planType);
				map.put("planStatus", planStatus);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdPlan_QueryAllByCustom,
						EpdFinal.ServerNamespace,EpdFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<EpdPlan>)JsonUtil.convertArray(
			    			mapResult.get("epdPlans").toString(), new EpdPlan());
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
	public int queryCountByCustom(String organizeSeq, String routeSeq, String stationSeq,
			String planId, String planType, String planStatus)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdPlan iNetEpdPlan = new ImpNetEpdPlan();
				return iNetEpdPlan.queryCountByCustom(organizeSeq,
						routeSeq, stationSeq, planId, planType, planStatus);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				map.put("routeSeq", routeSeq);
				map.put("stationSeq", stationSeq);
				map.put("planId", planId);
				map.put("planType", planType);
				map.put("planStatus", planStatus);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdPlan_QueryCountByCustom,
						EpdFinal.ServerNamespace,EpdFinal.ServerAddres);
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<EpdPlan> queryByOrganizeSeq(String organizeSeq)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdPlan iNetEpdPlan = new ImpNetEpdPlan();
				return iNetEpdPlan.queryByOrganizeSeq(organizeSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdPlan_QueryByOrganizeSeq,
						EpdFinal.ServerNamespace,EpdFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<EpdPlan>)JsonUtil.convertArray(
			    			mapResult.get("epdPlans").toString(), new EpdPlan());
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