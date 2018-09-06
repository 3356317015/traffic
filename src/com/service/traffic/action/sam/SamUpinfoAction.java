
package com.service.traffic.action.sam;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.service.comm.SecurityFinal;
import com.service.traffic.business.sam.INetSamUpinfo;
import com.service.traffic.business.sam.impl.ImpNetSamUpinfo;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.model.SamUpinfo;

/**
 * ISamUpgrade概要说明：在线升级操作接口
 * @author lcy
 */
public class SamUpinfoAction {
	INetSamUpinfo iNetSamUpinfo = new ImpNetSamUpinfo();
	@SuppressWarnings({ "unchecked", "rawtypes" })
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
	        
	        String fileVer = mapParameter.get("fileVer").toString();
	        String remark = mapParameter.get("remark").toString();
	        
	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        
	        //3.调用业务
			SamUpinfo newSamUpinfo = iNetSamUpinfo.insert(fileVer, remark, mapConfig);
			result.put("samUpinfo", JSONObject.fromObject(newSamUpinfo).toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
}
	
	
