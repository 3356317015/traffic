
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.traffic.model.EpdFaresuit;

public class EpdFaresuitDao extends BaseDao{
	public EpdFaresuitDao(Connection conn){
		super(conn);
	}
	
	public EpdFaresuit insert(EpdFaresuit epdFaresuit, Map<String, Object> config){
		String pk = super.insert(epdFaresuit,config);
		epdFaresuit.setFaresuitSeq(pk);
		return epdFaresuit;
	}
	
	public void update(EpdFaresuit epdFaresuit, Map<String, Object> config){
		super.update(epdFaresuit,config);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String faresuitSeq){
		String strSql = "delete from epd_faresuit where faresuit_seq=?";
		List params = new ArrayList();
		params.add(faresuitSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdFaresuit> queryByValid(EpdFaresuit epdFaresuit) {
		StringBuffer strSql = new StringBuffer("select * from epd_faresuit where organize_seq =?" +
				" and faresuit_code = ?");
		List params = new ArrayList();
		params.add(epdFaresuit.getOrganizeSeq());
		params.add(epdFaresuit.getFaresuitCode());
		String faresuitSeq = epdFaresuit.getFaresuitSeq();
		if (null != faresuitSeq && !"".equals(faresuitSeq)){
			strSql.append(" and faresuit_seq <> ?");
			params.add(faresuitSeq);
		}
		List<EpdFaresuit> faresuits = (List<EpdFaresuit>) super.queryAll(strSql.toString(),
				params,new EpdFaresuit());
		return faresuits;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdFaresuit> queryByPK(String faresuitSeq){
		String strSql = "select * from epd_faresuit where faresuit_seq=?";
		List params = new ArrayList();
		params.add(faresuitSeq);
		List<EpdFaresuit> faresuits = (List<EpdFaresuit>) super.queryAll(strSql,params,new EpdFaresuit());
		return faresuits;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdFaresuit> queryByAll(String organizeSeq){
		StringBuffer strSql = new StringBuffer("select * from epd_faresuit where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		strSql.append(" order by faresuit_code");
		List<EpdFaresuit> faresuits = (List<EpdFaresuit>) super.queryAll(strSql.toString(),
				params,new EpdFaresuit());
		return faresuits;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdFaresuit> queryPageByCustom(String organizeSeq, String faresuitCode, String faresuitName,
			int start,int limit){
		StringBuffer strSql = new StringBuffer("select * from epd_faresuit where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != faresuitCode && !"".equals(faresuitCode)){
			strSql.append(" and faresuit_code = ?");
			params.add(faresuitCode);
		}
		if (null != faresuitName && !"".equals(faresuitName)){
			strSql.append(" and faresuit_name = ?");
			params.add(faresuitName);
		}
		strSql.append(" order by faresuit_code");
		List<EpdFaresuit> faresuits = (List<EpdFaresuit>) super.queryPage(strSql.toString(),
				params,new EpdFaresuit(),start,limit);
		return faresuits;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdFaresuit> queryAllByCustom(String organizeSeq, String faresuitCode, String faresuitName){
		StringBuffer strSql = new StringBuffer("select * from epd_faresuit where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != faresuitCode && !"".equals(faresuitCode)){
			strSql.append(" and faresuit_code = ?");
			params.add(faresuitCode);
		}
		if (null != faresuitName && !"".equals(faresuitName)){
			strSql.append(" and faresuit_name = ?");
			params.add(faresuitName);
		}
		strSql.append(" order by faresuit_code");
		List<EpdFaresuit> faresuits = (List<EpdFaresuit>) super.queryAll(strSql.toString(),
				params,new EpdFaresuit());
		return faresuits;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int queryCountByCustom(String organizeSeq, String faresuitCode, String faresuitName){
		StringBuffer strSql = new StringBuffer("select count(1) from epd_faresuit where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != faresuitCode && !"".equals(faresuitCode)){
			strSql.append(" and faresuit_code = ?");
			params.add(faresuitCode);
		}
		if (null != faresuitName && !"".equals(faresuitName)){
			strSql.append(" and faresuit_name = ?");
			params.add(faresuitName);
		}
		return super.queryCount(strSql.toString(),params);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdFaresuit> queryByLinerDate(String organizeSeq, String linerDate) {
		StringBuffer strSql = new StringBuffer("SELECT * FROM epd_faresuit WHERE" +
				" organize_seq =? and start_date<=? AND end_date>=? and faresuit_status='1'");
		List params = new ArrayList();
		params.add(organizeSeq);
		params.add(linerDate);
		params.add(linerDate);
		List<EpdFaresuit> faresuits = (List<EpdFaresuit>) super.queryAll(strSql.toString(),
				params,new EpdFaresuit());
		return faresuits;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdFaresuit> queryByOrganizeSeq(String organizeSeq) {
		StringBuffer strSql = new StringBuffer("select * from epd_faresuit where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		strSql.append(" order by faresuit_seq");
		List<EpdFaresuit> faresuits = (List<EpdFaresuit>) super.queryAll(strSql.toString(),
				params,new EpdFaresuit());
		return faresuits;
	}

}
