
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.traffic.model.EpdReturnrate;

public class EpdReturnrateDao extends BaseDao{
	public EpdReturnrateDao(Connection conn){
		super(conn);
	}
	
	public EpdReturnrate insert(EpdReturnrate epdReturnrate, Map<String, Object> config){
		String pk = super.insert(epdReturnrate,config);
		epdReturnrate.setReturnrateSeq(pk);
		return epdReturnrate;
	}
	
	public void update(EpdReturnrate epdReturnrate, Map<String, Object> config){
		super.update(epdReturnrate,config);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String returnrateSeq){
		String strSql = "delete from epd_returnrate where returnrate_seq=?";
		List params = new ArrayList();
		params.add(returnrateSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdReturnrate> queryByValid(EpdReturnrate epdReturnrate) {
		StringBuffer strSql = new StringBuffer("select * from epd_returnrate where organize_seq =?" +
				" and returnrate_code = ?");
		List params = new ArrayList();
		params.add(epdReturnrate.getOrganizeSeq());
		params.add(epdReturnrate.getReturnrateCode());
		String returnrateSeq = epdReturnrate.getReturnrateSeq();
		if (null != returnrateSeq && !"".equals(returnrateSeq)){
			strSql.append(" and returnrate_seq <> ?");
			params.add(returnrateSeq);
		}
		List<EpdReturnrate> returnrates = (List<EpdReturnrate>) super.queryAll(strSql.toString(),
				params,new EpdReturnrate());
		return returnrates;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdReturnrate> queryByPK(String returnrateSeq){
		String strSql = "select * from epd_returnrate where returnrate_seq=?";
		List params = new ArrayList();
		params.add(returnrateSeq);
		List<EpdReturnrate> returnrates = (List<EpdReturnrate>) super.queryAll(strSql,params,new EpdReturnrate());
		return returnrates;
	}
	
	@SuppressWarnings("unchecked")
	public List<EpdReturnrate> queryByAll(){
		String strSql = "select * from epd_returnrate";
		List<EpdReturnrate> returnrates = (List<EpdReturnrate>) super.queryAll(strSql,null,new EpdReturnrate());
		return returnrates;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdReturnrate> queryPageByCustom(String organizeSeq, String returnrateCode,
			String returnrateName,int start,int limit){
		StringBuffer strSql = new StringBuffer("select * from epd_returnrate where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != returnrateCode && !"".equals(returnrateCode)){
			strSql.append(" and returnrate_code = ?");
			params.add(returnrateCode);
		}
		if (null != returnrateName && !"".equals(returnrateName)){
			strSql.append(" and returnrate_name = ?");
			params.add(returnrateName);
		}
		strSql.append(" order by returnrate_code");
		List<EpdReturnrate> returnrates = (List<EpdReturnrate>) super.queryPage(strSql.toString(),
				params,new EpdReturnrate(),start,limit);
		return returnrates;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdReturnrate> queryAllByCustom(String organizeSeq, String returnrateCode, String returnrateName){
		StringBuffer strSql = new StringBuffer("select * from epd_returnrate where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != returnrateCode && !"".equals(returnrateCode)){
			strSql.append(" and returnrate_code = ?");
			params.add(returnrateCode);
		}
		if (null != returnrateName && !"".equals(returnrateName)){
			strSql.append(" and returnrate_name = ?");
			params.add(returnrateName);
		}
		strSql.append(" order by returnrate_code");
		List<EpdReturnrate> returnrates = (List<EpdReturnrate>) super.queryAll(strSql.toString(),params,new EpdReturnrate());
		return returnrates;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int queryCountByCustom(String organizeSeq, String returnrateCode, String returnrateName){
		StringBuffer strSql = new StringBuffer("select count(1) from epd_returnrate where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != returnrateCode && !"".equals(returnrateCode)){
			strSql.append(" and returnrate_code = ?");
			params.add(returnrateCode);
		}
		if (null != returnrateName && !"".equals(returnrateName)){
			strSql.append(" and returnrate_name = ?");
			params.add(returnrateName);
		}
		return super.queryCount(strSql.toString(),params);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<EpdReturnrate> queryByOrganizeSeq(String organizeSeq) {
		StringBuffer strSql = new StringBuffer("select * from epd_returnrate where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		strSql.append(" order by returnrate_code");
		List<EpdReturnrate> returnrates = (List<EpdReturnrate>) super.queryAll(strSql.toString(),params,new EpdReturnrate());
		return returnrates;
	}

}
