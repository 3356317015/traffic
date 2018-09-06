
package com.zhima.traffic.action.epd;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.EpdPlandeal;

public interface IEpdPlandeal {

	public EpdPlandeal insert(EpdPlandeal epdPlandeal,Map<String, Object> config) throws UserBusinessException;

	public void update(EpdPlandeal epdPlandeal,Map<String, Object> config) throws UserBusinessException;

	public void delete(List<EpdPlandeal> plandeals,Map<String, Object> config) throws UserBusinessException;

	public List<EpdPlandeal> queryByPK(String plandealSeq) throws UserBusinessException;
	
	public List<EpdPlandeal> queryByPlanSeqAndPlanId(String planSeq, String planId) throws UserBusinessException;

}
	
	
