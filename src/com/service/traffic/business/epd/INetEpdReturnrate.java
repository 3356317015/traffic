
package com.service.traffic.business.epd;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.EpdReturnrate;

public interface INetEpdReturnrate {

	public EpdReturnrate insert(EpdReturnrate epdReturnrate,Map<String, Object> config) throws UserBusinessException;

	public void update(EpdReturnrate epdReturnrate,Map<String, Object> config) throws UserBusinessException;

	public void delete(List<EpdReturnrate> epdReturnrates,Map<String, Object> config) throws UserBusinessException;

	public List<EpdReturnrate> queryByPK(String returnrateSeq) throws UserBusinessException;
	
	public List<EpdReturnrate> queryByAll() throws UserBusinessException;

	public List<EpdReturnrate> queryPageByCustom(String organizeSeq, String returnrateCode, String returnrateName,
			int start,int limit) throws UserBusinessException;

	public List<EpdReturnrate> queryAllByCustom(String organizeSeq, String returnrateCode, String returnrateName
			) throws UserBusinessException;

	public int queryCountByCustom(String organizeSeq, String returnrateCode, String returnrateName
			) throws UserBusinessException;

	public List<EpdReturnrate> queryByOrganizeSeq(String organizeSeq) throws UserBusinessException;

}
	
	
