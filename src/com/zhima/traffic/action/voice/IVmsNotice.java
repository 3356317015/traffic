
package com.zhima.traffic.action.voice;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.VmsNotice;

public interface IVmsNotice {
	

	public VmsNotice insert(VmsNotice vmsNotice, Map<String, Object> config) throws UserBusinessException;
	
	public void update(VmsNotice vmsNotice, Map<String, Object> config) throws UserBusinessException;
	
	public void delete(List<VmsNotice> vmsNotices,Map<String, Object> config)
			throws UserBusinessException;
	
	public List<VmsNotice> queryByPK(String noticeSeq) throws UserBusinessException;
	
	public List<VmsNotice> queryPageByOrganizeSeq(String organizeSeq,int start,int limit)
			throws UserBusinessException;
	
	public List<VmsNotice> queryByOrganizeSeq(String organizeSeq) throws UserBusinessException;
	
	public int queryCountByOrganizeSeq(String organizeSeq)
			throws UserBusinessException;
	
	public List<VmsNotice> queryPageByCustom(String organizeSeq, String noticeName,
			String noticeStatus,int start,int limit) throws UserBusinessException;
	
	public List<VmsNotice> queryAllByCustom(String organizeSeq, String noticeName,
			String noticeStatus) throws UserBusinessException;
	
	public int queryCountByCustom(String organizeSeq, String noticeName,
			String noticeStatus) throws UserBusinessException;

	public List<VmsNotice> queryByStatusAndTime(String organizeSeq,
			String noticeStatus, String currTime) throws UserBusinessException;

}
	
	
