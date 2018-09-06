
package com.service.traffic.action.voice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.comm.SecurityFinal;
import com.service.traffic.business.voice.INetVmsRoute;
import com.service.traffic.business.voice.impl.ImpNetVmsRoute;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.VmsRoute;

/**
 * IEpdRoute概要说明：线路接口
 * @author lcy
 */
public class VmsRouteAction {
	
	INetVmsRoute iNetVmsRoute = new ImpNetVmsRoute();
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

	        VmsRoute vmsRoute =(VmsRoute)JsonUtil.convertObject(
	        		mapParameter.get("vmsRoute").toString(),VmsRoute.class);
	        
	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			VmsRoute newVmsRoute = iNetVmsRoute.insert(vmsRoute, mapConfig);
	        result.put("vmsRoute", JSONObject.fromObject(newVmsRoute).toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String inserts(String security,String parameter) {
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

	        List<VmsRoute> vmsRoutes = (List<VmsRoute>)JsonUtil.convertArray(
					mapParameter.get("vmsRoutes").toString(), new VmsRoute());
	        
	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			List<VmsRoute> newVmsRoutes = iNetVmsRoute.inserts(vmsRoutes, mapConfig);
			JSONArray jsonarray = JSONArray.fromObject(newVmsRoutes);
			result.put("vmsRoutes", jsonarray.toString());
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

	        VmsRoute vmsRoute =(VmsRoute)JsonUtil.convertObject(
	        		mapParameter.get("vmsRoute").toString(),VmsRoute.class);
			
	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			iNetVmsRoute.update(vmsRoute, mapConfig);
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

			List<VmsRoute> vmsRoutes = (List<VmsRoute>)JsonUtil.convertArray(
					mapParameter.get("vmsRoutes").toString(), new VmsRoute());

	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			iNetVmsRoute.delete(vmsRoutes, mapConfig);
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
	        String routeSeq = String.valueOf(mapParameter.get("routeSeq"));
	        //3.调用业务
	        List<VmsRoute> vmsRoutes = iNetVmsRoute.queryByPK(routeSeq);
			JSONArray jsonarray = JSONArray.fromObject(vmsRoutes);
			result.put("vmsRoutes", jsonarray.toString());
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
	        List<VmsRoute> vmsRoutes = iNetVmsRoute.queryByOrganizeSeq(organizeSeq);
			JSONArray jsonarray = JSONArray.fromObject(vmsRoutes);
			result.put("vmsRoutes", jsonarray.toString());
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
	        String routeName = String.valueOf(mapParameter.get("routeName"));
	        String voiceStatus = String.valueOf(mapParameter.get("voiceStatus"));
	        int start = Integer.valueOf(mapParameter.get("start").toString());
	        int limit = Integer.valueOf(mapParameter.get("limit").toString());
	        //3.调用业务
	        List<VmsRoute> vmsRoutes = iNetVmsRoute.queryPageByCustom(
	        		organizeSeq, routeCode, routeName, voiceStatus, start, limit);
			JSONArray jsonarray = JSONArray.fromObject(vmsRoutes);
			result.put("vmsRoutes", jsonarray.toString());
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
	        String routeName = String.valueOf(mapParameter.get("routeName"));
	        String voiceStatus = String.valueOf(mapParameter.get("voiceStatus"));
	        //3.调用业务
	        List<VmsRoute> vmsRoutes = iNetVmsRoute.queryAllByCustom(organizeSeq, routeCode, routeName, voiceStatus);
			JSONArray jsonarray = JSONArray.fromObject(vmsRoutes);
			result.put("vmsRoutes", jsonarray.toString());
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
	        String routeName = String.valueOf(mapParameter.get("routeName"));
	        String voiceStatus = String.valueOf(mapParameter.get("voiceStatus"));
	        //3.调用业务
	        int count = iNetVmsRoute.queryCountByCustom(organizeSeq, routeCode, routeName, voiceStatus);
			result.put("count", String.valueOf(count));
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}

	@SuppressWarnings("rawtypes")
	public String queryTrafficRoute(String security,String parameter) {
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
        String routeSource = String.valueOf(mapParameter.get("routeSource"));
        //3.调用业务
        List<VmsRoute> vmsRoutes = iNetVmsRoute.queryTrafficRoute(routeSource);
		JSONArray jsonarray = JSONArray.fromObject(vmsRoutes);
		result.put("vmsRoutes", jsonarray.toString());
	} catch (UserBusinessException e) {
		e.printStackTrace();
		result.put("ResultCode", "1");
		result.put("Message", e.getMessage());
	}
	return JSONObject.fromObject(result).toString();
}
}
