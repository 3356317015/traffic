
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.traffic.model.InsTickettype;

public class InsTickettypeDao extends BaseDao{
	public InsTickettypeDao(Connection conn){
		super(conn);
	}
	
	public InsTickettype insert(InsTickettype insTickettype,Map<String, Object> config){
		String pk = super.insert(insTickettype,config);
		insTickettype.setTickettypeSeq(pk);
		return insTickettype;
	}
	
	public void update(InsTickettype insTickettype,Map<String, Object> config){
		super.update(insTickettype,config);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String tickettypeSeq){
		String strSql = "delete from ins_tickettype where tickettype_seq=?";
		List params = new ArrayList();
		params.add(tickettypeSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<InsTickettype> queryByValid(InsTickettype insTickettype) {
		StringBuffer strSql = new StringBuffer("select * from ins_tickettype where organize_seq = ? and company_seq = ? and ticket_name = ?");
		List params = new ArrayList();
		params.add(insTickettype.getOrganizeSeq());
		params.add(insTickettype.getCompanySeq());
		params.add(insTickettype.getTicketName());
		String tickettypeSeq = insTickettype.getTickettypeSeq();
		if (null != tickettypeSeq && !"".equals(tickettypeSeq)){
			strSql.append(" and tickettype_seq <> ?");
			params.add(tickettypeSeq);
		}
		List<InsTickettype> tickettypes = (List<InsTickettype>) super.queryAll(strSql.toString(),
				params,new InsTickettype());
		return tickettypes;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<InsTickettype> queryByPK(String tickettypeSeq){
		String strSql = "select * from ins_tickettype where tickettype_seq=?";
		List params = new ArrayList();
		params.add(tickettypeSeq);
		List<InsTickettype> tickettypes = (List<InsTickettype>) super.queryAll(strSql,params,new InsTickettype());
		return tickettypes;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<InsTickettype> queryByOrganizeSeq(String organizeSeq) {
		StringBuffer strSql = new StringBuffer(
				"select ins_tickettype.*," +
					"ins_company.insurance_code," +
					"ins_company.insurance_name" +
				" from ins_tickettype" +
					" left join ins_company on ins_tickettype.company_seq = ins_company.company_seq" +
				" where ins_tickettype.organize_seq=?");
		List params = new ArrayList();
		params.add(organizeSeq);
		List<InsTickettype> tickettypes = (List<InsTickettype>) super.queryAll(strSql.toString(),params,new InsTickettype());
		return tickettypes;
	}

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<InsTickettype> queryPageByCustom(String organizeSeq,
			String companySeq, int start,int limit){
		StringBuffer strSql = new StringBuffer(
				"select ins_tickettype.*," +
					"ins_company.insurance_code," +
					"ins_company.insurance_name" +
				" from ins_tickettype" +
					" left join ins_company on ins_tickettype.company_seq = ins_company.company_seq" +
				" where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and ins_tickettype.organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != companySeq && !"".equals(companySeq)){
			strSql.append(" and ins_tickettype.company_seq = ?");
			params.add(companySeq);
		}
		strSql.append(" order by ins_tickettype.company_seq,ins_tickettype.ticket_name");
		List<InsTickettype> tickettypes = (List<InsTickettype>) super.queryPage(strSql.toString(),
				params,new InsTickettype(),start,limit);
		return tickettypes;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<InsTickettype> queryAllByCustom(String organizeSeq, String companySeq){
		StringBuffer strSql = new StringBuffer(
			"select ins_tickettype.*," +
				"ins_company.insurance_code," +
				"ins_company.insurance_name" +
			" from ins_tickettype" +
				" left join ins_company on ins_tickettype.company_seq = ins_company.company_seq" +
			" where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and ins_tickettype.organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != companySeq && !"".equals(companySeq)){
			strSql.append(" and ins_tickettype.company_seq = ?");
			params.add(companySeq);
		}
		strSql.append(" order by ins_tickettype.company_seq,ins_tickettype.ticket_name");
		List<InsTickettype> tickettypes = (List<InsTickettype>) super.queryAll(strSql.toString(),params,new InsTickettype());
		return tickettypes;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int queryCountByCustom(String organizeSeq, String companySeq){
		StringBuffer strSql = new StringBuffer("select count(1) from ins_tickettype where 1=1");
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

}
