
package com.zhima.traffic.action.epd;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.EpdRoute;
import com.zhima.traffic.model.EpdRouteservice;
import com.zhima.traffic.model.EpdRoutestation;

/**
 * IEpdRoute概要说明：线路接口
 * @author lcy
 */
public interface IEpdRoute {
	

	public EpdRoute insert(EpdRoute epdRoute,List<EpdRoutestation> routestations,List<EpdRouteservice> routeservices,
			Map<String, Object> config) throws UserBusinessException;
	
	public void update(EpdRoute epdRoute,List<EpdRoutestation> routestations,List<EpdRoutestation> delstations,
			List<EpdRouteservice> routeservices,Map<String, Object> config) throws UserBusinessException;
	
	public void delete(List<EpdRoute> epdRoutes,Map<String, Object> config) throws UserBusinessException;
	
	public List<EpdRoute> queryByPK(String routeSeq) throws UserBusinessException;
	
	public List<EpdRoute> queryByAll() throws UserBusinessException;
	
	public List<EpdRoute> queryByOrganizeSeq(String organizeSeq) throws UserBusinessException;
	
	public List<EpdRoute> queryPageByCustom(String organizeSeq, String routeCode, String routeSpell, String routeName,
			String routeType, String roadType,String routeStatus,int start,int limit) throws UserBusinessException;
	
	public List<EpdRoute> queryAllByCustom(String organizeSeq, String routeCode, String routeSpell, String routeName,
			String routeType, String roadType,String routeStatus) throws UserBusinessException;
	
	public int queryCountByCustom(String organizeSeq, String routeCode, String routeSpell, String routeName,
			String routeType, String roadType,String routeStatus) throws UserBusinessException;
	
	public List<EpdRoute> queryByNoFare(String organizeSeq) throws UserBusinessException;
}
	
	
