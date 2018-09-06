
package com.service.traffic.business.epd;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.EpdDriver;
import com.zhima.traffic.model.EpdDriverinfo;


public interface INetEpdDriver {
	
	public EpdDriver insert(EpdDriver epdDriver, List<EpdDriverinfo> epdDriverinfos,Map<String, Object> config) throws UserBusinessException;
	
	public void update(EpdDriver epdDriver, List<EpdDriverinfo> epdDriverinfos,Map<String, Object> config) throws UserBusinessException;
	
	public void delete(List<EpdDriver> epdDrivers,Map<String, Object> config) throws UserBusinessException;
	
	public List<EpdDriver> queryPageByCustom(String organizeSeq, String idNumber,String driverName,String companyName,
			String status,int start,int limit) throws UserBusinessException;
	
	public List<EpdDriver> queryAllByCustom(String organizeSeq, String idNumber,String driverName,String companyName,
			String status) throws UserBusinessException;
	
	public int queryCountByCustom(String organizeSeq, String idNumber,String driverName,String companyName,
			String status) throws UserBusinessException;

	public List<EpdDriver> queryByAll() throws UserBusinessException;
	
	public List<EpdDriver> queryByOrganizeSeq(String organizeSeq) throws UserBusinessException;

}
	
	
