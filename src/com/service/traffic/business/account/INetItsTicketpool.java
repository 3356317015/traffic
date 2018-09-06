
package com.service.traffic.business.account;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.ItsTicketpool;

public interface INetItsTicketpool {

	public ItsTicketpool insert(ItsTicketpool itsTicketpool,Map<String, Object> config) throws UserBusinessException;

	public void update(ItsTicketpool itsTicketpool,Map<String, Object> config) throws UserBusinessException;

	public void delete(List<ItsTicketpool> itsTicketpools,Map<String, Object> config) throws UserBusinessException;

	public List<ItsTicketpool> queryByPK(String ticketpoolSeq) throws UserBusinessException;
	
	public List<ItsTicketpool> queryByOrganizeSeq(String organizeSeq) throws UserBusinessException;

	public List<ItsTicketpool> queryPageByCustom(String organizeSeq, String tickettypeSeq, String operType,
			String userCode, String poolStatus, int start,int limit) throws UserBusinessException;

	public List<ItsTicketpool> queryAllByCustom(String organizeSeq, String tickettypeSeq, String operType,
			String userCode, String poolStatus) throws UserBusinessException;

	public int queryCountByCustom(String organizeSeq, String tickettypeSeq, String operType,
			String userCode, String poolStatus) throws UserBusinessException;

	public ItsTicketpool send(ItsTicketpool itsTicketpool,
			ItsTicketpool newTicketpool, Map<String, Object> config) throws UserBusinessException;

}
	
	
