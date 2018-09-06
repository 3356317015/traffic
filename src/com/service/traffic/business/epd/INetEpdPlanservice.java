
package com.service.traffic.business.epd;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.EpdPlanservice;

public interface INetEpdPlanservice {

	public EpdPlanservice insert(EpdPlanservice epdPlanservice,Map<String, Object> config) throws UserBusinessException;

	public void update(EpdPlanservice epdPlanservice,Map<String, Object> config) throws UserBusinessException;

	public void delete(List<EpdPlanservice> epdPlanservices,Map<String, Object> config) throws UserBusinessException;

	public List<EpdPlanservice> queryByPK(String planserviceSeq) throws UserBusinessException;
	
	public List<EpdPlanservice> queryByPlanSeq(String planSeq) throws UserBusinessException;

}
	
	
