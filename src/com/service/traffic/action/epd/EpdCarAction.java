
package com.service.traffic.action.epd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.comm.SecurityFinal;
import com.service.traffic.business.epd.INetEpdCar;
import com.service.traffic.business.epd.impl.ImpNetEpdCar;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.EpdCar;
import com.zhima.traffic.model.EpdCardriver;
import com.zhima.traffic.model.EpdCarinfo;


public class EpdCarAction {
	INetEpdCar iNetEpdCar = new ImpNetEpdCar();
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

	        EpdCar epdCar =(EpdCar) JsonUtil.convertObject(
	        		mapParameter.get("epdCar").toString(), EpdCar.class);
	        List<EpdCarinfo> epdCarinfos = (List<EpdCarinfo>) JsonUtil.convertArray(
	        		mapParameter.get("epdCarinfos").toString(), new EpdCarinfo());
	        List<EpdCardriver> epdCardrivers = (List<EpdCardriver>) JsonUtil.convertArray(
	        		mapParameter.get("epdCardrivers").toString(), new EpdCardriver());
	        
	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			EpdCar newEpdCar = iNetEpdCar.insert(epdCar,epdCarinfos,epdCardrivers,mapConfig);
	        result.put("epdCar", JSONObject.fromObject(newEpdCar).toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
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

	        EpdCar epdCar =(EpdCar)JsonUtil.convertObject(
	        		mapParameter.get("epdCar").toString(), EpdCar.class);
			List<EpdCarinfo> epdCarinfos = (List<EpdCarinfo>) JsonUtil.convertArray(
					mapParameter.get("epdCarinfos").toString(), new EpdCarinfo());
			List<EpdCardriver> epdCardrivers = (List<EpdCardriver>)JsonUtil.convertArray(
					mapParameter.get("epdCardrivers").toString(), new EpdCardriver());
			List<EpdCardriver> delCardrivers = (List<EpdCardriver>)JsonUtil.convertArray(
					mapParameter.get("delCardrivers").toString(), new EpdCardriver());
	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			iNetEpdCar.update(epdCar, epdCarinfos, epdCardrivers, delCardrivers, mapConfig);
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

			List<EpdCar> epdCars = (List<EpdCar>)JsonUtil.convertArray(
					mapParameter.get("epdCars").toString(), new EpdCar());

	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			iNetEpdCar.delete(epdCars, mapConfig);
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	
	@SuppressWarnings("rawtypes")
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
	        String carCode = String.valueOf(mapParameter.get("carCode"));
	        String carNumber = String.valueOf(mapParameter.get("carNumber"));
	        String companyName = String.valueOf(mapParameter.get("companyName"));
	        String status = String.valueOf(mapParameter.get("status"));
	        int start = Integer.valueOf(mapParameter.get("start").toString());
	        int limit = Integer.valueOf(mapParameter.get("limit").toString());
	        //3.调用业务
			List<EpdCar> epdCars = iNetEpdCar.queryPageByCustom(organizeSeq, routeSeq, carCode,
					carNumber, companyName, status, start, limit);
			JSONArray jsonarray = JSONArray.fromObject(epdCars);
			result.put("epdCars", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	
	@SuppressWarnings("rawtypes")
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
	        String carCode = String.valueOf(mapParameter.get("carCode"));
	        String carNumber = String.valueOf(mapParameter.get("carNumber"));
	        String companyName = String.valueOf(mapParameter.get("companyName"));
	        String status = String.valueOf(mapParameter.get("status"));
	        //3.调用业务
			List<EpdCar> epdCars = iNetEpdCar.queryAllByCustom(organizeSeq, routeSeq, carCode,
					carNumber, companyName, status);
			JSONArray jsonarray = JSONArray.fromObject(epdCars);
			result.put("epdCars", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	
	@SuppressWarnings("rawtypes")
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
	        String carCode = String.valueOf(mapParameter.get("carCode"));
	        String carNumber = String.valueOf(mapParameter.get("carNumber"));
	        String companyName = String.valueOf(mapParameter.get("companyName"));
	        String status = String.valueOf(mapParameter.get("status"));
	        //3.调用业务
			int count = iNetEpdCar.queryCountByCustom(organizeSeq, routeSeq, carCode,
					carNumber, companyName, status);
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
			List<EpdCar> epdCars = iNetEpdCar.queryByAll();
			JSONArray jsonarray = JSONArray.fromObject(epdCars);
			result.put("epdCars", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	
	@SuppressWarnings("rawtypes")
	public String queryByPlanId(String security,String parameter) {
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
	        String planId = String.valueOf(mapParameter.get("planId"));
	        //3.调用业务
			List<EpdCar> epdCars = iNetEpdCar.queryByPlanId(organizeSeq, planId);
			JSONArray jsonarray = JSONArray.fromObject(epdCars);
			result.put("epdCars", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}

	@SuppressWarnings("rawtypes")
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
			List<EpdCar> epdCars = iNetEpdCar.queryByRouteSeq(routeSeq);
			JSONArray jsonarray = JSONArray.fromObject(epdCars);
			result.put("epdCars", jsonarray.toString());
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
			List<EpdCar> epdCars = iNetEpdCar.queryByOrganizeSeq(organizeSeq);
			JSONArray jsonarray = JSONArray.fromObject(epdCars);
			result.put("epdCars", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
}
	
	
