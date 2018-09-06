
package com.service.traffic.business.epd;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.EpdTickettype;

public interface INetEpdTickettype {

	public EpdTickettype insert(EpdTickettype epdTickettype,Map<String, Object> config) throws UserBusinessException;

	public void update(EpdTickettype epdTickettype,Map<String, Object> config) throws UserBusinessException;

	public void delete(List<EpdTickettype> epdTickettypes, Map<String, Object> config) throws UserBusinessException;

	public List<EpdTickettype> queryByPK(String tickettypeSeq) throws UserBusinessException;
	
	public List<EpdTickettype> queryByOrganizeSeq(String organizeSeq) throws UserBusinessException;

	public List<EpdTickettype> queryPageByCustom(String organizeSeq, String category,int start,int limit) throws UserBusinessException;

	public List<EpdTickettype> queryAllByCustom(String organizeSeq, String category) throws UserBusinessException;

	public int queryCountByCustom(String organizeSeq, String category) throws UserBusinessException;

}
	
	
