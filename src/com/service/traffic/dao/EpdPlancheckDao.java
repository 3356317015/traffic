
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.traffic.model.EpdPlancheck;

public class EpdPlancheckDao extends BaseDao{
	public EpdPlancheckDao(Connection conn){
		super(conn);
	}
	
	public EpdPlancheck insert(EpdPlancheck epdPlancheck, Map<String, Object> config){
		String pk = super.insert(epdPlancheck,config);
		epdPlancheck.setPlancheckSeq(pk);
		return epdPlancheck;
	}
	
	public void update(EpdPlancheck epdPlancheck, Map<String, Object> config){
		super.update(epdPlancheck,config);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String plancheckSeq){
		String strSql = "delete from epd_plancheck where plancheck_seq=?";
		List params = new ArrayList();
		params.add(plancheckSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void deleteByPlanSeqAndPlanId(String planSeq, String planId) {
		String strSql = "delete from epd_plancheck where plan_seq=? and plan_id=?";
		List params = new ArrayList();
		params.add(planSeq);
		params.add(planId);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdPlancheck> queryByPK(String plancheckSeq){
		String strSql = "select * from epd_plancheck where plancheck_seq=?";
		List params = new ArrayList();
		params.add(plancheckSeq);
		List<EpdPlancheck> planchecks = (List<EpdPlancheck>) super.queryAll(strSql,params,
				new EpdPlancheck());
		return planchecks;
	}
	
	@SuppressWarnings("unchecked")
	public List<EpdPlancheck> queryByAll(){
		String strSql = "select * from epd_plancheck";
		List<EpdPlancheck> planchecks = (List<EpdPlancheck>) super.queryAll(strSql,null,
				new EpdPlancheck());
		return planchecks;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdPlancheck> queryByPlanSeqAndPlanId(String planSeq, String planId) {
		String strSql = "select epd_plancheck.*,"+
							"epd_checkgate.check_code," +
							"epd_checkgate.check_name" + 
				" from epd_plancheck" +
					" left join epd_checkgate on epd_plancheck.checkgate_seq=epd_checkgate.checkgate_seq" +
				" where plan_seq=? and plan_id=?";
		List params = new ArrayList();
		params.add(planSeq);
		params.add(planId);
		List<EpdPlancheck> planchecks = (List<EpdPlancheck>) super.queryAll(strSql,params,
				new EpdPlancheck());
		return planchecks;
	}

}
