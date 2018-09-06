package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.traffic.model.ItsLinerservice;

public class ItsLinerserviceDao extends BaseDao{
	public ItsLinerserviceDao(Connection conn){
		super(conn);
	}
	
	public ItsLinerservice insert(ItsLinerservice itsLinerservice, Map<String, Object> config){
		String pk = super.insert(itsLinerservice,config);
		itsLinerservice.setLinerserviceSeq(pk);
		return itsLinerservice;
	}
	
	public void update(ItsLinerservice itsLinerservice, Map<String, Object> config){
		super.update(itsLinerservice,config);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String linerserviceSeq){
		String strSql = "delete from its_linerservice where linerservice_seq=?";
		List params = new ArrayList();
		params.add(linerserviceSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ItsLinerservice> queryByValid(ItsLinerservice itsLinerservice) {
		StringBuffer strSql = new StringBuffer("select * from its_linerservice where liner_date = ? and liner_id = ?" +
				" and route_seq=? and service_seq=?");
		List params = new ArrayList();
		params.add(itsLinerservice.getLinerDate());
		params.add(itsLinerservice.getLinerId());
		params.add(itsLinerservice.getRouteSeq());
		params.add(itsLinerservice.getServiceSeq());
		String linerserviceSeq = itsLinerservice.getLinerserviceSeq();
		if (null != linerserviceSeq && !"".equals(linerserviceSeq)){
			strSql.append(" and linerservice_seq <> ?");
			params.add(linerserviceSeq);
		}
		List<ItsLinerservice> itsLinerservices = (List<ItsLinerservice>) super.queryAll(strSql.toString(),
				params,new ItsLinerservice());
		return itsLinerservices;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ItsLinerservice> queryByPK(String linerserviceSeq){
		String strSql = "select * from its_linerservice where linerservice_seq=?";
		List params = new ArrayList();
		params.add(linerserviceSeq);
		List<ItsLinerservice> itsLinerservices = (List<ItsLinerservice>) super.queryAll(strSql.toString(),
				params,new ItsLinerservice());
		return itsLinerservices;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void deleteByLinerSeq(String linerSeq) {
		String strSql = "delete from its_linerservice where liner_seq=?";
		List params = new ArrayList();
		params.add(linerSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ItsLinerservice> queryByLinerSeq(String linerSeq){
		String strSql = "select * from its_linerservice where liner_seq=?";
		List params = new ArrayList();
		params.add(linerSeq);
		List<ItsLinerservice> itsLinerservices = (List<ItsLinerservice>) super.queryAll(strSql.toString(),
				params,new ItsLinerservice());
		return itsLinerservices;
	}

}
