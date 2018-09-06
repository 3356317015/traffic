
package com.service.traffic.business.operate;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.ItsLiner;
import com.zhima.traffic.model.ItsLinerservice;

public interface INetItsLinerservice {

	public List<ItsLinerservice> queryByLinerSeq(String linerSeq) throws UserBusinessException;
	
	public void updateAttribute(ItsLiner itsLiner, List<ItsLinerservice> linerservices,
			Map<String, Object> config) throws UserBusinessException;
	
}
	
	
