package com.zhima.traffic.action.epd.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.traffic.business.epd.INetEpdDriverinfo;
import com.service.traffic.business.epd.impl.ImpNetEpdDriverinfo;
import com.zhima.basic.CommFinal;
import com.zhima.basic.EpdFinal;
import com.zhima.basic.ServiceClient;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.action.epd.IEpdDriverinfo;
import com.zhima.traffic.model.EpdDriverinfo;
import com.zhima.util.MapUtil;

public class ImpEpdDriverinfo implements IEpdDriverinfo {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<EpdDriverinfo> queryByDriverSeq(String driverSeq) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdDriverinfo iNetEpdDriverinfo = new ImpNetEpdDriverinfo();
				return iNetEpdDriverinfo.queryByDriverSeq(driverSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("driverSeq", driverSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdDriverinfo_QueryByDriverSeq,
						EpdFinal.ServerNamespace,EpdFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<EpdDriverinfo>)JsonUtil.convertArray(
			    			mapResult.get("epdDriverinfos").toString(), new EpdDriverinfo());
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