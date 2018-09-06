package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.traffic.model.ItsLinerstation;

public class ItsLinerstationDao extends BaseDao{
	public ItsLinerstationDao(Connection conn){
		super(conn);
	}
	
	public ItsLinerstation insert(ItsLinerstation itsLinerstation, Map<String, Object> config){
		String pk = super.insert(itsLinerstation,config);
		itsLinerstation.setLinerstationSeq(pk);
		return itsLinerstation;
	}
	
	public void update(ItsLinerstation itsLinerstation, Map<String, Object> config){
		super.update(itsLinerstation,config);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String linerstationSeq){
		String strSql = "delete from its_linerstation where linerstation_seq=?";
		List params = new ArrayList();
		params.add(linerstationSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ItsLinerstation> queryByValid(ItsLinerstation itsLinerstation) {
		StringBuffer strSql = new StringBuffer("select * from its_linerstation where liner_date = ? and liner_id = ?" +
				" and route_seq=? and station_seq=?");
		List params = new ArrayList();
		params.add(itsLinerstation.getLinerDate());
		params.add(itsLinerstation.getLinerId());
		params.add(itsLinerstation.getRouteSeq());
		params.add(itsLinerstation.getStationSeq());
		String linerstationSeq = itsLinerstation.getLinerstationSeq();
		if (null != linerstationSeq && !"".equals(linerstationSeq)){
			strSql.append(" and linerstation_seq <> ?");
			params.add(linerstationSeq);
		}
		List<ItsLinerstation> itsstationLiners = (List<ItsLinerstation>) super.queryAll(strSql.toString(),
				params,new ItsLinerstation());
		return itsstationLiners;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ItsLinerstation> queryByPK(String linerstationSeq){
		String strSql = "select * from its_linerstation where linerstation_seq=?";
		List params = new ArrayList();
		params.add(linerstationSeq);
		List<ItsLinerstation> linerstations = (List<ItsLinerstation>) super.queryAll(strSql,params,new ItsLinerstation());
		return linerstations;
	}
	
	@SuppressWarnings("unchecked")
	public List<ItsLinerstation> queryByAll(){
		String strSql = "select * from its_linerstation";
		List<ItsLinerstation> linerstations = (List<ItsLinerstation>) super.queryAll(strSql,null,new ItsLinerstation());
		return linerstations;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void deleteByLinerSeq(String linerSeq) {
		String strSql = "delete from its_linerstation where liner_seq=?";
		List params = new ArrayList();
		params.add(linerSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ItsLinerstation> queryByLinerSeq(String linerSeq){
		String strSql = "select its_linerstation.*,epd_station.station_mileage" +
				" from its_linerstation" +
					" left join epd_station on epd_station.station_seq = its_linerstation.station_seq" +
				" where liner_seq=?";
		List params = new ArrayList();
		params.add(linerSeq);
		List<ItsLinerstation> linerstations = (List<ItsLinerstation>) super.queryAll(strSql,params,new ItsLinerstation());
		return linerstations;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void updateAttribute(ItsLinerstation itsLinerstation, Map<String, Object> config){
		String strSql = "update its_linerstation set if_sale=?,station_num=?,update_time=? where linerstation_seq=?";
		List params = new ArrayList();
		params.add(itsLinerstation.getIfSale());
		params.add(itsLinerstation.getStationNum());
		params.add(itsLinerstation.getUpdateTime());
		params.add(itsLinerstation.getLinerstationSeq());
		super.executeSql(strSql, params);
	}

}
