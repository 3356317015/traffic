
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.traffic.model.EpdFaretype;

public class EpdFaretypeDao extends BaseDao{
	public EpdFaretypeDao(Connection conn){
		super(conn);
	}
	
	public EpdFaretype insert(EpdFaretype epdFaretype, Map<String, Object> config){
		String pk = super.insert(epdFaretype,config);
		epdFaretype.setFaretypeSeq(pk);
		return epdFaretype;
	}
	
	public void update(EpdFaretype epdFaretype, Map<String, Object> config){
		super.update(epdFaretype,config);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String faretypeSeq){
		String strSql = "delete from epd_faretype where faretype_seq=?";
		List params = new ArrayList();
		params.add(faretypeSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdFaretype> queryByValid(EpdFaretype epdFaretype) {
		StringBuffer strSql = new StringBuffer("select * from epd_faretype where organize_seq =?" +
				" and faretype_code = ?");
		List params = new ArrayList();
		params.add(epdFaretype.getOrganizeSeq());
		params.add(epdFaretype.getFaretypeCode());
		String faretypeSeq = epdFaretype.getFaretypeSeq();
		if (null != faretypeSeq && !"".equals(faretypeSeq)){
			strSql.append(" and faretype_seq <> ?");
			params.add(faretypeSeq);
		}
		List<EpdFaretype> faretypes = (List<EpdFaretype>) super.queryAll(strSql.toString(),
				params,new EpdFaretype());
		return faretypes;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdFaretype> queryByPK(String faretypeSeq){
		String strSql = "select * from epd_faretype where faretype_seq=?";
		List params = new ArrayList();
		params.add(faretypeSeq);
		List<EpdFaretype> faretypes = (List<EpdFaretype>) super.queryAll(strSql,params,new EpdFaretype());
		return faretypes;
	}
	
	@SuppressWarnings("unchecked")
	public List<EpdFaretype> queryByAll(){
		String strSql = "select * from epd_faretype";
		List<EpdFaretype> faretypes = (List<EpdFaretype>) super.queryAll(strSql,null,new EpdFaretype());
		return faretypes;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdFaretype> queryPageByCustom(String organizeSeq,
			String faretypeCode, String faretypeName,String faretypeStatus,int start,int limit){
		StringBuffer strSql = new StringBuffer("select * from epd_faretype where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != faretypeCode && !"".equals(faretypeCode)){
			strSql.append(" and faretype_code = ?");
			params.add(faretypeCode);
		}
		if (null != faretypeName && !"".equals(faretypeName)){
			strSql.append(" and faretype_name = ?");
			params.add(faretypeName);
		}
		if (null != faretypeStatus && !"".equals(faretypeStatus)){
			strSql.append(" and faretype_status = ?");
			params.add(faretypeStatus);
		}
		strSql.append(" order by faretype_code");
		List<EpdFaretype> faretypes = (List<EpdFaretype>) super.queryPage(strSql.toString(),
				params,new EpdFaretype(),start,limit);
		return faretypes;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdFaretype> queryAllByCustom(String organizeSeq,
			String faretypeCode, String faretypeName,String faretypeStatus){
		StringBuffer strSql = new StringBuffer("select * from epd_faretype where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != faretypeCode && !"".equals(faretypeCode)){
			strSql.append(" and faretype_code = ?");
			params.add(faretypeCode);
		}
		if (null != faretypeName && !"".equals(faretypeName)){
			strSql.append(" and faretype_name = ?");
			params.add(faretypeName);
		}
		if (null != faretypeStatus && !"".equals(faretypeStatus)){
			strSql.append(" and faretype_status = ?");
			params.add(faretypeStatus);
		}
		strSql.append(" order by faretype_code");
		List<EpdFaretype> faretypes = (List<EpdFaretype>) super.queryAll(strSql.toString(),params,new EpdFaretype());
		return faretypes;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int queryCountByCustom(String organizeSeq,
			String faretypeCode, String faretypeName,String faretypeStatus){
		StringBuffer strSql = new StringBuffer("select count(1) from epd_faretype where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != faretypeCode && !"".equals(faretypeCode)){
			strSql.append(" and faretype_code = ?");
			params.add(faretypeCode);
		}
		if (null != faretypeName && !"".equals(faretypeName)){
			strSql.append(" and faretype_name = ?");
			params.add(faretypeName);
		}
		if (null != faretypeStatus && !"".equals(faretypeStatus)){
			strSql.append(" and faretype_status = ?");
			params.add(faretypeStatus);
		}
		return super.queryCount(strSql.toString(),params);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdFaretype> queryByOrganizeSeq(String organizeSeq) {
		StringBuffer strSql = new StringBuffer("select * from epd_faretype where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		strSql.append(" order by faretype_code");
		List<EpdFaretype> faretypes = (List<EpdFaretype>) super.queryAll(strSql.toString(),params,new EpdFaretype());
		return faretypes;
	}

}
