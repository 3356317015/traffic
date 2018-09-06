
package com.service.traffic.business.epd;

import java.util.List;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.EpdCarinfo;

public interface INetEpdCarinfo {
	
	public List<EpdCarinfo> queryByCarSeq(String carSeq) throws UserBusinessException;

}
	
	
