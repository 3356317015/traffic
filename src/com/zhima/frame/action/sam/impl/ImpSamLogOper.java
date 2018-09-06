package com.zhima.frame.action.sam.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.traffic.business.sam.INetSamLogOper;
import com.service.traffic.business.sam.impl.ImpNetSamLogOper;
import com.zhima.basic.CommFinal;
import com.zhima.basic.SamFinal;
import com.zhima.basic.ServiceClient;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.action.sam.ISamLogOper;
import com.zhima.frame.model.SamLogOper;
import com.zhima.util.MapUtil;

/**
 * ImpSamLogOper概要说明：日志操作接口
 * @author lcy
 */
public class ImpSamLogOper implements ISamLogOper {
	
	@SuppressWarnings("rawtypes")
	@Override
	public SamLogOper insert(SamLogOper samLogOper,Map<String, Object> config)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamLogOper iNetSamLogOper = new ImpNetSamLogOper();
				return iNetSamLogOper.insert(samLogOper,config);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("samLogOper", samLogOper);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.LogOper_Insert,
						SamFinal.ServerNamespace,SamFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	SamLogOper newSamLogOper=(SamLogOper)JsonUtil.convertObject(
			    			mapResult.get("samLogOper").toString(), SamLogOper.class);
			    	return newSamLogOper;
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
	public void update(SamLogOper samLogOper, String status, String operDesc)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamLogOper iNetSamLogOper = new ImpNetSamLogOper();
				iNetSamLogOper.update(samLogOper, status, operDesc);
				return;
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				String strJson = JSONObject.fromObject(samLogOper).toString();
				map.put("samLogOper", strJson);
				map.put("status", status);
				map.put("operDesc", operDesc);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.LogOper_Update,
						SamFinal.ServerNamespace,SamFinal.ServerAddres);
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
	public List<SamLogOper> queryPageByCustom(String organizeSeq, String moduleCode,
			String operType, String status, String operUser, String startDate,
			String endDate, int start, int limit) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamLogOper iNetSamLogOper = new ImpNetSamLogOper();
				return iNetSamLogOper.queryPageByCustom(
						organizeSeq, moduleCode, operType, status, operUser, startDate, endDate, start, limit);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				map.put("moduleCode", moduleCode);
				map.put("operType", operType);
				map.put("status", status);
				map.put("operUser", operUser);
				map.put("startDate", startDate);
				map.put("endDate", endDate);
				map.put("start", start);
				map.put("limit", limit);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.LogOper_QueryPageByCustom,
						SamFinal.ServerNamespace,SamFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<SamLogOper>)JsonUtil.convertArray(
			    			mapResult.get("samLogOpers").toString(), new SamLogOper());
			    }else{
			    	throw new UserBusinessException(String.valueOf(mapResult.get("Message")));
			    }
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new UserBusinessException(e.getMessage());
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<SamLogOper> queryAllByCustom(String organizeSeq, String moduleCode,
			String operType, String status, String operUser, String startDate,
			String endDate) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamLogOper iNetSamLogOper = new ImpNetSamLogOper();
				return iNetSamLogOper.queryAllByCustom(
						organizeSeq, moduleCode, operType, status, operUser, startDate, endDate);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				map.put("moduleCode", moduleCode);
				map.put("operType", operType);
				map.put("status", status);
				map.put("operUser", operUser);
				map.put("startDate", startDate);
				map.put("endDate", endDate);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.LogOper_QueryAllByCustom,
						SamFinal.ServerNamespace,SamFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<SamLogOper>)JsonUtil.convertArray(
			    			mapResult.get("samLogOpers").toString(), new SamLogOper());
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
	public int queryCountByCustom(String organizeSeq, String moduleCode, String operType,
			String status, String operUser, String startDate, String endDate)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamLogOper iNetSamLogOper = new ImpNetSamLogOper();
				return iNetSamLogOper.queryCountByCustom(
						organizeSeq, moduleCode, operType, status, operUser, startDate, endDate);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				map.put("moduleCode", moduleCode);
				map.put("operType", operType);
				map.put("status", status);
				map.put("operUser", operUser);
				map.put("startDate", startDate);
				map.put("endDate", endDate);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.LogOper_QueryCountByCustom,
						SamFinal.ServerNamespace,SamFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	int count = Integer.valueOf(mapResult.get("count").toString());
					return count;
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
	public void deleteByOperSeq(List<SamLogOper> samLogOpers,Map<String, Object> config)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamLogOper iNetSamLogOper = new ImpNetSamLogOper();
				iNetSamLogOper.deleteByOperSeq(samLogOpers,config);
				return;
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("samLogOpers", samLogOpers);
				map.put("config",config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.LogOper_DeleteByOperSeq,
						SamFinal.ServerNamespace,SamFinal.ServerAddres);
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