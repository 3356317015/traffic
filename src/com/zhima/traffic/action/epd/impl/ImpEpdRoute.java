package com.zhima.traffic.action.epd.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.traffic.business.epd.INetEpdRoute;
import com.service.traffic.business.epd.impl.ImpNetEpdRoute;
import com.zhima.basic.CommFinal;
import com.zhima.basic.EpdFinal;
import com.zhima.basic.ServiceClient;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.action.epd.IEpdRoute;
import com.zhima.traffic.model.EpdRoute;
import com.zhima.traffic.model.EpdRouteservice;
import com.zhima.traffic.model.EpdRoutestation;
import com.zhima.util.MapUtil;

/**
 * ImpEpdRegion概要说明：城市区域
 * @author lcy
 */
public class ImpEpdRoute implements IEpdRoute {

	@SuppressWarnings("rawtypes")
	@Override
	public EpdRoute insert(EpdRoute epdRoute,List<EpdRoutestation> routestations,List<EpdRouteservice> routeservices,
			Map<String, Object> config)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdRoute iNetEpdRoute = new ImpNetEpdRoute();
				return iNetEpdRoute.insert(epdRoute, routestations, routeservices, config);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("epdRoute", epdRoute);
				map.put("epdRoutestations", routestations);
				map.put("epdRouteservices", routeservices);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdRoute_Insert,
						EpdFinal.ServerNamespace,EpdFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (EpdRoute)JsonUtil.convertObject(
			    			mapResult.get("epdRoute").toString(), EpdRoute.class);
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
	public void update(EpdRoute epdRoute,List<EpdRoutestation> routestations,
			List<EpdRoutestation> delstations,List<EpdRouteservice> routeservices,Map<String, Object> config)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdRoute iNetEpdRoute = new ImpNetEpdRoute();
				iNetEpdRoute.update(epdRoute, routestations, delstations, routeservices, config);
				return;
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("epdRoute", epdRoute);
				map.put("epdRoutestations", routestations);
				map.put("delstations", delstations);
				map.put("epdRouteservices", routeservices);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdRoute_Update,
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
	public void delete(List<EpdRoute> epdRoutes,Map<String, Object> config)throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdRoute iNetEpdRoute = new ImpNetEpdRoute();
				iNetEpdRoute.delete(epdRoutes, config);
				return;
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("epdRoutes", epdRoutes);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdRoute_Delete,
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
	public List<EpdRoute> queryByPK(String routeSeq) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdRoute iNetEpdRoute = new ImpNetEpdRoute();
				return iNetEpdRoute.queryByPK(routeSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("routeSeq", routeSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdRoute_QueryByPK,
						EpdFinal.ServerNamespace,EpdFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<EpdRoute>)JsonUtil.convertArray(
			    			mapResult.get("epdRoutes").toString(), new EpdRoute());
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
	public List<EpdRoute> queryByAll() throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdRoute iNetEpdRoute = new ImpNetEpdRoute();
				return iNetEpdRoute.queryByAll();
			case 2: 
				//1.参数对象为JSONObject
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(null, EpdFinal.EpdRoute_QueryByAll,
						EpdFinal.ServerNamespace,EpdFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<EpdRoute>)JsonUtil.convertArray(
			    			mapResult.get("epdRoutes").toString(), new EpdRoute());
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
	public List<EpdRoute> queryByOrganizeSeq(String organizeSeq)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdRoute iNetEpdRoute = new ImpNetEpdRoute();
				return iNetEpdRoute.queryByOrganizeSeq(organizeSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdRoute_QueryByOrganizeSeq,
						EpdFinal.ServerNamespace,EpdFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<EpdRoute>)JsonUtil.convertArray(
			    			mapResult.get("epdRoutes").toString(), new EpdRoute());
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
	public List<EpdRoute> queryPageByCustom(String organizeSeq, String routeCode, String routeSpell, String routeName,
			String routeType, String roadType,String routeStatus,int start,int limit) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdRoute iNetEpdRoute = new ImpNetEpdRoute();
				return iNetEpdRoute.queryPageByCustom(organizeSeq, routeCode, routeSpell,
						routeName, routeType, roadType, routeStatus, start, limit);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				map.put("routeCode", routeCode);
				map.put("routeSpell", routeSpell);
				map.put("routeName", routeName);
				map.put("routeType", routeType);
				map.put("roadType", roadType);
				map.put("routeStatus", routeStatus);
				map.put("start", start);
				map.put("limit", limit);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdRoute_QueryPageByCustom,
						EpdFinal.ServerNamespace,EpdFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<EpdRoute>)JsonUtil.convertArray(
			    			mapResult.get("epdRoutes").toString(), new EpdRoute());
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
	public List<EpdRoute> queryAllByCustom(String organizeSeq, String routeCode, String routeSpell, String routeName,
			String routeType, String roadType,String routeStatus) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdRoute iNetEpdRoute = new ImpNetEpdRoute();
				return iNetEpdRoute.queryAllByCustom(organizeSeq, routeCode, routeSpell,
						routeName, routeType, roadType, routeStatus);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				map.put("routeCode", routeCode);
				map.put("routeSpell", routeSpell);
				map.put("routeName", routeName);
				map.put("routeType", routeType);
				map.put("roadType", roadType);
				map.put("routeStatus", routeStatus);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdRoute_QueryAllByCustom,
						EpdFinal.ServerNamespace,EpdFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<EpdRoute>)JsonUtil.convertArray(
			    			mapResult.get("epdRoutes").toString(), new EpdRoute());
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
	public int queryCountByCustom(String organizeSeq, String routeCode, String routeSpell, String routeName,
			String routeType, String roadType,String routeStatus) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdRoute iNetEpdRoute = new ImpNetEpdRoute();
				return iNetEpdRoute.queryCountByCustom(organizeSeq, routeCode, routeSpell,
						routeName, routeType, roadType, routeStatus);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				map.put("routeCode", routeCode);
				map.put("routeSpell", routeSpell);
				map.put("routeName", routeName);
				map.put("routeType", routeType);
				map.put("roadType", roadType);
				map.put("routeStatus", routeStatus);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdRoute_QueryCountByCustom,
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
	public List<EpdRoute> queryByNoFare(String organizeSeq) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdRoute iNetEpdRoute = new ImpNetEpdRoute();
				return iNetEpdRoute.queryByNoFare(organizeSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdRoute_QueryByNoFare,
						EpdFinal.ServerNamespace,EpdFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<EpdRoute>)JsonUtil.convertArray(
			    			mapResult.get("epdRoutes").toString(), new EpdRoute());
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