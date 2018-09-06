
package com.service.traffic.action.epd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.comm.SecurityFinal;
import com.service.traffic.business.epd.INetEpdPlancar;
import com.service.traffic.business.epd.impl.ImpNetEpdPlancar;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.EpdCar;
import com.zhima.traffic.model.EpdPlan;
import com.zhima.traffic.model.EpdPlancar;

public class EpdPlancarAction {
	INetEpdPlancar iNetEpdPlancar = new ImpNetEpdPlancar();
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

	        EpdPlancar epdPlancar =(EpdPlancar)JsonUtil.convertObject(
	        		mapParameter.get("epdPlancar").toString(),EpdPlancar.class);
	        
	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			EpdPlancar newEpdPlancar = iNetEpdPlancar.insert(epdPlancar, mapConfig);
	        result.put("epdPlancar", JSONObject.fromObject(newEpdPlancar).toString());
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

	        EpdPlancar epdPlancar =(EpdPlancar)JsonUtil.convertObject(
	        		mapParameter.get("epdPlancar").toString(),EpdPlancar.class);
	        
	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			iNetEpdPlancar.update(epdPlancar, mapConfig);
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

			List<EpdPlancar> epdPlancars = (List<EpdPlancar>)JsonUtil.convertArray(
					mapParameter.get("epdPlancars").toString(), new EpdPlancar());
	        
	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			iNetEpdPlancar.delete(epdPlancars, mapConfig);
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	
	@SuppressWarnings("rawtypes")
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
	        String plancarSeq = String.valueOf(mapParameter.get("plancarSeq"));
	        //3.调用业务
			List<EpdPlancar> epdPlancars = iNetEpdPlancar.queryByPK(plancarSeq);
			JSONArray jsonarray = JSONArray.fromObject(epdPlancars);
			result.put("epdPlancars", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	
	@SuppressWarnings("rawtypes")
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
			List<EpdPlancar> epdPlancars = iNetEpdPlancar.queryByPlanSeqAndPlanId(planSeq, planId);
			JSONArray jsonarray = JSONArray.fromObject(epdPlancars);
			result.put("epdPlancars", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String updateBatch(String security,String parameter) {
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
	        EpdPlan epdPlan =(EpdPlan)JsonUtil.convertObject(
	        		mapParameter.get("epdPlan").toString(),EpdPlan.class);
	        
			List<EpdCar> epdCars = (List<EpdCar>)JsonUtil.convertArray(
					mapParameter.get("epdCars").toString(), new EpdCar());
	        
	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        
	        //3.调用业务
			iNetEpdPlancar.updateBatch(epdPlan, epdCars, mapConfig);
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
}
