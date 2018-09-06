
package com.service.traffic.action.epd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.comm.SecurityFinal;
import com.service.traffic.business.epd.INetEpdStation;
import com.service.traffic.business.epd.impl.ImpNetEpdStation;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.EpdStation;

/**
 * IEpdStation概要说明：站点为设置
 * @author lcy
 */
public class EpdStationAction {
	INetEpdStation iNetEpdStation = new ImpNetEpdStation();
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
	        String organizeSeq = String.valueOf(mapParameter.get("organizeSeq"));
	        EpdStation epdStation =(EpdStation)JsonUtil.convertObject(
	        		mapParameter.get("epdStation").toString(),EpdStation.class);
	        
	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			EpdStation newEpdStation = iNetEpdStation.insert(organizeSeq,epdStation, mapConfig);
	        result.put("epdStation", JSONObject.fromObject(newEpdStation).toString());
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
	        String organizeSeq = String.valueOf(mapParameter.get("organizeSeq"));
	        EpdStation epdStation =(EpdStation)JsonUtil.convertObject(
	        		mapParameter.get("epdStation").toString(),EpdStation.class);

	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			iNetEpdStation.update(organizeSeq,epdStation, mapConfig);
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

			List<EpdStation> epdStations = (List<EpdStation>)JsonUtil.convertArray(
					mapParameter.get("epdStations").toString(), new EpdStation());
	        
	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			iNetEpdStation.delete(epdStations, mapConfig);
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
	        String stationCode = String.valueOf(mapParameter.get("stationCode"));
	        String stationSpell = String.valueOf(mapParameter.get("stationSpell"));
	        String stationName = String.valueOf(mapParameter.get("stationName"));
	        String stationStatus = String.valueOf(mapParameter.get("stationStatus"));
	        int start = Integer.valueOf(mapParameter.get("start").toString());
	        int limit = Integer.valueOf(mapParameter.get("limit").toString());
	        //3.调用业务
			List<EpdStation> epdStations = iNetEpdStation.queryPageByCustom(
					organizeSeq, stationCode, stationSpell, stationName, stationStatus, start, limit);
			JSONArray jsonarray = JSONArray.fromObject(epdStations);
			result.put("epdStations", jsonarray.toString());
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
	        String stationCode = String.valueOf(mapParameter.get("stationCode"));
	        String stationSpell = String.valueOf(mapParameter.get("stationSpell"));
	        String stationName = String.valueOf(mapParameter.get("stationName"));
	        String stationStatus = String.valueOf(mapParameter.get("stationStatus"));

	        //3.调用业务
			List<EpdStation> epdStations = iNetEpdStation.queryAllByCustom(
					organizeSeq, stationCode, stationSpell, stationName, stationStatus);
			JSONArray jsonarray = JSONArray.fromObject(epdStations);
			result.put("epdStations", jsonarray.toString());
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
	        String stationCode = String.valueOf(mapParameter.get("stationCode"));
	        String stationSpell = String.valueOf(mapParameter.get("stationSpell"));
	        String stationName = String.valueOf(mapParameter.get("stationName"));
	        String stationStatus = String.valueOf(mapParameter.get("stationStatus"));

	        //3.调用业务
			int count = iNetEpdStation.queryCountByCustom(
					organizeSeq, stationCode, stationSpell, stationName, stationStatus);
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
			List<EpdStation> epdStations = iNetEpdStation.queryByAll();
			JSONArray jsonarray = JSONArray.fromObject(epdStations);
			result.put("epdStations", jsonarray.toString());
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
			List<EpdStation> epdStations = iNetEpdStation.queryByOrganizeSeq(organizeSeq);
			JSONArray jsonarray = JSONArray.fromObject(epdStations);
			result.put("epdStations", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}

	@SuppressWarnings("rawtypes")
	public String queryByStatus(String security,String parameter) {
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
	        String stationStatus = String.valueOf(mapParameter.get("stationStatus"));

	        //3.调用业务
			List<EpdStation> epdStations = iNetEpdStation.queryByStatus(organizeSeq, stationStatus);
			JSONArray jsonarray = JSONArray.fromObject(epdStations);
			result.put("epdStations", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
}
	
	
