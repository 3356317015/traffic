
package com.service.traffic.business.epd;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.EpdPlanstation;

public interface INetEpdPlanstation {

	public EpdPlanstation insert(EpdPlanstation epdPlanstation,Map<String, Object> config) throws UserBusinessException;

	public void update(EpdPlanstation epdPlanstation,Map<String, Object> config) throws UserBusinessException;

	public void delete(List<EpdPlanstation> planstations,Map<String, Object> config) throws UserBusinessException;

	public List<EpdPlanstation> queryByPK(String planstationSeq) throws UserBusinessException;
	
	public List<EpdPlanstation> queryByPlanSeqAndPlanId(String planSeq, String planId) throws UserBusinessException;

}
	
	
