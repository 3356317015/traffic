
package com.zhima.traffic.action.epd;

import java.util.List;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.EpdCardriver;

public interface IEpdCardriver {
	
	public List<EpdCardriver> queryByCarSeq(String carSeq) throws UserBusinessException;

}
	
	
