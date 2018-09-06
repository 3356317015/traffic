
package com.zhima.traffic.action.epd;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.EpdPlancheck;

public interface IEpdPlancheck {

	public EpdPlancheck insert(EpdPlancheck epdPlancheck,Map<String, Object> config) throws UserBusinessException;

	public void update(EpdPlancheck epdPlancheck,Map<String, Object> config) throws UserBusinessException;

	public void delete(List<EpdPlancheck> planchecks,Map<String, Object> config) throws UserBusinessException;

	public List<EpdPlancheck> queryByPK(String plancheckSeq) throws UserBusinessException;
	
	public List<EpdPlancheck> queryByPlanSeqAndPlanId(String planSeq, String planId) throws UserBusinessException;

}
	
	
