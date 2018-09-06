package com.zhima.frame.action.sam.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.traffic.business.sam.INetSamService;
import com.service.traffic.business.sam.impl.ImpNetSamService;
import com.zhima.basic.CommFinal;
import com.zhima.basic.SamFinal;
import com.zhima.basic.ServiceClient;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.action.sam.ISamService;
import com.zhima.frame.model.SamService;
import com.zhima.util.MapUtil;

/**
 * ImpSamOrganize概要说明：服务网点接口
 * @author lcy
 */
public class ImpSamService implements ISamService {

	@SuppressWarnings({ "rawtypes" })
	@Override
	public SamService insert(SamService samService,Map<String, Object> config)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamService iNetSamService = new ImpNetSamService();
				return iNetSamService.insert(samService,config);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("samService", samService);
				map.put("config",config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.Service_Insert,
						SamFinal.ServerNamespace,SamFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	SamService newSamService=(SamService)JsonUtil.convertObject(
			    			mapResult.get("samService").toString(), SamService.class);
			    	return newSamService;
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
	public void update(SamService samService,Map<String, Object> config) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamService iNetSamService = new ImpNetSamService();
				iNetSamService.update(samService,config);
				return;
			case 2:
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("samService",samService);
				map.put("config",config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.Service_Update,
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
	public void delete(List<SamService> samServices,Map<String, Object> config)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamService iNetSamService = new ImpNetSamService();
				iNetSamService.delete(samServices,config);
				return;
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("samServices", samServices);
				map.put("config",config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.Service_Delete,
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
	public List<SamService> queryPageByCustom(String organizeSeq, String serviceCode,String serviceName,
			String serviceStatus,int start,int limit) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamService iNetSamService = new ImpNetSamService();
				return iNetSamService.queryPageByCustom(organizeSeq, serviceCode, serviceName, serviceStatus, start, limit);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				map.put("serviceCode", serviceCode);
				map.put("serviceName", serviceName);
				map.put("serviceStatus", serviceStatus);
				map.put("start", start);
				map.put("limit", limit);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.Service_QueryPageByCustom,
						SamFinal.ServerNamespace,SamFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
					List<SamService> samServices = (List<SamService>)JsonUtil.convertArray(
							mapResult.get("samServices").toString(), new SamService());
					return samServices;
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
	public List<SamService> queryAllByCustom(String organizeSeq, String serviceCode,
			String serviceName, String serviceStatus) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamService iNetSamService = new ImpNetSamService();
				return iNetSamService.queryAllByCustom(organizeSeq, serviceCode, serviceName, serviceStatus);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				map.put("serviceCode", serviceCode);
				map.put("serviceName", serviceName);
				map.put("serviceStatus", serviceStatus);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.Service_QueryAllByCustom,
						SamFinal.ServerNamespace,SamFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
					List<SamService> samServices = (List<SamService>)JsonUtil.convertArray(
							mapResult.get("samServices").toString(), new SamService());
					return samServices;
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
	public int queryCountByCustom(String organizeSeq, String serviceCode,
			String serviceName, String serviceStatus)throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamService iNetSamService = new ImpNetSamService();
				return iNetSamService.queryCountByCustom(organizeSeq, serviceCode, serviceName, serviceStatus);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				map.put("serviceCode", serviceCode);
				map.put("serviceName", serviceName);
				map.put("serviceStatus", serviceStatus);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.Service_QueryCountByCustom,
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<SamService> queryByOrganizeSeq(String organizeSeq)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamService iNetSamService = new ImpNetSamService();
				return iNetSamService.queryByOrganizeSeq(organizeSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.Service_QueryByOrganizeSeq,
						SamFinal.ServerNamespace,SamFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
					List<SamService> samServices = (List<SamService>)JsonUtil.convertArray(
							mapResult.get("samServices").toString(), new SamService());
					return samServices;
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
	public List<SamService> queryByOrganizeAndSaleType(String organizeSeq,
			String saleType) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamService iNetSamService = new ImpNetSamService();
				return iNetSamService.queryByOrganizeAndSaleType(organizeSeq, saleType);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.Service_QueryByOrganizeAndSaleType,
						SamFinal.ServerNamespace,SamFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
					List<SamService> samServices = (List<SamService>)JsonUtil.convertArray(
							mapResult.get("samServices").toString(), new SamService());
					return samServices;
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