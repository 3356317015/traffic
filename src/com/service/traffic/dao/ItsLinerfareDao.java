package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.traffic.model.ItsLinerfare;

public class ItsLinerfareDao extends BaseDao{
	public ItsLinerfareDao(Connection conn){
		super(conn);
	}
	
	public ItsLinerfare insert(ItsLinerfare itslinerfare, Map<String, Object> config){
		String pk = super.insert(itslinerfare,config);
		itslinerfare.setLinerfareSeq(pk);
		return itslinerfare;
	}
	
	public void update(ItsLinerfare itslinerfare, Map<String, Object> config){
		super.update(itslinerfare,config);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String linerfareSeq){
		String strSql = "delete from its_linerfare where linerfare_seq=?";
		List params = new ArrayList();
		params.add(linerfareSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ItsLinerfare> queryByValid(ItsLinerfare itslinerfare) {
		StringBuffer strSql = new StringBuffer("select * from its_linerfare where liner_date = ? and liner_id = ?" +
				" and route_seq=? and station_seq=?");
		List params = new ArrayList();
		params.add(itslinerfare.getLinerDate());
		params.add(itslinerfare.getLinerId());
		params.add(itslinerfare.getRouteSeq());
		params.add(itslinerfare.getStationSeq());
		String linerfareSeq = itslinerfare.getLinerfareSeq();
		if (null != linerfareSeq && !"".equals(linerfareSeq)){
			strSql.append(" and liner_seq <> ?");
			params.add(linerfareSeq);
		}
		List<ItsLinerfare> itsstationLiners = (List<ItsLinerfare>) super.queryAll(strSql.toString(),
				params,new ItsLinerfare());
		return itsstationLiners;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ItsLinerfare> queryByPK(String linerfareSeq){
		String strSql = "select * from its_linerfare where linerfare_seq=?";
		List params = new ArrayList();
		params.add(linerfareSeq);
		List<ItsLinerfare> linerfares = (List<ItsLinerfare>) super.queryAll(strSql,params,new ItsLinerfare());
		return linerfares;
	}
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ItsLinerfare> queryByLinerSeq(String linerSeq) {
		String strSql = "select its_linerfare.*," +
					"epd_route.route_name," +
					"epd_route.route_code," +
					"epd_route.route_spell," +
					"epd_station.station_name" +
				" from its_linerfare" +
					" left join epd_route on its_linerfare.route_seq = epd_route.route_seq" +
					" left join epd_station on its_linerfare.station_seq = epd_station.station_seq" +
				" where liner_seq=?";
		List params = new ArrayList();
		params.add(linerSeq);
		List<ItsLinerfare> linerfares = (List<ItsLinerfare>) super.queryAll(strSql,params,new ItsLinerfare());
		return linerfares;
	}
	
	@SuppressWarnings("unchecked")
	public List<ItsLinerfare> queryByAll(){
		String strSql = "select * from its_linerfare";
		List<ItsLinerfare> linerfares = (List<ItsLinerfare>) super.queryAll(strSql,null,new ItsLinerfare());
		return linerfares;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ItsLinerfare> queryPageByCustom(String organizeSeq, String routeSeq,
			String stationSeq, String linerId,
			String startDate, String limitDate, int start, int limit) {
		StringBuffer strSql = new StringBuffer("select its_linerfare.*," +
				"epd_route.route_name," +
				"epd_route.route_code," +
				"epd_route.route_spell," +
				"epd_station.station_name" +
			" from its_linerfare" +
				" left join epd_route on its_linerfare.route_seq = epd_route.route_seq" +
				" left join epd_station on its_linerfare.station_seq = epd_station.station_seq" +
			" where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and its_linerfare.organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != routeSeq && !"".equals(routeSeq)){
			strSql.append(" and its_linerfare.route_seq = ?");
			params.add(routeSeq);
		}
		if (null != stationSeq && !"".equals(stationSeq)){
			strSql.append(" and its_linerfare.station_seq = ?");
			params.add(stationSeq);
		}
		if (null != linerId && !"".equals(linerId)){
			strSql.append(" and its_linerfare.liner_id = ?");
			params.add(linerId);
		}

		if (null != startDate && !"".equals(startDate)){
			strSql.append(" and its_linerfare.liner_date >= ?");
			params.add(startDate);
		}
		
		if (null != limitDate && !"".equals(limitDate)){
			strSql.append(" and its_linerfare.liner_date <= ?");
			params.add(limitDate);
		}
		
		strSql.append(" order by its_linerfare.route_seq,its_linerfare.station_seq,its_linerfare.liner_id");
		List<ItsLinerfare> linerfares = (List<ItsLinerfare>) super.queryPage(strSql.toString(),
				params,new ItsLinerfare(),start,limit);
		return linerfares;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ItsLinerfare> queryAllByCustom(String organizeSeq,String routeSeq,
			String stationSeq, String linerId,
			String startDate, String limitDate) {
		StringBuffer strSql = new StringBuffer("select its_linerfare.*," +
				"epd_route.route_name," +
				"epd_route.route_code," +
				"epd_route.route_spell," +
				"epd_station.station_name" +
			" from its_linerfare" +
				" left join epd_route on its_linerfare.route_seq = epd_route.route_seq" +
				" left join epd_station on its_linerfare.station_seq = epd_station.station_seq" +
			" where its_linerfare.liner_seq = its_liner.liner_seq");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and its_liner.organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != routeSeq && !"".equals(routeSeq)){
			strSql.append(" and its_linerfare.route_seq = ?");
			params.add(routeSeq);
		}
		if (null != stationSeq && !"".equals(stationSeq)){
			strSql.append(" and its_linerfare.station_seq = ?");
			params.add(stationSeq);
		}
		if (null != linerId && !"".equals(linerId)){
			strSql.append(" and its_linerfare.liner_id = ?");
			params.add(linerId);
		}

		if (null != startDate && !"".equals(startDate)){
			strSql.append(" and its_linerfare.liner_date >= ?");
			params.add(startDate);
		}
		
		if (null != limitDate && !"".equals(limitDate)){
			strSql.append(" and its_linerfare.liner_date <= ?");
			params.add(limitDate);
		}
		
		strSql.append(" order by its_linerfare.route_seq,its_linerfare.station_seq,its_linerfare.liner_id");
		List<ItsLinerfare> linerfares = (List<ItsLinerfare>) super.queryAll(strSql.toString(),
				params,new ItsLinerfare());
		return linerfares;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int queryCountByCustom(String organizeSeq, String routeSeq, String stationSeq,
			String linerId, String startDate, String limitDate) {
		StringBuffer strSql = new StringBuffer("select count(1)" +
			" from its_liner,its_linerfare" +
			" where its_linerfare.liner_seq = its_liner.liner_seq");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and its_liner.organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != routeSeq && !"".equals(routeSeq)){
			strSql.append(" and its_linerfare.route_seq = ?");
			params.add(routeSeq);
		}
		if (null != stationSeq && !"".equals(stationSeq)){
			strSql.append(" and its_linerfare.station_seq = ?");
			params.add(stationSeq);
		}
		if (null != linerId && !"".equals(linerId)){
			strSql.append(" and its_linerfare.liner_id = ?");
			params.add(linerId);
		}

		if (null != startDate && !"".equals(startDate)){
			strSql.append(" and its_linerfare.liner_date >= ?");
			params.add(startDate);
		}
		
		if (null != limitDate && !"".equals(limitDate)){
			strSql.append(" and its_linerfare.liner_date <= ?");
			params.add(limitDate);
		}
		return super.queryCount(strSql.toString(),params);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void deleteByLinerSeq(String linerSeq) {
		String strSql = "delete from its_linerfare where liner_seq=?";
		List params = new ArrayList();
		params.add(linerSeq);
		super.executeSql(strSql, params);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<ItsLinerfare> queryByLinerSeqAndStationSeq(String linerSeq,String stationSeq) {
		StringBuffer strSql = new StringBuffer("select its_linerfare.*," +
				"epd_routestation.if_release as if_release" +
			" from its_linerfare" +
				" left join epd_routestation on epd_routestation.route_seq = its_linerfare.route_seq" +
					" and epd_routestation.station_seq = its_linerfare.station_seq" +
			" where 1=1");
		List params = new ArrayList();
		if (null != linerSeq && !"".equals(linerSeq)){
			strSql.append(" and its_linerfare.liner_seq = ?");
			params.add(linerSeq);
		}
		if (null != stationSeq && !"".equals(stationSeq)){
			strSql.append(" and its_linerfare.station_seq = ?");
			params.add(stationSeq);
		}
		strSql.append(" order by its_linerfare.full_fare");
		List<ItsLinerfare> itsLinerfares = (List<ItsLinerfare>) super.queryAll(strSql.toString(),
				params,new ItsLinerfare());
		return itsLinerfares;
	}

}
