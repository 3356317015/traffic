
package com.service.traffic.business.sam;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.model.SamParameter;


public interface INetSamParameter {
	
	public SamParameter insert(SamParameter samParameter,Map<String, Object> config) throws UserBusinessException;
	
	public void update(SamParameter samParameter,Map<String, Object> config) throws UserBusinessException;
	
	public void delete(List<SamParameter> samParameters,Map<String, Object> config) throws UserBusinessException;
	
	public List<SamParameter> queryPageByCustom(String organizeSeq,String groupName,
			int start,int limit)throws UserBusinessException;
	
	public List<SamParameter> queryAllByCustom(String organizeSeq,String groupName)
		throws UserBusinessException;
	
	public int queryCountByCustom(String organizeSeq,String groupName) throws UserBusinessException;
	
	public SamParameter queryByCode(String organizeSeq, String parameterCode,String parameterName,
			String defalutValue) throws UserBusinessException;

}
	
	
