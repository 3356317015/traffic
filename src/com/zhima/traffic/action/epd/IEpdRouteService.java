
package com.zhima.traffic.action.epd;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.EpdRouteservice;;

public interface IEpdRouteService {
	

	public EpdRouteservice insert(EpdRouteservice epdRouteservice,Map<String, Object> config) throws UserBusinessException;
	
	public void update(EpdRouteservice epdRouteservice,Map<String, Object> config) throws UserBusinessException;
	
	public void delete(List<EpdRouteservice> epdRouteservices,Map<String, Object> config) throws UserBusinessException;
	
	public List<EpdRouteservice> queryByPK(String routeserviceSeq) throws UserBusinessException;
	
	public List<EpdRouteservice> queryByRouteSeq(String routeSeq) throws UserBusinessException;
	
}
	
	
