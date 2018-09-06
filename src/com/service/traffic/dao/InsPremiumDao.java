
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.traffic.model.InsPremium;

public class InsPremiumDao extends BaseDao{
	public InsPremiumDao(Connection conn){
		super(conn);
	}
	
	public InsPremium insert(InsPremium insPremium, Map<String, Object> config){
		String pk = super.insert(insPremium,config);
		insPremium.setPremiumSeq(pk);
		return insPremium;
	}
	
	public void update(InsPremium insPremium, Map<String, Object> config){
		super.update(insPremium,config);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String premiumSeq){
		String strSql = "delete from ins_premium where premium_seq=?";
		List params = new ArrayList();
		params.add(premiumSeq);
		super.executeSql(strSql, params);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<InsPremium> queryPageByCustom(String organizeSeq, String companySeq,
			int start, int limit){
		StringBuffer strSql = new StringBuffer(
				"select ins_premium.*," +
					"ins_company.insurance_code," +
					"ins_company.insurance_name" +
				" from ins_premium" +
					" left join ins_company on ins_premium.company_seq = ins_company.company_seq" +
				" where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and ins_premium.organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != companySeq && !"".equals(companySeq)){
			strSql.append(" and ins_premium.company_seq = ?");
			params.add(companySeq);
		}
		strSql.append(" order by ins_premium.company_seq,ins_premium.premium");
		List<InsPremium> premiums = (List<InsPremium>) super.queryPage(strSql.toString(),params,new InsPremium(),start,limit);
		return premiums;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<InsPremium> queryAllByCustom(String organizeSeq, String companySeq){
		StringBuffer strSql = new StringBuffer(
				"select ins_premium.*," +
					"ins_company.insurance_code," +
					"ins_company.insurance_name" +
				" from ins_premium" +
					" left join ins_company on ins_premium.company_seq = ins_company.company_seq" +
				" where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and ins_premium.organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != companySeq && !"".equals(companySeq)){
			strSql.append(" and ins_premium.company_seq = ?");
			params.add(companySeq);
		}
		strSql.append(" order by ins_premium.company_seq,ins_premium.premium");
		List<InsPremium> premiums = (List<InsPremium>) super.queryAll(strSql.toString(),params,new InsPremium());
		return premiums;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int queryCountByCustom(String organizeSeq, String companySeq){
		StringBuffer strSql = new StringBuffer("select count(1) from ins_premium where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != companySeq && !"".equals(companySeq)){
			strSql.append(" and company_seq = ?");
			params.add(companySeq);
		}
		return super.queryCount(strSql.toString(),params);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<InsPremium> queryByOrganizeSeq(String organizeSeq) {
		StringBuffer strSql = new StringBuffer("select * from ins_premium where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		strSql.append(" order by company_seq,premium");
		List<InsPremium> premiums = (List<InsPremium>) super.queryAll(strSql.toString(),params,new InsPremium());
		return premiums;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<InsPremium> queryByValid(InsPremium insPremium) {
		StringBuffer strSql = new StringBuffer("select * from ins_premium where organize_seq =?" +
				" and company_seq = ? and premium = ? and start_price =?  and limit_price=?");
		List params = new ArrayList();
		params.add(insPremium.getOrganizeSeq());
		params.add(insPremium.getCompanySeq());
		params.add(insPremium.getPremium());
		params.add(insPremium.getStartPrice());
		params.add(insPremium.getLimitPrice());
		String companySeq = insPremium.getPremiumSeq();
		if (null != companySeq && !"".equals(companySeq)){
			strSql.append(" and premium_seq <> ?");
			params.add(companySeq);
		}
		List<InsPremium> premiums = (List<InsPremium>) super.queryAll(strSql.toString(),
				params,new InsPremium());
		return premiums;
	}
}
