
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.traffic.model.EpdFare;

public class EpdFareDao extends BaseDao{
	public EpdFareDao(Connection conn){
		super(conn);
	}
	
	public EpdFare insert(EpdFare epdFare, Map<String, Object> config){
		String pk = super.insert(epdFare,config);
		epdFare.setFareSeq(pk);
		return epdFare;
	}
	
	public void update(EpdFare epdFare, Map<String, Object> config){
		super.update(epdFare,config);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String fareSeq){
		String strSql = "delete from epd_fare where fare_seq=?";
		List params = new ArrayList();
		params.add(fareSeq);
		super.executeSql(strSql, params);
	}
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void deleteByRouteSeq(String routeSeq) {
		String strSql = "delete from epd_fare where route_seq=?";
		List params = new ArrayList();
		params.add(routeSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdFare> queryByPK(String fareSeq){
		String strSql = "select * from epd_fare where fare_seq=?";
		List params = new ArrayList();
		params.add(fareSeq);
		List<EpdFare> fares = (List<EpdFare>) super.queryAll(strSql,params,new EpdFare());
		return fares;
	}
	
	@SuppressWarnings("unchecked")
	public List<EpdFare> queryByAll(){
		String strSql = "select * from epd_fare";
		List<EpdFare> fares = (List<EpdFare>) super.queryAll(strSql,null,new EpdFare());
		return fares;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdFare> queryPageByCustom(String routeSeq, String stationSeq, String cargradeSeq,int start,int limit){
		StringBuffer strSql = new StringBuffer("select epd_fare.*," +
				"epd_route.route_name," +
				"epd_route.route_code," +
				"epd_route.route_spell," +
				"epd_station.station_name," +
				"epd_cargrade.cargrade_name" +
			" from epd_fare" +
			" left join epd_route on epd_fare.route_seq = epd_route.route_seq" +
			" left join epd_station on epd_fare.station_seq = epd_station.station_seq" +
			" left join epd_cargrade on epd_fare.cargrade_seq = epd_cargrade.cargrade_seq" +
			" where 1=1");
		List params = new ArrayList();
		if (null != routeSeq && !"".equals(routeSeq)){
			strSql.append(" and epd_fare.route_seq = ?");
			params.add(routeSeq);
		}
		if (null != stationSeq && !"".equals(stationSeq)){
			strSql.append(" and epd_fare.station_seq = ?");
			params.add(stationSeq);
		}
		if (null != cargradeSeq && !"".equals(cargradeSeq)){
			strSql.append(" and epd_fare.cargrade_seq = ?");
			params.add(cargradeSeq);
		}
		strSql.append(" order by epd_fare.route_seq,epd_fare.cargrade_seq,epd_fare.fare_seq");
		List<EpdFare> fares = (List<EpdFare>) super.queryPage(strSql.toString(),
				params,new EpdFare(),start,limit);
		return fares;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdFare> queryAllByCustom(String routeSeq, String stationSeq, String cargradeSeq){
		StringBuffer strSql = new StringBuffer("select epd_fare.*," +
				"epd_route.route_name," +
				"epd_route.route_code," +
				"epd_route.route_spell," +
				"epd_station.station_name," +
				"epd_cargrade.cargrade_name" +
			" from epd_fare" +
			" left join epd_route on epd_fare.route_seq = epd_route.route_seq" +
			" left join epd_station on epd_fare.station_seq = epd_station.station_seq" +
			" left join epd_cargrade on epd_fare.cargrade_seq = epd_cargrade.cargrade_seq" +
			" where 1=1");
		List params = new ArrayList();
		if (null != routeSeq && !"".equals(routeSeq)){
			strSql.append(" and epd_fare.route_seq = ?");
			params.add(routeSeq);
		}
		if (null != stationSeq && !"".equals(stationSeq)){
			strSql.append(" and epd_fare.station_seq = ?");
			params.add(stationSeq);
		}
		if (null != cargradeSeq && !"".equals(cargradeSeq)){
			strSql.append(" and epd_fare.cargrade_seq = ?");
			params.add(cargradeSeq);
		}
		strSql.append(" order by epd_fare.route_seq,epd_fare.cargrade_seq,epd_fare.fare_seq");
		List<EpdFare> fares = (List<EpdFare>) super.queryAll(strSql.toString(),params,new EpdFare());
		return fares;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int queryCountByCustom(String routeSeq, String stationSeq, String cargradeSeq){
		StringBuffer strSql = new StringBuffer("select count(1) from epd_fare where 1=1");
		List params = new ArrayList();
		if (null != routeSeq && !"".equals(routeSeq)){
			strSql.append(" and epd_fare.route_seq = ?");
			params.add(routeSeq);
		}
		if (null != stationSeq && !"".equals(stationSeq)){
			strSql.append(" and epd_fare.station_seq = ?");
			params.add(stationSeq);
		}
		if (null != cargradeSeq && !"".equals(cargradeSeq)){
			strSql.append(" and epd_fare.cargrade_seq = ?");
			params.add(cargradeSeq);
		}
		return super.queryCount(strSql.toString(),params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int queryCountByRouteSeq(String routeSeq){
		StringBuffer strSql = new StringBuffer("select count(1) from epd_fare where epd_fare.route_seq = ?");
		List params = new ArrayList();
		params.add(routeSeq);
		return super.queryCount(strSql.toString(),params);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<EpdFare> queryByRouteSeq(String routeSeq) {
		StringBuffer strSql = new StringBuffer("select epd_fare.*," +
				"epd_route.route_name," +
				"epd_route.route_code," +
				"epd_route.route_spell," +
				"epd_station.station_name," +
				"epd_cargrade.cargrade_name" +
			" from epd_fare" +
			" left join epd_route on epd_fare.route_seq = epd_route.route_seq" +
			" left join epd_station on epd_fare.station_seq = epd_station.station_seq" +
			" left join epd_cargrade on epd_fare.cargrade_seq = epd_cargrade.cargrade_seq" +
			" where 1=1");
		List params = new ArrayList();
		if (null != routeSeq && !"".equals(routeSeq)){
			strSql.append(" and epd_fare.route_seq = ?");
			params.add(routeSeq);
		}
		strSql.append(" order by epd_fare.route_seq,epd_fare.cargrade_seq,epd_fare.fare_seq");
		List<EpdFare> fares = (List<EpdFare>) super.queryAll(strSql.toString(),
				params,new EpdFare());
		return fares;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdFare> queryByRouteAndStationAndCargrade(String routeSeq,
			String stationSeq, String cargradeSeq) {
		String strSql = "select * from epd_fare where route_seq=? and station_seq=? and cargrade_seq=?";
		List params = new ArrayList();
		params.add(routeSeq);
		params.add(stationSeq);
		params.add(cargradeSeq);
		List<EpdFare> fares = (List<EpdFare>) super.queryAll(strSql,params,new EpdFare());
		return fares;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdFare> queryByRouteAndCargadeAndStation(String organizeSeq,
			String routeSeq, String cargradeSeq, String stationSeq) {
		StringBuffer strSql = new StringBuffer("select epd_fare.*," +
				"epd_routestation.if_release as if_release" +
			" from epd_fare" +
				" left join epd_routestation on epd_routestation.route_seq = epd_fare.route_seq" +
					" and epd_routestation.station_seq = epd_fare.station_seq" +
			" where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and epd_fare.organize_seq = ?");
			params.add(organizeSeq);
		}
		
		if (null != routeSeq && !"".equals(routeSeq)){
			strSql.append(" and epd_fare.route_seq = ?");
			params.add(routeSeq);
		}
		
		if (null != cargradeSeq && !"".equals(cargradeSeq)){
			strSql.append(" and epd_fare.cargrade_seq = ?");
			params.add(cargradeSeq);
		}
		
		if (null != stationSeq && !"".equals(stationSeq)){
			strSql.append(" and epd_fare.station_seq = ?");
			params.add(stationSeq);
		}
		strSql.append(" order by epd_fare.full_fare");
		List<EpdFare> epdFares = (List<EpdFare>) super.queryAll(strSql.toString(),
				params,new EpdFare());
		return epdFares;
	}

}
