
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.traffic.model.EpdPlanservice;

public class EpdPlanserviceDao extends BaseDao{
	public EpdPlanserviceDao(Connection conn){
		super(conn);
	}
	
	public EpdPlanservice insert(EpdPlanservice epdPlanservice, Map<String, Object> config){
		String pk = super.insert(epdPlanservice,config);
		epdPlanservice.setPlanserviceSeq(pk);
		return epdPlanservice;
	}
	
	public void update(EpdPlanservice epdPlanservice, Map<String, Object> config){
		super.update(epdPlanservice,config);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String planserviceSeq){
		String strSql = "delete from epd_planservice where planservice_seq=?";
		List params = new ArrayList();
		params.add(planserviceSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void deleteByPlanSeq(String planSeq) {
		String strSql = "delete from epd_planservice where plan_seq =?";
		List params = new ArrayList();
		params.add(planSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdPlanservice> queryByPK(String planserviceSeq){
		String strSql = "select epd_planservice.*," +
					"sam_service.service_name" +
				" from epd_planservice" +
					" left join sam_service on epd_planservice.service_seq = sam_service.service_seq" +
				" where planservice_seq=?";
		List params = new ArrayList();
		params.add(planserviceSeq);
		List<EpdPlanservice> planservices = (List<EpdPlanservice>) super.queryAll(strSql,params,
				new EpdPlanservice());
		return planservices;
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdPlanservice> queryByPlanSeq(String planSeq) {
		String strSql = "select epd_planservice.*," +
				"epd_route.route_name," +
				"sam_service.service_name" +
			" from epd_planservice" +
				" left join epd_route on epd_planservice.route_seq = epd_route.route_seq" +
				" left join sam_service on epd_planservice.service_seq = sam_service.service_seq" +
			" where plan_seq =?" +
			" order by epd_planservice.service_order";
		List params = new ArrayList();
		params.add(planSeq);
		List<EpdPlanservice> planservices = (List<EpdPlanservice>) super.queryAll(strSql,params,
				new EpdPlanservice());
		return planservices;
	}

}
