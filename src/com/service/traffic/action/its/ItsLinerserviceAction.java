
package com.service.traffic.action.its;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.comm.SecurityFinal;
import com.service.traffic.business.operate.INetItsLinerservice;
import com.service.traffic.business.operate.impl.ImpNetItsLinerservice;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.ItsLiner;
import com.zhima.traffic.model.ItsLinerservice;


public class ItsLinerserviceAction {
	INetItsLinerservice iNetItsLinerservice = new ImpNetItsLinerservice();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String updateAttribute(String security, String parameter) {
		Map<String, String> result = new HashMap<String, String>();
		result.put("ResultCode", "0");
		result.put("Message", "操作成功");
		try {
			//1.安全认证
			SecurityFinal.certification(security);
			//2.解析parameter
			String strParameter = JSONObject.fromObject(parameter).toString();
	        JSONObject jsonParameter = JSONObject.fromObject(strParameter);
	        Map mapParameter = (Map)jsonParameter;
	        
	        ItsLiner itsLiner =(ItsLiner)JsonUtil.convertObject(
	        		mapParameter.get("itsLiner").toString(),ItsLiner.class);
	        
	        List<ItsLinerservice> itsLinerservices = (List<ItsLinerservice>) JsonUtil.convertArray(
	        		mapParameter.get("itsLinerservices").toString(), new ItsLinerservice());
	        
	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			iNetItsLinerservice.updateAttribute(itsLiner, itsLinerservices, mapConfig);
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}

	@SuppressWarnings("rawtypes")
	public String queryByLinerSeq(String security, String parameter) {
		Map<String, String> result = new HashMap<String, String>();
		result.put("ResultCode", "0");
		result.put("Message", "操作成功");
		try {
			//1.安全认证
			SecurityFinal.certification(security);
			//2.解析parameter
			String strParameter = JSONObject.fromObject(parameter).toString();
	        JSONObject jsonParameter = JSONObject.fromObject(strParameter);
	        Map mapParameter = (Map)jsonParameter;
	        String linerSeq = String.valueOf(mapParameter.get("linerSeq"));
	        //3.调用业务
	        List<ItsLinerservice> itsLinerservices = iNetItsLinerservice.queryByLinerSeq(linerSeq);
			JSONArray jsonarray = JSONArray.fromObject(itsLinerservices);
			result.put("itsLinerservices", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	
}
	
	
