
package com.service.traffic.action.epd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.comm.SecurityFinal;
import com.service.traffic.business.epd.INetEpdPlandeal;
import com.service.traffic.business.epd.impl.ImpNetEpdPlandeal;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.EpdPlandeal;

public class EpdPlandealAction {
	INetEpdPlandeal iNetEpdPlandeal = new ImpNetEpdPlandeal();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String insert(String security,String parameter) {
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

	        EpdPlandeal epdPlandeal =(EpdPlandeal)JsonUtil.convertObject(
	        		mapParameter.get("epdPlandeal").toString(),EpdPlandeal.class);

	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			EpdPlandeal newEpdPlandeal = iNetEpdPlandeal.insert(epdPlandeal,mapConfig);
	        result.put("epdPlandeal", JSONObject.fromObject(newEpdPlandeal).toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String update(String security,String parameter) {
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

	        EpdPlandeal epdPlandeal =(EpdPlandeal)JsonUtil.convertObject(
	        		mapParameter.get("epdPlandeal").toString(),EpdPlandeal.class);
	        
	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			iNetEpdPlandeal.update(epdPlandeal,mapConfig);
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String delete(String security,String parameter) {
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

			List<EpdPlandeal> epdPlandeals = (List<EpdPlandeal>)JsonUtil.convertArray(
					mapParameter.get("epdPlandeals").toString(), new EpdPlandeal());

	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			iNetEpdPlandeal.delete(epdPlandeals,mapConfig);
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	@SuppressWarnings({ "unused", "rawtypes" })
	public String queryByPK(String security,String parameter) {
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

	        String plandealSeq = String.valueOf(mapParameter.get("plandealSeq"));
	        
	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			List<EpdPlandeal> epdPlandeals = iNetEpdPlandeal.queryByPK(plandealSeq);
			JSONArray jsonarray = JSONArray.fromObject(epdPlandeals);
			result.put("epdPlandeals", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	
	@SuppressWarnings({ "rawtypes" })
	public String queryByPlanSeqAndPlanId(String security,String parameter) {
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

	        String planSeq = String.valueOf(mapParameter.get("planSeq"));
	        String planId = String.valueOf(mapParameter.get("planId"));

	        //3.调用业务
			List<EpdPlandeal> epdPlandeals = iNetEpdPlandeal.queryByPlanSeqAndPlanId(planSeq, planId);
			JSONArray jsonarray = JSONArray.fromObject(epdPlandeals);
			result.put("epdPlandeals", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
}
	
	
