
package com.service.traffic.action.voice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.comm.SecurityFinal;
import com.service.traffic.business.voice.INetVmsLiner;
import com.service.traffic.business.voice.impl.ImpNetVmsLiner;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.VmsLiner;

/**
 * VmsLinerAction概要说明：播音班次
 * @author lcy
 */
public class VmsLinerAction {
	
	INetVmsLiner iNetVmsLiner = new ImpNetVmsLiner();
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

	        VmsLiner vmsLiner =(VmsLiner)JsonUtil.convertObject(
	        		mapParameter.get("vmsLiner").toString(),VmsLiner.class);
	        
	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			VmsLiner newVmsLiner = iNetVmsLiner.insert(vmsLiner, mapConfig);
	        result.put("vmsLiner", JSONObject.fromObject(newVmsLiner).toString());
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

	        VmsLiner vmsLiner =(VmsLiner)JsonUtil.convertObject(
	        		mapParameter.get("vmsLiner").toString(),VmsLiner.class);
			
	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			iNetVmsLiner.update(vmsLiner, mapConfig);
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

			List<VmsLiner> vmsLiners = (List<VmsLiner>)JsonUtil.convertArray(
					mapParameter.get("vmsLiners").toString(), new VmsLiner());

	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			iNetVmsLiner.delete(vmsLiners, mapConfig);
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String deleteByLinerDate(String security,String parameter) {
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
	        String linerDate = String.valueOf(mapParameter.get("linerDate"));

	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			iNetVmsLiner.deleteByLinerDate(organizeSeq, linerDate, mapConfig);
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
	        String linerSeq = String.valueOf(mapParameter.get("linerSeq"));
	        //3.调用业务
	        List<VmsLiner> vmsLiners = iNetVmsLiner.queryByPK(linerSeq);
			JSONArray jsonarray = JSONArray.fromObject(vmsLiners);
			result.put("vmsLiners", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}

	@SuppressWarnings("rawtypes")
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
	        List<VmsLiner> vmsLiners = iNetVmsLiner.queryByOrganizeSeq(organizeSeq);
			JSONArray jsonarray = JSONArray.fromObject(vmsLiners);
			result.put("vmsLiners", jsonarray.toString());
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
	        String routeCode = String.valueOf(mapParameter.get("routeCode"));
	        String linerId = String.valueOf(mapParameter.get("linerId"));
	        String reportStatus = String.valueOf(mapParameter.get("reportStatus"));
	        String printbillStatus = String.valueOf(mapParameter.get("printbillStatus"));
	        int start = Integer.valueOf(mapParameter.get("start").toString());
	        int limit = Integer.valueOf(mapParameter.get("limit").toString());
	        //3.调用业务
	        List<VmsLiner> vmsLiners = iNetVmsLiner.queryPageByCustom(
	        		organizeSeq, routeCode, linerId, reportStatus, printbillStatus, start, limit);
			JSONArray jsonarray = JSONArray.fromObject(vmsLiners);
			result.put("vmsLiners", jsonarray.toString());
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
	        String routeCode = String.valueOf(mapParameter.get("routeCode"));
	        String linerId = String.valueOf(mapParameter.get("linerId"));
	        String reportStatus = String.valueOf(mapParameter.get("reportStatus"));
	        String printbillStatus = String.valueOf(mapParameter.get("printbillStatus"));
	        //3.调用业务
	        List<VmsLiner> vmsLiners = iNetVmsLiner.queryAllByCustom(organizeSeq, routeCode,
	        		linerId, reportStatus, printbillStatus);
			JSONArray jsonarray = JSONArray.fromObject(vmsLiners);
			result.put("vmsLiners", jsonarray.toString());
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
	        String routeCode = String.valueOf(mapParameter.get("routeCode"));
	        String linerId = String.valueOf(mapParameter.get("linerId"));
	        String reportStatus = String.valueOf(mapParameter.get("reportStatus"));
	        String printbillStatus = String.valueOf(mapParameter.get("printbillStatus"));
	        //3.调用业务
	        int count = iNetVmsLiner.queryCountByCustom(organizeSeq, routeCode, linerId,
	        		reportStatus, printbillStatus);
			result.put("count", String.valueOf(count));
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	
	@SuppressWarnings("rawtypes")
	public String queryAllByLinerTime(String security,String parameter) {
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
	        String linerStatus = String.valueOf(mapParameter.get("linerStatus"));
	        String voiceTime = String.valueOf(mapParameter.get("voiceTime"));
	        String linerMinute = String.valueOf(mapParameter.get("linerMinute"));
	        //3.调用业务
	        List<VmsLiner> vmsLiners = iNetVmsLiner.queryByStatusAndTime(organizeSeq, linerStatus,
	        		voiceTime, linerMinute);
			JSONArray jsonarray = JSONArray.fromObject(vmsLiners);
			result.put("vmsLiners", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String importTrafficLiner(String security,String parameter) {
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
        String linerSource = String.valueOf(mapParameter.get("linerSource"));
        
        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
		JSONObject jsonConfig = JSONObject.fromObject(strConfig);
		Map mapConfig = (Map)jsonConfig;
        //3.调用业务
        iNetVmsLiner.importTrafficLiner(organizeSeq, linerSource,mapConfig);
	} catch (UserBusinessException e) {
		e.printStackTrace();
		result.put("ResultCode", "1");
		result.put("Message", e.getMessage());
	}
	return JSONObject.fromObject(result).toString();
}
}
