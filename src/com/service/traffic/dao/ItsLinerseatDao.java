package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.traffic.model.ItsLinerseat;

public class ItsLinerseatDao extends BaseDao{
	public ItsLinerseatDao(Connection conn){
		super(conn);
	}
	
	public ItsLinerseat insert(ItsLinerseat itsLinerseat, Map<String, Object> config){
		String pk = super.insert(itsLinerseat,config);
		itsLinerseat.setLinerseatSeq(pk);
		return itsLinerseat;
	}
	
	public void update(ItsLinerseat itsLinerseat, Map<String, Object> config){
		super.update(itsLinerseat,config);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String linerseatSeq){
		String strSql = "delete from its_linerseat where linerseat_seq=?";
		List params = new ArrayList();
		params.add(linerseatSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ItsLinerseat> queryByValid(ItsLinerseat itsLinerseat) {
		StringBuffer strSql = new StringBuffer("select * from its_linerseat where liner_date = ? and liner_id = ?" +
				" and seat_id=?");
		List params = new ArrayList();
		params.add(itsLinerseat.getLinerDate());
		params.add(itsLinerseat.getLinerId());
		params.add(itsLinerseat.getSeatId());
		String linerseatSeq = itsLinerseat.getLinerseatSeq();
		if (null != linerseatSeq && !"".equals(linerseatSeq)){
			strSql.append(" and linerseat_seq <> ?");
			params.add(linerseatSeq);
		}
		List<ItsLinerseat> itsseatLiners = (List<ItsLinerseat>) super.queryAll(strSql.toString(),
				params,new ItsLinerseat());
		return itsseatLiners;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ItsLinerseat> queryByPK(String linerseatSeq){
		String strSql = "select * from its_linerseat where linerseat_seq=?";
		List params = new ArrayList();
		params.add(linerseatSeq);
		List<ItsLinerseat> linerseats = (List<ItsLinerseat>) super.queryAll(strSql,params,new ItsLinerseat());
		return linerseats;
	}
	
	@SuppressWarnings("unchecked")
	public List<ItsLinerseat> queryByAll(){
		String strSql = "select * from its_linerseat";
		List<ItsLinerseat> linerseats = (List<ItsLinerseat>) super.queryAll(strSql,null,new ItsLinerseat());
		return linerseats;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void deleteByLinerSeq(String linerSeq) {
		String strSql = "delete from its_linerseat where liner_seq=?";
		List params = new ArrayList();
		params.add(linerSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ItsLinerseat> queryByLinerSeq(String linerSeq){
		String strSql = "select * from its_linerseat where liner_seq=?";
		List params = new ArrayList();
		params.add(linerSeq);
		List<ItsLinerseat> linerseats = (List<ItsLinerseat>) super.queryAll(strSql,params,new ItsLinerseat());
		return linerseats;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ItsLinerseat> queryPageByLinerSeq(String linerSeq, int start,
			int limit) {
		String strSql = "select * from its_linerseat where liner_seq=?";
		List params = new ArrayList();
		params.add(linerSeq);
		List<ItsLinerseat> linerseats = (List<ItsLinerseat>) super.queryPage(strSql,params,new ItsLinerseat(),start,limit);
		return linerseats;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int queryCountByLinerSeq(String linerSeq) {
		String strSql = "select count(1) from its_linerseat where liner_seq=?";
		List params = new ArrayList();
		params.add(linerSeq);
		int count =  super.queryCount(strSql,params);
		return count;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void updateAttribute(ItsLinerseat linerseat,
			Map<String, Object> config) {
		String strSql = "update its_linerseat set seat_status=?,update_time=? where linerseat_seq=?";
		List params = new ArrayList();
		params.add(linerseat.getSeatStatus());
		params.add(linerseat.getUpdateTime());
		params.add(linerseat.getLinerseatSeq());
		super.executeSql(strSql, params);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int queryCountByLinerSeqAndState(String linerSeq, int seatStatus) {
		String strSql = "select count(1) from its_linerseat where liner_seq=? and seat_status=?";
		List params = new ArrayList();
		params.add(linerSeq);
		params.add(seatStatus);
		int count =  super.queryCount(strSql,params);
		return count;
	}





}
