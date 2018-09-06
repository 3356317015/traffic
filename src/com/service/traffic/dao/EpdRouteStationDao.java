
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.traffic.model.EpdRoutestation;

/**
 * EpdRouteDao概要说明：线路数据操作
 * @author lcy
 */
public class EpdRouteStationDao extends BaseDao{
	public EpdRouteStationDao(Connection conn){
		super(conn);
	}
	
	public EpdRoutestation insert(EpdRoutestation epdRoutestation, Map<String, Object> config){
		String pk = super.insert(epdRoutestation,config);
		epdRoutestation.setRoutestationSeq(pk);
		return epdRoutestation;
	}
	
	public void update(EpdRoutestation epdRoutestation, Map<String, Object> config){
		super.update(epdRoutestation,config);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String routestationSeq){
		String strSql = "delete from epd_routestation where routestation_seq=?";
		List params = new ArrayList();
		params.add(routestationSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByRouteSeq(String routeSeq){
		String strSql = "delete from epd_routestation where route_seq=?";
		List params = new ArrayList();
		params.add(routeSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdRoutestation> queryByPK(String routeSeq){
		String strSql = "select * from epd_routestation where routestation_seq=?";
		List params = new ArrayList();
		params.add(routeSeq);
		List<EpdRoutestation> routestations = (List<EpdRoutestation>) super.queryAll(strSql,params,new EpdRoutestation());
		return routestations;
	}
	
	@SuppressWarnings("unchecked")
	public List<EpdRoutestation> queryByAll(){
		String strSql = "select * from epd_routestation";
		List<EpdRoutestation> routestations = (List<EpdRoutestation>) super.queryAll(strSql,null,new EpdRoutestation());
		return routestations;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdRoutestation> queryByRouteSeq(String routeSeq){
		StringBuffer strSql = new StringBuffer(
				"SELECT epd_routestation.*,epd_station.station_name,epd_station.station_mileage," +
						"CONCAT(IFNULL(city,''),IFNULL(county,''),IFNULL(towns,'')) AS region_name" +
				" FROM epd_routestation" +
					" LEFT JOIN epd_station ON epd_routestation.station_seq=epd_station.station_seq" +
					" LEFT JOIN epd_region ON epd_station.region_seq = epd_region.region_seq" +
				" WHERE epd_routestation.route_seq = ?");
		List params = new ArrayList();
		params.add(routeSeq);
		strSql.append(" order by epd_routestation.station_order");
		List<EpdRoutestation> routestations = (List<EpdRoutestation>) super.queryAll(strSql.toString(),params,new EpdRoutestation());
		return routestations;
	}

}
