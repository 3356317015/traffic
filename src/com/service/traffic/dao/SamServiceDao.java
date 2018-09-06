
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.frame.model.SamService;

/**
 * SamServiceDao概要说明：销售点设置数据表操作
 * @author lcy
 */
public class SamServiceDao extends BaseDao{
	public SamServiceDao(Connection conn){
		super(conn);
	}

	public SamService insert(SamService samService, Map<String, Object> config){
		String pk = super.insert(samService,config);
		samService.setServiceSeq(pk);
		return samService;
	}
	
	public void update(SamService samService, Map<String, Object> config){
		super.update(samService,config);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String serviceSeq){
		String strSql = "delete from sam_service where service_seq=?";
		List params = new ArrayList();
		params.add(serviceSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<SamService> queryByPK(String serviceSeq) {
		String strSql = "select * from sam_service where service_seq =?";
		List params = new ArrayList();
		params.add(serviceSeq);
		List<SamService> samServices = (List<SamService>) super.queryAll(strSql,params,new SamService());
		return samServices;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<SamService> queryByValid(SamService samService) {
		StringBuffer strSql = new StringBuffer("select * from sam_service where (service_code = ? or service_spell=? or service_name=?)");
		List params = new ArrayList();
		params.add(samService.getServiceCode());
		params.add(samService.getServiceSpell());
		params.add(samService.getServiceName());
		String serviceSeq = samService.getServiceSeq();
		if (null != serviceSeq && !"".equals(serviceSeq)){
			strSql.append(" and service_seq <> ?");
			params.add(serviceSeq);
		}
		List<SamService> samServices = (List<SamService>) super.queryAll(strSql.toString(),
				params,new SamService());
		return samServices;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<SamService> queryPageByCustom(String organizeSeq,
			String serviceCode,String serviceName,String serviceStatus, int start, int limit) {
		StringBuffer strSql = new StringBuffer(
				" select sam_service.*" +
				" from sam_service" +
				" where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and sam_service.organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != serviceCode && !"".equals(serviceCode)){
			strSql.append(" and (sam_service.service_code like ? or sam_service.service_spell like ?)");
			params.add("%" + serviceCode + "%");
			params.add("%" + serviceCode + "%");
		}
		if (null != serviceName && !"".equals(serviceName)){
			strSql.append(" and sam_service.service_name like ?");
			params.add("%" + serviceName + "%");
		}
		if (null != serviceStatus && !"".equals(serviceStatus)){
			strSql.append(" and sam_service.status = ?");
			params.add(serviceStatus);
		}
		strSql.append(" order by sam_service.service_code");
		List<SamService> samServices = (List<SamService>) super.queryPage(strSql.toString(),params,new SamService(),start,limit);
		return samServices;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<SamService> queryAllByCustom(String organizeSeq,
			String serviceCode,String serviceName,String serviceStatus) {
		StringBuffer strSql = new StringBuffer(
				" select sam_service.*" +
				" from sam_service" +
				" where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and sam_service.organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != serviceCode && !"".equals(serviceCode)){
			strSql.append(" and (sam_service.service_code like ? or sam_service.service_spell like ?)");
			params.add("%" + serviceCode + "%");
			params.add("%" + serviceCode + "%");
		}
		if (null != serviceName && !"".equals(serviceName)){
			strSql.append(" and sam_service.service_name like ?");
			params.add("%" + serviceName + "%");
		}
		if (null != serviceStatus && !"".equals(serviceStatus)){
			strSql.append(" and sam_service.status = ?");
			params.add(serviceStatus);
		}
		strSql.append(" order by sam_service.service_code");
		List<SamService> samServices = (List<SamService>) super.queryAll(strSql.toString(),params,new SamService());
		return samServices;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int queryCountByCustom(String organizeSeq,
			String serviceCode,String serviceName,String serviceStatus) {
		StringBuffer strSql = new StringBuffer("select count(1) from sam_service where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and sam_service.organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != serviceCode && !"".equals(serviceCode)){
			strSql.append(" and (sam_service.service_code like ? or sam_service.service_spell like ?)");
			params.add("%" + serviceCode + "%");
			params.add("%" + serviceCode + "%");
		}
		if (null != serviceName && !"".equals(serviceName)){
			strSql.append(" and sam_service.service_name like ?");
			params.add("%" + serviceName + "%");
		}
		if (null != serviceStatus && !"".equals(serviceStatus)){
			strSql.append(" and sam_service.status = ?");
			params.add(serviceStatus);
		}
		return super.queryCount(strSql.toString(),params);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<SamService> queryByOrganizeSeq(String organizeSeq) {
		String strSql = "select sam_service.*" +
				" from sam_service" +
				" where sam_service.organize_seq =?";
		List params = new ArrayList();
		params.add(organizeSeq);
		List<SamService> samServices = (List<SamService>) super.queryAll(strSql,params,new SamService());
		return samServices;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<SamService> queryByOrganizeAndSaleType(String organizeSeq,
			String saleType) {
		String strSql = "select sam_service.*" +
				" from sam_service" +
				" where sam_service.organize_seq =? and sam_service.sale_type=?";
		List params = new ArrayList();
		params.add(organizeSeq);
		params.add(saleType);
		List<SamService> samServices = (List<SamService>) super.queryAll(strSql,params,new SamService());
		return samServices;
	}



}
