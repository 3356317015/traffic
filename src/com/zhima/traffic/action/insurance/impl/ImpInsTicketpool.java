package com.zhima.traffic.action.insurance.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.traffic.business.insurance.INetInsTicketpool;
import com.service.traffic.business.insurance.impl.ImpNetInsTicketpool;
import com.zhima.basic.CommFinal;
import com.zhima.basic.InsFinal;
import com.zhima.basic.ServiceClient;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.action.insurance.IInsTicketpool;
import com.zhima.traffic.model.InsTicketpool;
import com.zhima.util.MapUtil;
public class ImpInsTicketpool implements IInsTicketpool {

	@SuppressWarnings("rawtypes")
	@Override
	public InsTicketpool insert(InsTicketpool insTicketpool,Map<String, Object> config)throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetInsTicketpool iNetInsTicketpool = new ImpNetInsTicketpool();
				return iNetInsTicketpool.insert(insTicketpool, config);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("insTicketpool", insTicketpool);
				map.put("config",config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, InsFinal.InsTicketpool_Insert,
						InsFinal.ServerNamespace,InsFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (InsTicketpool)JsonUtil.convertObject(
			    			mapResult.get("itsTicketpool").toString(), InsTicketpool.class);
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
	public void update(InsTicketpool insTicketpool,Map<String, Object> config)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetInsTicketpool iNetInsTicketpool = new ImpNetInsTicketpool();
				iNetInsTicketpool.update(insTicketpool, config);
				return;
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("insTicketpool", insTicketpool);
				map.put("config",config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, InsFinal.InsTicketpool_Update,
						InsFinal.ServerNamespace,InsFinal.ServerAddres);
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
	public void delete(List<InsTicketpool> insTicketpools,Map<String, Object> config)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetInsTicketpool iNetInsTicketpool = new ImpNetInsTicketpool();
				iNetInsTicketpool.delete(insTicketpools, config);
				return;
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("insTicketpools", insTicketpools);
				map.put("config",config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, InsFinal.InsTicketpool_Delete,
						InsFinal.ServerNamespace,InsFinal.ServerAddres);
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
	public List<InsTicketpool> queryByPK(String ticketpoolSeq) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetInsTicketpool iNetInsTicketpool = new ImpNetInsTicketpool();
				return iNetInsTicketpool.queryByPK(ticketpoolSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("ticketpoolSeq", ticketpoolSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, InsFinal.InsTicketpool_QueryByPK,
						InsFinal.ServerNamespace,InsFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<InsTicketpool>)JsonUtil.convertArray(
			    			mapResult.get("insTicketpools").toString(), new InsTicketpool());
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
	public List<InsTicketpool> queryByOrganizeSeq(String organizeSeq)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetInsTicketpool iNetInsTicketpool = new ImpNetInsTicketpool();
				return iNetInsTicketpool.queryByOrganizeSeq(organizeSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, InsFinal.InsTicketpool_QueryByOrganizeSeq,
						InsFinal.ServerNamespace,InsFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<InsTicketpool>)JsonUtil.convertArray(
			    			mapResult.get("insTicketpools").toString(), new InsTicketpool());
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
	public List<InsTicketpool> queryPageByCustom(String organizeSeq, String companySeq, String tickettypeSeq,
			String operType, String userCode, String poolStatus, int start,int limit) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetInsTicketpool iNetInsTicketpool = new ImpNetInsTicketpool();
				return iNetInsTicketpool.queryPageByCustom(organizeSeq, companySeq,
						tickettypeSeq, operType, userCode, poolStatus, start, limit);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				map.put("companySeq", companySeq);
				map.put("tickettypeSeq", tickettypeSeq);
				map.put("operType", operType);
				map.put("userCode", userCode);
				map.put("poolStatus", poolStatus);
				map.put("start", start);
				map.put("limit", limit);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, InsFinal.InsTicketpool_QueryPageByCustom,
						InsFinal.ServerNamespace,InsFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<InsTicketpool>)JsonUtil.convertArray(
			    			mapResult.get("insTicketpools").toString(), new InsTicketpool());
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
	public List<InsTicketpool> queryAllByCustom(String organizeSeq, String companySeq, String tickettypeSeq,
			String operType, String userCode, String poolStatus) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetInsTicketpool iNetInsTicketpool = new ImpNetInsTicketpool();
				return iNetInsTicketpool.queryAllByCustom(organizeSeq, companySeq,
						tickettypeSeq, operType, userCode, poolStatus);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				map.put("companySeq", companySeq);
				map.put("tickettypeSeq", tickettypeSeq);
				map.put("operType", operType);
				map.put("userCode", userCode);
				map.put("poolStatus", poolStatus);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, InsFinal.InsTicketpool_QueryAllByCustom,
						InsFinal.ServerNamespace,InsFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<InsTicketpool>)JsonUtil.convertArray(
			    			mapResult.get("insTicketpools").toString(), new InsTicketpool());
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
	public int queryCountByCustom(String organizeSeq, String companySeq, String tickettypeSeq, String operType,
			String userCode, String poolStatus) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetInsTicketpool iNetInsTicketpool = new ImpNetInsTicketpool();
				return iNetInsTicketpool.queryCountByCustom(organizeSeq, companySeq,
						tickettypeSeq, operType, userCode, poolStatus);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				map.put("companySeq", companySeq);
				map.put("tickettypeSeq", tickettypeSeq);
				map.put("operType", operType);
				map.put("userCode", userCode);
				map.put("poolStatus", poolStatus);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, InsFinal.InsTicketpool_QueryCountByCustom,
						InsFinal.ServerNamespace,InsFinal.ServerAddres);
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
	public InsTicketpool send(InsTicketpool insTicketpool,InsTicketpool newTicketpool, 
			Map<String, Object> config) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetInsTicketpool iNetInsTicketpool = new ImpNetInsTicketpool();
				return iNetInsTicketpool.send(insTicketpool,newTicketpool, config);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("insTicketpool", insTicketpool);
				map.put("newTicketpool", newTicketpool);
				map.put("config",config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, InsFinal.InsTicketpool_Send,
						InsFinal.ServerNamespace,InsFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (InsTicketpool)JsonUtil.convertObject(
			    			mapResult.get("insTicketpool").toString(), InsTicketpool.class);
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
	public List<InsTicketpool> queryValid(String organizeSeq,
			String tickettypeSeq, String operType, String userCode) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetInsTicketpool iNetInsTicketpool = new ImpNetInsTicketpool();
				return iNetInsTicketpool.queryValid(organizeSeq, tickettypeSeq, operType, userCode);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				map.put("tickettypeSeq", tickettypeSeq);
				map.put("operType", operType);
				map.put("userCode", userCode);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, InsFinal.InsTicketpool_QueryValid,
						InsFinal.ServerNamespace,InsFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<InsTicketpool>)JsonUtil.convertArray(
			    			mapResult.get("insTicketpools").toString(), new InsTicketpool());
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