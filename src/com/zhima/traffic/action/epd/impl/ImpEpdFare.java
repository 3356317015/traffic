package com.zhima.traffic.action.epd.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.traffic.business.epd.INetEpdFare;
import com.service.traffic.business.epd.impl.ImpNetEpdFare;
import com.zhima.basic.CommFinal;
import com.zhima.basic.EpdFinal;
import com.zhima.basic.ServiceClient;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.action.epd.IEpdFare;
import com.zhima.traffic.model.EpdFare;
import com.zhima.util.MapUtil;

public class ImpEpdFare implements IEpdFare {

	@SuppressWarnings("rawtypes")
	@Override
	public EpdFare insert(EpdFare epdFare,Map<String, Object> config)throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdFare iNetEpdFare = new ImpNetEpdFare();
				return iNetEpdFare.insert(epdFare, config);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("epdFare", epdFare);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdFare_Insert,
						EpdFinal.ServerNamespace,EpdFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (EpdFare)JsonUtil.convertObject(
			    			mapResult.get("epdFare").toString(), EpdFare.class);
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
	public void update(EpdFare epdFare,Map<String, Object> config)throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdFare iNetEpdFare = new ImpNetEpdFare();
				iNetEpdFare.update(epdFare, config);
				return;
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("epdFare", epdFare);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdFare_Update,
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
	public void delete(List<EpdFare> epdFares,Map<String, Object> config)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdFare iNetEpdFare = new ImpNetEpdFare();
				iNetEpdFare.delete(epdFares, config);
				return;
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("epdFares", epdFares);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdFare_Delete,
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
	public List<EpdFare> queryByPK(String fareSeq) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdFare iNetEpdFare = new ImpNetEpdFare();
				return iNetEpdFare.queryByPK(fareSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("fareSeq", fareSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdFare_QueryByPK,
						EpdFinal.ServerNamespace,EpdFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<EpdFare>)JsonUtil.convertArray(
			    			mapResult.get("epdFares").toString(), new EpdFare());
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
	public List<EpdFare> queryByAll() throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdFare iNetEpdFare = new ImpNetEpdFare();
				return iNetEpdFare.queryByAll();
			case 2: 
				//1.参数对象为JSONObject
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(null, EpdFinal.EpdFare_QueryByAll,
						EpdFinal.ServerNamespace,EpdFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<EpdFare>)JsonUtil.convertArray(
			    			mapResult.get("epdFares").toString(), new EpdFare());
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
	public List<EpdFare> queryPageByCustom(String routeSeq, String stationSeq,
			String cargradeSeq, int start, int limit) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdFare iNetEpdFare = new ImpNetEpdFare();
				return iNetEpdFare.queryPageByCustom(routeSeq, stationSeq, cargradeSeq, start, limit);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("routeSeq", routeSeq);
				map.put("stationSeq", stationSeq);
				map.put("cargradeSeq", cargradeSeq);
				map.put("start", start);
				map.put("limit", limit);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdFare_QueryPageByCustom,
						EpdFinal.ServerNamespace,EpdFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<EpdFare>)JsonUtil.convertArray(
			    			mapResult.get("epdFares").toString(), new EpdFare());
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
	public  List<EpdFare> queryAllByCustom(String routeSeq, String stationSeq,
			String cargradeSeq) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdFare iNetEpdFare = new ImpNetEpdFare();
				return iNetEpdFare.queryAllByCustom(routeSeq, stationSeq, cargradeSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("routeSeq", routeSeq);
				map.put("stationSeq", stationSeq);
				map.put("cargradeSeq", cargradeSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdFare_QueryAllByCustom,
						EpdFinal.ServerNamespace,EpdFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<EpdFare>)JsonUtil.convertArray(
			    			mapResult.get("epdFares").toString(), new EpdFare());
			    }else{
			    	throw new UserBusinessException(String.valueOf(mapResult.get("Message")));
			    }
			}
			return null;
		} catch (Exception e) {
			throw new UserBusinessException(e.getMessage());
		}
	}

	@SuppressWarnings({ "rawtypes" })
	@Override
	public int queryCountByCustom(String routeSeq, String stationSeq,
			String cargradeSeq) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdFare iNetEpdFare = new ImpNetEpdFare();
				return iNetEpdFare.queryCountByCustom(routeSeq, stationSeq, cargradeSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("routeSeq", routeSeq);
				map.put("stationSeq", stationSeq);
				map.put("cargradeSeq", cargradeSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdFare_QueryCountByCustom,
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
	public List<EpdFare> queryByRouteSeq(String routeSeq) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdFare iNetEpdFare = new ImpNetEpdFare();
				return iNetEpdFare.queryByRouteSeq(routeSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("routeSeq", routeSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdFare_QueryByRouteSeq,
						EpdFinal.ServerNamespace,EpdFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<EpdFare>)JsonUtil.convertArray(
			    			mapResult.get("epdFares").toString(), new EpdFare());
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
	public void updateBatch(String routeSeq, List<EpdFare> epdFares, Map<String, Object> config)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdFare iNetEpdFare = new ImpNetEpdFare();
				iNetEpdFare.updateBatch(routeSeq, epdFares, config);
				return;
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("routeSeq", routeSeq);
				map.put("epdFares", epdFares);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdFare_UpdateBatch,
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

}