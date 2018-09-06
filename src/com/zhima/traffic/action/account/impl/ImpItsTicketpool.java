package com.zhima.traffic.action.account.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.traffic.business.account.INetItsTicketpool;
import com.service.traffic.business.account.impl.ImpNetItsTicketpool;
import com.zhima.basic.CommFinal;
import com.zhima.basic.ItsFinal;
import com.zhima.basic.ServiceClient;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.action.account.IItsTicketpool;
import com.zhima.traffic.model.ItsTicketpool;
import com.zhima.util.MapUtil;
public class ImpItsTicketpool implements IItsTicketpool {

	@SuppressWarnings("rawtypes")
	@Override
	public ItsTicketpool insert(ItsTicketpool itsTicketpool,Map<String, Object> config)throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetItsTicketpool iNetItsTicketpool = new ImpNetItsTicketpool();
				return iNetItsTicketpool.insert(itsTicketpool, config);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("itsTicketpool", itsTicketpool);
				map.put("config",config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, ItsFinal.ItsTicketpool_Insert,
						ItsFinal.ServerNamespace,ItsFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (ItsTicketpool)JsonUtil.convertObject(
			    			mapResult.get("itsTicketpool").toString(), ItsTicketpool.class);
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
	public void update(ItsTicketpool itsTicketpool,Map<String, Object> config)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetItsTicketpool iNetItsTicketpool = new ImpNetItsTicketpool();
				iNetItsTicketpool.update(itsTicketpool, config);
				return;
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("itsTicketpool", itsTicketpool);
				map.put("config",config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, ItsFinal.ItsTicketpool_Update,
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

	@SuppressWarnings("rawtypes")
	@Override
	public void delete(List<ItsTicketpool> itsTicketpools,Map<String, Object> config)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetItsTicketpool iNetItsTicketpool = new ImpNetItsTicketpool();
				iNetItsTicketpool.delete(itsTicketpools, config);
				return;
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("itsTicketpools", itsTicketpools);
				map.put("config",config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, ItsFinal.ItsTicketpool_Delete,
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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<ItsTicketpool> queryByPK(String ticketpoolSeq) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetItsTicketpool iNetItsTicketpool = new ImpNetItsTicketpool();
				return iNetItsTicketpool.queryByPK(ticketpoolSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("ticketpoolSeq", ticketpoolSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, ItsFinal.ItsTicketpool_QueryByPK,
						ItsFinal.ServerNamespace,ItsFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<ItsTicketpool>)JsonUtil.convertArray(
			    			mapResult.get("itsTicketpools").toString(), new ItsTicketpool());
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
	public List<ItsTicketpool> queryByOrganizeSeq(String organizeSeq)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetItsTicketpool iNetItsTicketpool = new ImpNetItsTicketpool();
				return iNetItsTicketpool.queryByOrganizeSeq(organizeSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, ItsFinal.ItsTicketpool_QueryByOrganizeSeq,
						ItsFinal.ServerNamespace,ItsFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<ItsTicketpool>)JsonUtil.convertArray(
			    			mapResult.get("itsTicketpools").toString(), new ItsTicketpool());
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
	public List<ItsTicketpool> queryPageByCustom(String organizeSeq, String tickettypeSeq, String operType,
			String userCode, String poolStatus, int start,int limit) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetItsTicketpool iNetItsTicketpool = new ImpNetItsTicketpool();
				return iNetItsTicketpool.queryPageByCustom(organizeSeq, tickettypeSeq, operType, userCode, poolStatus, start, limit);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				map.put("tickettypeSeq", tickettypeSeq);
				map.put("operType", operType);
				map.put("userCode", userCode);
				map.put("poolStatus", poolStatus);
				map.put("start", start);
				map.put("limit", limit);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, ItsFinal.ItsTicketpool_QueryPageByCustom,
						ItsFinal.ServerNamespace,ItsFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<ItsTicketpool>)JsonUtil.convertArray(
			    			mapResult.get("itsTicketpools").toString(), new ItsTicketpool());
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
	public List<ItsTicketpool> queryAllByCustom(String organizeSeq, String tickettypeSeq, String operType,
			String userCode, String poolStatus) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetItsTicketpool iNetItsTicketpool = new ImpNetItsTicketpool();
				return iNetItsTicketpool.queryAllByCustom(organizeSeq, tickettypeSeq, operType, userCode, poolStatus);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				map.put("tickettypeSeq", tickettypeSeq);
				map.put("operType", operType);
				map.put("userCode", userCode);
				map.put("poolStatus", poolStatus);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, ItsFinal.ItsTicketpool_QueryAllByCustom,
						ItsFinal.ServerNamespace,ItsFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<ItsTicketpool>)JsonUtil.convertArray(
			    			mapResult.get("itsTicketpools").toString(), new ItsTicketpool());
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
	public int queryCountByCustom(String organizeSeq, String tickettypeSeq, String operType,
			String userCode, String poolStatus) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetItsTicketpool iNetItsTicketpool = new ImpNetItsTicketpool();
				return iNetItsTicketpool.queryCountByCustom(organizeSeq, tickettypeSeq, operType, userCode, poolStatus);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				map.put("tickettypeSeq", tickettypeSeq);
				map.put("operType", operType);
				map.put("userCode", userCode);
				map.put("poolStatus", poolStatus);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, ItsFinal.ItsTicketpool_QueryCountByCustom,
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
	public ItsTicketpool send(ItsTicketpool itsTicketpool,ItsTicketpool newTicketpool, 
			Map<String, Object> config) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetItsTicketpool iNetItsTicketpool = new ImpNetItsTicketpool();
				return iNetItsTicketpool.send(itsTicketpool,newTicketpool, config);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("itsTicketpool", itsTicketpool);
				map.put("newTicketpool", newTicketpool);
				map.put("config",config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, ItsFinal.ItsTicketpool_Send,
						ItsFinal.ServerNamespace,ItsFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (ItsTicketpool)JsonUtil.convertObject(
			    			mapResult.get("itsTicketpool").toString(), ItsTicketpool.class);
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