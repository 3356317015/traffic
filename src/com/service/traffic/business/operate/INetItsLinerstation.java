
package com.service.traffic.business.operate;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.ItsLinerstation;

public interface INetItsLinerstation {

	public List<ItsLinerstation> queryByLinerSeq(String linerSeq) throws UserBusinessException;
	
	public void updateAttribute(List<ItsLinerstation> linerstations, Map<String, Object> config) throws UserBusinessException;
	
}
	
	
