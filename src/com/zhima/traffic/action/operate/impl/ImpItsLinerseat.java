package com.zhima.traffic.action.operate.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.traffic.business.operate.INetItsLinerseat;
import com.service.traffic.business.operate.impl.ImpNetItsLinerseat;
import com.zhima.basic.CommFinal;
import com.zhima.basic.EpdFinal;
import com.zhima.basic.ItsFinal;
import com.zhima.basic.ServiceClient;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.action.operate.IItsLinerseat;
import com.zhima.traffic.model.ItsLiner;
import com.zhima.traffic.model.ItsLinerseat;
import com.zhima.util.MapUtil;

public class ImpItsLinerseat implements IItsLinerseat {

	@SuppressWarnings("rawtypes")
	@Override
	public void updateAttribute(ItsLiner itsLiner, List<ItsLinerseat> itsLinerseats,Map<String, Object> config)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetItsLinerseat iNetItsLinerseat = new ImpNetItsLinerseat();
				iNetItsLinerseat.updateAttribute(itsLiner, itsLinerseats, config);
				return;
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("itsLiner", itsLiner);
				map.put("itsLinerseats", itsLinerseats);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, ItsFinal.ItsLinerseat_UpdateAttribute,
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
	public List<ItsLinerseat> queryByLinerSeq(String linerSeq)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetItsLinerseat iNetItsLinerseat = new ImpNetItsLinerseat();
				return iNetItsLinerseat.queryByLinerSeq(linerSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("linerSeq", linerSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, ItsFinal.ItsLinerseat_QueryByLinerSeq,
						ItsFinal.ServerNamespace,ItsFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<ItsLinerseat>)JsonUtil.convertArray(
			    			mapResult.get("itsLinerseats").toString(), new ItsLinerseat());
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
	public List<ItsLinerseat> queryPageByLinerSeq(String linerSeq, int start,
			int limit) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetItsLinerseat iNetItsLinerseat = new ImpNetItsLinerseat();
				return iNetItsLinerseat.queryPageByLinerSeq(linerSeq,start,limit);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("linerSeq", linerSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, ItsFinal.ItsLinerseat_QueryPageByLinerSeq,
						ItsFinal.ServerNamespace,ItsFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<ItsLinerseat>)JsonUtil.convertArray(
			    			mapResult.get("itsLinerseats").toString(), new ItsLinerseat());
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
	public int queryCountByLinerSeq(String linerSeq) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetItsLinerseat iNetItsLinerseat = new ImpNetItsLinerseat();
				return iNetItsLinerseat.queryCountByLinerSeq(linerSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("linerSeq", linerSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, ItsFinal.ItsLinerseat_QueryPageByLinerSeq,
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
}