
package com.service.traffic.business.epd;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.EpdCheckgate;
import com.zhima.traffic.model.EpdPlan;
import com.zhima.traffic.model.EpdPlandeal;
import com.zhima.traffic.model.EpdPlanservice;
import com.zhima.widget.seatBar.Seat;
import com.zhima.widget.stationBar.Station;

public interface INetEpdPlan {

	public EpdPlan insert(EpdPlan epdPlan,List<EpdCheckgate> checkgates, List<Station> stations,
			List<Seat> seats,List<EpdPlandeal> plandeals,List<EpdPlanservice> planservices,
			Map<String, Object> config) throws UserBusinessException;

	public void update(EpdPlan epdPlan,List<EpdCheckgate> checkgates,List<Station> stations,
			List<Seat> seats,List<EpdPlandeal> plandeals,List<EpdPlanservice> planservices,
			Map<String, Object> config) throws UserBusinessException;

	public void delete(List<EpdPlan> epdPlans,Map<String, Object> config) throws UserBusinessException;

	public List<EpdPlan> queryByPK(String planSeq) throws UserBusinessException;
	
	public List<EpdPlan> queryByRouteSeq(String routeSeq) throws UserBusinessException;
	
	public List<EpdPlan> queryPageByCustom(String organizeSeq, String routeSeq,String stationSeq,String planId,
			String planType, String planStatus,int start,int limit)
			throws UserBusinessException;
	
	public List<EpdPlan> queryAllByCustom(String organizeSeq, String routeSeq,String stationSeq,String planId,
			String planType, String planStatus) throws UserBusinessException;
	
	public int queryCountByCustom(String organizeSeq, String routeSeq,String stationSeq,String planId,
			String planType, String planStatus) throws UserBusinessException;
	
	public List<EpdPlan> queryByAll() throws UserBusinessException;

	public List<EpdPlan> queryByOrganizeSeq(String organizeSeq) throws UserBusinessException;

}
	
	
