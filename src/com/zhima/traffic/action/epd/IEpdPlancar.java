
package com.zhima.traffic.action.epd;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.EpdCar;
import com.zhima.traffic.model.EpdPlan;
import com.zhima.traffic.model.EpdPlancar;

public interface IEpdPlancar {

	public EpdPlancar insert(EpdPlancar epdPlancar,Map<String, Object> config) throws UserBusinessException;

	public void update(EpdPlancar epdPlancar,Map<String, Object> config) throws UserBusinessException;

	public void delete(List<EpdPlancar> plancars,Map<String, Object> config) throws UserBusinessException;

	public List<EpdPlancar> queryByPK(String plancarSeq) throws UserBusinessException;
	
	public List<EpdPlancar> queryByPlanSeqAndPlanId(String planSeq, String planId) throws UserBusinessException;

	public void updateBatch(EpdPlan epdPlan, List<EpdCar> epdCars,Map<String, Object> config) throws UserBusinessException;

}
	
	
