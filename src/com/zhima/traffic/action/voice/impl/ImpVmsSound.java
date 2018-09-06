package com.zhima.traffic.action.voice.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.traffic.business.voice.INetVmsSound;
import com.service.traffic.business.voice.impl.ImpNetVmsSound;
import com.zhima.basic.CommFinal;
import com.zhima.basic.ServiceClient;
import com.zhima.basic.VmsFinal;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.action.voice.IVmsSound;
import com.zhima.traffic.model.VmsSound;
import com.zhima.util.MapUtil;

/**
 * ImpEpdRegion概要说明：城市区域
 * @author lcy
 */
public class ImpVmsSound implements IVmsSound {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<VmsSound> inserts(List<VmsSound> vmsSounds, Map<String, Object> config
			)throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetVmsSound iNetVmsSound = new ImpNetVmsSound();
				return iNetVmsSound.inserts(vmsSounds, config);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("vmsSounds", vmsSounds);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, VmsFinal.VmsSound_Inserts,
						VmsFinal.ServerNamespace,VmsFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<VmsSound>)JsonUtil.convertArray(
			    			mapResult.get("vmsSounds").toString(), new VmsSound());
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
	public void update(VmsSound vmsSound, Map<String, Object> config)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetVmsSound iNetVmsSound = new ImpNetVmsSound();
				iNetVmsSound.update(vmsSound, config);
				return;
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("vmsSound", vmsSound);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, VmsFinal.VmsSound_Update,
						VmsFinal.ServerNamespace,VmsFinal.ServerAddres);
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
	public void delete(List<VmsSound> vmsSounds,Map<String, Object> config)throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetVmsSound iNetVmsSound = new ImpNetVmsSound();
				iNetVmsSound.delete(vmsSounds, config);
				return;
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("vmsSounds", vmsSounds);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, VmsFinal.VmsSound_Delete,
						VmsFinal.ServerNamespace,VmsFinal.ServerAddres);
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
	public List<VmsSound> queryByPK(String soundSeq) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetVmsSound iNetVmsSound = new ImpNetVmsSound();
				return iNetVmsSound.queryByPK(soundSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("soundSeq", soundSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, VmsFinal.VmsSound_QueryByPK,
						VmsFinal.ServerNamespace,VmsFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<VmsSound>)JsonUtil.convertArray(
			    			mapResult.get("vmsSounds").toString(), new VmsSound());
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
	public List<VmsSound> queryByOrganizeSeq(String organizeSeq)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetVmsSound iNetVmsSound = new ImpNetVmsSound();
				return iNetVmsSound.queryByOrganizeSeq(organizeSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, VmsFinal.VmsSound_QueryByOrganizeSeq,
						VmsFinal.ServerNamespace,VmsFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<VmsSound>)JsonUtil.convertArray(
			    			mapResult.get("vmsSounds").toString(), new VmsSound());
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
	public List<VmsSound> queryPageByCustom(String organizeSeq, String soundType,
			int start,int limit) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetVmsSound iNetVmsSound = new ImpNetVmsSound();
				return iNetVmsSound.queryPageByCustom(organizeSeq,
						soundType, start, limit);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				map.put("soundType", soundType);
				map.put("start", start);
				map.put("limit", limit);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, VmsFinal.VmsSound_QueryPageByCustom,
						VmsFinal.ServerNamespace,VmsFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<VmsSound>)JsonUtil.convertArray(
			    			mapResult.get("vmsSounds").toString(), new VmsSound());
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
	public List<VmsSound> queryAllByCustom(String organizeSeq, String soundType
			) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetVmsSound iNetVmsSound = new ImpNetVmsSound();
				return iNetVmsSound.queryAllByCustom(organizeSeq,soundType);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				map.put("soundType", soundType);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, VmsFinal.VmsSound_QueryAllByCustom,
						VmsFinal.ServerNamespace,VmsFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<VmsSound>)JsonUtil.convertArray(
			    			mapResult.get("vmsSounds").toString(), new VmsSound());
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
	public int queryCountByCustom(String organizeSeq, String soundType
			) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetVmsSound iNetVmsSound = new ImpNetVmsSound();
				return iNetVmsSound.queryCountByCustom(organizeSeq, soundType);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				map.put("soundType", soundType);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, VmsFinal.VmsSound_QueryCountByCustom,
						VmsFinal.ServerNamespace,VmsFinal.ServerAddres);
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
	public List<VmsSound> queryByStatusAndTime(String organizeSeq, String soundStatus,String currTime
			) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetVmsSound iNetVmsSound = new ImpNetVmsSound();
				return iNetVmsSound.queryByStatusAndTime(organizeSeq,soundStatus,currTime);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				map.put("soundStatus", soundStatus);
				map.put("currTime", currTime);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, VmsFinal.VmsSound_QueryByStatusAndTime,
						VmsFinal.ServerNamespace,VmsFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<VmsSound>)JsonUtil.convertArray(
			    			mapResult.get("vmsSounds").toString(), new VmsSound());
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