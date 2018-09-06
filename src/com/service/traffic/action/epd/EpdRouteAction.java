
package com.service.traffic.action.epd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.comm.SecurityFinal;
import com.service.traffic.business.epd.INetEpdRoute;
import com.service.traffic.business.epd.impl.ImpNetEpdRoute;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.EpdRoute;
import com.zhima.traffic.model.EpdRouteservice;
import com.zhima.traffic.model.EpdRoutestation;

/**
 * IEpdRoute概要说明：线路接口
 * @author lcy
 */
public class EpdRouteAction {
	
	INetEpdRoute iNetEpdRoute = new ImpNetEpdRoute();
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

	        EpdRoute epdRoute =(EpdRoute)JsonUtil.convertObject(
	        		mapParameter.get("epdRoute").toString(),EpdRoute.class);
	        
			List<EpdRoutestation> epdRoutestations = (List<EpdRoutestation>)JsonUtil.convertArray(
					mapParameter.get("epdRoutestations").toString(), new EpdRoutestation());
			
			List<EpdRouteservice> epdRouteservices = (List<EpdRouteservice>)JsonUtil.convertArray(
					mapParameter.get("epdRouteservices").toString(), new EpdRouteservice());
	        
	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			EpdRoute newEpdRoute = iNetEpdRoute.insert(epdRoute, epdRoutestations, epdRouteservices, mapConfig);
	        result.put("epdRoute", JSONObject.fromObject(newEpdRoute).toString());
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

	        EpdRoute epdRoute =(EpdRoute)JsonUtil.convertObject(
	        		mapParameter.get("epdRoute").toString(),EpdRoute.class);
	        
			List<EpdRoutestation> routestations = (List<EpdRoutestation>)JsonUtil.convertArray(
					mapParameter.get("epdRoutestations").toString(), new EpdRoutestation());

			List<EpdRoutestation> delstations = (List<EpdRoutestation>)JsonUtil.convertArray(
					mapParameter.get("delstations").toString(), new EpdRoutestation());
			
			List<EpdRouteservice> epdRouteservices = (List<EpdRouteservice>)JsonUtil.convertArray(
					mapParameter.get("epdRouteservices").toString(), new EpdRouteservice());
			
	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			iNetEpdRoute.update(epdRoute, routestations, delstations, epdRouteservices, mapConfig);
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

			List<EpdRoute> epdRoutes = (List<EpdRoute>)JsonUtil.convertArray(
					mapParameter.get("epdRoutes").toString(), new EpdRoute());

	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			iNetEpdRoute.delete(epdRoutes, mapConfig);
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
	        List<EpdRoute> epdRoutes = iNetEpdRoute.queryByPK(routeSeq);
			JSONArray jsonarray = JSONArray.fromObject(epdRoutes);
			result.put("epdRoutes", jsonarray.toString());
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
	        List<EpdRoute> epdRoutes = iNetEpdRoute.queryByAll();
			JSONArray jsonarray = JSONArray.fromObject(epdRoutes);
			result.put("epdRoutes", jsonarray.toString());
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
	        List<EpdRoute> epdRoutes = iNetEpdRoute.queryByOrganizeSeq(organizeSeq);
			JSONArray jsonarray = JSONArray.fromObject(epdRoutes);
			result.put("epdRoutes", jsonarray.toString());
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
	        String routeSpell = String.valueOf(mapParameter.get("routeSpell"));
	        String routeName = String.valueOf(mapParameter.get("routeName"));
	        String routeType = String.valueOf(mapParameter.get("routeType"));
	        String roadType = String.valueOf(mapParameter.get("roadType"));
	        String routeStatus = String.valueOf(mapParameter.get("routeStatus"));
	        int start = Integer.valueOf(mapParameter.get("start").toString());
	        int limit = Integer.valueOf(mapParameter.get("limit").toString());
	        //3.调用业务
	        List<EpdRoute> epdRoutes = iNetEpdRoute.queryPageByCustom(
	        		organizeSeq, routeCode, routeSpell, routeName, routeType, roadType, routeStatus, start, limit);
			JSONArray jsonarray = JSONArray.fromObject(epdRoutes);
			result.put("epdRoutes", jsonarray.toString());
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
	        String routeSpell = String.valueOf(mapParameter.get("routeSpell"));
	        String routeName = String.valueOf(mapParameter.get("routeName"));
	        String routeType = String.valueOf(mapParameter.get("routeType"));
	        String roadType = String.valueOf(mapParameter.get("roadType"));
	        String routeStatus = String.valueOf(mapParameter.get("routeStatus"));
	        //3.调用业务
	        List<EpdRoute> epdRoutes = iNetEpdRoute.queryAllByCustom(
	        		organizeSeq, routeCode, routeSpell, routeName, routeType, roadType, routeStatus);
			JSONArray jsonarray = JSONArray.fromObject(epdRoutes);
			result.put("epdRoutes", jsonarray.toString());
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
	        String routeSpell = String.valueOf(mapParameter.get("routeSpell"));
	        String routeName = String.valueOf(mapParameter.get("routeName"));
	        String routeType = String.valueOf(mapParameter.get("routeType"));
	        String roadType = String.valueOf(mapParameter.get("roadType"));
	        String routeStatus = String.valueOf(mapParameter.get("routeStatus"));
	        //3.调用业务
	        int count = iNetEpdRoute.queryCountByCustom(
	        		organizeSeq, routeCode, routeSpell, routeName, routeType, roadType, routeStatus);
			result.put("count", String.valueOf(count));
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	@SuppressWarnings("rawtypes")
	public String queryByNoFare(String security,String parameter) {
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
	        List<EpdRoute> epdRoutes = iNetEpdRoute.queryByNoFare(organizeSeq);
			JSONArray jsonarray = JSONArray.fromObject(epdRoutes);
			result.put("epdRoutes", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
}
	
	
