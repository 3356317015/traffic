package com.zhima.traffic.action.operate.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.traffic.business.operate.INetItsLinercheck;
import com.service.traffic.business.operate.impl.ImpNetItsLinercheck;
import com.zhima.basic.CommFinal;
import com.zhima.basic.ItsFinal;
import com.zhima.basic.ServiceClient;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.action.operate.IItsLinercheck;
import com.zhima.traffic.model.ItsLiner;
import com.zhima.traffic.model.ItsLinercheck;
import com.zhima.util.MapUtil;

public class ImpItsLinercheck implements IItsLinercheck {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<ItsLinercheck> queryByLinerSeq(String linerSeq)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetItsLinercheck iNetItsLinercheck = new ImpNetItsLinercheck();
				return iNetItsLinercheck.queryByLinerSeq(linerSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("linerSeq", linerSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, ItsFinal.ItsLinercheck_QueryByLinerSeq,
						ItsFinal.ServerNamespace,ItsFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<ItsLinercheck>)JsonUtil.convertArray(
			    			mapResult.get("itsLinerchecks").toString(), new ItsLinercheck());
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
	public void updateAttribute(ItsLiner itsLiner, List<ItsLinercheck> itsLinerchecks,
			Map<String, Object> config)throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetItsLinercheck iNetItsLinercheck = new ImpNetItsLinercheck();
				iNetItsLinercheck.updateAttribute(itsLiner, itsLinerchecks, config);
				return;
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("itsLiner", itsLiner);
				map.put("itsLinerchecks", itsLinerchecks);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, ItsFinal.ItsLinercheck_UpdateAttribute,
						ItsFinal.ServerNamespace,ItsFinal.ServerAddres);
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
}