
package com.service.traffic.business.epd;

import java.util.List;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.EpdDriverinfo;


public interface INetEpdDriverinfo {
	
	public List<EpdDriverinfo> queryByDriverSeq(String driverSeq) throws UserBusinessException;

}
	
	
