
package com.service.traffic.action.voice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.comm.SecurityFinal;
import com.service.traffic.business.voice.INetVmsSoundset;
import com.service.traffic.business.voice.impl.ImpNetVmsSoundset;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.VmsSound;
import com.zhima.traffic.model.VmsSoundset;

/**
 * VmsSoundsetAction概要说明：语音设置接口
 * @author lcy
 */
public class VmsSoundsetAction {
	
	INetVmsSoundset iNetVmsSoundset = new ImpNetVmsSoundset();
	
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

	        VmsSound vmsSound =(VmsSound)JsonUtil.convertObject(
	        		mapParameter.get("vmsSound").toString(),VmsSound.class);
	        
			List<VmsSoundset> vmsSoundsets = (List<VmsSoundset>)JsonUtil.convertArray(
					mapParameter.get("vmsSoundsets").toString(), new VmsSoundset());
			
	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			iNetVmsSoundset.update(vmsSound, vmsSoundsets, mapConfig);
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	
	@SuppressWarnings("rawtypes")
	public String queryByNoticeSeq(String security,String parameter) {
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
	        String noticeSeq = String.valueOf(mapParameter.get("noticeSeq"));
	        //3.调用业务
	        List<VmsSoundset> vmsSoundsets = iNetVmsSoundset.queryBySoundSeq(noticeSeq);
			JSONArray jsonarray = JSONArray.fromObject(vmsSoundsets);
			result.put("vmsSoundsets", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	
}
	
	
