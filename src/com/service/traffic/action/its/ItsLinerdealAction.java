
package com.service.traffic.action.its;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.comm.SecurityFinal;
import com.service.traffic.business.operate.INetItsLinerdeal;
import com.service.traffic.business.operate.impl.ImpNetItsLinerdeal;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.ItsLiner;
import com.zhima.traffic.model.ItsLinerdeal;


public class ItsLinerdealAction {
	INetItsLinerdeal iNetItsLinerdeal = new ImpNetItsLinerdeal();

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
	        
	        List<ItsLinerdeal> itsLinerdeals = (List<ItsLinerdeal>) JsonUtil.convertArray(
	        		mapParameter.get("itsLinerdeals").toString(), new ItsLinerdeal());
	        
	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			iNetItsLinerdeal.updateAttribute(itsLiner, itsLinerdeals, mapConfig);
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
	        List<ItsLinerdeal> itsLinerdeals = iNetItsLinerdeal.queryByLinerSeq(linerSeq);
			JSONArray jsonarray = JSONArray.fromObject(itsLinerdeals);
			result.put("itsLinerdeals", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	
}
	
	
