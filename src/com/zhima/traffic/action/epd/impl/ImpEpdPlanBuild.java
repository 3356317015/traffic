package com.zhima.traffic.action.epd.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.traffic.business.epd.INetEpdPlanBuild;
import com.service.traffic.business.epd.impl.ImpNetEpdPlanBuild;
import com.zhima.basic.CommFinal;
import com.zhima.basic.EpdFinal;
import com.zhima.basic.ServiceClient;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.action.epd.IEpdPlanBuild;
import com.zhima.traffic.model.EpdPlan;
import com.zhima.traffic.model.ItsLiner;
import com.zhima.util.MapUtil;

public class ImpEpdPlanBuild implements IEpdPlanBuild {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<EpdPlan> queryByAll() throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdPlanBuild iNetEpdPlanBuild = new ImpNetEpdPlanBuild();
				return iNetEpdPlanBuild.queryByAll();
			case 2: 
				//1.参数对象为JSONObject
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(null, EpdFinal.EpdPlanBuild_QueryByAll,
						EpdFinal.ServerNamespace,EpdFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<EpdPlan>)JsonUtil.convertArray(
			    			mapResult.get("epdPlans").toString(), new EpdPlan());
			    }else{
			    	throw new UserBusinessException(String.valueOf(mapResult.get("Message")));
			    }
			}
			return null;
		} catch (Exception e) {
			throw new UserBusinessException(e.getMessage());
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<ItsLiner> planBuild(EpdPlan epdPlan,String buildFare,Map<String, Object> config)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetEpdPlanBuild iNetEpdPlanBuild = new ImpNetEpdPlanBuild();
				return iNetEpdPlanBuild.planBuild(epdPlan,buildFare,config);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("epdPlan", epdPlan);
				map.put("buildFare", buildFare);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, EpdFinal.EpdPlanBuild_PlanBuild,
						EpdFinal.ServerNamespace,EpdFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<ItsLiner>)JsonUtil.convertArray(
			    			mapResult.get("itsLiners").toString(), new ItsLiner());
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
