
package com.zhima.traffic.action.operate;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.ItsLiner;
import com.zhima.traffic.model.ItsLinercheck;
import com.zhima.traffic.model.ItsLinerdeal;
import com.zhima.traffic.model.ItsLinerfare;
import com.zhima.traffic.model.ItsLinerseat;
import com.zhima.traffic.model.ItsLinerservice;
import com.zhima.traffic.model.ItsLinerstation;

public interface IItsLiner {
	public ItsLiner insert(ItsLiner itsLiner,List<ItsLinerstation> itsLinerstations,
			List<ItsLinerseat> itsLinerseats,List<ItsLinerfare> itsLinerfares,
			List<ItsLinercheck> itsLinerchecks, List<ItsLinerdeal> itsLinerdeals,
			List<ItsLinerservice> itsLinerservices, Map<String, Object> config) throws UserBusinessException;

	public void updateLiner(ItsLiner itsLiner,Map<String, Object> config) throws UserBusinessException;

	public void delete(List<ItsLiner> itsLiners,Map<String, Object> config) throws UserBusinessException;

	public List<ItsLiner> queryByPK(String linerSeq) throws UserBusinessException;

	public List<ItsLiner> queryPageByCustom(String organizeSeq,
			String routeSeq, String stationSeq, String linerId,
			String cargradeSeq, String linerStatus, String ifReport,
			String startDate, String limitDate,int start,int limit) throws UserBusinessException;

	public int queryCountByCustom(String organizeSeq,
			String routeSeq, String stationSeq, String linerId,
			String cargradeSeq, String linerStatus,String ifReport,
			String startDate, String limitDate) throws UserBusinessException;
	
	public void updateAttribute (ItsLiner itsLiner,Map<String, Object> config) throws UserBusinessException;
}
	
	
