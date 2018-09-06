package com.zhima.traffic.action.voice.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.traffic.business.voice.INetVmsNotice;
import com.service.traffic.business.voice.impl.ImpNetVmsNotice;
import com.zhima.basic.CommFinal;
import com.zhima.basic.ServiceClient;
import com.zhima.basic.VmsFinal;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.action.voice.IVmsNotice;
import com.zhima.traffic.model.VmsNotice;
import com.zhima.util.MapUtil;

/**
 * ImpEpdRegion概要说明：城市区域
 * @author lcy
 */
public class ImpVmsNotice implements IVmsNotice {

	@SuppressWarnings("rawtypes")
	@Override
	public VmsNotice insert(VmsNotice vmsNotice, Map<String, Object> config)throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetVmsNotice iNetVmsNotice = new ImpNetVmsNotice();
				return iNetVmsNotice.insert(vmsNotice, config);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("vmsNotice", vmsNotice);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, VmsFinal.VmsNotice_Insert,
						VmsFinal.ServerNamespace,VmsFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (VmsNotice)JsonUtil.convertObject(
			    			mapResult.get("vmsNotice").toString(), VmsNotice.class);
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
	public void update(VmsNotice vmsNotice, Map<String, Object> config)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetVmsNotice iNetVmsNotice = new ImpNetVmsNotice();
				iNetVmsNotice.update(vmsNotice, config);
				return;
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("vmsNotice", vmsNotice);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, VmsFinal.VmsNotice_Update,
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
	public void delete(List<VmsNotice> vmsNotices,Map<String, Object> config)throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetVmsNotice iNetVmsNotice = new ImpNetVmsNotice();
				iNetVmsNotice.delete(vmsNotices, config);
				return;
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("vmsNotices", vmsNotices);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, VmsFinal.VmsNotice_Delete,
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
	public List<VmsNotice> queryByPK(String noticeSeq) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetVmsNotice iNetVmsNotice = new ImpNetVmsNotice();
				return iNetVmsNotice.queryByPK(noticeSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("noticeSeq", noticeSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, VmsFinal.VmsNotice_QueryByPK,
						VmsFinal.ServerNamespace,VmsFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<VmsNotice>)JsonUtil.convertArray(
			    			mapResult.get("vmsNotices").toString(), new VmsNotice());
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
	public List<VmsNotice> queryPageByOrganizeSeq(String organizeSeq,
			int start, int limit) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetVmsNotice iNetVmsNotice = new ImpNetVmsNotice();
				return iNetVmsNotice.queryPageByOrganizeSeq(organizeSeq,start,limit);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				map.put("start", start);
				map.put("limit", limit);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, VmsFinal.VmsNotice_QueryPageByOrganizeSeq,
						VmsFinal.ServerNamespace,VmsFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<VmsNotice>)JsonUtil.convertArray(
			    			mapResult.get("vmsNotices").toString(), new VmsNotice());
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
	public List<VmsNotice> queryByOrganizeSeq(String organizeSeq)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetVmsNotice iNetVmsNotice = new ImpNetVmsNotice();
				return iNetVmsNotice.queryByOrganizeSeq(organizeSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, VmsFinal.VmsNotice_QueryByOrganizeSeq,
						VmsFinal.ServerNamespace,VmsFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<VmsNotice>)JsonUtil.convertArray(
			    			mapResult.get("vmsNotices").toString(), new VmsNotice());
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
	public int queryCountByOrganizeSeq(String organizeSeq)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetVmsNotice iNetVmsNotice = new ImpNetVmsNotice();
				return iNetVmsNotice.queryCountByOrganizeSeq(organizeSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, VmsFinal.VmsNotice_QueryCountByOrganizeSeq,
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
	public List<VmsNotice> queryPageByCustom(String organizeSeq, String noticeName,
			String noticeStatus,int start,int limit) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetVmsNotice iNetVmsNotice = new ImpNetVmsNotice();
				return iNetVmsNotice.queryPageByCustom(organizeSeq,
						noticeName, noticeStatus, start, limit);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				map.put("noticeName", noticeName);
				map.put("noticeStatus", noticeStatus);
				map.put("start", start);
				map.put("limit", limit);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, VmsFinal.VmsNotice_QueryPageByCustom,
						VmsFinal.ServerNamespace,VmsFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<VmsNotice>)JsonUtil.convertArray(
			    			mapResult.get("vmsNotices").toString(), new VmsNotice());
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
	public List<VmsNotice> queryAllByCustom(String organizeSeq, String noticeName,
			String noticeStatus) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetVmsNotice iNetVmsNotice = new ImpNetVmsNotice();
				return iNetVmsNotice.queryAllByCustom(organizeSeq,
						noticeName, noticeStatus);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				map.put("noticeName", noticeName);
				map.put("noticeStatus", noticeStatus);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, VmsFinal.VmsNotice_QueryAllByCustom,
						VmsFinal.ServerNamespace,VmsFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<VmsNotice>)JsonUtil.convertArray(
			    			mapResult.get("vmsNotices").toString(), new VmsNotice());
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
	public int queryCountByCustom(String organizeSeq, String noticeName,
			String noticeStatus) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetVmsNotice iNetVmsNotice = new ImpNetVmsNotice();
				return iNetVmsNotice.queryCountByCustom(organizeSeq,
						noticeName, noticeStatus);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				map.put("noticeName", noticeName);
				map.put("noticeStatus", noticeStatus);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, VmsFinal.VmsNotice_QueryCountByCustom,
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
	public List<VmsNotice> queryByStatusAndTime(String organizeSeq,
			String noticeStatus, String currTime) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetVmsNotice iNetVmsNotice = new ImpNetVmsNotice();
				return iNetVmsNotice.queryByStatusAndTime(organizeSeq,
						noticeStatus, currTime);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				map.put("noticeStatus", noticeStatus);
				map.put("currTime", currTime);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, VmsFinal.VmsNotice_QueryByStatusAndTime,
						VmsFinal.ServerNamespace,VmsFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<VmsNotice>)JsonUtil.convertArray(
			    			mapResult.get("vmsNotices").toString(), new VmsNotice());
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