
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.traffic.model.EpdPlanseat;

public class EpdPlanseatDao extends BaseDao{
	public EpdPlanseatDao(Connection conn){
		super(conn);
	}
	
	public EpdPlanseat insert(EpdPlanseat epdPlanseat, Map<String, Object> config){
		String pk = super.insert(epdPlanseat,config);
		epdPlanseat.setPlanseatSeq(pk);
		return epdPlanseat;
	}
	
	public void update(EpdPlanseat epdPlanseat, Map<String, Object> config){
		super.update(epdPlanseat,config);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String planseatSeq){
		String strSql = "delete from epd_planseat where planseat_seq=?";
		List params = new ArrayList();
		params.add(planseatSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void deleteByPlanSeqAndPlanId(String planSeq, String planId) {
		String strSql = "delete from epd_planseat where plan_seq=? and plan_id=?";
		List params = new ArrayList();
		params.add(planSeq);
		params.add(planId);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdPlanseat> queryByPK(String planseatSeq){
		String strSql = "select * from epd_planseat where planseat_seq=?";
		List params = new ArrayList();
		params.add(planseatSeq);
		List<EpdPlanseat> planseats = (List<EpdPlanseat>) super.queryAll(strSql,params,
				new EpdPlanseat());
		return planseats;
	}
	
	@SuppressWarnings("unchecked")
	public List<EpdPlanseat> queryByAll(){
		String strSql = "select * from epd_planseat";
		List<EpdPlanseat> planseats = (List<EpdPlanseat>) super.queryAll(strSql,null,
				new EpdPlanseat());
		return planseats;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdPlanseat> queryByPlanSeqAndPlanId(String planSeq, String planId) {
		String strSql = "select * from epd_planseat where plan_seq =? and plan_id=?";
		List params = new ArrayList();
		params.add(planSeq);
		params.add(planId);
		List<EpdPlanseat> planseats = (List<EpdPlanseat>) super.queryAll(strSql,params,
				new EpdPlanseat());
		return planseats;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int queryCountByPlanIdAndState(String planSeq, String planId, int seatState) {
		String strSql = "select count(1) from epd_planseat where plan_seq = ? and plan_id=? and seat_state =?";
		List params = new ArrayList();
		params.add(planSeq);
		params.add(planId);
		params.add(seatState);
		return super.queryCount(strSql, params);
	}

}
