package com.zhima.traffic.action.insurance.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.traffic.business.insurance.INetInsPremium;
import com.service.traffic.business.insurance.impl.ImpNetInsPremium;
import com.zhima.basic.CommFinal;
import com.zhima.basic.InsFinal;
import com.zhima.basic.ServiceClient;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.action.insurance.IInsPremium;
import com.zhima.traffic.model.InsPremium;
import com.zhima.util.MapUtil;

/**
 * ImpLogin概要说明：用户登录接口
 * @author lcy
 */
public class ImpInsPremium implements IInsPremium {

	@SuppressWarnings("rawtypes")
	@Override
	public InsPremium insert(InsPremium insPremium,Map<String, Object> config)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetInsPremium iNetInsPremium = new ImpNetInsPremium();
				return iNetInsPremium.insert(insPremium, config);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("insPremium", insPremium);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, InsFinal.InsPremium_Insert,
						InsFinal.ServerNamespace,InsFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (InsPremium)JsonUtil.convertObject(
			    			mapResult.get("insPremium").toString(), InsPremium.class);
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
	public void update(InsPremium insPremium,Map<String, Object> config) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetInsPremium iNetInsPremium = new ImpNetInsPremium();
				iNetInsPremium.update(insPremium, config);
				return;
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("insPremium", insPremium);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, InsFinal.InsPremium_Update,
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
	public void delete(List<InsPremium> insPremiums,Map<String, Object> config) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetInsPremium iNetInsPremium = new ImpNetInsPremium();
				iNetInsPremium.delete(insPremiums, config);
				return;
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("insPremiums", insPremiums);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, InsFinal.InsPremium_Delete,
						InsFinal.ServerNamespace,InsFinal.ServerAddres);
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
	public List<InsPremium> queryPageByCustom(String organizeSeq, String companySeq,
			int start,int limit) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetInsPremium iNetInsPremium = new ImpNetInsPremium();
				return iNetInsPremium.queryPageByCustom(organizeSeq, companySeq, start, limit);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				map.put("companySeq", companySeq);
				map.put("start", start);
				map.put("limit", limit);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, InsFinal.InsPremium_QueryPageByCustom,
						InsFinal.ServerNamespace,InsFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<InsPremium>)JsonUtil.convertArray(
			    			mapResult.get("insPremiums").toString(), new InsPremium());
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
	public List<InsPremium> queryAllByCustom(String organizeSeq, String companySeq) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetInsPremium iNetInsPremium = new ImpNetInsPremium();
				return iNetInsPremium.queryAllByCustom(organizeSeq, companySeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				map.put("companySeq", companySeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, InsFinal.InsPremium_QueryAllByCustom,
						InsFinal.ServerNamespace,InsFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<InsPremium>)JsonUtil.convertArray(
			    			mapResult.get("insPremiums").toString(), new InsPremium());
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
	public int queryCountByCustom(String organizeSeq, String companySeq)throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetInsPremium iNetInsPremium = new ImpNetInsPremium();
				return iNetInsPremium.queryCountByCustom(organizeSeq, companySeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				map.put("companySeq", companySeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, InsFinal.InsPremium_QueryCountByCustom,
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<InsPremium> queryByOrganizeSeq(String organizeSeq)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetInsPremium iNetInsPremium = new ImpNetInsPremium();
				return iNetInsPremium.queryByOrganizeSeq(organizeSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, InsFinal.InsCompany_QueryByOrganizeSeq,
						InsFinal.ServerNamespace,InsFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<InsPremium>)JsonUtil.convertArray(
			    			mapResult.get("insPremiums").toString(), new InsPremium());
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