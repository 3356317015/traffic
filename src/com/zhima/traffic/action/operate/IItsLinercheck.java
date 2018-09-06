
package com.zhima.traffic.action.operate;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.ItsLiner;
import com.zhima.traffic.model.ItsLinercheck;

public interface IItsLinercheck {

	public List<ItsLinercheck> queryByLinerSeq(String linerSeq) throws UserBusinessException;
	
	public void updateAttribute(ItsLiner itsLiner, List<ItsLinercheck> itsLinerchecks,
			Map<String, Object> config) throws UserBusinessException;
	
}
	
	
