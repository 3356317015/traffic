
package com.service.traffic.business.epd;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.EpdContractdetail;

public interface INetEpdContractdetail {

	public EpdContractdetail insert(EpdContractdetail epdContractdetail,Map<String, Object> config) throws UserBusinessException;

	public void update(EpdContractdetail epdContractdetail,Map<String, Object> config) throws UserBusinessException;

	public void delete(List<EpdContractdetail> epdContractdetails,Map<String, Object> config) throws UserBusinessException;

	public List<EpdContractdetail> queryByPK(String contractdetailSeq) throws UserBusinessException;
	
	public List<EpdContractdetail> queryByAll() throws UserBusinessException;
	
	public List<EpdContractdetail> queryByContractSeq(String contractSeq) throws UserBusinessException;

}
	
	
