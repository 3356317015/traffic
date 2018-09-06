
package com.service.traffic.action.sam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.comm.SecurityFinal;
import com.service.traffic.business.sam.INetSamVariables;
import com.service.traffic.business.sam.impl.ImpNetSamVariables;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.model.SamVariables;

/**
 * ISamVariables概要说明：变量接口
 * @author lcy
 */
public class SamVariablesAction {
	INetSamVariables iNetSamVariables = new ImpNetSamVariables();
	@SuppressWarnings({ "rawtypes", "unchecked" })
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

	        SamVariables samVariables =(SamVariables)JsonUtil.convertObject(
	        		mapParameter.get("samVariables").toString(),SamVariables.class);

	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        
	        //3.调用业务
	        SamVariables newSamVariables = iNetSamVariables.insert(samVariables,mapConfig);
			result.put("samVariables", JSONObject.fromObject(newSamVariables).toString());
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

	        SamVariables samVariables =(SamVariables)JsonUtil.convertObject(
	        		mapParameter.get("samVariables").toString(),SamVariables.class);

	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        
	        //3.调用业务
	        iNetSamVariables.update(samVariables,mapConfig);
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

			List<SamVariables> samVariables = (List<SamVariables>)JsonUtil.convertArray(
					mapParameter.get("samVariables").toString(), new SamVariables());
			
	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        
	        //3.调用业务
	        iNetSamVariables.delete(samVariables, mapConfig);
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
	        String organizeSeq =String.valueOf(mapParameter.get("organizeSeq"));
	        String variableType =String.valueOf(mapParameter.get("variableType"));
	        String variableCode =String.valueOf(mapParameter.get("variableCode"));
	        String variableName =String.valueOf(mapParameter.get("variableName"));
	        String status =String.valueOf(mapParameter.get("status"));
			int start = Integer.valueOf(mapParameter.get("start").toString());
			int limit = Integer.valueOf(mapParameter.get("limit").toString());
	        //3.调用业务
			List<SamVariables> samVariables = iNetSamVariables.queryPageByCustom(
					organizeSeq,variableType,variableCode,variableName,status,start,limit);
			JSONArray jsonarray = JSONArray.fromObject(samVariables);
			result.put("samVariables", jsonarray.toString());
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
	        String organizeSeq =String.valueOf(mapParameter.get("organizeSeq"));
	        String variableType =String.valueOf(mapParameter.get("variableType"));
	        String variableCode =String.valueOf(mapParameter.get("variableCode"));
	        String variableName =String.valueOf(mapParameter.get("variableName"));
	        String status =String.valueOf(mapParameter.get("status"));
	        //3.调用业务
			List<SamVariables> samVariables = iNetSamVariables.queryAllByCustom(
					organizeSeq,variableType,variableCode,variableName,status);
			JSONArray jsonarray = JSONArray.fromObject(samVariables);
			result.put("samVariables", jsonarray.toString());
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
	        String organizeSeq =String.valueOf(mapParameter.get("organizeSeq"));
	        String variableType =String.valueOf(mapParameter.get("variableType"));
	        String variableCode =String.valueOf(mapParameter.get("variableCode"));
	        String variableName =String.valueOf(mapParameter.get("variableName"));
	        String status =String.valueOf(mapParameter.get("status"));
	        //3.调用业务
			int count = iNetSamVariables.queryCountByCustom(
					organizeSeq,variableType,variableCode,variableName,status);
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
			List<SamVariables> samVariables = iNetSamVariables.queryByAll();
			JSONArray jsonarray = JSONArray.fromObject(samVariables);
			result.put("samVariables", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}

	@SuppressWarnings("rawtypes")
	public String queryByVariableType(String security,String parameter) {
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
	        String organizeSeq =String.valueOf(mapParameter.get("organizeSeq"));
	        String variableType =String.valueOf(mapParameter.get("variableType"));
	        //3.调用业务
			List<SamVariables> samVariables = iNetSamVariables.queryByVariableType(organizeSeq,variableType);
			JSONArray jsonarray = JSONArray.fromObject(samVariables);
			result.put("samVariables", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}

}
	
	
