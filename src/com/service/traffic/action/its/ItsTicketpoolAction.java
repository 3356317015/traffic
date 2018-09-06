
package com.service.traffic.action.its;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.service.comm.JsonUtil;
import com.service.comm.SecurityFinal;
import com.service.traffic.business.account.INetItsTicketpool;
import com.service.traffic.business.account.impl.ImpNetItsTicketpool;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.ItsTicketpool;

public class ItsTicketpoolAction {
	INetItsTicketpool iNetItsTicketpool = new ImpNetItsTicketpool();
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

	        ItsTicketpool itsTicketpool = (ItsTicketpool)JsonUtil.convertObject(
	        		mapParameter.get("itsTicketpool").toString(), ItsTicketpool.class);

	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			ItsTicketpool newItsTicketpool = iNetItsTicketpool.insert(itsTicketpool, mapConfig);
	        result.put("itsTicketpool", JSONObject.fromObject(newItsTicketpool).toString());
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

	        ItsTicketpool itsTicketpool = (ItsTicketpool)JsonUtil.convertObject(
	        		mapParameter.get("itsTicketpool").toString(), ItsTicketpool.class);

	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			iNetItsTicketpool.update(itsTicketpool, mapConfig);
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

			List<ItsTicketpool> itsTicketpools = (List<ItsTicketpool>)JsonUtil.convertArray(
					mapParameter.get("itsTicketpools").toString(), new JsonConfig());

	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			iNetItsTicketpool.delete(itsTicketpools, mapConfig);
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
	        String ticketpoolSeq = String.valueOf(mapParameter.get("ticketpoolSeq"));
	        //3.调用业务
			List<ItsTicketpool> itsTicketpools = iNetItsTicketpool.queryByPK(ticketpoolSeq);
			JSONArray jsonarray = JSONArray.fromObject(itsTicketpools);
			result.put("itsTicketpools", jsonarray.toString());
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
	        List<ItsTicketpool> itsTicketpools = iNetItsTicketpool.queryByOrganizeSeq(organizeSeq);
			JSONArray jsonarray = JSONArray.fromObject(itsTicketpools);
			result.put("itsTicketpools", jsonarray.toString());
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
	        String tickettypeSeq = String.valueOf(mapParameter.get("tickettypeSeq"));
	        String operType = String.valueOf(mapParameter.get("operType"));
	        String userCode = String.valueOf(mapParameter.get("userCode"));
	        String poolStatus = String.valueOf(mapParameter.get("poolStatus"));
	        int start = Integer.valueOf(mapParameter.get("start").toString());
	        int limit = Integer.valueOf(mapParameter.get("limit").toString());
	        //3.调用业务
			List<ItsTicketpool> itsTicketpools = iNetItsTicketpool.queryPageByCustom(organizeSeq,
					tickettypeSeq, operType, userCode, poolStatus, start, limit);
			JSONArray jsonarray = JSONArray.fromObject(itsTicketpools);
			result.put("itsTicketpools", jsonarray.toString());
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
	        String tickettypeSeq = String.valueOf(mapParameter.get("tickettypeSeq"));
	        String operType = String.valueOf(mapParameter.get("operType"));
	        String userCode = String.valueOf(mapParameter.get("userCode"));
	        String poolStatus = String.valueOf(mapParameter.get("poolStatus"));
	        //3.调用业务
	        List<ItsTicketpool> itsTicketpools = iNetItsTicketpool.queryAllByCustom(organizeSeq,
	        		tickettypeSeq, operType, userCode, poolStatus);
			JSONArray jsonarray = JSONArray.fromObject(itsTicketpools);
			result.put("itsTicketpools", jsonarray.toString());
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
	        String tickettypeSeq = String.valueOf(mapParameter.get("tickettypeSeq"));
	        String operType = String.valueOf(mapParameter.get("operType"));
	        String userCode = String.valueOf(mapParameter.get("userCode"));
	        String poolStatus = String.valueOf(mapParameter.get("poolStatus"));
	        //3.调用业务
			int count = iNetItsTicketpool.queryCountByCustom(organizeSeq,
					tickettypeSeq, operType, userCode, poolStatus);
			result.put("count", String.valueOf(count));
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	
}
	
	
