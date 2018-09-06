
package com.service.traffic.business.sam;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.model.SamUserToolbar;

/**
 * ISamUserToolbar概要说明：用户工具栏
 * @author lcy
 */
public interface INetSamUserToolbar {
	
	public SamUserToolbar insert(String userSeq, SamUserToolbar userToolbar,Map<String, Object> config) throws UserBusinessException;
	
	public void update(SamUserToolbar userToolbar, Map<String, Object> config) throws UserBusinessException;
	
	public void deleteByPk(String toolBarSeq) throws UserBusinessException;
	
	public void deleteByUserSeq(String userSeq,String moduleSeq) throws UserBusinessException;
	
	public List<SamUserToolbar> queryByUserSeq(String userSeq,String moduleSeq) throws UserBusinessException;
	
}
	
	
