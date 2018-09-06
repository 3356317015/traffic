
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.traffic.model.EpdPlanstation;

public class EpdPlanstationDao extends BaseDao{
	public EpdPlanstationDao(Connection conn){
		super(conn);
	}
	
	public EpdPlanstation insert(EpdPlanstation epdPlanstation, Map<String, Object> config){
		String pk = super.insert(epdPlanstation,config);
		epdPlanstation.setPlanstationSeq(pk);
		return epdPlanstation;
	}
	
	public void update(EpdPlanstation epdPlanstation, Map<String, Object> config){
		super.update(epdPlanstation,config);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String planstationSeq){
		String strSql = "delete from epd_planstation where planstation_seq=?";
		List params = new ArrayList();
		params.add(planstationSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void deleteByPlanSeqAndPlanId(String planSeq, String planId) {
		String strSql = "delete from epd_planstation where plan_seq =? and plan_id=?";
		List params = new ArrayList();
		params.add(planSeq);
		params.add(planId);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdPlanstation> queryByPK(String planstationSeq){
		String strSql = "select epd_planstation.*," +
					"epd_station.station_name" +
				" from epd_planstation" +
					" left join epd_station on epd_planstation.station_seq = epd_station.station_seq" +
				" where planstation_seq=?";
		List params = new ArrayList();
		params.add(planstationSeq);
		List<EpdPlanstation> planstations = (List<EpdPlanstation>) super.queryAll(strSql,params,
				new EpdPlanstation());
		return planstations;
	}
	
	@SuppressWarnings("unchecked")
	public List<EpdPlanstation> queryByAll(){
		String strSql = "select epd_planstation.*," +
				"epd_station.station_name" +
			" from epd_planstation" +
				" left join epd_station on epd_planstation.station_seq = epd_station.station_seq";
		List<EpdPlanstation> planstations = (List<EpdPlanstation>) super.queryAll(strSql,null,
				new EpdPlanstation());
		return planstations;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdPlanstation> queryByPlanSeqAndPlanId(String planSeq, String planId) {
		String strSql = "select epd_planstation.*," +
				"epd_route.route_name," +
				"epd_station.station_name" +
			" from epd_planstation" +
				" left join epd_route on epd_planstation.route_seq = epd_route.route_seq" +
				" left join epd_station on epd_planstation.station_seq = epd_station.station_seq" +
			" where plan_seq =? and plan_id=?" +
			" order by epd_planstation.station_order";
		List params = new ArrayList();
		params.add(planSeq);
		params.add(planId);
		List<EpdPlanstation> planstations = (List<EpdPlanstation>) super.queryAll(strSql,params,
				new EpdPlanstation());
		return planstations;
	}

}
