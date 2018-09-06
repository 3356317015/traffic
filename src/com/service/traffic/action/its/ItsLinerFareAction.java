
package com.service.traffic.action.its;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.comm.SecurityFinal;
import com.service.traffic.business.operate.INetItsLinerfare;
import com.service.traffic.business.operate.impl.ImpNetItsLinerfare;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.ItsLinerfare;


public class ItsLinerFareAction {
	INetItsLinerfare iNetItsLinerfare = new ImpNetItsLinerfare();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String insert(String security, String parameter) {
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

	        ItsLinerfare itsLinerfare =(ItsLinerfare)JsonUtil.convertObject(
	        		mapParameter.get("itsLinerfare").toString(),ItsLinerfare.class);

	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			ItsLinerfare newItsLinerfare = iNetItsLinerfare.insert(itsLinerfare, mapConfig);
	        result.put("itsLinerfare", JSONObject.fromObject(newItsLinerfare).toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String update(String security, String parameter) {
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

	        ItsLinerfare itsLinerfare =(ItsLinerfare)JsonUtil.convertObject(
	        		mapParameter.get("itsLinerfare").toString(),ItsLinerfare.class);
	        
	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			iNetItsLinerfare.update(itsLinerfare, mapConfig);
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String delete(String security, String parameter) {
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

	        List<ItsLinerfare> itsLinerfares = (List<ItsLinerfare>) JsonUtil.convertArray(
	        		mapParameter.get("itsLinerfares").toString(), new ItsLinerfare());
	        
	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			iNetItsLinerfare.delete(itsLinerfares, mapConfig);
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}

	@SuppressWarnings("rawtypes")
	public String queryByPK(String security, String parameter) {
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
	        String linerfareSeq = String.valueOf(mapParameter.get("linerfareSeq"));
	        //3.调用业务
	        List<ItsLinerfare> itsLinerfares = iNetItsLinerfare.queryByPK(linerfareSeq);
			JSONArray jsonarray = JSONArray.fromObject(itsLinerfares);
			result.put("itsLinerfares", jsonarray.toString());
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
	        List<ItsLinerfare> itsLinerfares = iNetItsLinerfare.queryByLinerSeq(linerSeq);
			JSONArray jsonarray = JSONArray.fromObject(itsLinerfares);
			result.put("itsLinerfares", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}

	public String queryByAll(String security, String parameter) {
		Map<String, String> result = new HashMap<String, String>();
		result.put("ResultCode", "0");
		result.put("Message", "操作成功");
		try {
			//1.安全认证
			SecurityFinal.certification(security);
			//2.解析parameter
	        //3.调用业务
			List<ItsLinerfare> itsLinerfares = iNetItsLinerfare.queryByAll();
			JSONArray jsonarray = JSONArray.fromObject(itsLinerfares);
			result.put("itsLinerfares", jsonarray.toString());
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
	        String startDate = String.valueOf(mapParameter.get("startDate"));
	        String limitDate = String.valueOf(mapParameter.get("limitDate"));
	        int start = Integer.valueOf(mapParameter.get("start").toString());
	        int limit = Integer.valueOf(mapParameter.get("limit").toString());
	        //3.调用业务
	        List<ItsLinerfare> itsLinerfares = iNetItsLinerfare.queryPageByCustom(organizeSeq,
	        		routeSeq, stationSeq, linerId, startDate, limitDate, start, limit);
			JSONArray jsonarray = JSONArray.fromObject(itsLinerfares);
			result.put("itsLinerfares", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}

	@SuppressWarnings("rawtypes")
	public String queryAllByCustom(String security, String parameter) {
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
	        String startDate = String.valueOf(mapParameter.get("startDate"));
	        String limitDate = String.valueOf(mapParameter.get("limitDate"));
	        //3.调用业务
	        List<ItsLinerfare> itsLinerfares = iNetItsLinerfare.queryAllByCustom(organizeSeq,
    			routeSeq, stationSeq, linerId, startDate, limitDate);
			JSONArray jsonarray = JSONArray.fromObject(itsLinerfares);
			result.put("itsLinerfares", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}

	@SuppressWarnings("rawtypes")
	public String queryCountByCustom(String security, String parameter) {
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
	        String startDate = String.valueOf(mapParameter.get("startDate"));
	        String limitDate = String.valueOf(mapParameter.get("limitDate"));
	        //3.调用业务
			int count = iNetItsLinerfare.queryCountByCustom(organizeSeq,
	    			routeSeq, stationSeq, linerId, startDate, limitDate);
			result.put("count", String.valueOf(count));
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
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

	        List<ItsLinerfare> itsLinerfares = (List<ItsLinerfare>) JsonUtil.convertArray(
	        		mapParameter.get("itsLinerfares").toString(), new ItsLinerfare());
	        
	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			iNetItsLinerfare.updateAttribute(itsLinerfares, mapConfig);
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	
}
	
	
