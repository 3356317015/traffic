
package com.service.traffic.business.epd;

import java.util.List;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.EpdCardriver;

public interface INetEpdCardriver {
	
	public List<EpdCardriver> queryByCarSeq(String carSeq) throws UserBusinessException;

}
	
	
