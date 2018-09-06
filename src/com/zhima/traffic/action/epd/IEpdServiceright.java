
package com.zhima.traffic.action.epd;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.EpdServiceright;

public interface IEpdServiceright {

	public List<EpdServiceright> queryByServiceSeq(String serviceSeq) throws UserBusinessException;
	
	public void saveRight(String serviceSeq, List<EpdServiceright> addRights,
			Map<String, Object> config) throws UserBusinessException;
}
	
	
