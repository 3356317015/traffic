
package com.service.traffic.business.voice;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.VmsParameter;


public interface INetVmsParameter {
	
	public void update(List<VmsParameter> vmsParameters,Map<String, Object> config) throws UserBusinessException;

	public List<VmsParameter> queryByOrganizeSeq(String organizeSeq) throws UserBusinessException;
	
	public List<VmsParameter> queryByParameterCode(String organizeSeq,String parameterCode) throws UserBusinessException;

}
	
	
