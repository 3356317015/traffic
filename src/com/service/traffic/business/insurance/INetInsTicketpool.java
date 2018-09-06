
package com.service.traffic.business.insurance;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.InsTicketpool;

public interface INetInsTicketpool {

	public InsTicketpool insert(InsTicketpool insTicketpool,Map<String, Object> config) throws UserBusinessException;

	public void update(InsTicketpool insTicketpool,Map<String, Object> config) throws UserBusinessException;

	public void delete(List<InsTicketpool> insTicketpools,Map<String, Object> config) throws UserBusinessException;

	public List<InsTicketpool> queryByPK(String ticketpoolSeq) throws UserBusinessException;
	
	public List<InsTicketpool> queryByOrganizeSeq(String organizeSeq) throws UserBusinessException;

	public List<InsTicketpool> queryPageByCustom(String organizeSeq, String companySeq, String tickettypeSeq, String operType,
			String userCode, String poolStatus, int start,int limit) throws UserBusinessException;

	public List<InsTicketpool> queryAllByCustom(String organizeSeq, String companySeq, String tickettypeSeq, String operType,
			String userCode, String poolStatus) throws UserBusinessException;

	public int queryCountByCustom(String organizeSeq, String companySeq, String tickettypeSeq, String operType,
			String userCode, String poolStatus) throws UserBusinessException;

	public List<InsTicketpool> queryValid(String organizeSeq,
			String tickettypeSeq, String operType, String userCode) throws UserBusinessException;

	public InsTicketpool send(InsTicketpool insTicketpool,
			InsTicketpool newTicketpool, Map<String, Object> config) throws UserBusinessException;


}
	
	
