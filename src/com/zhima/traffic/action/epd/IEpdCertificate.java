
package com.zhima.traffic.action.epd;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.EpdCertificate;

public interface IEpdCertificate {

	public EpdCertificate insert(EpdCertificate epdCertificate,Map<String, Object> config) throws UserBusinessException;

	public void update(EpdCertificate epdCertificate,String oldCertype, String oldCername,Map<String, Object> config) throws UserBusinessException;

	public void delete(List<EpdCertificate> epdCertificates,Map<String, Object> config) throws UserBusinessException;

	public List<EpdCertificate> queryByPK(String certificateSeq) throws UserBusinessException;
	
	public List<EpdCertificate> queryByAll() throws UserBusinessException;

	public List<EpdCertificate> queryPageByCustom(String organizeSeq, String cerType,
			String cerName, String cerStatus, int start,int limit) throws UserBusinessException;

	public List<EpdCertificate> queryAllByCustom(String organizeSeq, String cerType, String cerName,
			String cerStatus) throws UserBusinessException;

	public int queryCountByCustom(String organizeSeq, String cerType, String cerName,
			String cerStatus) throws UserBusinessException;

}
	
	
