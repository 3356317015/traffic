
package com.zhima.traffic.action.epd;

import java.util.List;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.EpdCarinfo;

public interface IEpdCarinfo {
	
	public List<EpdCarinfo> queryByCarSeq(String carSeq) throws UserBusinessException;

}
	
	
