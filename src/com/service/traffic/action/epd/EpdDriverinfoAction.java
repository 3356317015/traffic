
package com.service.traffic.action.epd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.service.comm.SecurityFinal;
import com.service.traffic.business.epd.INetEpdDriverinfo;
import com.service.traffic.business.epd.impl.ImpNetEpdDriverinfo;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.EpdDriverinfo;


public class EpdDriverinfoAction {
	INetEpdDriverinfo iNetEpdDriverinfo = new ImpNetEpdDriverinfo();
	@SuppressWarnings("rawtypes")
	public String queryByDriverSeq(String security,String parameter) {
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
	        String driverSeq = String.valueOf(mapParameter.get("driverSeq"));
	        //3.调用业务
			List<EpdDriverinfo> epdDriverinfos = iNetEpdDriverinfo.queryByDriverSeq(driverSeq);
			JSONArray jsonarray = JSONArray.fromObject(epdDriverinfos);
			result.put("epdDriverinfos", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}

}	
