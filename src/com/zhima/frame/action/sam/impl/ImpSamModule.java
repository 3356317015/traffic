package com.zhima.frame.action.sam.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.traffic.business.sam.INetSamModule;
import com.service.traffic.business.sam.impl.ImpNetSamModule;
import com.zhima.basic.CommFinal;
import com.zhima.basic.SamFinal;
import com.zhima.basic.ServiceClient;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.action.sam.ISamModule;
import com.zhima.frame.model.SamModule;
import com.zhima.util.MapUtil;

/**
 * ImpSamModule概要说明：用户登录接口
 * @author lcy
 */
public class ImpSamModule implements ISamModule {
	
	@SuppressWarnings("rawtypes")
	@Override
	public SamModule insert(SamModule samModule,String copyModuleSeq,Map<String, Object> config) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamModule iNetSamModule = new ImpNetSamModule();
				return iNetSamModule.insert(samModule, copyModuleSeq, config);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				String strJson = JSONObject.fromObject(samModule).toString();
				map.put("samModule", strJson);
				map.put("copyModuleSeq", copyModuleSeq);
				map.put("config",config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.SamModule_Insert,
						SamFinal.ServerNamespace,SamFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	SamModule newModule =(SamModule)JsonUtil.convertObject(
			    			mapResult.get("samModule").toString(), SamModule.class);
			    	return newModule;
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
	public void update(SamModule samModule,Map<String, Object> config) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamModule iNetSamModule = new ImpNetSamModule();
				iNetSamModule.update(samModule, config);
				return;
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("samModule", samModule);
				map.put("config",config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.SamModule_Update,
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
	public void deleteByPk(SamModule samModule,Map<String, Object> config) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamModule iNetSamModule = new ImpNetSamModule();
				iNetSamModule.deleteByPk(samModule, config);
				return;
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("samModule", samModule);
				map.put("config",config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.SamModule_DeleteByPk,
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
	public List<SamModule> queryTopModule(String organizeSeq) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamModule iNetSamModule = new ImpNetSamModule();
				return iNetSamModule.queryTopModule(organizeSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.SamModule_QueryTopModule,
						SamFinal.ServerNamespace,SamFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
					List<SamModule> samModules = (List<SamModule>)JsonUtil.convertArray(
							mapResult.get("samModules").toString(), new SamModule());
					return samModules;
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
	public List<SamModule> querySubModule(String organizeSeq) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamModule iNetSamModule = new ImpNetSamModule();
				return iNetSamModule.querySubModule(organizeSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.SamModule_QuerySubModule,
						SamFinal.ServerNamespace,SamFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
					List<SamModule> samModules = (List<SamModule>)JsonUtil.convertArray(
							mapResult.get("samModules").toString(), new SamModule());
					return samModules;
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
	public List<SamModule> querySubAll(String parentSeq)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamModule iNetSamModule = new ImpNetSamModule();
				return iNetSamModule.querySubAll(parentSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("parentSeq", parentSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.SamModule_QuerySubAll,
						SamFinal.ServerNamespace,SamFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
					List<SamModule> samModules = (List<SamModule>)JsonUtil.convertArray(
							mapResult.get("samModules").toString(), new SamModule());
					return samModules;
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
	public List<SamModule> queryModuleByUser(String userSeq)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamModule iNetSamModule = new ImpNetSamModule();
				return iNetSamModule.queryModuleByUser(userSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userSeq", userSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.SamModule_QueryModuleByUser,
						SamFinal.ServerNamespace,SamFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
					List<SamModule> samModules = (List<SamModule>)JsonUtil.convertArray(
							mapResult.get("samModules").toString(), new SamModule());
					return samModules;
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
	public List<SamModule> queryByGroupSeq(String groupSeq)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamModule iNetSamModule = new ImpNetSamModule();
				return iNetSamModule.queryByGroupSeq(groupSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("groupSeq", groupSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.SamModule_QueryByGroupSeq,
						SamFinal.ServerNamespace,SamFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
					List<SamModule> samModules = (List<SamModule>)JsonUtil.convertArray(
							mapResult.get("samModules").toString(), new SamModule());
					return samModules;
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
	public List<SamModule> queryByUserSeq(String userSeq)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamModule iNetSamModule = new ImpNetSamModule();
				return iNetSamModule.queryByUserSeq(userSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userSeq", userSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.SamModule_QueryByUserSeq,
						SamFinal.ServerNamespace,SamFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
					List<SamModule> samModules = (List<SamModule>)JsonUtil.convertArray(
							mapResult.get("samModules").toString(), new SamModule());
					return samModules;
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
	public List<SamModule> queryModuleParent(String organizeSeq) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamModule iNetSamModule = new ImpNetSamModule();
				return iNetSamModule.queryModuleParent(organizeSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.SamModule_QueryModuleParent,
						SamFinal.ServerNamespace,SamFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
					List<SamModule> samModules = (List<SamModule>)JsonUtil.convertArray(
							mapResult.get("samModules").toString(), new SamModule());
					return samModules;
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
	public List<SamModule> queryByType(String organizeSeq, String moduleType)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamModule iNetSamModule = new ImpNetSamModule();
				return iNetSamModule.queryByType(organizeSeq,moduleType);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				map.put("moduleType", moduleType);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.SamModule_QueryByType,
						SamFinal.ServerNamespace,SamFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
					List<SamModule> samModules = (List<SamModule>)JsonUtil.convertArray(
							mapResult.get("samModules").toString(), new SamModule());
					return samModules;
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
	public List<SamModule> queryToolbarByUserSeq(String userSeq)throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamModule iNetSamModule = new ImpNetSamModule();
				return iNetSamModule.queryToolbarByUserSeq(userSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userSeq", userSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.SamModule_QueryToolbarByUserSeq,
						SamFinal.ServerNamespace,SamFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
					List<SamModule> samModules = (List<SamModule>)JsonUtil.convertArray(
							mapResult.get("samModules").toString(), new SamModule());
					return samModules;
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