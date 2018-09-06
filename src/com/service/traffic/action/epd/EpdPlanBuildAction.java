package com.service.traffic.action.epd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.comm.SecurityFinal;
import com.service.traffic.business.epd.INetEpdPlanBuild;
import com.service.traffic.business.epd.impl.ImpNetEpdPlanBuild;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.EpdPlan;
import com.zhima.traffic.model.ItsLiner;

public class EpdPlanBuildAction {
	INetEpdPlanBuild iNetEpdPlanBuild = new ImpNetEpdPlanBuild();
	public String queryByAll(String security) {
		Map<String, String> result = new HashMap<String, String>();
		result.put("ResultCode", "0");
		result.put("Message", "操作成功");
		try {
			//1.安全认证
			SecurityFinal.certification(security);
			//2.解析parameter
			
	        //3.调用业务
			List<EpdPlan> epdPlans = iNetEpdPlanBuild.queryByAll();
			JSONArray jsonarray = JSONArray.fromObject(epdPlans);
			result.put("epdPlans", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String planBuild(String security,String parameter) {
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
			String buildFare = String.valueOf(mapParameter.get("buildFare"));
	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			List<ItsLiner> itsLiners = iNetEpdPlanBuild.planBuild(epdPlan, buildFare, mapConfig);
			JSONArray jsonarray = JSONArray.fromObject(itsLiners);
			result.put("itsLiners", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	
}
