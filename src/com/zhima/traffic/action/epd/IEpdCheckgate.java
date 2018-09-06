
package com.zhima.traffic.action.epd;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.EpdCheckgate;

public interface IEpdCheckgate {
	
	public EpdCheckgate insert(EpdCheckgate epdCheckgate,Map<String, Object> config) throws UserBusinessException;

	public void update(EpdCheckgate epdCheckgate,Map<String, Object> config) throws UserBusinessException;

	public void delete(List<EpdCheckgate> checkgates,Map<String, Object> config) throws UserBusinessException;

	public List<EpdCheckgate> queryByPK(String checkgateSeq) throws UserBusinessException;
	
	public List<EpdCheckgate> queryByAll() throws UserBusinessException;

	public List<EpdCheckgate> queryPageByCustom(String organizeSeq, String checkCode, String checkName
			,int start,int limit) throws UserBusinessException;

	public List<EpdCheckgate> queryAllByCustom(String organizeSeq, String checkCode, String checkName) throws UserBusinessException;

	public int queryCountByCustom(String organizeSeq, String checkCode, String checkName) throws UserBusinessException;
	
	public List<EpdCheckgate> queryByOrganizeSeq(String organizeSeq) throws UserBusinessException;
}
	
	
