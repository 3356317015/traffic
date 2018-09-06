package com.service.traffic.action.sam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.comm.SecurityFinal;
import com.service.traffic.business.sam.INetSamOrganize;
import com.service.traffic.business.sam.impl.ImpNetSamOrganize;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.model.SamOrganize;

/**
 * ImpEpdCar概要说明：车辆接口实现
 * @author lcy
 */
public class SamOrganizeAction {
	INetSamOrganize iNetSamOrganize = new ImpNetSamOrganize();
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String insert(String security,String parameter){
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

	        SamOrganize organize =(SamOrganize)JsonUtil.convertObject(
	        		mapParameter.get("samOrganize").toString(),SamOrganize.class);
	        
	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
	        SamOrganize samOrganize = iNetSamOrganize.insert(organize,mapConfig);
	        result.put("samOrganize", JSONObject.fromObject(samOrganize).toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String update(String security,String parameter){
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
	        
	        SamOrganize organize =(SamOrganize)JsonUtil.convertObject(
	        		mapParameter.get("samOrganize").toString(),SamOrganize.class);
	        
	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			iNetSamOrganize.update(organize,mapConfig);
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String delete(String security,String parameter){
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

			List<SamOrganize> organizes = (List<SamOrganize>)JsonUtil.convertArray(
					mapParameter.get("samOrganizes").toString(), new SamOrganize());
			
	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			iNetSamOrganize.delete(organizes,mapConfig);
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}

	@SuppressWarnings({ "rawtypes" })
	public String queryPageByCustom(String security,String parameter){
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
	        //3.调用业务
			List<SamOrganize> organizes = iNetSamOrganize.queryPageByCustom(
					String.valueOf(mapParameter.get("organizeCode")),
					String.valueOf(mapParameter.get("organizeName")),
					String.valueOf(mapParameter.get("organizeLevel")),
					String.valueOf(mapParameter.get("organizeType")),
					String.valueOf(mapParameter.get("organizeStatus")),
					Integer.valueOf(mapParameter.get("start").toString()),
					Integer.valueOf(mapParameter.get("limit").toString()));
			JSONArray jsonarray = JSONArray.fromObject(organizes);
			result.put("samOrganizes", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	
	@SuppressWarnings("rawtypes")
	public String queryAllByCustom(String security,String parameter){
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
	        //3.调用业务
			List<SamOrganize> organizes = iNetSamOrganize.queryAllByCustom(
					String.valueOf(mapParameter.get("organizeCode")),
					String.valueOf(mapParameter.get("organizeName")),
					String.valueOf(mapParameter.get("organizeLevel")),
					String.valueOf(mapParameter.get("organizeType")),
					String.valueOf(mapParameter.get("organizeStatus")));
			JSONArray jsonarray = JSONArray.fromObject(organizes);
			result.put("samOrganizes", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}

	@SuppressWarnings("rawtypes")
	public String queryCountByCustom(String security,String parameter){
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
	        //3.调用业务
			int count = iNetSamOrganize.queryCountByCustom(
					String.valueOf(mapParameter.get("organizeCode")),
					String.valueOf(mapParameter.get("organizeName")),
					String.valueOf(mapParameter.get("organizeLevel")),
					String.valueOf(mapParameter.get("organizeType")),
					String.valueOf(mapParameter.get("organizeStatus")));
			result.put("count", String.valueOf(count));
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}

	public String queryByAll(String security){
		Map<String, String> result = new HashMap<String, String>();
		result.put("ResultCode", "0");
		result.put("Message", "操作成功");
		try {
			//1.安全认证
			SecurityFinal.certification(security);
			//2.解析parameter
	        //3.调用业务
			List<SamOrganize> organizes = iNetSamOrganize.queryByAll();
			JSONArray jsonarray = JSONArray.fromObject(organizes);
			result.put("samOrganizes", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}

	@SuppressWarnings("rawtypes")
	public String queryByOrganizeType(String security,String parameter){
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
	        String organizeType = String.valueOf(mapParameter.get("organizeType"));
	        //3.调用业务
			List<SamOrganize> organizes = iNetSamOrganize.queryByOrganizeType(organizeType);
			JSONArray jsonarray = JSONArray.fromObject(organizes);
			result.put("samOrganizes", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}

	@SuppressWarnings("rawtypes")
	public String queryDealByOrganizeSeq(String security,String parameter) {
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
			List<SamOrganize> organizes = iNetSamOrganize.queryDealByOrganizeSeq(organizeSeq);
			JSONArray jsonarray = JSONArray.fromObject(organizes);
			result.put("samOrganizes", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}

}