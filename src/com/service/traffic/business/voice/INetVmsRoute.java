
package com.service.traffic.business.voice;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.VmsRoute;

public interface INetVmsRoute {

	public VmsRoute insert(VmsRoute vmsRoute, Map<String, Object> config) throws UserBusinessException;

	public List<VmsRoute> inserts(List<VmsRoute> vmsRoutes,
			Map<String, Object> config) throws UserBusinessException;
	
	public void update(VmsRoute vmsRoute, Map<String, Object> config) throws UserBusinessException;
	
	public void delete(List<VmsRoute> vmsRoutes,Map<String, Object> config) throws UserBusinessException;
	
	public List<VmsRoute> queryByPK(String routeSeq) throws UserBusinessException;
	
	public List<VmsRoute> queryByOrganizeSeq(String organizeSeq) throws UserBusinessException;
	
	public List<VmsRoute> queryPageByCustom(String organizeSeq, String routeCode, String routeName,
			String voiceStatus,int start,int limit) throws UserBusinessException;
	
	public List<VmsRoute> queryAllByCustom(String organizeSeq, String routeCode, String routeName,
			String voiceStatus) throws UserBusinessException;
	
	public int queryCountByCustom(String organizeSeq, String routeCode, String routeName,
			String voiceStatus) throws UserBusinessException;
	
	public List<VmsRoute> queryTrafficRoute(String routeSource) throws UserBusinessException;

}
	
	
