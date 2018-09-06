package com.zhima.traffic.action.voice.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.traffic.business.voice.INetVmsSoundset;
import com.service.traffic.business.voice.impl.ImpNetVmsSoundset;
import com.zhima.basic.CommFinal;
import com.zhima.basic.ServiceClient;
import com.zhima.basic.VmsFinal;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.action.voice.IVmsSoundset;
import com.zhima.traffic.model.VmsSound;
import com.zhima.traffic.model.VmsSoundset;
import com.zhima.util.MapUtil;

/**
 * ImpEpdRegion概要说明：城市区域
 * @author lcy
 */
public class ImpVmsSoundset implements IVmsSoundset {
	
	@SuppressWarnings("rawtypes")
	@Override
	public void update(VmsSound vmsSound, List<VmsSoundset> vmsSoundsets,
			Map<String, Object> config)throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetVmsSoundset iNetVmsSoundset = new ImpNetVmsSoundset();
				iNetVmsSoundset.update(vmsSound, vmsSoundsets, config);
				return;
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("vmsSound", vmsSound);
				map.put("vmsSoundsets", vmsSoundsets);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, VmsFinal.VmsSoundset_update,
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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<VmsSoundset> queryBySoundSeq(String soundSeq) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetVmsSoundset iNetVmsSoundset = new ImpNetVmsSoundset();
				return iNetVmsSoundset.queryBySoundSeq(soundSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("soundSeq", soundSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, VmsFinal.VmsSoundset_queryBySoundSeq,
						VmsFinal.ServerNamespace,VmsFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<VmsSoundset>)JsonUtil.convertArray(
			    			mapResult.get("vmsSoundsets").toString(), new VmsSoundset());
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