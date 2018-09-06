
package com.zhima.traffic.action.epd;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.EpdFare;

public interface IEpdFare {

	public EpdFare insert(EpdFare epdFare,Map<String, Object> config) throws UserBusinessException;

	public void update(EpdFare epdFare,Map<String, Object> config) throws UserBusinessException;

	public void delete(List<EpdFare> epdFares,Map<String, Object> config) throws UserBusinessException;

	public List<EpdFare> queryByPK(String fareSeq) throws UserBusinessException;
	
	public List<EpdFare> queryByAll() throws UserBusinessException;

	public List<EpdFare> queryPageByCustom(String routeSeq, String stationSeq,
			String cargradeSeq, int start,int limit) throws UserBusinessException;

	public List<EpdFare> queryAllByCustom(String routeSeq, String stationSeq,
			String cargradeSeq) throws UserBusinessException;

	public int queryCountByCustom(String routeSeq, String stationSeq,
			String cargradeSeq) throws UserBusinessException;

	public List<EpdFare> queryByRouteSeq(String routeSeq) throws UserBusinessException;

	public void updateBatch(String routeSeq, List<EpdFare> epdFares, Map<String, Object> config) throws UserBusinessException;

}
	
	
