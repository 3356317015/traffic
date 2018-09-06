
package com.service.traffic.business.insurance;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.InsPremium;

public interface INetInsPremium {
	
	public InsPremium insert(InsPremium insPremium,Map<String, Object> config) throws UserBusinessException;
	
	public void update(InsPremium insPremium,Map<String, Object> config) throws UserBusinessException;
	
	public void delete(List<InsPremium> premiums,Map<String, Object> config) throws UserBusinessException;
	
	public List<InsPremium> queryPageByCustom(String organizeSeq, String companySeq,
			int start,int limit) throws UserBusinessException;

	public List<InsPremium> queryAllByCustom(String organizeSeq, String companySeq)
			throws UserBusinessException;
	
	public int queryCountByCustom(String organizeSeq, String companySeq) throws UserBusinessException;

	public List<InsPremium> queryByOrganizeSeq(String organizeSeq) throws UserBusinessException;

}
	
	
