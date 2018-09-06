
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.traffic.model.EpdPlancar;

public class EpdPlancarDao extends BaseDao{
	public EpdPlancarDao(Connection conn){
		super(conn);
	}
	
	public EpdPlancar insert(EpdPlancar epdPlancar, Map<String, Object> config){
		String pk = super.insert(epdPlancar,config);
		epdPlancar.setPlancarSeq(pk);
		return epdPlancar;
	}
	
	public void update(EpdPlancar epdPlancar, Map<String, Object> config){
		super.update(epdPlancar,config);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String plancarSeq){
		String strSql = "delete from epd_plancar where plancar_seq=?";
		List params = new ArrayList();
		params.add(plancarSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void deleteByPlanSeqAndPlanId(String planSeq, String planId) {
		String strSql = "delete from epd_plancar where plan_seq=? and plan_id=?";
		List params = new ArrayList();
		params.add(planSeq);
		params.add(planId);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdPlancar> queryByPK(String plancarSeq){
		String strSql = "select * from epd_plancar where plancar_seq=?";
		List params = new ArrayList();
		params.add(plancarSeq);
		List<EpdPlancar> plancars = (List<EpdPlancar>) super.queryAll(strSql,params,
				new EpdPlancar());
		return plancars;
	}
	
	@SuppressWarnings("unchecked")
	public List<EpdPlancar> queryByAll(){
		String strSql = "select * from epd_plancar";
		List<EpdPlancar> plancars = (List<EpdPlancar>) super.queryAll(strSql,null,
				new EpdPlancar());
		return plancars;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdPlancar> queryByPlanSeqAndPlanId(String planSeq, String planId) {
		String strSql = "select * from epd_plancar where plan_seq=? and plan_id=?";
		List params = new ArrayList();
		params.add(planSeq);
		params.add(planId);
		List<EpdPlancar> plancars = (List<EpdPlancar>) super.queryAll(strSql,params,
				new EpdPlancar());
		return plancars;
	}

}
