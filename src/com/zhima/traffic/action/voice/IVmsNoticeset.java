
package com.zhima.traffic.action.voice;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.VmsNotice;
import com.zhima.traffic.model.VmsNoticeset;

public interface IVmsNoticeset {

	public void update(VmsNotice vmsNotice, List<VmsNoticeset> vmsNoticesets,
			Map<String, Object> config) throws UserBusinessException;
	
	public List<VmsNoticeset> queryByNoticeSeq(String noticeSeq) throws UserBusinessException;
	
}
	
	
