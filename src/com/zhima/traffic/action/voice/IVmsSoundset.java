
package com.zhima.traffic.action.voice;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.VmsSound;
import com.zhima.traffic.model.VmsSoundset;

public interface IVmsSoundset {

	public void update(VmsSound vmsSound, List<VmsSoundset> vmsSoundsets,
			Map<String, Object> config) throws UserBusinessException;
	
	public List<VmsSoundset> queryBySoundSeq(String soundSeq) throws UserBusinessException;
	
}
	
	
