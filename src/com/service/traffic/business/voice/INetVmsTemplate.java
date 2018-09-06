
package com.service.traffic.business.voice;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.VmsTemplate;


public interface INetVmsTemplate {
	public VmsTemplate insert(VmsTemplate vmsTemplate, Map<String, Object> config) throws UserBusinessException;
	
	public void update(List<VmsTemplate> vmsTemplates,Map<String, Object> config) throws UserBusinessException;
	
	public void delete(List<VmsTemplate> vmsTemplates, Map<String, Object> config) throws UserBusinessException;

	public List<VmsTemplate> queryByOrganizeAndType(String organizeSeq, String templateType) throws UserBusinessException;

}
	
	
