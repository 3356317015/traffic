
package com.service.traffic.action.sam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.comm.SecurityFinal;
import com.service.traffic.business.sam.INetSamUserSecret;
import com.service.traffic.business.sam.impl.ImpNetSamUserSecret;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.model.SamUserSecret;

/**
 * ISamUserColumn概要说明：用户自定义表格接口
 * @author lcy
 */
public class SamUserSecretAction {
	INetSamUserSecret iNetSamUserSecret = new ImpNetSamUserSecret();
	@SuppressWarnings("rawtypes")
	public String queryByGridSecret(String security,String parameter) {
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
	        String className = String.valueOf(mapParameter.get("className")); 
	        String gridName = String.valueOf(mapParameter.get("gridName"));
	        //3.调用业务
			List<SamUserSecret> samUserSecrets = iNetSamUserSecret.queryByGridSecret(className,gridName);
			JSONArray jsonarray = JSONArray.fromObject(samUserSecrets);
			result.put("samUserSecrets", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String updateUserSecret(String security,String parameter) {
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
	        
	        List<SamUserSecret> samUserSecrets = (List<SamUserSecret>)JsonUtil.convertArray(
	        		mapParameter.get("samUserSecrets").toString(), new SamUserSecret());
			
			String className = String.valueOf(mapParameter.get("className")); 
	        String gridName = String.valueOf(mapParameter.get("gridName"));
			
			String strConfig = JSONObject.fromObject(
					mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map) jsonConfig;
	        //3.调用业务
			iNetSamUserSecret.updateUserSecret(samUserSecrets, className, gridName, mapConfig);
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}

}
	
	
