package com.zhima.frame.action.sam.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.traffic.business.sam.INetSamOrganize;
import com.service.traffic.business.sam.impl.ImpNetSamOrganize;
import com.zhima.basic.CommFinal;
import com.zhima.basic.SamFinal;
import com.zhima.basic.ServiceClient;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.action.sam.ISamOrganize;
import com.zhima.frame.model.SamOrganize;
import com.zhima.util.MapUtil;

/**
 * ImpSamOrganize概要说明：服务网点接口
 * @author lcy
 */
public class ImpSamOrganize implements ISamOrganize {

	@SuppressWarnings({ "rawtypes" })
	@Override
	public SamOrganize insert(SamOrganize samOrganize,Map<String, Object> config)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamOrganize iNetSamOrganize = new ImpNetSamOrganize();
				return iNetSamOrganize.insert(samOrganize,config);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("samOrganize", samOrganize);
				map.put("config",config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.Organize_Insert,
						SamFinal.ServerNamespace,SamFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	SamOrganize newOrganize=(SamOrganize)JsonUtil.convertObject(
			    			mapResult.get("samOrganize").toString(), SamOrganize.class);
			    	return newOrganize;
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
	public void update(SamOrganize samOrganize,Map<String, Object> config) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamOrganize iNetSamOrganize = new ImpNetSamOrganize();
				iNetSamOrganize.update(samOrganize,config);
				return;
			case 2:
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("samOrganize",samOrganize);
				map.put("config",config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.Organize_Update,
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
	public void delete(List<SamOrganize> samOrganizes,Map<String, Object> config)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamOrganize iNetSamOrganize = new ImpNetSamOrganize();
				iNetSamOrganize.delete(samOrganizes,config);
				return;
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("samOrganizes", samOrganizes);
				map.put("config",config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.Organize_Delete,
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
	public List<SamOrganize> queryByAll() throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamOrganize iNetSamOrganize = new ImpNetSamOrganize();
				return iNetSamOrganize.queryByAll();
			case 2: 
				//1.参数对象为JSONObject
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(null, SamFinal.Organize_QueryByAll,
						SamFinal.ServerNamespace,SamFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
					List<SamOrganize> organizes = (List<SamOrganize>)JsonUtil.convertArray(
							mapResult.get("samOrganizes").toString(), new SamOrganize());
					return organizes;
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
	public List<SamOrganize> queryPageByCustom(String organizeCode,String organizeName,
			String organizeLevel,String organizeType,String organizeStatus,int start,int limit) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamOrganize iNetSamOrganize = new ImpNetSamOrganize();
				return iNetSamOrganize.queryPageByCustom(organizeCode, organizeName, organizeLevel, organizeType, organizeStatus, start, limit);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeCode", organizeCode);
				map.put("organizeName", organizeName);
				map.put("organizeLevel", organizeLevel);
				map.put("organizeType", organizeType);
				map.put("organizeStatus", organizeStatus);
				map.put("start", start);
				map.put("limit", limit);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.Organize_QueryPageByCustom,
						SamFinal.ServerNamespace,SamFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
					List<SamOrganize> organizes = (List<SamOrganize>)JsonUtil.convertArray(
							mapResult.get("samOrganizes").toString(), new SamOrganize());
					return organizes;
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
	public List<SamOrganize> queryAllByCustom(String organizeCode,String organizeName,
			String organizeLevel,String organizeType,String organizeStatus) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamOrganize iNetSamOrganize = new ImpNetSamOrganize();
				return iNetSamOrganize.queryAllByCustom(organizeCode, organizeName, organizeLevel, organizeType, organizeStatus);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeCode", organizeCode);
				map.put("organizeName", organizeName);
				map.put("organizeLevel", organizeLevel);
				map.put("organizeType", organizeType);
				map.put("organizeStatus", organizeStatus);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.Organize_QueryAllByCustom,
						SamFinal.ServerNamespace,SamFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
					List<SamOrganize> organizes = (List<SamOrganize>)JsonUtil.convertArray(
							mapResult.get("samOrganizes").toString(), new SamOrganize());
					return organizes;
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
	public int queryCountByCustom(String organizeCode,String organizeName,
			String organizeLevel,String organizeType,String organizeStatus)throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamOrganize iNetSamOrganize = new ImpNetSamOrganize();
				return iNetSamOrganize.queryCountByCustom(organizeCode, organizeName, organizeLevel, organizeType, organizeStatus);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeCode", organizeCode);
				map.put("organizeName", organizeName);
				map.put("organizeLevel", organizeLevel);
				map.put("organizeType", organizeType);
				map.put("organizeStatus", organizeStatus);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.Organize_QueryCountByCustom,
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
	public List<SamOrganize> queryByOrganizeType(String organizeType)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamOrganize iNetSamOrganize = new ImpNetSamOrganize();
				return iNetSamOrganize.queryByOrganizeType(organizeType);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeType", organizeType);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.Organize_QueryAllByCustom,
						SamFinal.ServerNamespace,SamFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
					List<SamOrganize> organizes = (List<SamOrganize>)JsonUtil.convertArray(
							mapResult.get("samOrganizes").toString(), new SamOrganize());
					return organizes;
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
	public List<SamOrganize> queryDealByOrganizeSeq(String organizeSeq)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamOrganize iNetSamOrganize = new ImpNetSamOrganize();
				return iNetSamOrganize.queryDealByOrganizeSeq(organizeSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.Organize_QueryDealByOrganizeSeq,
						SamFinal.ServerNamespace,SamFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
					List<SamOrganize> organizes = (List<SamOrganize>)JsonUtil.convertArray(
							mapResult.get("samOrganizes").toString(), new SamOrganize());
					return organizes;
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