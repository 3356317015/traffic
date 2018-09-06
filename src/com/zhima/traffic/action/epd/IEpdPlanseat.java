
package com.zhima.traffic.action.epd;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.EpdPlanseat;

public interface IEpdPlanseat {

	public EpdPlanseat insert(EpdPlanseat planseat,Map<String, Object> config) throws UserBusinessException;

	public void update(EpdPlanseat planseat,Map<String, Object> config) throws UserBusinessException;

	public void delete(List<EpdPlanseat> planseats,Map<String, Object> config) throws UserBusinessException;

	public List<EpdPlanseat> queryByPK(String planseatSeq) throws UserBusinessException;
	
	public List<EpdPlanseat> queryByPlanSeqAndPlanId(String planSeq, String planId) throws UserBusinessException;

}
	
	
