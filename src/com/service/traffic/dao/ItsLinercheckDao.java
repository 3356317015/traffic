package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.traffic.model.ItsLinercheck;

public class ItsLinercheckDao extends BaseDao{
	public ItsLinercheckDao(Connection conn){
		super(conn);
	}
	
	public ItsLinercheck insert(ItsLinercheck itsLinercheck, Map<String, Object> config){
		String pk = super.insert(itsLinercheck,config);
		itsLinercheck.setLinercheckSeq(pk);
		return itsLinercheck;
	}
	
	public void update(ItsLinercheck itsLinercheck, Map<String, Object> config){
		super.update(itsLinercheck,config);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String linercheckSeq){
		String strSql = "delete from its_linercheck where linercheck_seq=?";
		List params = new ArrayList();
		params.add(linercheckSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ItsLinercheck> queryByValid(ItsLinercheck itsLinercheck) {
		StringBuffer strSql = new StringBuffer("select * from its_linercheck where liner_date = ? and liner_id = ?" +
				" and checkgate_seq=?");
		List params = new ArrayList();
		params.add(itsLinercheck.getLinerDate());
		params.add(itsLinercheck.getLinerId());
		params.add(itsLinercheck.getCheckgateSeq());
		String linercheckSeq = itsLinercheck.getLinercheckSeq();
		if (null != linercheckSeq && !"".equals(linercheckSeq)){
			strSql.append(" and linercheck_seq <> ?");
			params.add(linercheckSeq);
		}
		List<ItsLinercheck> itscheckLiners = (List<ItsLinercheck>) super.queryAll(strSql.toString(),
				params,new ItsLinercheck());
		return itscheckLiners;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ItsLinercheck> queryByPK(String linercheckSeq){
		String strSql = "select * from its_linercheck where linercheck_seq=?";
		List params = new ArrayList();
		params.add(linercheckSeq);
		List<ItsLinercheck> linerchecks = (List<ItsLinercheck>) super.queryAll(strSql,params,new ItsLinercheck());
		return linerchecks;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ItsLinercheck> queryByLinerSeq(String linerSeq){
		String strSql = "select its_linercheck.*," +
					"epd_checkgate.check_code," +
					"epd_checkgate.check_name" +
				" from its_linercheck" +
					" left join epd_checkgate on epd_checkgate.checkgate_seq=its_linercheck.checkgate_seq" +
				" where liner_seq=?";
		List params = new ArrayList();
		params.add(linerSeq);
		List<ItsLinercheck> linerchecks = (List<ItsLinercheck>) super.queryAll(strSql,params,new ItsLinercheck());
		return linerchecks;
	}
	
	@SuppressWarnings("unchecked")
	public List<ItsLinercheck> queryByAll(){
		String strSql = "select * from its_linercheck";
		List<ItsLinercheck> linerchecks = (List<ItsLinercheck>) super.queryAll(strSql,null,new ItsLinercheck());
		return linerchecks;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void deleteByLinerSeq(String linerSeq) {
		String strSql = "delete from its_linercheck where liner_seq=?";
		List params = new ArrayList();
		params.add(linerSeq);
		super.executeSql(strSql, params);
	}

}
