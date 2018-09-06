
package com.service.traffic.action.sam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.service.comm.SecurityFinal;
import com.service.traffic.business.sam.INetSamUserOnline;
import com.service.traffic.business.sam.impl.ImpNetSamUserOnline;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.model.SamUserOnline;

/**
 * ISamUserOnline概要说明：在线用户操作接口
 * @author lcy
 */
public class SamUserOnlineAction {
	INetSamUserOnline iNetSamUserOnline = new ImpNetSamUserOnline();
	@SuppressWarnings("rawtypes")
	public String queryPageByAll(String security,String parameter) {
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
			List<SamUserOnline> samUserOnlines = iNetSamUserOnline.queryPageByAll(
					String.valueOf(mapParameter.get("organizeSeq")),
					Integer.valueOf(mapParameter.get("start").toString()),
					Integer.valueOf(mapParameter.get("limit").toString()));
			JSONArray jsonarray = JSONArray.fromObject(samUserOnlines);
			result.put("samUserOnlines", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}

	@SuppressWarnings("rawtypes")
	public String queryCountByAll(String security,String parameter) {
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
			int count = iNetSamUserOnline.queryCountByAll(
					String.valueOf(mapParameter.get("organizeSeq")));
			result.put("count", String.valueOf(count));
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}

	@SuppressWarnings("rawtypes")
	public String clearUser(String security,String parameter) {
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
			iNetSamUserOnline.clearUser(String.valueOf(mapParameter.get("organizeSeq")),
					Integer.valueOf(mapParameter.get("onlineUpdateTime").toString()));
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
}
	
	
