
package com.service.traffic.business.epd;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.EpdStation;

/**
 * IEpdStation概要说明：站点为设置
 * @author lcy
 */
public interface INetEpdStation {
	
	public EpdStation insert(String organizeSeq,EpdStation epdStation,Map<String, Object> config) throws UserBusinessException;
	
	public void update(String organizeSeq,EpdStation epdStation,Map<String, Object> config) throws UserBusinessException;
	
	public void delete(List<EpdStation> epdStations,Map<String, Object> config) throws UserBusinessException;
	
	public List<EpdStation> queryPageByCustom(String organizeSeq,String stationCode,String stationSpell,String stationName,
			String stationStatus,int start,int limit) throws UserBusinessException;
	
	public List<EpdStation> queryAllByCustom(String organizeSeq,String stationCode,String stationSpell,String stationName,
			String stationStatus) throws UserBusinessException;
	
	public int queryCountByCustom(String organizeSeq,String stationCode,String stationSpell,String stationName,
			String stationStatus) throws UserBusinessException;

	public List<EpdStation> queryByAll() throws UserBusinessException;
	
	public List<EpdStation> queryByOrganizeSeq(String organizeSeq) throws UserBusinessException;

	public List<EpdStation> queryByStatus(String organizeSeq,String stationStatus) throws UserBusinessException;


}
	
	
