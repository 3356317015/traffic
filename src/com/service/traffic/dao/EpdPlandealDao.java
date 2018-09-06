
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.traffic.model.EpdPlandeal;

public class EpdPlandealDao extends BaseDao{
	public EpdPlandealDao(Connection conn){
		super(conn);
	}
	
	public EpdPlandeal insert(EpdPlandeal epdPlandeal, Map<String, Object> config){
		String pk = super.insert(epdPlandeal,config);
		epdPlandeal.setPlandealSeq(pk);
		return epdPlandeal;
	}
	
	public void update(EpdPlandeal epdPlandeal, Map<String, Object> config){
		super.update(epdPlandeal,config);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String plandealSeq){
		String strSql = "delete from epd_plandeal where plandeal_seq=?";
		List params = new ArrayList();
		params.add(plandealSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void deleteByPlanSeqAndPlanId(String planSeq, String planId) {
		String strSql = "delete from epd_plandeal where plan_seq=? and plan_id=?";
		List params = new ArrayList();
		params.add(planSeq);
		params.add(planId);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdPlandeal> queryByPK(String plandealSeq){
		String strSql = "select * from epd_plandeal where plandeal_seq=?";
		List params = new ArrayList();
		params.add(plandealSeq);
		List<EpdPlandeal> plandeals = (List<EpdPlandeal>) super.queryAll(strSql,params,
				new EpdPlandeal());
		return plandeals;
	}
	
	@SuppressWarnings("unchecked")
	public List<EpdPlandeal> queryByAll(){
		String strSql = "select * from epd_plandeal";
		List<EpdPlandeal> plandeals = (List<EpdPlandeal>) super.queryAll(strSql,null,
				new EpdPlandeal());
		return plandeals;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdPlandeal> queryByPlanSeqAndPlanId(String planSeq, String planId) {
		String strSql = "select epd_plandeal.*,sam_organize.organize_name" +
				" from epd_plandeal" +
						" left join sam_organize on sam_organize.organize_code=epd_plandeal.deal_organize" +
				" where plan_seq =? and plan_id=?";
		List params = new ArrayList();
		params.add(planSeq);
		params.add(planId);
		List<EpdPlandeal> plandeals = (List<EpdPlandeal>) super.queryAll(strSql,params,
				new EpdPlandeal());
		return plandeals;
	}

}
