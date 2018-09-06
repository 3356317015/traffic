
package com.service.traffic.action.epd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.comm.SecurityFinal;
import com.service.traffic.business.epd.INetEpdPlan;
import com.service.traffic.business.epd.impl.ImpNetEpdPlan;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.EpdCheckgate;
import com.zhima.traffic.model.EpdPlan;
import com.zhima.traffic.model.EpdPlandeal;
import com.zhima.traffic.model.EpdPlanservice;
import com.zhima.widget.seatBar.Seat;
import com.zhima.widget.stationBar.Station;

public class EpdPlanAction {
	INetEpdPlan iNetEpdPlan = new ImpNetEpdPlan();
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

	        EpdPlan epdPlan =(EpdPlan)JsonUtil.convertObject(
	        		mapParameter.get("epdPlan").toString(),EpdPlan.class);
	        
	        List<EpdCheckgate> checkgates = (List<EpdCheckgate>)JsonUtil.convertArray(
	        		mapParameter.get("epdCheckgates").toString(), new EpdCheckgate());

	        List<Station> stations = (List<Station>)JsonUtil.convertArray(
	        		mapParameter.get("stations").toString(), new Station());

	        List<Seat> seats = (List<Seat>)JsonUtil.convertArray(
	        		mapParameter.get("seats").toString(), new Seat());

	        List<EpdPlandeal> plandeals = (List<EpdPlandeal>)JsonUtil.convertArray(
	        		mapParameter.get("epdPlandeals").toString(), new EpdPlandeal());
	        
	        List<EpdPlanservice> planservices = (List<EpdPlanservice>)JsonUtil.convertArray(
	        		mapParameter.get("epdPlanservices").toString(), new EpdPlanservice());

	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			EpdPlan newEpdPlan = iNetEpdPlan.insert(epdPlan, checkgates, stations, seats, plandeals,
					planservices, mapConfig);
	        result.put("epdPlan", JSONObject.fromObject(newEpdPlan).toString());
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

	        EpdPlan epdPlan =(EpdPlan)JsonUtil.convertObject(
	        		mapParameter.get("epdPlan").toString(),EpdPlan.class);
	        
	        List<EpdCheckgate> checkgates = (List<EpdCheckgate>)JsonUtil.convertArray(
	        		mapParameter.get("epdCheckgates").toString(), new EpdCheckgate());

	        List<Station> stations = (List<Station>)JsonUtil.convertArray(
	        		mapParameter.get("stations").toString(), new Station());

	        List<Seat> seats = (List<Seat>)JsonUtil.convertArray(
	        		mapParameter.get("seats").toString(), new Seat());

	        List<EpdPlandeal> plandeals = (List<EpdPlandeal>)JsonUtil.convertArray(
	        		mapParameter.get("epdPlandeals").toString(), new EpdPlandeal());
	        
	        List<EpdPlanservice> planservices = (List<EpdPlanservice>)JsonUtil.convertArray(
	        		mapParameter.get("epdPlanservices").toString(), new EpdPlanservice());

	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			iNetEpdPlan.update(epdPlan, checkgates, stations, seats, plandeals,
					planservices, mapConfig);
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

	        List<EpdPlan> epdPlans = (List<EpdPlan>)JsonUtil.convertArray(
	        		mapParameter.get("epdPlans").toString(), new EpdPlan());

	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			iNetEpdPlan.delete(epdPlans, mapConfig);
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	
	@SuppressWarnings({ "rawtypes" })
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

	        String planSeq = String.valueOf(mapParameter.get("planSeq"));

