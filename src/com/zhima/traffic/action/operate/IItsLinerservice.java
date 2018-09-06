
package com.zhima.traffic.action.operate;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.ItsLiner;
import com.zhima.traffic.model.ItsLinerservice;

public interface IItsLinerservice {

	public List<ItsLinerservice> queryByLinerSeq(String linerSeq) throws UserBusinessException;
	
	public void updateAttribute(ItsLiner itsLiner, List<ItsLinerservice> itsLinerservices,
			Map<String,Object> config) throws UserBusinessException;
	
}
	
	
