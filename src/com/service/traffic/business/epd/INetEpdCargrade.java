
package com.service.traffic.business.epd;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.EpdCargrade;

public interface INetEpdCargrade {

	public EpdCargrade insert(EpdCargrade epdCargrade,Map<String, Object> config) throws UserBusinessException;

	public void update(EpdCargrade epdCargrade,Map<String, Object> config) throws UserBusinessException;

	public void delete(List<EpdCargrade> epdCargrades,Map<String, Object> config) throws UserBusinessException;

	public List<EpdCargrade> queryByPK(String cargradeSeq) throws UserBusinessException;
	
	public List<EpdCargrade> queryByAll() throws UserBusinessException;
	
	public List<EpdCargrade> queryByOrganizeSeq(String organizeSeq) throws UserBusinessException;

	public List<EpdCargrade> queryPageByCustom(String organizeSeq, String cargradeCode, String cargradeName
			,int start,int limit) throws UserBusinessException;

	public List<EpdCargrade> queryAllByCustom(String organizeSeq, String cargradeCode, String cargradeName) throws UserBusinessException;

	public int queryCountByCustom(String organizeSeq, String cargradeCode, String cargradeName) throws UserBusinessException;

	public List<EpdCargrade> queryByRouteSeq(String routeSeq) throws UserBusinessException;

	

}
	
	
