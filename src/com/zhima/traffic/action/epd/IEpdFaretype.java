
package com.zhima.traffic.action.epd;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.EpdFaretype;

public interface IEpdFaretype {

	public EpdFaretype insert(EpdFaretype epdFaretype,Map<String, Object> config) throws UserBusinessException;

	public void update(EpdFaretype epdFaretype,Map<String, Object> config) throws UserBusinessException;

	public void delete(List<EpdFaretype> epdFaretypes,Map<String, Object> config) throws UserBusinessException;

	public List<EpdFaretype> queryByPK(String faretypeSeq) throws UserBusinessException;
	
	public List<EpdFaretype> queryByAll() throws UserBusinessException;

	public List<EpdFaretype> queryPageByCustom(String organizeSeq,String faretypeCode, String faretypeName,
			String faretypeStatus,int start,int limit) throws UserBusinessException;

	public List<EpdFaretype> queryAllByCustom(String organizeSeq,String faretypeCode, String faretypeName,
			String faretypeStatus) throws UserBusinessException;

	public int queryCountByCustom(String organizeSeq,String faretypeCode, String faretypeName,
			String faretypeStatus) throws UserBusinessException;
	
	public List<EpdFaretype> queryByOrganizeSeq(String organizeSeq) throws UserBusinessException;

}
	
	
