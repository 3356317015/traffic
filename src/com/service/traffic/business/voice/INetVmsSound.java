
package com.service.traffic.business.voice;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.VmsSound;

public interface INetVmsSound {
	

	public List<VmsSound> inserts(List<VmsSound> vmsSounds, Map<String, Object> config
			) throws UserBusinessException;
	
	public void update(VmsSound vmsSound, Map<String, Object> config) throws UserBusinessException;
	
	public void delete(List<VmsSound> vmsSounds,Map<String, Object> config) throws UserBusinessException;
	
	public List<VmsSound> queryByPK(String soundSeq) throws UserBusinessException;
	
	public List<VmsSound> queryByOrganizeSeq(String organizeSeq) throws UserBusinessException;
	
	public List<VmsSound> queryPageByCustom(String organizeSeq, String soundType,
			int start,int limit) throws UserBusinessException;
	
	public List<VmsSound> queryAllByCustom(String organizeSeq, String soundType
			) throws UserBusinessException;
	
	public int queryCountByCustom(String organizeSeq, String soundType) throws UserBusinessException;

	public List<VmsSound> queryByStatusAndTime(String organizeSeq,
			String soundStatus, String currTime) throws UserBusinessException;

}
	
	
