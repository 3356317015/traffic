
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.traffic.model.EpdCertificate;

public class EpdCertificateDao extends BaseDao{
	public EpdCertificateDao(Connection conn){
		super(conn);
	}
	
	public EpdCertificate insert(EpdCertificate epdCertificate, Map<String, Object> config){
		String pk = super.insert(epdCertificate,config);
		epdCertificate.setCertificateSeq(pk);
		return epdCertificate;
	}
	
	public void update(EpdCertificate epdCertificate, Map<String, Object> config){
		super.update(epdCertificate,config);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String certificateSeq){
		String strSql = "delete from epd_certificate where certificate_seq=?";
		List params = new ArrayList();
		params.add(certificateSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdCertificate> queryByValid(EpdCertificate epdCertificate) {
		StringBuffer strSql = new StringBuffer("select * from epd_certificate where organize_seq =?" +
				" and cer_type = ? and cer_name = ?");
		List params = new ArrayList();
		params.add(epdCertificate.getOrganizeSeq());
		params.add(epdCertificate.getCerType());
		params.add(epdCertificate.getCerName());
		String certificateSeq = epdCertificate.getCertificateSeq();
		if (null != certificateSeq && !"".equals(certificateSeq)){
			strSql.append(" and certificate_seq <> ?");
			params.add(certificateSeq);
		}
		List<EpdCertificate> certificates = (List<EpdCertificate>) super.queryAll(strSql.toString(),
				params,new EpdCertificate());
		return certificates;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdCertificate> queryByPK(String certificateSeq){
		String strSql = "select * from epd_certificate where certificate_seq=?";
		List params = new ArrayList();
		params.add(certificateSeq);
		List<EpdCertificate> certificates = (List<EpdCertificate>) super.queryAll(strSql,params,new EpdCertificate());
		return certificates;
	}
	
	@SuppressWarnings("unchecked")
	public List<EpdCertificate> queryByAll(){
		String strSql = "select * from epd_certificate";
		List<EpdCertificate> certificates = (List<EpdCertificate>) super.queryAll(strSql,null,new EpdCertificate());
		return certificates;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdCertificate> queryPageByCustom(String organizeSeq, String cerType, String cerName,
			String cerStatus,int start,int limit){
		StringBuffer strSql = new StringBuffer("select * from epd_certificate where 1=1");
		List params = new ArrayList();
		if (null != cerType && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != cerType && !"".equals(cerType)){
			strSql.append(" and cer_type = ?");
			params.add(cerType);
		}
		if (null != cerName && !"".equals(cerName)){
			strSql.append(" and cer_name = ?");
			params.add(cerName);
		}
		if (null != cerStatus && !"".equals(cerStatus)){
			strSql.append(" and cer_status = ?");
			params.add(cerStatus);
		}
		strSql.append(" order by cer_type,cer_name");
		List<EpdCertificate> certificates = (List<EpdCertificate>) super.queryPage(strSql.toString(),
				params,new EpdCertificate(),start,limit);
		return certificates;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdCertificate> queryAllByCustom(String organizeSeq, String cerType, String cerName, String cerStatus){
		StringBuffer strSql = new StringBuffer("select * from epd_certificate where 1=1");
		List params = new ArrayList();
		if (null != cerType && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != cerType && !"".equals(cerType)){
			strSql.append(" and cer_type = ?");
			params.add(cerType);
		}
		if (null != cerName && !"".equals(cerName)){
			strSql.append(" and cer_name = ?");
			params.add(cerName);
		}
		if (null != cerStatus && !"".equals(cerStatus)){
			strSql.append(" and cer_status = ?");
			params.add(cerStatus);
		}
		strSql.append(" order by cer_type,cer_name");
		List<EpdCertificate> certificates = (List<EpdCertificate>) super.queryAll(strSql.toString(),
				params,new EpdCertificate());
		return certificates;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int queryCountByCustom(String organizeSeq, String cerType, String cerName, String cerStatus){
		StringBuffer strSql = new StringBuffer("select count(1) from epd_certificate where 1=1");
		List params = new ArrayList();
		if (null != cerType && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != cerType && !"".equals(cerType)){
			strSql.append(" and cer_type = ?");
			params.add(cerType);
		}
		if (null != cerName && !"".equals(cerName)){
			strSql.append(" and cer_name = ?");
			params.add(cerName);
		}
		if (null != cerStatus && !"".equals(cerStatus)){
			strSql.append(" and cer_status = ?");
			params.add(cerStatus);
		}
		return super.queryCount(strSql.toString(),params);
	}

}
