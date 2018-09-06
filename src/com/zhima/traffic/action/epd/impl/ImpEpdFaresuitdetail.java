package com.zhima.traffic.action.epd.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.traffic.business.epd.INetEpdFaresuitdetail;
import com.service.traffic.business.epd.impl.ImpNetEpdFaresuitdetail;
import com.zhima.basic.CommFinal;
import com.zhima.basic.EpdFinal;
import com.zhima.basic.ServiceClient;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.action.epd.IEpdFaresuitdetail;
import com.zhima.traffic.model.EpdFaresuitdetail;
import com.zhima.util.MapUtil;

public class ImpEpdFaresuitdetail implements IEpdFaresuitdetail {

	@SuppressWarnings("rawtypes")
	@Override
	public EpdFaresuitdetail insert(EpdFaresuitdetail epdFaresuitdetail,Map<String, Object> config)throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdFaresuitdetail iNetEpdFaresuitdetail = new ImpNetEpdFaresuitdetail();
				return iNetEpdFaresuitdetail.insert(epdFaresuitdetail, config);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("epdFaresuitdetail", epdFaresuitdetail);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdFaresuitdetail_Insert,
						EpdFinal.ServerNamespace,EpdFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (EpdFaresuitdetail)JsonUtil.convertObject(
			    			mapResult.get("epdFaresuitdetail").toString(), EpdFaresuitdetail.class);
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
	public void update(EpdFaresuitdetail epdFaresuitdetail,Map<String, Object> config)throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdFaresuitdetail iNetEpdFaresuitdetail = new ImpNetEpdFaresuitdetail();
				iNetEpdFaresuitdetail.update(epdFaresuitdetail, config);
				return;
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("epdFaresuitdetail", epdFaresuitdetail);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdFaresuitdetail_Update,
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
	public void delete(List<EpdFaresuitdetail> epdFaresuitdetails,Map<String, Object> config)throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdFaresuitdetail iNetEpdFaresuitdetail = new ImpNetEpdFaresuitdetail();
				iNetEpdFaresuitdetail.delete(epdFaresuitdetails, config);
				return;
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("epdFaresuitdetails", epdFaresuitdetails);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdFaresuitdetail_Delete,
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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<EpdFaresuitdetail> queryByPK(String faresuitdetailSeq) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdFaresuitdetail iNetEpdFaresuitdetail = new ImpNetEpdFaresuitdetail();
				return iNetEpdFaresuitdetail.queryByPK(faresuitdetailSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("faresuitdetailSeq", faresuitdetailSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdFaresuitdetail_QueryByPK,
						EpdFinal.ServerNamespace,EpdFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<EpdFaresuitdetail>)JsonUtil.convertArray(
			    			mapResult.get("epdFaresuitdetails").toString(), new EpdFaresuitdetail());
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
	public List<EpdFaresuitdetail> queryByAll() throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdFaresuitdetail iNetEpdFaresuitdetail = new ImpNetEpdFaresuitdetail();
				return iNetEpdFaresuitdetail.queryByAll();
			case 2: 
				//1.参数对象为JSONObject
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(null, EpdFinal.EpdFaresuitdetail_QueryByAll,
						EpdFinal.ServerNamespace,EpdFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<EpdFaresuitdetail>)JsonUtil.convertArray(
			    			mapResult.get("epdFaresuitdetails").toString(), new EpdFaresuitdetail());
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
	public List<EpdFaresuitdetail> queryPageByCustom(String faresuitSeq, String routeSeq, String stationSeq,
			String cargradeSeq, int start, int limit) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdFaresuitdetail iNetEpdFaresuitdetail = new ImpNetEpdFaresuitdetail();
				return iNetEpdFaresuitdetail.queryPageByCustom(faresuitSeq, routeSeq, stationSeq, cargradeSeq, start, limit);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("faresuitSeq", faresuitSeq);
				map.put("routeSeq", routeSeq);
				map.put("stationSeq", stationSeq);
				map.put("cargradeSeq", cargradeSeq);
				map.put("start", start);
				map.put("limit", limit);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdFaresuitdetail_QueryPageByCustom,
						EpdFinal.ServerNamespace,EpdFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<EpdFaresuitdetail>)JsonUtil.convertArray(
			    			mapResult.get("epdFaresuitdetails").toString(), new EpdFaresuitdetail());
			    }else{
			    	throw new UserBusinessException(String.valueOf(mapResult.get("Message")));
			    }
			}
			return null;
		} catch (Exception e) {
			throw new UserBusinessException(e.getMessage());
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public  List<EpdFaresuitdetail> queryAllByCustom(String faresuitSeq, String routeSeq, String stationSeq,
			String cargradeSeq) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdFaresuitdetail iNetEpdFaresuitdetail = new ImpNetEpdFaresuitdetail();
				return iNetEpdFaresuitdetail.queryAllByCustom(faresuitSeq, routeSeq, stationSeq, cargradeSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("faresuitSeq", faresuitSeq);
				map.put("routeSeq", routeSeq);
				map.put("stationSeq", stationSeq);
				map.put("cargradeSeq", cargradeSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdFaresuitdetail_QueryAllByCustom,
						EpdFinal.ServerNamespace,EpdFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<EpdFaresuitdetail>)JsonUtil.convertArray(
			    			mapResult.get("epdFaresuitdetails").toString(), new EpdFaresuitdetail());
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
	public int queryCountByCustom(String faresuitSeq, String routeSeq, String stationSeq,
			String cargradeSeq) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdFaresuitdetail iNetEpdFaresuitdetail = new ImpNetEpdFaresuitdetail();
				return iNetEpdFaresuitdetail.queryCountByCustom(faresuitSeq, routeSeq, stationSeq, cargradeSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("faresuitSeq", faresuitSeq);
				map.put("routeSeq", routeSeq);
				map.put("stationSeq", stationSeq);
				map.put("cargradeSeq", cargradeSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdFaresuitdetail_QueryCountByCustom,
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
	public List<EpdFaresuitdetail> queryByRouteSeq(String routeSeq) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdFaresuitdetail iNetEpdFaresuitdetail = new ImpNetEpdFaresuitdetail();
				return iNetEpdFaresuitdetail.queryByRouteSeq(routeSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("routeSeq", routeSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdFaresuitdetail_QueryByRouteSeq,
						EpdFinal.ServerNamespace,EpdFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<EpdFaresuitdetail>)JsonUtil.convertArray(
			    			mapResult.get("epdFaresuitdetails").toString(), new EpdFaresuitdetail());
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
	public void updateBatch(String routeSeq, List<EpdFaresuitdetail> faresuitdetails,
			Map<String, Object> config) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdFaresuitdetail iNetEpdFaresuitdetail = new ImpNetEpdFaresuitdetail();
				iNetEpdFaresuitdetail.updateBatch(routeSeq, faresuitdetails, config);
				return;
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("routeSeq", routeSeq);
				map.put("epdFaresuitdetails", faresuitdetails);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdFaresuitdetail_UpdateBatch,
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
	public List<EpdFaresuitdetail> queryByRouteSeqAndFaresuitSeq(String routeSeq,
			String faresuitSeq) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdFaresuitdetail iNetEpdFaresuitdetail = new ImpNetEpdFaresuitdetail();
				return iNetEpdFaresuitdetail.queryByRouteSeqAndFaresuitSeq(routeSeq, faresuitSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("routeSeq", routeSeq);
				map.put("faresuitSeq", faresuitSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdFaresuitdetail_QueryByRouteSeqAndFaresuitSeq,
						EpdFinal.ServerNamespace,EpdFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<EpdFaresuitdetail>)JsonUtil.convertArray(
			    			mapResult.get("epdFaresuitdetails").toString(), new EpdFaresuitdetail());
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