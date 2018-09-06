
package com.service.traffic.action.its;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.comm.SecurityFinal;
import com.service.traffic.business.operate.INetItsLiner;
import com.service.traffic.business.operate.impl.ImpNetItsLiner;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.ItsLiner;
import com.zhima.traffic.model.ItsLinercheck;
import com.zhima.traffic.model.ItsLinerdeal;
import com.zhima.traffic.model.ItsLinerfare;
import com.zhima.traffic.model.ItsLinerseat;
import com.zhima.traffic.model.ItsLinerservice;
import com.zhima.traffic.model.ItsLinerstation;

public class ItsLinerAction {
	INetItsLiner iNetItsLiner = new ImpNetItsLiner();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String insert(String security, String parameter){
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
	        List<ItsLinerstation> itsLinerstations = (List<ItsLinerstation>) JsonUtil.convertArray(
	        		mapParameter.get("itsLinerstations").toString(),ItsLinerstation.class);
	        List<ItsLinerseat> itsLinerseats = (List<ItsLinerseat>) JsonUtil.convertArray(
	        		mapParameter.get("itsLinerseats").toString(),ItsLinerseat.class);
	        List<ItsLinerfare> itsLinerfares = (List<ItsLinerfare>) JsonUtil.convertArray(
	        		mapParameter.get("itsLinerfares").toString(),ItsLinerfare.class);
	        List<ItsLinercheck> itsLinerchecks = (List<ItsLinercheck>) JsonUtil.convertArray(
	        		mapParameter.get("itsLinerchecks").toString(),ItsLinercheck.class);
	        List<ItsLinerdeal> itsLinerdeals = (List<ItsLinerdeal>) JsonUtil.convertArray(
	        		mapParameter.get("itsLinerdeals").toString(),ItsLinerdeal.class);
	        List<ItsLinerservice> itsLinerservices = (List<ItsLinerservice>) JsonUtil.convertArray(
	        		mapParameter.get("itsLinerservices").toString(),ItsLinerservice.class);
	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			ItsLiner newItsLiner = iNetItsLiner.insert(itsLiner, itsLinerstations, itsLinerseats,
					itsLinerfares, itsLinerchecks, itsLinerdeals, itsLinerservices, mapConfig);
	        result.put("itsLiner", JSONObject.fromObject(newItsLiner).toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String updateLiner(String security, String parameter){
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

	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			iNetItsLiner.updateLiner(itsLiner, mapConfig);
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String delete(String security, String parameter){
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

	        List<ItsLiner> itsLiners =(List<ItsLiner>)JsonUtil.convertObject(
	        		mapParameter.get("itsLiners").toString(),ItsLiner.class);

	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			iNetItsLiner.delete(itsLiners, mapConfig);
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}

	@SuppressWarnings("rawtypes")
	public String queryByPK(String security, String parameter){
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
	        List<ItsLiner> itsLiners = iNetItsLiner.queryByPK(linerSeq);
			JSONArray jsonarray = JSONArray.fromObject(itsLiners);
			result.put("itsLiners", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}

	@SuppressWarnings("rawtypes")
	public String queryPageByCustom(String security, String parameter) {
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
	        String linerId = String.valueOf(mapParameter.get("linerId"));
	        String cargradeSeq = String.valueOf(mapParameter.get("cargradeSeq"));
	        String linerStatus = String.valueOf(mapParameter.get("linerStatus"));
	        String ifReport = String.valueOf(mapParameter.get("ifReport"));
	        String startDate = String.valueOf(mapParameter.get("startDate"));
	        String limitDate = String.valueOf(mapParameter.get("limitDate"));
	        Integer start = Integer.valueOf(mapParameter.get("start").toString());
	        Integer limit = Integer.valueOf(mapParameter.get("limit").toString());
	        //3.调用业务
	        List<ItsLiner> itsLiners = iNetItsLiner.queryPageByCustom(organizeSeq, routeSeq,
	        		stationSeq, linerId, cargradeSeq, linerStatus,
	        		ifReport, startDate, limitDate, start, limit);
			JSONArray jsonarray = JSONArray.fromObject(itsLiners);
			result.put("itsLiners", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}

	@SuppressWarnings("rawtypes")
	public String queryCountByCustom(String security, String parameter){
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
	        String linerId = String.valueOf(mapParameter.get("linerId"));
	        String cargradeSeq = String.valueOf(mapParameter.get("cargradeSeq"));
	        String linerStatus = String.valueOf(mapParameter.get("linerStatus"));
	        String ifReport = String.valueOf(mapParameter.get("ifReport"));
	        String startDate = String.valueOf(mapParameter.get("startDate"));
	        String limitDate = String.valueOf(mapParameter.get("limitDate"));
	        //3.调用业务
	        int count = iNetItsLiner.queryCountByCustom(organizeSeq, routeSeq, stationSeq,
	        		linerId, cargradeSeq, linerStatus, ifReport,
	        		startDate, limitDate);
			result.put("count", String.valueOf(count));
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String updateAttribute(String security, String parameter){
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

	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			iNetItsLiner.updateAttribute(itsLiner, mapConfig);
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
}
	
	
