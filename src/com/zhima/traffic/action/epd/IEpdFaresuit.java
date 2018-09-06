
package com.zhima.traffic.action.epd;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.EpdFaresuit;

public interface IEpdFaresuit {
	
	public EpdFaresuit insert(EpdFaresuit epdFaresuit,Map<String, Object> config) throws UserBusinessException;

	public void update(EpdFaresuit epdFaresuit,Map<String, Object> config) throws UserBusinessException;

	public void delete(List<EpdFaresuit> faresuits,Map<String, Object> config) throws UserBusinessException;

	public List<EpdFaresuit> queryByPK(String faresuitSeq) throws UserBusinessException;
	
	public List<EpdFaresuit> queryByAll(String organizeSeq) throws UserBusinessException;

	public List<EpdFaresuit> queryPageByCustom(String organizeSeq, String faresuitCode, String faresuitName
			,int start,int limit) throws UserBusinessException;

	public List<EpdFaresuit> queryAllByCustom(String organizeSeq, String faresuitCode, String faresuitName) throws UserBusinessException;

	public int queryCountByCustom(String organizeSeq, String faresuitCode, String faresuitName) throws UserBusinessException;

	public List<EpdFaresuit> queryByOrganizeSeq(String organizeSeq) throws UserBusinessException;
}
	
	
