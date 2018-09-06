package com.zhima.frame.action.sam.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.traffic.business.sam.INetSamModuleRight;
import com.service.traffic.business.sam.impl.ImpNetSamModuleRight;
import com.zhima.basic.CommFinal;
import com.zhima.basic.SamFinal;
import com.zhima.basic.ServiceClient;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.action.sam.ISamModuleRight;
import com.zhima.frame.model.SamModuleRight;
import com.zhima.util.MapUtil;

/**
 * ImpLogin概要说明：用户登录接口
 * @author lcy
 */
public class ImpSamModuleRight implements ISamModuleRight {
	
	@SuppressWarnings("rawtypes")
	@Override
	public SamModuleRight insert(SamModuleRight samModuleRight,Map<String, Object> config) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamModuleRight iNetSamModuleRight = new ImpNetSamModuleRight();
				return iNetSamModuleRight.insert(samModuleRight, config);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("samModuleRight", samModuleRight);
				map.put("config",config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.ModuleRight_Insert,
						SamFinal.ServerNamespace,SamFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (SamModuleRight)JsonUtil.convertObject(
			    			mapResult.get("samModuleRight").toString(), SamModuleRight.class);
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
	public void update(SamModuleRight samModuleRight,Map<String, Object> config) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamModuleRight iNetSamModuleRight = new ImpNetSamModuleRight();
				iNetSamModuleRight.update(samModuleRight, config);
				return;
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("samModuleRight", samModuleRight);
				map.put("config",config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.ModuleRight_Update,
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
	
	@SuppressWarnings("rawtypes")
	@Override
	public void deleteByPk(List<SamModuleRight> samModuleRights, Map<String, Object> config) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamModuleRight iNetSamModuleRight = new ImpNetSamModuleRight();
				iNetSamModuleRight.deleteByPk(samModuleRights, config);
				return;
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("samModuleRights", samModuleRights);
				map.put("config",config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.ModuleRight_DeleteByPk,
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
	public List<SamModuleRight> queryModuleRight(String moduleSeq)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamModuleRight iNetSamModuleRight = new ImpNetSamModuleRight();
				return iNetSamModuleRight.queryModuleRight(moduleSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("moduleSeq", moduleSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.ModuleRight_QueryModuleRight,
						SamFinal.ServerNamespace,SamFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<SamModuleRight>)JsonUtil.convertArray(
			    			mapResult.get("samModuleRights").toString(), new SamModuleRight());
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
	public List<SamModuleRight> queryByModuleSeq(String moduleSeq) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamModuleRight iNetSamModuleRight = new ImpNetSamModuleRight();
				return iNetSamModuleRight.queryByModuleSeq(moduleSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("moduleSeq", moduleSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.ModuleRight_QueryByModuleSeq,
						SamFinal.ServerNamespace,SamFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<SamModuleRight>)JsonUtil.convertArray(
			    			mapResult.get("samModuleRights").toString(), new SamModuleRight());
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
	public List<SamModuleRight> queryByGroupSeq(String groupSeq)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamModuleRight iNetSamModuleRight = new ImpNetSamModuleRight();
				return iNetSamModuleRight.queryByGroupSeq(groupSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("groupSeq", groupSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.ModuleRight_QueryByGroupSeq,
						SamFinal.ServerNamespace,SamFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<SamModuleRight>)JsonUtil.convertArray(
			    			mapResult.get("samModuleRights").toString(), new SamModuleRight());
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
	public List<SamModuleRight> queryByUserSeq(String userSeq)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamModuleRight iNetSamModuleRight = new ImpNetSamModuleRight();
				return iNetSamModuleRight.queryByUserSeq(userSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userSeq", userSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.ModuleRight_QueryByUserSeq,
						SamFinal.ServerNamespace,SamFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<SamModuleRight>)JsonUtil.convertArray(
			    			mapResult.get("samModuleRights").toString(), new SamModuleRight());
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
	public List<SamModuleRight> queryRightByUser(String userSeq)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamModuleRight iNetSamModuleRight = new ImpNetSamModuleRight();
				return iNetSamModuleRight.queryRightByUser(userSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userSeq", userSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.ModuleRight_QueryRightByUser,
						SamFinal.ServerNamespace,SamFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<SamModuleRight>)JsonUtil.convertArray(
			    			mapResult.get("samModuleRights").toString(), new SamModuleRight());
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
	public List<SamModuleRight> queryRightByModuleSeq(String moduleSeq)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamModuleRight iNetSamModuleRight = new ImpNetSamModuleRight();
				return iNetSamModuleRight.queryRightByModuleSeq(moduleSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("moduleSeq", moduleSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.ModuleRight_QueryRightByModuleSeq,
						SamFinal.ServerNamespace,SamFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<SamModuleRight>)JsonUtil.convertArray(
			    			mapResult.get("samModuleRights").toString(), new SamModuleRight());
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
	public List<SamModuleRight> queryRightByOrganizeSeq(String organizeSeq)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamModuleRight iNetSamModuleRight = new ImpNetSamModuleRight();
				return iNetSamModuleRight.queryRightByOrganizeSeq(organizeSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.ModuleRight_QueryRightByOrganizeSeq,
						SamFinal.ServerNamespace,SamFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<SamModuleRight>)JsonUtil.convertArray(
			    			mapResult.get("samModuleRights").toString(), new SamModuleRight());
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