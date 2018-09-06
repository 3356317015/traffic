package com.zhima.traffic.action.operate.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.traffic.business.operate.INetItsLinerdeal;
import com.service.traffic.business.operate.impl.ImpNetItsLinerdeal;
import com.zhima.basic.CommFinal;
import com.zhima.basic.ItsFinal;
import com.zhima.basic.ServiceClient;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.action.operate.IItsLinerdeal;
import com.zhima.traffic.model.ItsLiner;
import com.zhima.traffic.model.ItsLinerdeal;
import com.zhima.util.MapUtil;

public class ImpItsLinerdeal implements IItsLinerdeal {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<ItsLinerdeal> queryByLinerSeq(String linerSeq)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetItsLinerdeal iNetItsLinerdeal = new ImpNetItsLinerdeal();
				return iNetItsLinerdeal.queryByLinerSeq(linerSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("linerSeq", linerSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, ItsFinal.ItsLinerdeal_QueryByLinerSeq,
						ItsFinal.ServerNamespace,ItsFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<ItsLinerdeal>)JsonUtil.convertArray(
			    			mapResult.get("itsLinerdeals").toString(), new ItsLinerdeal());
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
	public void updateAttribute(ItsLiner itsLiner,List<ItsLinerdeal> itsLinerdeals,
			Map<String, Object> config)throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetItsLinerdeal iNetItsLinerdeal = new ImpNetItsLinerdeal();
				iNetItsLinerdeal.updateAttribute(itsLiner, itsLinerdeals, config);
				return;
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("itsLiner", itsLiner);
				map.put("itsLinerdeals", itsLinerdeals);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, ItsFinal.ItsLinerdeal_UpdateAttribute,
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