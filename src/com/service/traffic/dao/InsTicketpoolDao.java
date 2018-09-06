
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.traffic.model.InsTicketpool;

public class InsTicketpoolDao extends BaseDao{
	public InsTicketpoolDao(Connection conn){
		super(conn);
	}
	
	public InsTicketpool insert(InsTicketpool insTicketpool,Map<String, Object> config){
		String pk = super.insert(insTicketpool,config);
		insTicketpool.setTicketpoolSeq(pk);
		return insTicketpool;
	}
	
	public void update(InsTicketpool insTicketpool,Map<String, Object> config){
		super.update(insTicketpool,config);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String ticketpoolSeq){
		String strSql = "delete from ins_ticketpool where ticketpool_seq=?";
		List params = new ArrayList();
		params.add(ticketpoolSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<InsTicketpool> queryByValid(InsTicketpool insTicketpool) {
		StringBuffer strSql = new StringBuffer("select * from ins_ticketpool where organize_seq =? and  tickettype_seq= ? and oper_type = ?");
		List params = new ArrayList();
		params.add(insTicketpool.getOrganizeSeq());
		params.add(insTicketpool.getTickettypeSeq());
		params.add(insTicketpool.getOperType());
		String ticketpoolSeq = insTicketpool.getTicketpoolSeq();
		if (null != ticketpoolSeq && !"".equals(ticketpoolSeq)){
			strSql.append(" and ticketpool_seq <> ?");
			params.add(ticketpoolSeq);
		}
		
		strSql.append(" and ((insurance_start<= ? and insurance_limit>=?) or (insurance_start<= ? and insurance_limit>=?))");
		params.add(insTicketpool.getInsuranceStart());
		params.add(insTicketpool.getInsuranceStart());
		params.add(insTicketpool.getInsuranceLimit());
		params.add(insTicketpool.getInsuranceLimit());

		List<InsTicketpool> ticketpools = (List<InsTicketpool>) super.queryAll(strSql.toString(),
				params,new InsTicketpool());
		return ticketpools;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<InsTicketpool> queryByPK(String ticketpoolSeq){
		String strSql = "select * from ins_ticketpool where ticketpool_seq=?";
		List params = new ArrayList();
		params.add(ticketpoolSeq);
		List<InsTicketpool> ticketpools = (List<InsTicketpool>) super.queryAll(strSql,params,new InsTicketpool());
		return ticketpools;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<InsTicketpool> queryByOrganizeSeq(String organizeSeq) {
		String strSql = "select * from ins_ticketpool where organize_seq=?";
		List params = new ArrayList();
		params.add(organizeSeq);
		List<InsTicketpool> ticketpools = (List<InsTicketpool>) super.queryAll(strSql,params,new InsTicketpool());
		return ticketpools;
	}

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<InsTicketpool> queryPageByCustom(String organizeSeq, String companySeq, String tickettypeSeq,
			String operType, String userCode, String poolStatus, int start,int limit){
		StringBuffer strSql = new StringBuffer(
				"select ins_ticketpool.*," +
					"ins_company.insurance_code," +
					"ins_company.insurance_name" +
				" from ins_ticketpool" +
					" left join ins_company on ins_company.company_seq = ins_ticketpool.company_seq" +
				" where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and ins_ticketpool.organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != companySeq && !"".equals(companySeq)){
			strSql.append(" and ins_ticketpool.company_seq = ?");
			params.add(organizeSeq);
		}
		if (null != tickettypeSeq && !"".equals(tickettypeSeq)){
			strSql.append(" and ins_ticketpool.tickettype_seq = ?");
			params.add(tickettypeSeq);
		}
		if (null != operType && !"".equals(operType)){
			strSql.append(" and ins_ticketpool.oper_type = ?");
			params.add(operType);
		}
		if (null != userCode && !"".equals(userCode)){
			strSql.append(" and ins_ticketpool.user_code = ?");
			params.add(userCode);
		}
		if (null != poolStatus && !"".equals(poolStatus)){
			strSql.append(" and ins_ticketpool.pool_status = ?");
			params.add(poolStatus);
		}
		if (null != operType && !"".equals(operType)){
			if("1".equals(operType)){
				strSql.append(" order by ins_ticketpool.company_seq,ins_ticketpool.tickettype_seq," +
					"ins_ticketpool.oper_type,ins_ticketpool.insurance_start");
			}else{
				strSql.append(" order by ins_ticketpool.company_seq,ins_ticketpool.tickettype_seq," +
					"ins_ticketpool.oper_type,ins_ticketpool.user_code,ins_ticketpool.insurance_start");
			}
		}
		List<InsTicketpool> ticketpools = (List<InsTicketpool>) super.queryPage(strSql.toString(),
				params,new InsTicketpool(),start,limit);
		return ticketpools;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<InsTicketpool> queryAllByCustom(String organizeSeq, String companySeq, String tickettypeSeq, String operType,
			String userCode, String poolStatus){
		StringBuffer strSql = new StringBuffer(
				"select ins_ticketpool.*," +
					"ins_company.insurance_code," +
					"ins_company.insurance_name" +
				" from ins_ticketpool" +
					" left join ins_company on ins_company.company_seq = ins_ticketpool.company_seq" +
				" where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and ins_ticketpool.organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != companySeq && !"".equals(companySeq)){
			strSql.append(" and ins_ticketpool.company_seq = ?");
			params.add(organizeSeq);
		}
		if (null != tickettypeSeq && !"".equals(tickettypeSeq)){
			strSql.append(" and ins_ticketpool.tickettype_seq = ?");
			params.add(tickettypeSeq);
		}
		if (null != operType && !"".equals(operType)){
			strSql.append(" and ins_ticketpool.oper_type = ?");
			params.add(operType);
		}
		if (null != userCode && !"".equals(userCode)){
			strSql.append(" and ins_ticketpool.user_code = ?");
			params.add(userCode);
		}
		if (null != poolStatus && !"".equals(poolStatus)){
			strSql.append(" and ins_ticketpool.pool_status = ?");
			params.add(poolStatus);
		}
		if (null != operType && !"".equals(operType)){
			if("1".equals(operType)){
				strSql.append(" order by ins_ticketpool.company_seq,ins_ticketpool.tickettype_seq," +
					"ins_ticketpool.oper_type,ins_ticketpool.insurance_start");
			}else{
				strSql.append(" order by ins_ticketpool.company_seq,ins_ticketpool.tickettype_seq," +
					"ins_ticketpool.oper_type,ins_ticketpool.user_code,ins_ticketpool.insurance_start");
			}
		}
		List<InsTicketpool> ticketpools = (List<InsTicketpool>) super.queryAll(strSql.toString(),params,new InsTicketpool());
		return ticketpools;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int queryCountByCustom(String organizeSeq, String companySeq, String tickettypeSeq,
			String operType, String userCode, String poolStatus){
		StringBuffer strSql = new StringBuffer("select count(1) from ins_ticketpool where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != companySeq && !"".equals(companySeq)){
			strSql.append(" and company_seq = ?");
			params.add(organizeSeq);
		}
		if (null != tickettypeSeq && !"".equals(tickettypeSeq)){
			strSql.append(" and tickettype_seq = ?");
			params.add(tickettypeSeq);
		}
		if (null != operType && !"".equals(operType)){
			strSql.append(" and oper_type = ?");
			params.add(operType);
		}
		if (null != userCode && !"".equals(userCode)){
			strSql.append(" and user_code = ?");
			params.add(userCode);
		}
		if (null != poolStatus && !"".equals(poolStatus)){
			strSql.append(" and pool_status = ?");
			params.add(poolStatus);
		}
		return super.queryCount(strSql.toString(),params);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<InsTicketpool> queryValid(String organizeSeq,
			String tickettypeSeq, String operType, String userCode, String poolStatus) {
		StringBuffer strSql = new StringBuffer(
				"select ins_ticketpool.*," +
					"ins_company.insurance_code," +
					"ins_company.insurance_name" +
				" from ins_ticketpool" +
					" left join ins_company on ins_company.company_seq = ins_ticketpool.company_seq" +
				" where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and ins_ticketpool.organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != tickettypeSeq && !"".equals(tickettypeSeq)){
			strSql.append(" and ins_ticketpool.tickettype_seq = ?");
			params.add(tickettypeSeq);
		}

		if (null != userCode && !"".equals(userCode)){
			strSql.append(" and ins_ticketpool.user_code = ?");
			params.add(userCode);
		}
		if (null != poolStatus && !"".equals(poolStatus)){
			strSql.append(" and ins_ticketpool.pool_status = ?");
			params.add(poolStatus);
		}
		strSql.append(" order by ins_ticketpool.company_seq,ins_ticketpool.tickettype_seq," +
			"ins_ticketpool.oper_type,ins_ticketpool.user_code,ins_ticketpool.insurance_start");
		List<InsTicketpool> ticketpools = (List<InsTicketpool>) super.queryAll(strSql.toString(),params,new InsTicketpool());
		return ticketpools;
	}

}
