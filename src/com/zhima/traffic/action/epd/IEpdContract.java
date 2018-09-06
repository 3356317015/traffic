
package com.zhima.traffic.action.epd;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.EpdContract;

public interface IEpdContract {

	public EpdContract insert(EpdContract epdContract,Map<String, Object> config) throws UserBusinessException;

	public void update(EpdContract epdContract,Map<String, Object> config) throws UserBusinessException;

	public void delete(List<EpdContract> epdContracts,Map<String, Object> config) throws UserBusinessException;

	public List<EpdContract> queryByPK(String contractSeq) throws UserBusinessException;
	
	public List<EpdContract> queryByAll() throws UserBusinessException;
	
	public List<EpdContract> queryByOrganizeSeq(String organizeSeq) throws UserBusinessException;

}
	
	
