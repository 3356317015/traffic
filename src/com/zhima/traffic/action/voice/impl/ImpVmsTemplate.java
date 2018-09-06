package com.zhima.traffic.action.voice.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.traffic.business.voice.INetVmsTemplate;
import com.service.traffic.business.voice.impl.ImpNetVmsTemplate;
import com.zhima.basic.CommFinal;
import com.zhima.basic.ServiceClient;
import com.zhima.basic.VmsFinal;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.action.voice.IVmsTemplate;
import com.zhima.traffic.model.VmsTemplate;
import com.zhima.util.MapUtil;

public class ImpVmsTemplate implements IVmsTemplate {

	@SuppressWarnings("rawtypes")
	@Override
	public VmsTemplate insert(VmsTemplate vmsTemplate,
			Map<String, Object> config) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetVmsTemplate iNetVmsTemplate = new ImpNetVmsTemplate();
				return iNetVmsTemplate.insert(vmsTemplate, config);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("vmsTemplate", vmsTemplate);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, VmsFinal.VmsTemplate_Insert,
						VmsFinal.ServerNamespace,VmsFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (VmsTemplate)JsonUtil.convertObject(
			    			mapResult.get("vmsTemplate").toString(), VmsTemplate.class);
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
	public void update(List<VmsTemplate> vmsTemplates,Map<String, Object> config)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetVmsTemplate iNetVmsTemplate = new ImpNetVmsTemplate();
				iNetVmsTemplate.update(vmsTemplates, config);
				return;
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("vmsTemplates", vmsTemplates);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, VmsFinal.VmsTemplate_Update,
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
	
	@SuppressWarnings("rawtypes")
	@Override
	public void delete(List<VmsTemplate> vmsTemplates,
			Map<String, Object> config) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetVmsTemplate iNetVmsTemplate = new ImpNetVmsTemplate();
				iNetVmsTemplate.delete(vmsTemplates, config);
				return;
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("vmsTemplates", vmsTemplates);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, VmsFinal.VmsTemplate_Delete,
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
	public List<VmsTemplate> queryByOrganizeAndType(String organizeSeq,String templateType) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetVmsTemplate iNetVmsTemplate = new ImpNetVmsTemplate();
				return iNetVmsTemplate.queryByOrganizeAndType(organizeSeq,templateType);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				map.put("templateType", templateType);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, VmsFinal.VmsTemplate_QueryByOrganizeAndType,
						VmsFinal.ServerNamespace,VmsFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<VmsTemplate>)JsonUtil.convertArray(
			    			mapResult.get("vmsTemplates").toString(), new VmsTemplate());
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