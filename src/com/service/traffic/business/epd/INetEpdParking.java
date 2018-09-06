
package com.service.traffic.business.epd;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.EpdCheckgate;
import com.zhima.traffic.model.EpdParking;

public interface INetEpdParking {
	
	public EpdParking insert(EpdParking epdParking,Map<String, Object> config) throws UserBusinessException;

	public void update(EpdParking epdParking,Map<String, Object> config) throws UserBusinessException;

	public void delete(List<EpdParking> parkings,Map<String, Object> config) throws UserBusinessException;

	public List<EpdParking> queryByPK(String parkingSeq) throws UserBusinessException;
	
	public List<EpdParking> queryByAll() throws UserBusinessException;

	public List<EpdParking> queryPageByCustom(String organizeSeq, String parkingCode, String parkingName
			,int start,int limit) throws UserBusinessException;

	public List<EpdParking> queryAllByCustom(String organizeSeq, String parkingCode, String parkingName) throws UserBusinessException;

	public int queryCountByCustom(String organizeSeq, String parkingCode, String parkingName) throws UserBusinessException;
	
	public List<EpdParking> queryByOrganizeSeq(String organizeSeq) throws UserBusinessException;

	public List<EpdParking> queryByOrganizeAndCheckSeq(String organizeSeq,
			List<EpdCheckgate> checkgates) throws UserBusinessException;
}
	
	
