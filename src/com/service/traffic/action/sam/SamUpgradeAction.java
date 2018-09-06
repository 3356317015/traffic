
package com.service.traffic.action.sam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.comm.SecurityFinal;
import com.service.traffic.business.sam.INetSamUpgrade;
import com.service.traffic.business.sam.impl.ImpNetSamUpgrade;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.model.SamUpblob;
import com.zhima.frame.model.SamUpgrade;

/**
 * ISamUpgrade概要说明：在线升级操作接口
 * @author lcy
 */
public class SamUpgradeAction {
	INetSamUpgrade iNetSamUpgrade = new ImpNetSamUpgrade();
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

	        SamUpgrade samUpgrade =(SamUpgrade)JsonUtil.convertObject(
	        		mapParameter.get("samUpgrade").toString(),SamUpgrade.class);
	        
			List<SamUpblob> samUpblobs = (List<SamUpblob>)JsonUtil.convertArray(
					mapParameter.get("samUpblobs").toString(), new SamUpblob());
	        
	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        
	        //3.调用业务
			SamUpgrade newSamUpgrade = iNetSamUpgrade.insert(samUpgrade, samUpblobs, mapConfig);
			result.put("samUpgrade", JSONObject.fromObject(newSamUpgrade).toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String delete(String security,String parameter){
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

			List<SamUpgrade> samUpgrades = (List<SamUpgrade>)JsonUtil.convertArray(
					mapParameter.get("samUpgrades").toString(), new SamUpgrade());
	        
	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        
	        //3.调用业务
			iNetSamUpgrade.delete(samUpgrades, mapConfig);
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}

	@SuppressWarnings("rawtypes")
	public String queryPageByCustom(String security,String parameter){
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
			List<SamUpgrade> samUpgrades = iNetSamUpgrade.queryPageByCustom(
					String.valueOf(mapParameter.get("organizeSeq")), 
					Integer.valueOf(mapParameter.get("start").toString()), 
					Integer.valueOf(mapParameter.get("limit").toString()));
			JSONArray jsonarray = JSONArray.fromObject(samUpgrades);
			result.put("samUpgrades", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}

	@SuppressWarnings("rawtypes")
	public String queryCountByCustom(String security,String parameter){
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
			int count = iNetSamUpgrade.queryCountByCustom(
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
	public String queryMaxVer(String security,String parameter){
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
			SamUpgrade samUpgrade = iNetSamUpgrade.queryMaxVer(
					String.valueOf(mapParameter.get("organizeSeq")));

			result.put("samUpgrade", JSONObject.fromObject(samUpgrade).toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}

	@SuppressWarnings("rawtypes")
	public String queryUpgradeByVer(String security,String parameter){
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
			int count = iNetSamUpgrade.queryUpgradeByVer(
					String.valueOf(mapParameter.get("organizeSeq")), 
					String.valueOf(mapParameter.get("sysVersion")));
			result.put("count", String.valueOf(count));
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}

}