	        //3.调用业务
			List<EpdPlan> epdPlans = iNetEpdPlan.queryByPK(planSeq);
			JSONArray jsonarray = JSONArray.fromObject(epdPlans);
			result.put("epdPlans", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	
	@SuppressWarnings({ "rawtypes" })
	public String queryByRouteSeq(String security,String parameter) {
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

	        String routeSeq = String.valueOf(mapParameter.get("routeSeq"));
	        //3.调用业务
			List<EpdPlan> epdPlans = iNetEpdPlan.queryByRouteSeq(routeSeq);
			JSONArray jsonarray = JSONArray.fromObject(epdPlans);
			result.put("epdPlans", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	
	@SuppressWarnings({ "rawtypes" })
	public String queryPageByCustom(String security,String parameter) {
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
	        
	        String organizeSeq = String.valueOf(mapParameter.get("organizeSeq"));
	        String routeSeq = String.valueOf(mapParameter.get("routeSeq"));
	        String stationSeq = String.valueOf(mapParameter.get("stationSeq"));
	        String planId = String.valueOf(mapParameter.get("planId"));
	        String planType = String.valueOf(mapParameter.get("planType"));
	        String planStatus = String.valueOf(mapParameter.get("planStatus"));
	        int start = Integer.valueOf(mapParameter.get("start").toString());
	        int limit = Integer.valueOf(mapParameter.get("limit").toString());

	        //3.调用业务
			List<EpdPlan> epdPlans = iNetEpdPlan.queryPageByCustom(organizeSeq,
					routeSeq, stationSeq, planId, planType, planStatus, start, limit);
			JSONArray jsonarray = JSONArray.fromObject(epdPlans);
			result.put("epdPlans", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	
	@SuppressWarnings({ "rawtypes" })
	public String queryAllByCustom(String security,String parameter) {
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

	        String organizeSeq = String.valueOf(mapParameter.get("organizeSeq"));
	        String routeSeq = String.valueOf(mapParameter.get("routeSeq"));
	        String stationSeq = String.valueOf(mapParameter.get("stationSeq"));
	        String planId = String.valueOf(mapParameter.get("planId"));
	        String planType = String.valueOf(mapParameter.get("planType"));
	        String planStatus = String.valueOf(mapParameter.get("planStatus"));

	        //3.调用业务
			List<EpdPlan> epdPlans = iNetEpdPlan.queryAllByCustom(organizeSeq,
					routeSeq, stationSeq, planId, planType, planStatus);
			JSONArray jsonarray = JSONArray.fromObject(epdPlans);
			result.put("epdPlans", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	
	@SuppressWarnings({ "rawtypes" })
	public String queryCountByCustom(String security,String parameter) {
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

	        String organizeSeq = String.valueOf(mapParameter.get("organizeSeq"));
	        String routeSeq = String.valueOf(mapParameter.get("routeSeq"));
	        String stationSeq = String.valueOf(mapParameter.get("stationSeq"));
	        String planId = String.valueOf(mapParameter.get("planId"));
	        String planType = String.valueOf(mapParameter.get("planType"));
	        String planStatus = String.valueOf(mapParameter.get("planStatus"));
	        
	        //3.调用业务
			int count = iNetEpdPlan.queryCountByCustom(organizeSeq,
					routeSeq, stationSeq, planId, planType, planStatus);
			result.put("count", String.valueOf(count));
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	public String queryByAll(String security) {
		Map<String, String> result = new HashMap<String, String>();
		result.put("ResultCode", "0");
		result.put("Message", "操作成功");
		try {
			//1.安全认证
			SecurityFinal.certification(security);
			//2.解析parameter
	        //3.调用业务
			List<EpdPlan> epdPlans = iNetEpdPlan.queryByAll();
			JSONArray jsonarray = JSONArray.fromObject(epdPlans);
			result.put("epdPlans", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	
	@SuppressWarnings({ "rawtypes" })
	public String queryByOrganizeSeq(String security,String parameter) {
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

	        String organizeSeq = String.valueOf(mapParameter.get("organizeSeq"));

	        //3.调用业务
			List<EpdPlan> epdPlans = iNetEpdPlan.queryByOrganizeSeq(organizeSeq);
			JSONArray jsonarray = JSONArray.fromObject(epdPlans);
			result.put("epdPlans", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
}
	
	
