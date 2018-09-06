
package com.service.traffic.business.sam;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.model.SamVariables;

/**
 * ISamVariables概要说明：变量接口
 * @author lcy
 */
public interface INetSamVariables {
	
	public SamVariables insert(SamVariables samVariables,Map<String, Object> config) throws UserBusinessException;
	
	public void update(SamVariables samVariables,Map<String, Object> config) throws UserBusinessException;
	
	public void delete(List<SamVariables> samVariables,Map<String, Object> config) throws UserBusinessException;
	
	public List<SamVariables> queryPageByCustom(String organizeSeq,String variableType,String variableCode,String variableName,
			String status,int start,int limit) throws UserBusinessException;
	
	public List<SamVariables> queryAllByCustom(String organizeSeq,String variableType,String variableCode,String variableName,
			String status) throws UserBusinessException;
	
	public int queryCountByCustom(String organizeSeq,String variableType,String variableCode,String variableName,
			String status) throws UserBusinessException;

	public List<SamVariables> queryByAll() throws UserBusinessException;

	public List<SamVariables> queryByVariableType(String organizeSeq,String variableType) throws UserBusinessException;

}
	
	
