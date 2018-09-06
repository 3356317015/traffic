package com.zhima.frame.action.sam.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.service.traffic.business.sam.INetSamGroupModule;
import com.service.traffic.business.sam.impl.ImpNetSamGroupModule;
import com.zhima.basic.CommFinal;
import com.zhima.basic.SamFinal;
import com.zhima.basic.ServiceClient;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.action.sam.ISamGroupModule;
import com.zhima.frame.model.SamGroupModule;
import com.zhima.util.MapUtil;

/**
 * ImpSamGroup概要说明：角色管理接口
 * @author lcy
 */
public class ImpSamGroupModule implements ISamGroupModule {	
	@SuppressWarnings("rawtypes")
	@Override
	public void updateGroupRight(String groupSeq, List<SamGroupModule> samGroupModules, Map<String, Object> config) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamGroupModule iWebSamGroupModule = new ImpNetSamGroupModule();
				iWebSamGroupModule.updateGroupRight(groupSeq, samGroupModules,config);
				return;
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("groupSeq",groupSeq);
				map.put("samGroupModules", samGroupModules);
				map.put("config",config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.GroupModule_UpdateGroupRight,
						SamFinal.ServerNamespace,SamFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return;
			    }else{
			    	throw new UserBusinessException(String.valueOf(mapResult.get("Message")));
			    }
			}
		} catch (Exception e) {
			throw new UserBusinessException(e.getMessage());
		} 	
	}
	
}