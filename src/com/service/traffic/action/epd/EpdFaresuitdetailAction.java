
package com.service.traffic.action.epd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.comm.SecurityFinal;
import com.service.traffic.business.epd.INetEpdFaresuitdetail;
import com.service.traffic.business.epd.impl.ImpNetEpdFaresuitdetail;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.EpdFaresuitdetail;

public class EpdFaresuitdetailAction {
	INetEpdFaresuitdetail iNetEpdFaresuitdetail = new ImpNetEpdFaresuitdetail();
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

	        EpdFaresuitdetail epdFaresuitdetail =(EpdFaresuitdetail)JsonUtil.convertObject(
	        		mapParameter.get("epdFaresuitdetail").toString(),EpdFaresuitdetail.class);
	        
	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			EpdFaresuitdetail newEpdFaresuitdetail = iNetEpdFaresuitdetail.insert(epdFaresuitdetail, mapConfig);
	        result.put("epdFaresuitdetail", JSONObject.fromObject(newEpdFaresuitdetail).toString());
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

	        EpdFaresuitdetail epdFaresuitdetail =(EpdFaresuitdetail)JsonUtil.convertObject(
	        		mapParameter.get("epdFaresuitdetail").toString(),EpdFaresuitdetail.class);
	        
	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			iNetEpdFaresuitdetail.update(epdFaresuitdetail, mapConfig);
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

			List<EpdFaresuitdetail> epdFaresuitdetails = (List<EpdFaresuitdetail>)JsonUtil.convertArray(
					mapParameter.get("epdFaresuitdetails").toString(), new EpdFaresuitdetail());

	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			iNetEpdFaresuitdetail.delete(epdFaresuitdetails, mapConfig);
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
	        String faresuitdetailSeq = String.valueOf(mapParameter.get("faresuitdetailSeq"));
	        //3.调用业务
	        List<EpdFaresuitdetail> epdFaresuitdetails = iNetEpdFaresuitdetail.queryByPK(faresuitdetailSeq);
			JSONArray jsonarray = JSONArray.fromObject(epdFaresuitdetails);
			result.put("epdFaresuitdetails", jsonarray.toString());
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
	        List<EpdFaresuitdetail> epdFaresuitdetails = iNetEpdFaresuitdetail.queryByAll();
			JSONArray jsonarray = JSONArray.fromObject(epdFaresuitdetails);
			result.put("epdFaresuitdetails", jsonarray.toString());
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
	        String faresuitSeq = String.valueOf(mapParameter.get("faresuitSeq"));
	        String routeSeq = String.valueOf(mapParameter.get("routeSeq"));
	        String stationSeq = String.valueOf(mapParameter.get("stationSeq"));
	        String cargradeSeq = String.valueOf(mapParameter.get("cargradeSeq"));
	        int start = Integer.valueOf(mapParameter.get("start").toString());
	        int limit = Integer.valueOf(mapParameter.get("limit").toString());
	        //3.调用业务
	        List<EpdFaresuitdetail> epdFaresuitdetails = iNetEpdFaresuitdetail.queryPageByCustom(
	        		faresuitSeq, routeSeq, stationSeq, cargradeSeq, start, limit);
			JSONArray jsonarray = JSONArray.fromObject(epdFaresuitdetails);
			result.put("epdFaresuitdetails", jsonarray.toString());
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
	        String faresuitSeq = String.valueOf(mapParameter.get("faresuitSeq"));
	        String routeSeq = String.valueOf(mapParameter.get("routeSeq"));
	        String stationSeq = String.valueOf(mapParameter.get("stationSeq"));
	        String cargradeSeq = String.valueOf(mapParameter.get("cargradeSeq"));
	        //3.调用业务
	        List<EpdFaresuitdetail> epdFaresuitdetails = iNetEpdFaresuitdetail.queryAllByCustom(
	        		faresuitSeq, routeSeq, stationSeq, cargradeSeq);
			JSONArray jsonarray = JSONArray.fromObject(epdFaresuitdetails);
			result.put("epdFaresuitdetails", jsonarray.toString());
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
	        String faresuitSeq = String.valueOf(mapParameter.get("faresuitSeq"));
	        String routeSeq = String.valueOf(mapParameter.get("routeSeq"));
	        String stationSeq = String.valueOf(mapParameter.get("stationSeq"));
	        String cargradeSeq = String.valueOf(mapParameter.get("cargradeSeq"));
	        //3.调用业务
	        int count = iNetEpdFaresuitdetail.queryCountByCustom(
	        		faresuitSeq, routeSeq, stationSeq, cargradeSeq);
			result.put("count", String.valueOf(count));
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
	        List<EpdFaresuitdetail> epdFaresuitdetails = iNetEpdFaresuitdetail.queryByRouteSeq(routeSeq);
			JSONArray jsonarray = JSONArray.fromObject(epdFaresuitdetails);
			result.put("epdFaresuitdetails", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String updateBatch(String security,String parameter) {
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
	        
			List<EpdFaresuitdetail> epdFaresuitdetails = (List<EpdFaresuitdetail>)JsonUtil.convertArray(
					mapParameter.get("epdFaresuits").toString(), new EpdFaresuitdetail());
			
	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			iNetEpdFaresuitdetail.updateBatch(routeSeq, epdFaresuitdetails, mapConfig);
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}

	@SuppressWarnings("rawtypes")
	public String queryByRouteSeqAndFaresuitSeq(String security,String parameter) {
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
	        String faresuitSeq = String.valueOf(mapParameter.get("faresuitSeq"));
	        //3.调用业务
	        List<EpdFaresuitdetail> epdFaresuitdetails = iNetEpdFaresuitdetail.queryByRouteSeqAndFaresuitSeq(
	        		routeSeq, faresuitSeq);
			JSONArray jsonarray = JSONArray.fromObject(epdFaresuitdetails);
			result.put("epdFaresuitdetails", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
}
	
	
