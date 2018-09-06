
package com.service.traffic.business.operate;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.ItsLinerfare;

public interface INetItsLinerfare {

	public ItsLinerfare insert(ItsLinerfare itsLinerfare,Map<String, Object> config) throws UserBusinessException;

	public void update(ItsLinerfare itsLinerfare,Map<String, Object> config) throws UserBusinessException;

	public void delete(List<ItsLinerfare> itsLinerfares,Map<String, Object> config) throws UserBusinessException;

	public List<ItsLinerfare> queryByPK(String linerfareSeq) throws UserBusinessException;
	
	public List<ItsLinerfare> queryByLinerSeq(String linerSeq) throws UserBusinessException;
	
	public List<ItsLinerfare> queryByAll() throws UserBusinessException;

	public List<ItsLinerfare> queryPageByCustom(String organizeSeq,
			String routeSeq, String stationSeq,
			String linerId, String startDate, String limitDate,
			int start,int limit) throws UserBusinessException;

	public List<ItsLinerfare> queryAllByCustom(String organizeSeq,
			String routeSeq, String stationSeq,
			String linerId, String startDate, String limitDate
			) throws UserBusinessException;

	public int queryCountByCustom(String organizeSeq,
			String routeSeq, String stationSeq,
			String linerId, String startDate, String limitDate
			) throws UserBusinessException;
	
	public void updateAttribute(List<ItsLinerfare> itsLinerfares,
			Map<String, Object> config) throws UserBusinessException;
}
	
	
