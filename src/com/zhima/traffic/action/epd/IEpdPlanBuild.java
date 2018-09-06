package com.zhima.traffic.action.epd;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.EpdPlan;
import com.zhima.traffic.model.ItsLiner;

public interface IEpdPlanBuild {

	public List<EpdPlan> queryByAll() throws UserBusinessException;
	
	public List<ItsLiner> planBuild(EpdPlan epdPlan,String buildFare,Map<String, Object> config) throws UserBusinessException;
	
}
