
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.traffic.model.EpdCheckgate;

public class EpdCheckgateDao extends BaseDao{
	public EpdCheckgateDao(Connection conn){
		super(conn);
	}
	
	public EpdCheckgate insert(EpdCheckgate epdCheckgate, Map<String, Object> config){
		String pk = super.insert(epdCheckgate,config);
		epdCheckgate.setCheckgateSeq(pk);
		return epdCheckgate;
	}
	
	public void update(EpdCheckgate epdCheckgate, Map<String, Object> config){
		super.update(epdCheckgate,config);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String checkgateSeq){
		String strSql = "delete from epd_checkgate where checkgate_seq=?";
		List params = new ArrayList();
		params.add(checkgateSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdCheckgate> queryByValid(EpdCheckgate epdCheckgate) {
		StringBuffer strSql = new StringBuffer("select * from epd_checkgate where organize_seq =?" +
				" and check_code = ?");
		List params = new ArrayList();
		params.add(epdCheckgate.getOrganizeSeq());
		params.add(epdCheckgate.getCheckCode());
		String checkgateSeq = epdCheckgate.getCheckgateSeq();
		if (null != checkgateSeq && !"".equals(checkgateSeq)){
			strSql.append(" and checkgate_seq <> ?");
			params.add(checkgateSeq);
		}
		List<EpdCheckgate> checkgates = (List<EpdCheckgate>) super.queryAll(strSql.toString(),
				params,new EpdCheckgate());
		return checkgates;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdCheckgate> queryByPK(String checkgateSeq){
		String strSql = "select * from epd_checkgate where checkgate_seq=?";
		List params = new ArrayList();
		params.add(checkgateSeq);
		List<EpdCheckgate> checkgates = (List<EpdCheckgate>) super.queryAll(strSql,params,new EpdCheckgate());
		return checkgates;
	}
	
	@SuppressWarnings("unchecked")
	public List<EpdCheckgate> queryByAll(){
		String strSql = "select * from epd_checkgate";
		List<EpdCheckgate> checkgates = (List<EpdCheckgate>) super.queryAll(strSql,null,new EpdCheckgate());
		return checkgates;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdCheckgate> queryPageByCustom(String organizeSeq, String checkCode, String checkName,int start,int limit){
		StringBuffer strSql = new StringBuffer("select * from epd_checkgate where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != checkCode && !"".equals(checkCode)){
			strSql.append(" and check_code = ?");
			params.add(checkCode);
		}
		if (null != checkName && !"".equals(checkName)){
			strSql.append(" and check_name = ?");
			params.add(checkName);
		}
		strSql.append(" order by check_code");
		List<EpdCheckgate> checkgates = (List<EpdCheckgate>) super.queryPage(strSql.toString(),
				params,new EpdCheckgate(),start,limit);
		return checkgates;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdCheckgate> queryAllByCustom(String organizeSeq, String checkCode, String checkName){
		StringBuffer strSql = new StringBuffer("select * from epd_checkgate where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != checkCode && !"".equals(checkCode)){
			strSql.append(" and check_code = ?");
			params.add(checkCode);
		}
		if (null != checkName && !"".equals(checkName)){
			strSql.append(" and check_name = ?");
			params.add(checkName);
		}
		strSql.append(" order by check_code");
		List<EpdCheckgate> checkgates = (List<EpdCheckgate>) super.queryAll(strSql.toString(),params,new EpdCheckgate());
		return checkgates;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int queryCountByCustom(String organizeSeq, String checkCode, String checkName){
		StringBuffer strSql = new StringBuffer("select count(1) from epd_checkgate where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != checkCode && !"".equals(checkCode)){
			strSql.append(" and check_code = ?");
			params.add(checkCode);
		}
		if (null != checkName && !"".equals(checkName)){
			strSql.append(" and check_name = ?");
			params.add(checkName);
		}
		return super.queryCount(strSql.toString(),params);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdCheckgate> queryByOrganizeSeq(String organizeSeq) {
		StringBuffer strSql = new StringBuffer("select * from epd_checkgate where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		strSql.append(" order by check_code");
		List<EpdCheckgate> checkgates = (List<EpdCheckgate>) super.queryAll(strSql.toString(),params,new EpdCheckgate());
		return checkgates;
	}

}
