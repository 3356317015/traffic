package com.zhima.traffic.action.epd.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.traffic.business.epd.INetEpdCarinfo;
import com.service.traffic.business.epd.impl.ImpNetEpdCarinfo;
import com.zhima.basic.CommFinal;
import com.zhima.basic.EpdFinal;
import com.zhima.basic.ServiceClient;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.action.epd.IEpdCarinfo;
import com.zhima.traffic.model.EpdCarinfo;
import com.zhima.util.MapUtil;

public class ImpEpdCarinfo implements IEpdCarinfo {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<EpdCarinfo> queryByCarSeq(String carSeq) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdCarinfo iNetEpdCarinfo = new ImpNetEpdCarinfo();
				return iNetEpdCarinfo.queryByCarSeq(carSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("carSeq", carSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdCarinfo_QueryByCarSeq,
						EpdFinal.ServerNamespace,EpdFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<EpdCarinfo>)JsonUtil.convertArray(
			    			mapResult.get("epdCarinfos").toString(), new EpdCarinfo());
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