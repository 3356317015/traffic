
package com.zhima.traffic.action.insurance;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.InsCompany;

public interface IInsCompany {

	public InsCompany insert(InsCompany insCompany,Map<String, Object> config) throws UserBusinessException;
	
	public void update(InsCompany epdCompany,Map<String, Object> config) throws UserBusinessException;

	public void delete(List<InsCompany> companies,Map<String, Object> config) throws UserBusinessException;

	public List<InsCompany> queryPageByCustom(String organizeSeq, String insuranceCode,String insuranceName,
			String companyType,String industry,int start,int limit) throws UserBusinessException;
	
	public List<InsCompany> queryAllByCustom(String organizeSeq, String insuranceCode,String insuranceName,
			String companyType,String industry) throws UserBusinessException;
	
	public int queryCountByCustom(String organizeSeq, String insuranceCode,String insuranceName,
			String companyType, String industry) throws UserBusinessException;

	public List<InsCompany> queryByOrganizeSeq(String organizeSeq) throws UserBusinessException;
}
	
	
