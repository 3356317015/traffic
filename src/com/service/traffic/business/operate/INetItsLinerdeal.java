
package com.service.traffic.business.operate;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.ItsLiner;
import com.zhima.traffic.model.ItsLinerdeal;

public interface INetItsLinerdeal {

	public List<ItsLinerdeal> queryByLinerSeq(String linerSeq) throws UserBusinessException;
	
	public void updateAttribute(ItsLiner itsLiner, List<ItsLinerdeal> itsLinerdeals, Map<String, Object> config) throws UserBusinessException;
	
}
	
	
