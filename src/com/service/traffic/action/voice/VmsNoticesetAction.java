
package com.service.traffic.action.voice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.comm.SecurityFinal;
import com.service.traffic.business.voice.INetVmsNoticeset;
import com.service.traffic.business.voice.impl.ImpNetVmsNoticeset;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.VmsNotice;
import com.zhima.traffic.model.VmsNoticeset;

/**
 * IEpdRoute概要说明：线路接口
 * @author lcy
 */
public class VmsNoticesetAction {
	
	INetVmsNoticeset iNetVmsNoticeset = new ImpNetVmsNoticeset();
	
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

	        VmsNotice vmsNotice =(VmsNotice)JsonUtil.convertObject(
	        		mapParameter.get("vmsNotice").toString(),VmsNotice.class);
	        
			List<VmsNoticeset> vmsNoticesets = (List<VmsNoticeset>)JsonUtil.convertArray(
					mapParameter.get("vmsNoticesets").toString(), new VmsNoticeset());
			
	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			iNetVmsNoticeset.update(vmsNotice, vmsNoticesets, mapConfig);
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
	        List<VmsNoticeset> vmsNoticesets = iNetVmsNoticeset.queryByNoticeSeq(noticeSeq);
			JSONArray jsonarray = JSONArray.fromObject(vmsNoticesets);
			result.put("vmsNoticesets", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	
}
	
	
