
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.traffic.model.EpdPlan;

public class EpdPlanDao extends BaseDao{
	public EpdPlanDao(Connection conn){
		super(conn);
	}
	
	public EpdPlan insert(EpdPlan epdPlan, Map<String, Object> config){
		String pk = super.insert(epdPlan,config);
		epdPlan.setPlanSeq(pk);
		return epdPlan;
	}
	
	public void update(EpdPlan epdPlan, Map<String, Object> config){
		super.update(epdPlan,config);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String planSeq){
		String strSql = "delete from epd_plan where plan_seq=?";
		List params = new ArrayList();
		params.add(planSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void deleteByRouteSeq(String routeSeq) {
		String strSql = "delete from epd_plan where route_seq=?";
		List params = new ArrayList();
		params.add(routeSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdPlan> queryByValid(EpdPlan epdPlan) {
		StringBuffer strSql = new StringBuffer("select * from epd_plan where organize_seq =?" +
				" and plan_id = ?");
		List params = new ArrayList();
		params.add(epdPlan.getOrganizeSeq());
		params.add(epdPlan.getPlanId());
		String planSeq = epdPlan.getPlanSeq();
		if (null != planSeq && !"".equals(planSeq)){
			strSql.append(" and plan_seq <> ?");
			params.add(planSeq);
		}
		List<EpdPlan> plans = (List<EpdPlan>) super.queryAll(strSql.toString(),
				params,new EpdPlan());
		return plans;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdPlan> queryByPK(String planSeq){
		String strSql = "select * from epd_plan where plan_seq=?";
		List params = new ArrayList();
		params.add(planSeq);
		List<EpdPlan> plans = (List<EpdPlan>) super.queryAll(strSql,params,new EpdPlan());
		return plans;
	}
	
	@SuppressWarnings("unchecked")
	public List<EpdPlan> queryByAll(){
		StringBuffer strSql = new StringBuffer("select epd_plan.*," +
				"epd_route.route_code," +
				"epd_route.route_name," +
				"epd_cargrade.cargrade_name,"+
				"epd_checkgate.check_code,"+
				"epd_checkgate.check_name,"+
				"epd_parking.parking_name," +
				"sam_organize.organize_name as deal_organizename"+
			" from epd_plan" +
				" left join epd_route on epd_plan.route_seq = epd_route.route_seq" +
				" left join epd_cargrade on epd_plan.cargrade_seq = epd_cargrade.cargrade_seq" +
				" left join epd_checkgate on epd_plan.checkgate_seq = epd_checkgate.checkgate_seq"+
				" left join epd_parking on epd_plan.parking_seq = epd_parking.parking_seq" +
				" left join sam_organize on epd_plan.deal_organize = sam_organize.organize_seq");
		List<EpdPlan> plans = (List<EpdPlan>) super.queryAll(strSql.toString(),null,new EpdPlan());
		return plans;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int queryCountByRouteSeq(String routeSeq) {
		StringBuffer strSql = new StringBuffer("select count(1) from epd_plan where route_seq = ?");
		List params = new ArrayList();
		params.add(routeSeq);
		return super.queryCount(strSql.toString(), params);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdPlan> queryByRouteSeq(String routeSeq) {
		String strSql = "select * from epd_plan where route_seq=?";
		List params = new ArrayList();
		params.add(routeSeq);
		List<EpdPlan> plans = (List<EpdPlan>) super.queryAll(strSql,params,new EpdPlan());
		return plans;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdPlan> queryPageByCustom(String organizeSeq, String routeSeq,String stationSeq,String planId,
			String planType, String planStatus, int start, int limit){
		StringBuffer strSql = new StringBuffer("select epd_plan.*," +
					"epd_route.route_code," +
					"epd_route.route_name," +
					"epd_cargrade.cargrade_name,"+
					"epd_checkgate.check_code,"+
					"epd_checkgate.check_name,"+
					"epd_parking.parking_name," +
					"sam_organize.organize_name as deal_organizename"+
				" from epd_plan" +
					" left join epd_route on epd_plan.route_seq = epd_route.route_seq" +
					" left join epd_cargrade on epd_plan.cargrade_seq = epd_cargrade.cargrade_seq" +
					" left join epd_checkgate on epd_plan.checkgate_seq = epd_checkgate.checkgate_seq"+
					" left join epd_parking on epd_plan.parking_seq = epd_parking.parking_seq" +
					" left join sam_organize on epd_plan.deal_organize = sam_organize.organize_seq");
		if (null != stationSeq && !"".equals(stationSeq)){
			strSql.append(",epd_planstation");
		}
		strSql.append(" where 1=1");
		if (null != stationSeq && !"".equals(stationSeq)){
			strSql.append(" and epd_plan.plan_id=epd_planstation.plan_id");
		}
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and epd_plan.organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != routeSeq && !"".equals(routeSeq)){
			strSql.append(" and epd_plan.route_seq = ?");
			params.add(routeSeq);
		}
		if (null != stationSeq && !"".equals(stationSeq)){
			strSql.append(" and epd_planstation.station_seq = ?");
			params.add(stationSeq);
		}
		if (null != planId && !"".equals(planId)){
			strSql.append(" and epd_plan.plan_id = ?");
			params.add(planId);
		}
		if (null != planType && !"".equals(planType)){
			strSql.append(" and epd_plan.plan_type = ?");
			params.add(planType);
		}
		
		if (null != planStatus && !"".equals(planStatus)){
			strSql.append(" and epd_plan.plan_status = ?");
			params.add(planStatus);
		}
		strSql.append(" order by epd_plan.plan_id");
		List<EpdPlan> plans = (List<EpdPlan>) super.queryPage(strSql.toString(),params,new EpdPlan(),start,limit);
		return plans;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdPlan> queryAllByCustom(String organizeSeq, String routeSeq,String stationSeq,String planId,
			String planType, String planStatus){
		StringBuffer strSql = new StringBuffer("select epd_plan.*," +
				"epd_route.route_code," +
				"epd_route.route_name," +
				"epd_cargrade.cargrade_name,"+
				"epd_checkgate.check_code,"+
				"epd_checkgate.check_name,"+
				"epd_parking.parking_name," +
				"sam_organize.organize_name as deal_organizename"+
			" from epd_plan" +
				" left join epd_route on epd_plan.route_seq = epd_route.route_seq" +
				" left join epd_cargrade on epd_plan.cargrade_seq = epd_cargrade.cargrade_seq" +
				" left join epd_checkgate on epd_plan.checkgate_seq = epd_checkgate.checkgate_seq"+
				" left join epd_parking on epd_plan.parking_seq = epd_parking.parking_seq" +
				" left join sam_organize on epd_plan.deal_organize = sam_organize.organize_seq");
		if (null != stationSeq && !"".equals(stationSeq)){
			strSql.append(",epd_planstation");
		}
		strSql.append(" where 1=1");
		if (null != stationSeq && !"".equals(stationSeq)){
			strSql.append(" and epd_plan.plan_id=epd_planstation.plan_id");
		}
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and epd_plan.organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != routeSeq && !"".equals(routeSeq)){
			strSql.append(" and epd_plan.route_seq = ?");
			params.add(routeSeq);
		}
		if (null != stationSeq && !"".equals(stationSeq)){
			strSql.append(" and epd_planstation.station_seq = ?");
			params.add(stationSeq);
		}
		if (null != planId && !"".equals(planId)){
			strSql.append(" and epd_plan.plan_id = ?");
			params.add(planId);
		}
		if (null != planType && !"".equals(planType)){
			strSql.append(" and epd_plan.plan_type = ?");
			params.add(planType);
		}
		if (null != planStatus && !"".equals(planStatus)){
			strSql.append(" and epd_plan.plan_status = ?");
			params.add(planStatus);
		}
		strSql.append(" order by epd_plan.plan_id");
		List<EpdPlan> plans = (List<EpdPlan>) super.queryAll(strSql.toString(),params,new EpdPlan());
		return plans;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int queryCountByCustom(String organizeSeq, String routeSeq,String stationSeq,String planId,
			String planType, String planStatus){
		StringBuffer strSql = new StringBuffer("select count(1) from epd_plan");
		if (null != stationSeq && !"".equals(stationSeq)){
			strSql.append(",epd_planstation");
		}
		strSql.append(" where 1=1");
		if (null != stationSeq && !"".equals(stationSeq)){
			strSql.append(" and epd_plan.plan_id=epd_planstation.plan_id");
		}
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and epd_plan.organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != routeSeq && !"".equals(routeSeq)){
			strSql.append(" and epd_plan.route_seq = ?");
			params.add(routeSeq);
		}
		if (null != stationSeq && !"".equals(stationSeq)){
			strSql.append(" and epd_planstation.station_seq = ?");
			params.add(stationSeq);
		}
		if (null != planId && !"".equals(planId)){
			strSql.append(" and epd_plan.plan_id = ?");
			params.add(planId);
		}
		if (null != planType && !"".equals(planType)){
			strSql.append(" and epd_plan.plan_type = ?");
			params.add(planType);
		}
		
		if (null != planStatus && !"".equals(planStatus)){
			strSql.append(" and epd_plan.plan_status = ?");
			params.add(planStatus);
		}
		return super.queryCount(strSql.toString(),params);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdPlan> queryByOrganizeSeq(String organizeSeq) {
		StringBuffer strSql = new StringBuffer("select epd_plan.*," +
				"epd_route.route_code," +
				"epd_route.route_name," +
				"epd_cargrade.cargrade_name,"+
				"epd_checkgate.check_code,"+
				"epd_checkgate.check_name,"+
				"epd_parking.parking_name," +
				"sam_organize.organize_name as deal_organizename"+
			" from epd_plan" +
				" left join epd_route on epd_plan.route_seq = epd_route.route_seq" +
				" left join epd_cargrade on epd_plan.cargrade_seq = epd_cargrade.cargrade_seq" +
				" left join epd_checkgate on epd_plan.checkgate_seq = epd_checkgate.checkgate_seq"+
				" left join epd_parking on epd_plan.parking_seq = epd_parking.parking_seq" +
				" left join sam_organize on epd_plan.deal_organize = sam_organize.organize_seq" +
			" where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and epd_plan.organize_seq = ?");
			params.add(organizeSeq);
		}
		
		strSql.append(" order by epd_plan.plan_id");
		
		List<EpdPlan> plans = (List<EpdPlan>) super.queryAll(strSql.toString(),params,new EpdPlan());
		return plans;
	}


}
