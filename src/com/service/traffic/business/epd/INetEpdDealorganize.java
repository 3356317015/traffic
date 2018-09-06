
package com.service.traffic.business.epd;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.EpdDealorganize;

/**
 * IEpdStation概要说明：配载站站设置
 * @author lcy
 */
public interface INetEpdDealorganize {
	
	public EpdDealorganize insert(EpdDealorganize epdDealorganize,Map<String, Object> config) throws UserBusinessException;
	
	public void update(EpdDealorganize epdDealorganize,Map<String, Object> config) throws UserBusinessException;
	
	public void delete(List<EpdDealorganize> epdDealorganizes,Map<String, Object> config) throws UserBusinessException;
	
	public List<EpdDealorganize> queryPageByCustom(String organizeSeq, String dealorganize, 
			String dealStatus,int start,int limit) throws UserBusinessException;
	
	public List<EpdDealorganize> queryAllByCustom(String organizeSeq, String dealorganize, 
			String dealStatus) throws UserBusinessException;
	
	public int queryCountByCustom(String organizeSeq, String dealorganize, 
			String dealStatus) throws UserBusinessException;

	public List<EpdDealorganize> queryByAll() throws UserBusinessException;
	
	public List<EpdDealorganize> queryByOrganizeSeqAndStatus(String organizeSeq, String dealStatus) throws UserBusinessException;

}
	
	
