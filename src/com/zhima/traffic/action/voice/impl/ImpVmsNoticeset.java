package com.zhima.traffic.action.voice.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.traffic.business.voice.INetVmsNoticeset;
import com.service.traffic.business.voice.impl.ImpNetVmsNoticeset;
import com.zhima.basic.CommFinal;
import com.zhima.basic.ServiceClient;
import com.zhima.basic.VmsFinal;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.action.voice.IVmsNoticeset;
import com.zhima.traffic.model.VmsNotice;
import com.zhima.traffic.model.VmsNoticeset;
import com.zhima.util.MapUtil;

/**
 * ImpEpdRegion概要说明：城市区域
 * @author lcy
 */
public class ImpVmsNoticeset implements IVmsNoticeset {
	
	@SuppressWarnings("rawtypes")
	@Override
	public void update(VmsNotice vmsNotice, List<VmsNoticeset> vmsNoticesets,
			Map<String, Object> config)throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetVmsNoticeset iNetVmsNoticeset = new ImpNetVmsNoticeset();
				iNetVmsNoticeset.update(vmsNotice, vmsNoticesets, config);
				return;
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("vmsNotice", vmsNotice);
				map.put("vmsNoticesets", vmsNoticesets);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, VmsFinal.VmsNoticeset_update,
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
	public List<VmsNoticeset> queryByNoticeSeq(String noticeSeq) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetVmsNoticeset iNetVmsNoticeset = new ImpNetVmsNoticeset();
				return iNetVmsNoticeset.queryByNoticeSeq(noticeSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("noticeSeq", noticeSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, VmsFinal.VmsNoticeset_queryByNoticeSeq,
						VmsFinal.ServerNamespace,VmsFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<VmsNoticeset>)JsonUtil.convertArray(
			    			mapResult.get("vmsNoticesets").toString(), new VmsNoticeset());
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