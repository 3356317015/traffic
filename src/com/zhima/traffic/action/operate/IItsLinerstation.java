
package com.zhima.traffic.action.operate;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.ItsLinerstation;

public interface IItsLinerstation {

	public List<ItsLinerstation> queryByLinerSeq(String linerSeq) throws UserBusinessException;
	
	public void updateAttribute(List<ItsLinerstation> itsLinerstations,Map<String, Object> config) throws UserBusinessException;
	
}
	
	
