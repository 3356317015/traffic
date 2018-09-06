
package com.service.traffic.action.its;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.comm.SecurityFinal;
import com.service.traffic.business.operate.INetItsLinercheck;
import com.service.traffic.business.operate.impl.ImpNetItsLinercheck;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.ItsLiner;
import com.zhima.traffic.model.ItsLinercheck;


public class ItsLinercheckAction {
	INetItsLinercheck iNetItsLinercheck = new ImpNetItsLinercheck();
	
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
	        List<ItsLinercheck> itsLinerchecks = iNetItsLinercheck.queryByLinerSeq(linerSeq);
			JSONArray jsonarray = JSONArray.fromObject(itsLinerchecks);
			result.put("itsLinerchecks", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}

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
	        
	        List<ItsLinercheck> itsLinerchecks = (List<ItsLinercheck>) JsonUtil.convertArray(
	        		mapParameter.get("itsLinerchecks").toString(), new ItsLinercheck());
	        
	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			iNetItsLinercheck.updateAttribute(itsLiner,itsLinerchecks, mapConfig);
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	
}
	
	
