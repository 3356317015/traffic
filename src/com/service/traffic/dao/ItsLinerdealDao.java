package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.traffic.model.ItsLinerdeal;

public class ItsLinerdealDao extends BaseDao{
	public ItsLinerdealDao(Connection conn){
		super(conn);
	}
	
	public ItsLinerdeal insert(ItsLinerdeal itsLinerdeal, Map<String, Object> config){
		String pk = super.insert(itsLinerdeal,config);
		itsLinerdeal.setLinerdealSeq(pk);
		return itsLinerdeal;
	}
	
	public void update(ItsLinerdeal itsLinerdeal, Map<String, Object> config){
		super.update(itsLinerdeal,config);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String linerdealSeq){
		String strSql = "delete from its_linerdeal where linerdeal_seq=?";
		List params = new ArrayList();
		params.add(linerdealSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ItsLinerdeal> queryByValid(ItsLinerdeal itsLinerdeal) {
		StringBuffer strSql = new StringBuffer("select * from its_linerdeal where liner_date = ? and liner_id = ?" +
				" and deal_id=? and deal_organize=?");
		List params = new ArrayList();
		params.add(itsLinerdeal.getLinerDate());
		params.add(itsLinerdeal.getLinerId());
		params.add(itsLinerdeal.getDealId());
		params.add(itsLinerdeal.getDealOrganize());
		String linerdealSeq = itsLinerdeal.getLinerdealSeq();
		if (null != linerdealSeq && !"".equals(linerdealSeq)){
			strSql.append(" and liner_seq <> ?");
			params.add(linerdealSeq);
		}
		List<ItsLinerdeal> itsdealLiners = (List<ItsLinerdeal>) super.queryAll(strSql.toString(),
				params,new ItsLinerdeal());
		return itsdealLiners;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ItsLinerdeal> queryByPK(String linerdealSeq){
		String strSql = "select * from its_linerdeal where linerdeal_seq=?";
		List params = new ArrayList();
		params.add(linerdealSeq);
		List<ItsLinerdeal> linerdeals = (List<ItsLinerdeal>) super.queryAll(strSql,params,new ItsLinerdeal());
		return linerdeals;
	}
	
	@SuppressWarnings("unchecked")
	public List<ItsLinerdeal> queryByAll(){
		String strSql = "select * from its_linerdeal";
		List<ItsLinerdeal> linerdeals = (List<ItsLinerdeal>) super.queryAll(strSql,null,new ItsLinerdeal());
		return linerdeals;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void deleteByLinerSeq(String linerSeq) {
		String strSql = "delete from its_linerdeal where liner_seq=?";
		List params = new ArrayList();
		params.add(linerSeq);
		super.executeSql(strSql, params);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<ItsLinerdeal> queryByLinerSeq(String linerSeq) {
		String strSql = "select its_linerdeal.*,sam_organize.organize_name" +
				" from its_linerdeal" +
					" left join sam_organize on sam_organize.organize_code=its_linerdeal.deal_organize" +
				" where liner_seq=?";
		List params = new ArrayList();
		params.add(linerSeq);
		List<ItsLinerdeal> linerdeals = (List<ItsLinerdeal>) super.queryAll(strSql,params,new ItsLinerdeal());
		return linerdeals;
	}

}
