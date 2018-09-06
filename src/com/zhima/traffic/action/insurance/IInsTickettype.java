
package com.zhima.traffic.action.insurance;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.InsTickettype;

public interface IInsTickettype {

	public InsTickettype insert(InsTickettype insTickettype,Map<String, Object> config) throws UserBusinessException;

	public void update(InsTickettype insTickettype,Map<String, Object> config) throws UserBusinessException;

	public void delete(List<InsTickettype> insTickettypes, Map<String, Object> config) throws UserBusinessException;

	public List<InsTickettype> queryByPK(String tickettypeSeq) throws UserBusinessException;
	
	public List<InsTickettype> queryByOrganizeSeq(String organizeSeq) throws UserBusinessException;

	public List<InsTickettype> queryPageByCustom(String organizeSeq, String companySeq,int start,int limit) throws UserBusinessException;

	public List<InsTickettype> queryAllByCustom(String organizeSeq, String companySeq) throws UserBusinessException;

	public int queryCountByCustom(String organizeSeq, String companySeq) throws UserBusinessException;

}
	
	
