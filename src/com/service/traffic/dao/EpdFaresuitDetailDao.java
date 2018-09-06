
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.traffic.model.EpdFaresuitdetail;

public class EpdFaresuitDetailDao extends BaseDao{
	public EpdFaresuitDetailDao(Connection conn){
		super(conn);
	}
	
	public EpdFaresuitdetail insert(EpdFaresuitdetail epdFaresuitdetail, Map<String, Object> config){
		String pk = super.insert(epdFaresuitdetail,config);
		epdFaresuitdetail.setFaresuitdetailSeq(pk);
		return epdFaresuitdetail;
	}
	
	public void update(EpdFaresuitdetail epdFaresuitdetail, Map<String, Object> config){
		super.update(epdFaresuitdetail,config);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String faresuitdetailSeq){
		String strSql = "delete from epd_faresuitdetail where faresuitdetail_seq=?";
		List params = new ArrayList();
		params.add(faresuitdetailSeq);
		super.executeSql(strSql, params);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void deleteByRouteSeq(String routeSeq) {
		String strSql = "delete from epd_faresuitdetail where route_seq=?";
		List params = new ArrayList();
		params.add(routeSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdFaresuitdetail> queryByPK(String faresuitdetailSeq){
		String strSql = "select * from epd_faresuitdetail where faresuitdetail_seq=?";
		List params = new ArrayList();
		params.add(faresuitdetailSeq);
		List<EpdFaresuitdetail> faresuitdetails = (List<EpdFaresuitdetail>) super.queryAll(
				strSql,params,new EpdFaresuitdetail());
		return faresuitdetails;
	}
	
	@SuppressWarnings("unchecked")
	public List<EpdFaresuitdetail> queryByAll(){
		String strSql = "select * from epd_faresuitdetail";
		List<EpdFaresuitdetail> faresuitdetails = (List<EpdFaresuitdetail>) super.queryAll(
				strSql,null,new EpdFaresuitdetail());
		return faresuitdetails;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdFaresuitdetail> queryPageByCustom(String faresuitSeq, String routeSeq, String stationSeq, String cargradeSeq,int start,int limit){
		StringBuffer strSql = new StringBuffer("select epd_faresuitdetail.*," +
				"epd_faresuit.faresuit_name," +
				"epd_route.route_name," +
				"epd_route.route_code," +
				"epd_route.route_spell," +
				"epd_station.station_name," +
				"epd_cargrade.cargrade_name" +
			" from epd_faresuitdetail" +
				" left join epd_faresuit on epd_faresuitdetail.faresuit_seq = epd_faresuit.faresuit_seq" +
				" left join epd_route on epd_faresuitdetail.route_seq = epd_route.route_seq" +
				" left join epd_station on epd_faresuitdetail.station_seq = epd_station.station_seq" +
				" left join epd_cargrade on epd_faresuitdetail.cargrade_seq = epd_cargrade.cargrade_seq" +
			" where 1=1");
		List params = new ArrayList();
		if (null != faresuitSeq && !"".equals(faresuitSeq)){
			strSql.append(" and epd_faresuitdetail.faresuit_seq = ?");
			params.add(faresuitSeq);
		}
		if (null != routeSeq && !"".equals(routeSeq)){
			strSql.append(" and epd_faresuitdetail.route_seq = ?");
			params.add(routeSeq);
		}
		if (null != stationSeq && !"".equals(stationSeq)){
			strSql.append(" and epd_faresuitdetail.station_seq = ?");
			params.add(stationSeq);
		}
		if (null != cargradeSeq && !"".equals(cargradeSeq)){
			strSql.append(" and epd_faresuitdetail.cargrade_seq = ?");
			params.add(cargradeSeq);
		}
		strSql.append(" order by epd_faresuitdetail.route_seq,epd_faresuitdetail.cargrade_seq,epd_faresuitdetail.station_seq");
		List<EpdFaresuitdetail> faresuitdetails = (List<EpdFaresuitdetail>) super.queryPage(strSql.toString(),
				params,new EpdFaresuitdetail(),start,limit);
		return faresuitdetails;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdFaresuitdetail> queryAllByCustom(String faresuitSeq, String routeSeq, String stationSeq, String cargradeSeq){
		StringBuffer strSql = new StringBuffer("select epd_faresuitdetail.*," +
				"epd_faresuit.faresuit_name," +
				"epd_route.route_name," +
				"epd_route.route_code," +
				"epd_route.route_spell," +
				"epd_station.station_name," +
				"epd_cargrade.cargrade_name" +
			" from epd_faresuitdetail" +
				" left join epd_faresuit on epd_faresuitdetail.faresuit_seq = epd_faresuit.faresuit_seq" +
				" left join epd_route on epd_faresuitdetail.route_seq = epd_route.route_seq" +
				" left join epd_station on epd_faresuitdetail.station_seq = epd_station.station_seq" +
				" left join epd_cargrade on epd_faresuitdetail.cargrade_seq = epd_cargrade.cargrade_seq" +
			" where 1=1");
		List params = new ArrayList();
		if (null != faresuitSeq && !"".equals(faresuitSeq)){
			strSql.append(" and epd_faresuitdetail.faresuit_seq = ?");
			params.add(faresuitSeq);
		}
		if (null != routeSeq && !"".equals(routeSeq)){
			strSql.append(" and epd_faresuitdetail.route_seq = ?");
			params.add(routeSeq);
		}
		if (null != stationSeq && !"".equals(stationSeq)){
			strSql.append(" and epd_faresuitdetail.station_seq = ?");
			params.add(stationSeq);
		}
		if (null != cargradeSeq && !"".equals(cargradeSeq)){
			strSql.append(" and epd_faresuitdetail.cargrade_seq = ?");
			params.add(cargradeSeq);
		}
		strSql.append(" order by epd_faresuitdetail.route_seq,epd_faresuitdetail.cargrade_seq,epd_faresuitdetail.station_seq");
		List<EpdFaresuitdetail> faresuitdetails = (List<EpdFaresuitdetail>) super.queryAll(strSql.toString(),params,new EpdFaresuitdetail());
		return faresuitdetails;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int queryCountByCustom(String faresuitSeq, String routeSeq, String stationSeq, String cargradeSeq){
		StringBuffer strSql = new StringBuffer("select count(1) from epd_faresuitdetail where 1=1");
		List params = new ArrayList();
		if (null != faresuitSeq && !"".equals(faresuitSeq)){
			strSql.append(" and epd_faresuitdetail.faresuit_seq = ?");
			params.add(faresuitSeq);
		}
		if (null != routeSeq && !"".equals(routeSeq)){
			strSql.append(" and epd_faresuitdetail.route_seq = ?");
			params.add(routeSeq);
		}
		if (null != stationSeq && !"".equals(stationSeq)){
			strSql.append(" and epd_faresuitdetail.station_seq = ?");
			params.add(stationSeq);
		}
		if (null != cargradeSeq && !"".equals(cargradeSeq)){
			strSql.append(" and epd_faresuitdetail.cargrade_seq = ?");
			params.add(cargradeSeq);
		}
		return super.queryCount(strSql.toString(),params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int queryCountByRouteSeq(String routeSeq){
		StringBuffer strSql = new StringBuffer("select count(1) from epd_faresuitdetail where epd_fare.route_seq = ?");
		List params = new ArrayList();
		params.add(routeSeq);
		return super.queryCount(strSql.toString(),params);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<EpdFaresuitdetail> queryByRouteSeq(String routeSeq) {
		StringBuffer strSql = new StringBuffer("select epd_faresuitdetail.*," +
				"epd_faresuit.faresuit_name," +
				"epd_route.route_name," +
				"epd_route.route_code," +
				"epd_route.route_spell," +
				"epd_station.station_name," +
				"epd_cargrade.cargrade_name" +
			" from epd_faresuitdetail" +
				" left join epd_faresuit on epd_faresuitdetail.faresuit_seq = epd_faresuit.faresuit_seq" +
				" left join epd_route on epd_faresuitdetail.route_seq = epd_route.route_seq" +
				" left join epd_station on epd_faresuitdetail.station_seq = epd_station.station_seq" +
				" left join epd_cargrade on epd_faresuitdetail.cargrade_seq = epd_cargrade.cargrade_seq" +
			" where 1=1");
		List params = new ArrayList();
		if (null != routeSeq && !"".equals(routeSeq)){
			strSql.append(" and epd_faresuitdetail.route_seq = ?");
			params.add(routeSeq);
		}
		strSql.append(" order by epd_faresuitdetail.route_seq," +
				"epd_faresuitdetail.faresuit_seq," +
				"epd_faresuitdetail.cargrade_seq," +
				"epd_faresuitdetail.faresuitdetail_seq");
		List<EpdFaresuitdetail> faresuitdetails = (List<EpdFaresuitdetail>) super.queryAll(strSql.toString(),
				params,new EpdFaresuitdetail());
		return faresuitdetails;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdFaresuitdetail> queryByRouteSeqAndFaresuitSeq(
			String routeSeq, String faresuitSeq) {
		StringBuffer strSql = new StringBuffer("select epd_faresuitdetail.*," +
				"epd_faresuit.faresuit_name," +
				"epd_route.route_name," +
				"epd_route.route_code," +
				"epd_route.route_spell," +
				"epd_station.station_name," +
				"epd_cargrade.cargrade_name" +
			" from epd_faresuitdetail" +
				" left join epd_faresuit on epd_faresuitdetail.faresuit_seq = epd_faresuit.faresuit_seq" +
				" left join epd_route on epd_faresuitdetail.route_seq = epd_route.route_seq" +
				" left join epd_station on epd_faresuitdetail.station_seq = epd_station.station_seq" +
				" left join epd_cargrade on epd_faresuitdetail.cargrade_seq = epd_cargrade.cargrade_seq" +
			" where 1=1");
		List params = new ArrayList();
		if (null != routeSeq && !"".equals(routeSeq)){
			strSql.append(" and epd_faresuitdetail.route_seq = ?");
			params.add(routeSeq);
		}
		if (null != routeSeq && !"".equals(routeSeq)){
			strSql.append(" and epd_faresuitdetail.faresuit_seq = ?");
			params.add(faresuitSeq);
		}
		strSql.append(" order by epd_faresuitdetail.route_seq,epd_faresuitdetail.cargrade_seq,epd_faresuitdetail.station_seq");
		List<EpdFaresuitdetail> faresuitdetails = (List<EpdFaresuitdetail>) super.queryAll(strSql.toString(),
				params,new EpdFaresuitdetail());
		return faresuitdetails;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdFaresuitdetail> queryByRouteAndStationAndCargradeAndFaresuiit(
			String routeSeq, String stationSeq, String cargradeSeq,
			String faresuitSeq) {
		String strSql = "select * from epd_faresuitdetail where route_seq=? and station_seq=? and cargrade_seq=? and faresuit_seq=?";
		List params = new ArrayList();
		params.add(routeSeq);
		params.add(stationSeq);
		params.add(cargradeSeq);
		params.add(faresuitSeq);
		List<EpdFaresuitdetail> faresuitdetails = (List<EpdFaresuitdetail>) super.queryAll(
				strSql,params,new EpdFaresuitdetail());
		return faresuitdetails;
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdFaresuitdetail> queryByRouteAndCargadeAndStation(String organizeSeq,
			String routeSeq, String cargradeSeq, String stationSeq,
			String linerDate) {
		StringBuffer strSql = new StringBuffer("select epd_faresuitdetail.*," +
				"epd_routestation.if_release as if_release" +
			" from epd_faresuit,epd_faresuitdetail" +
				" left join epd_routestation on epd_routestation.route_seq = epd_faresuitdetail.route_seq" +
					" and epd_routestation.station_seq = epd_faresuitdetail.station_seq" +
			" where epd_faresuitdetail.faresuit_seq = epd_faresuit.faresuit_seq" +
				" and epd_faresuit.faresuit_status=?");
		List params = new ArrayList();
		params.add(1);
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and epd_faresuit.organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != linerDate && !"".equals(linerDate)){
			strSql.append(" and epd_faresuit.start_date<=?");
			strSql.append(" and epd_faresuit.start_date>=?");
			params.add(linerDate);
			params.add(linerDate);
		}
		if (null != routeSeq && !"".equals(routeSeq)){
			strSql.append(" and epd_faresuitdetail.route_seq = ?");
			params.add(routeSeq);
		}
		if (null != cargradeSeq && !"".equals(cargradeSeq)){
			strSql.append(" and epd_faresuitdetail.cargrade_seq = ?");
			params.add(cargradeSeq);
		}
		if (null != stationSeq && !"".equals(stationSeq)){
			strSql.append(" and epd_faresuitdetail.station_seq = ?");
			params.add(stationSeq);
		}
		strSql.append(" order by epd_faresuitdetail.full_fare");
		List<EpdFaresuitdetail> faresuitdetails = (List<EpdFaresuitdetail>) super.queryAll(strSql.toString(),
				params,new EpdFaresuitdetail());
		return faresuitdetails;
	}


}
