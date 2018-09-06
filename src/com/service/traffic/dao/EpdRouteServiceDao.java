
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.traffic.model.EpdRouteservice;

public class EpdRouteServiceDao extends BaseDao{
	public EpdRouteServiceDao(Connection conn){
		super(conn);
	}
	
	public EpdRouteservice insert(EpdRouteservice epdRouteservice, Map<String, Object> config){
		String pk = super.insert(epdRouteservice,config);
		epdRouteservice.setRouteserviceSeq(pk);
		return epdRouteservice;
	}
	
	public void update(EpdRouteservice epdRouteservice, Map<String, Object> config){
		super.update(epdRouteservice,config);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String routeserviceSeq){
		String strSql = "delete from epd_routeservice where routeservice_seq=?";
		List params = new ArrayList();
		params.add(routeserviceSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByRouteSeq(String routeSeq){
		String strSql = "delete from epd_routeservice where route_seq=?";
		List params = new ArrayList();
		params.add(routeSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdRouteservice> queryByPK(String routeserviceSeq){
		String strSql = "select * from epd_routeservice where routeserviceSeq=?";
		List params = new ArrayList();
		params.add(routeserviceSeq);
		List<EpdRouteservice> routeservices = (List<EpdRouteservice>) super.queryAll(strSql,params,new EpdRouteservice());
		return routeservices;
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdRouteservice> queryByRouteSeq(String routeSeq){
		StringBuffer strSql = new StringBuffer(
				"SELECT epd_routeservice.*,sam_service.service_name" +
				" FROM epd_routeservice" +
					" LEFT JOIN sam_service ON epd_routeservice.service_seq=sam_service.service_seq" +
				" WHERE epd_routeservice.route_seq = ?");
		List params = new ArrayList();
		params.add(routeSeq);
		strSql.append(" order by epd_routeservice.service_order");
		List<EpdRouteservice> routeservices = (List<EpdRouteservice>) super.queryAll(strSql.toString(),params,new EpdRouteservice());
		return routeservices;
	}

}
