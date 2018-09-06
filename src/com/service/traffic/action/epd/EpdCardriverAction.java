
package com.service.traffic.action.epd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.service.comm.SecurityFinal;
import com.service.traffic.business.epd.INetEpdCardriver;
import com.service.traffic.business.epd.impl.ImpNetEpdCardriver;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.EpdCardriver;

public class EpdCardriverAction {
	INetEpdCardriver iNetEpdCardriver = new ImpNetEpdCardriver();
	@SuppressWarnings("rawtypes")
	public String queryByCarSeq(String security,String parameter) {
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
	        String carSeq = String.valueOf(mapParameter.get("carSeq"));
	        //3.调用业务
			List<EpdCardriver> epdCardrivers = iNetEpdCardriver.queryByCarSeq(carSeq);
			JSONArray jsonarray = JSONArray.fromObject(epdCardrivers);
			result.put("epdCardrivers", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}

}
	
	
