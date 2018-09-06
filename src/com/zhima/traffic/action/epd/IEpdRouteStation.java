
package com.zhima.traffic.action.epd;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.EpdRoutestation;

/**
 * IEpdRoute概要说明：线路站点接口
 * @author lcy
 */
public interface IEpdRouteStation {
	

	public EpdRoutestation insert(EpdRoutestation epdRoutestation,Map<String, Object> config) throws UserBusinessException;
	
	public void update(EpdRoutestation epdRoutestation,Map<String, Object> config) throws UserBusinessException;
	
	public void delete(List<EpdRoutestation> epdRoutestations,Map<String, Object> config) throws UserBusinessException;
	
	public List<EpdRoutestation> queryByPK(String routestationSeq) throws UserBusinessException;
	
	public List<EpdRoutestation> queryByRouteSeq(String routeSeq) throws UserBusinessException;
	
}
	
	
