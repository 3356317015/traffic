
package com.zhima.traffic.action.epd;

import java.util.List;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.EpdDriverinfo;


public interface IEpdDriverinfo {
	
	public List<EpdDriverinfo> queryByDriverSeq(String driverSeq) throws UserBusinessException;

}
	
	
