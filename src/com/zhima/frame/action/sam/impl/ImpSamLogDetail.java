package com.zhima.frame.action.sam.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.traffic.business.sam.INetSamLogDetail;
import com.service.traffic.business.sam.impl.ImpNetSamLogDetail;
import com.zhima.basic.CommFinal;
import com.zhima.basic.SamFinal;
import com.zhima.basic.ServiceClient;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.action.sam.ISamLogDetail;
import com.zhima.frame.model.SamLogDetail;
import com.zhima.util.MapUtil;

/**
 * ImpSamLogOper概要说明：日志操作接口
 * @author lcy
 */
public class ImpSamLogDetail implements ISamLogDetail {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<SamLogDetail> queryByOperSeq(String operSeq)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamLogDetail iWebSamLogDetail = new ImpNetSamLogDetail();
				return iWebSamLogDetail.queryByOperSeq(operSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("operSeq", operSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.LogDetail_QueryByOperSeq,
						SamFinal.ServerNamespace,SamFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
					List<SamLogDetail> SamLogDetails = (List<SamLogDetail>)JsonUtil.convertArray(
							mapResult.get("samLogDetails").toString(), new SamLogDetail());
					return SamLogDetails;
			    }else{
			    	throw new UserBusinessException(String.valueOf(mapResult.get("Message")));
			    }
			}
			return null;
		} catch (Exception e) {
			throw new UserBusinessException(e.getMessage());
		}
	}
}