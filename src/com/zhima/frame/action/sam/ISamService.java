
package com.zhima.frame.action.sam;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.model.SamService;

/**
 * ISamService概要说明：销售点接口
 * @author lcy
 */
public interface ISamService {
	
	public SamService insert(SamService samService,Map<String, Object> config) throws UserBusinessException;
	
	public void update(SamService samService,Map<String, Object> config) throws UserBusinessException;
	
	public void delete(List<SamService> samServices, Map<String, Object> config) throws UserBusinessException;
	
	public List<SamService> queryPageByCustom(String organizeSeq,String serviceCode,
			String serviceName,String serviceStatus,int start,int limit) throws UserBusinessException;
	
	public List<SamService> queryAllByCustom(String organizeSeq,String serviceCode,
			String serviceName,String serviceStatus) throws UserBusinessException;

	public int queryCountByCustom(String organizeSeq,String serviceCode,
			String serviceName,String serviceStatus) throws UserBusinessException;
	
	public List<SamService> queryByOrganizeSeq(String organizeSeq) throws UserBusinessException;

	public List<SamService> queryByOrganizeAndSaleType(String organizeSeq, String saleType) throws UserBusinessException;

}