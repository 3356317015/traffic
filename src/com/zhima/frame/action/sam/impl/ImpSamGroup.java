package com.zhima.frame.action.sam.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.traffic.business.sam.INetSamGroup;
import com.service.traffic.business.sam.impl.ImpNetSamGroup;
import com.zhima.basic.CommFinal;
import com.zhima.basic.SamFinal;
import com.zhima.basic.ServiceClient;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.action.sam.ISamGroup;
import com.zhima.frame.model.SamGroup;
import com.zhima.util.MapUtil;

/**
 * ImpSamGroup概要说明：角色管理接口
 * @author lcy
 */
public class ImpSamGroup implements ISamGroup {
	
	@SuppressWarnings("rawtypes")
	@Override
	public SamGroup insert(SamGroup samGroup,Map<String, Object> config) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamGroup iNetSamGroup = new ImpNetSamGroup();
				return iNetSamGroup.insert(samGroup, config);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("samGroup", samGroup);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.Group_Insert,
						SamFinal.ServerNamespace,SamFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	SamGroup newGroup=(SamGroup)JsonUtil.convertObject(
			    			mapResult.get("samGroup").toString(), SamGroup.class);
			    	return newGroup;
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
	public void update(SamGroup samGroup,Map<String, Object> config) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamGroup iNetSamGroup = new ImpNetSamGroup();
				iNetSamGroup.update(samGroup, config);
				return;
			case 2:
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("samGroup", samGroup);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.Group_Update,
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
	public void deleteByPk(List<SamGroup> samGroups,Map<String, Object> config) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamGroup iNetSamGroup = new ImpNetSamGroup();
				iNetSamGroup.deleteByPk(samGroups,config);
				return;
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("samGroups", samGroups);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.Group_DeleteByPk,
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
	public List<SamGroup> queryByOrganizeSeq(String organizeSeq)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamGroup iNetSamGroup = new ImpNetSamGroup();
				return iNetSamGroup.queryByOrganizeSeq(organizeSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.Group_QueryByOrganizeSeq,
						SamFinal.ServerNamespace,SamFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
					List<SamGroup> groups = (List<SamGroup>)JsonUtil.convertArray(
							mapResult.get("samGroups").toString(), new SamGroup());
					return groups;
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