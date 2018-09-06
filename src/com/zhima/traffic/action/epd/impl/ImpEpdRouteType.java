package com.zhima.traffic.action.epd.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.traffic.business.epd.INetEpdRouteType;
import com.service.traffic.business.epd.impl.ImpNetEpdRouteType;
import com.zhima.basic.CommFinal;
import com.zhima.basic.EpdFinal;
import com.zhima.basic.ServiceClient;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.action.epd.IEpdRouteType;
import com.zhima.traffic.model.EpdRoutetype;
import com.zhima.util.MapUtil;

/**
 * ImpEpdRegion概要说明：线路类型
 * @author lcy
 */
public class ImpEpdRouteType implements IEpdRouteType {

	@SuppressWarnings("rawtypes")
	@Override
	public EpdRoutetype insert(EpdRoutetype epdRoutetype,Map<String, Object> config)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdRouteType iNetEpdRouteType = new ImpNetEpdRouteType();
				return iNetEpdRouteType.insert(epdRoutetype, config);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("epdRoutetype", epdRoutetype);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdRouteType_Insert,
						EpdFinal.ServerNamespace,EpdFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (EpdRoutetype)JsonUtil.convertObject(
			    			mapResult.get("epdRoutetype").toString(), EpdRoutetype.class);
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
	public void update(EpdRoutetype epdRoutetype,Map<String, Object> config)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdRouteType iNetEpdRouteType = new ImpNetEpdRouteType();
				iNetEpdRouteType.update(epdRoutetype, config);
				return;
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("epdRoutetype", epdRoutetype);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdRouteType_Update,
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
	public void delete(List<EpdRoutetype> epdRoutetypes,Map<String, Object> config)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdRouteType iNetEpdRouteType = new ImpNetEpdRouteType();
				iNetEpdRouteType.delete(epdRoutetypes, config);
				return;
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("epdRoutetypes", epdRoutetypes);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdRouteType_Delete,
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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<EpdRoutetype> queryByPK(String routetypeSeq) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdRouteType iNetEpdRouteType = new ImpNetEpdRouteType();
				return iNetEpdRouteType.queryByPK(routetypeSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("routetypeSeq", routetypeSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdRouteType_QueryByPK,
						EpdFinal.ServerNamespace,EpdFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<EpdRoutetype>)JsonUtil.convertArray(
			    			mapResult.get("epdRoutetypes").toString(), new EpdRoutetype());
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
	public List<EpdRoutetype> queryPageByCustom(String organizeSeq, String routetypeCode, String routetypeName
			, int start, int limit) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdRouteType iNetEpdRouteType = new ImpNetEpdRouteType();
				return iNetEpdRouteType.queryPageByCustom(organizeSeq, routetypeCode, routetypeName, start, limit);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				map.put("routetypeCode", routetypeCode);
				map.put("routetypeName", routetypeName);
				map.put("start", start);
				map.put("limit", limit);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdRouteType_QueryPageByCustom,
						EpdFinal.ServerNamespace,EpdFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<EpdRoutetype>)JsonUtil.convertArray(
			    			mapResult.get("epdRoutetypes").toString(), new EpdRoutetype());
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
	public List<EpdRoutetype> queryAllByCustom(String organizeSeq, String routetypeCode, String routetypeName) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdRouteType iNetEpdRouteType = new ImpNetEpdRouteType();
				return iNetEpdRouteType.queryAllByCustom(organizeSeq, routetypeCode, routetypeName);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				map.put("routetypeCode", routetypeCode);
				map.put("routetypeName", routetypeName);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdRouteType_QueryAllByCustom,
						EpdFinal.ServerNamespace,EpdFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<EpdRoutetype>)JsonUtil.convertArray(
			    			mapResult.get("epdRoutetypes").toString(), new EpdRoutetype());
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
	public int queryCountByCustom(String organizeSeq, String routetypeCode, String routetypeName)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdRouteType iNetEpdRouteType = new ImpNetEpdRouteType();
				return iNetEpdRouteType.queryCountByCustom(organizeSeq, routetypeCode, routetypeName);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				map.put("routetypeCode", routetypeCode);
				map.put("routetypeName", routetypeName);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdRouteType_QueryCountByCustom,
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
	public List<EpdRoutetype> queryByOrganizeSeq(String organizeSeq)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdRouteType iNetEpdRouteType = new ImpNetEpdRouteType();
				return iNetEpdRouteType.queryByOrganizeSeq(organizeSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdRouteType_QueryByOrganizeSeq,
						EpdFinal.ServerNamespace,EpdFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<EpdRoutetype>)JsonUtil.convertArray(
			    			mapResult.get("epdRoutetypes").toString(), new EpdRoutetype());
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
	public List<EpdRoutetype> queryByAll() throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdRouteType iNetEpdRouteType = new ImpNetEpdRouteType();
				return iNetEpdRouteType.queryByAll();
			case 2: 
				//1.参数对象为JSONObject
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(null, EpdFinal.EpdRouteType_QueryByAll,
						EpdFinal.ServerNamespace,EpdFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<EpdRoutetype>)JsonUtil.convertArray(
			    			mapResult.get("epdRoutetypes").toString(), new EpdRoutetype());
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