
package com.zhima.traffic.action.voice;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.VmsLiner;

public interface IVmsLiner {
	
	public VmsLiner insert(VmsLiner vmsLiner, Map<String, Object> config) throws UserBusinessException;
	
	public void update(VmsLiner vmsLiner, Map<String, Object> config) throws UserBusinessException;

	public void delete(List<VmsLiner> vmsLiners, Map<String, Object> config) throws UserBusinessException;
	
	public void deleteByLinerDate(String organizeSeq,String linerDate, Map<String, Object> config) throws UserBusinessException;
	
	public List<VmsLiner> queryByPK(String linerSeq) throws UserBusinessException;
	
	public List<VmsLiner> queryByOrganizeSeq(String organizeSeq) throws UserBusinessException;
	
	public List<VmsLiner> queryPageByCustom(String organizeSeq, String routeCode, String linerId,
			String reportStatus, String printbillStatus,int start,int limit) throws UserBusinessException;
	
	public List<VmsLiner> queryAllByCustom(String organizeSeq, String routeCode, String linerId,
			String reportStatus, String printbillStatus) throws UserBusinessException;

	public int queryCountByCustom(String organizeSeq, String routeCode, String linerId,
			String reportStatus, String printbillStatus) throws UserBusinessException;
	
	public List<VmsLiner> queryByStatusAndTime(String organizeSeq,String linerStatus, String voiceTime, String linerMinute) throws UserBusinessException;
	
	public void importTrafficLiner(String organizeSeq, String strSql,Map<String, Object> config) throws UserBusinessException;
	
}
	
	
