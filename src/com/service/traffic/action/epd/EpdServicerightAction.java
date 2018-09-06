
package com.service.traffic.action.epd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.comm.SecurityFinal;
import com.service.traffic.business.epd.INetEpdServiceright;
import com.service.traffic.business.epd.impl.ImpNetEpdServiceright;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.EpdServiceright;

public class EpdServicerightAction {
	
	INetEpdServiceright iNetEpdServiceright = new ImpNetEpdServiceright();
	
	@SuppressWarnings("rawtypes")
	public String queryByServiceSeq(String security,String parameter) {
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
	        String serviceSeq = String.valueOf(mapParameter.get("serviceSeq"));
	        //3.调用业务
	        List<EpdServiceright> epdServicerights = iNetEpdServiceright.queryByServiceSeq(serviceSeq);
			JSONArray jsonarray = JSONArray.fromObject(epdServicerights);
			result.put("epdServicerights", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	
	
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String saveRight(String security,String parameter) {
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
	        
	        String serviceSeq = String.valueOf(mapParameter.get("serviceSeq"));
	        
			List<EpdServiceright> addRights = (List<EpdServiceright>)JsonUtil.convertArray(
					mapParameter.get("addRights").toString(), new EpdServiceright());
	        
	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			iNetEpdServiceright.saveRight(serviceSeq, addRights, mapConfig);
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	
}
