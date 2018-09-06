
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.traffic.model.EpdDealorganize;


public class EpdDealorganizeDao extends BaseDao{
	public EpdDealorganizeDao(Connection conn){
		super(conn);
	}
	
	public EpdDealorganize insert(EpdDealorganize epdDealorganize, Map<String, Object> config){
		String pk = super.insert(epdDealorganize,config);
		epdDealorganize.setDealorganizeSeq(pk);
		return epdDealorganize;
	}
	
	public void update(EpdDealorganize epdDealorganize, Map<String, Object> config){
		super.update(epdDealorganize,config);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String dealorganizeSeq){
		String strSql = "delete from epd_dealorganize where dealorganize_seq=?";
		List params = new ArrayList();
		params.add(dealorganizeSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdDealorganize> queryByValid(EpdDealorganize epdDealorganize) {
		StringBuffer strSql = new StringBuffer("select * from epd_dealorganize where organize_seq=?" +
				" and dealorganize =?");
		List params = new ArrayList();
		params.add(epdDealorganize.getOrganizeSeq());
		params.add(epdDealorganize.getDealorganize());
		String dealorganizeSeq = epdDealorganize.getDealorganizeSeq();
		if (null != dealorganizeSeq && !"".equals(dealorganizeSeq)){
			strSql.append(" and dealorganize_seq <> ?");
			params.add(dealorganizeSeq);
		}
		List<EpdDealorganize> epdDealorganizes = (List<EpdDealorganize>) super.queryAll(strSql.toString(),params,new EpdDealorganize());
		return epdDealorganizes;
	}
	
	@SuppressWarnings("unchecked")
	public List<EpdDealorganize> queryByAll(){
		String strSql = "select epd_dealorganize.*" +
					",sam_organize.organize_name AS organize_name" +
				" from epd_dealorganize" +
					" left join sam_organize on epd_dealorganize.dealorganize = sam_organize.organize_seq";
		List<EpdDealorganize> epdDealorganizes = (List<EpdDealorganize>) super.queryAll(strSql,null,new EpdDealorganize());
		return epdDealorganizes;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdDealorganize> queryPageByCustom(String organizeSeq, String dealorganize,
			String dealStatus,int start,int limit){
		StringBuffer strSql = new StringBuffer("select epd_dealorganize.*" +
					",sam_organize.organize_name AS organize_name" +
				" from epd_dealorganize" +
					" left join sam_organize on epd_dealorganize.dealorganize = sam_organize.organize_seq" +
				" where 1=1");
		List params = new ArrayList();
		
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and epd_dealorganize.organize_seq = ?");
			params.add(organizeSeq);
		}
		
		if (null != dealorganize && !"".equals(dealorganize)){
			strSql.append(" and epd_dealorganize.dealorganize = ?");
			params.add(dealorganize);
		}
		
		if (null != dealStatus && !"".equals(dealStatus)){
			strSql.append(" and epd_dealorganize.deal_status = ?");
			params.add(dealStatus);
		}
		strSql.append(" order by sam_organize.organize_code");
		List<EpdDealorganize> epdDealorganizes = (List<EpdDealorganize>) super.queryPage(strSql.toString(),params,new EpdDealorganize(),start,limit);
		return epdDealorganizes;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdDealorganize> queryAllByCustom(String organizeSeq, String dealorganize,
			String dealStatus){
		StringBuffer strSql = new StringBuffer("select epd_dealorganize.*" +
					",sam_organize.organize_name AS organize_name" +
				" from epd_dealorganize" +
					" left join sam_organize on epd_dealorganize.dealorganize = sam_organize.organize_seq" +
				" where 1=1");
		List params = new ArrayList();
		
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and epd_dealorganize.organize_seq = ?");
			params.add(organizeSeq);
		}
		
		if (null != dealorganize && !"".equals(dealorganize)){
			strSql.append(" and epd_dealorganize.dealorganize = ?");
			params.add(dealorganize);
		}
		
		if (null != dealStatus && !"".equals(dealStatus)){
			strSql.append(" and epd_dealorganize.deal_status = ?");
			params.add(dealStatus);
		}
		strSql.append(" order by sam_organize.organize_code");
		List<EpdDealorganize> epdDealorganizes = (List<EpdDealorganize>) super.queryAll(strSql.toString(),params,new EpdDealorganize());
		return epdDealorganizes;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int queryCountByCustom(String organizeSeq, String dealorganize,String dealStatus){
		StringBuffer strSql = new StringBuffer("select count(1) from epd_dealorganize where 1=1");
		List params = new ArrayList();
		
		
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		
		if (null != dealorganize && !"".equals(dealorganize)){
			strSql.append(" and dealorganize = ?");
			params.add(dealorganize);
		}
		
		if (null != dealStatus && !"".equals(dealStatus)){
			strSql.append(" and deal_status = ?");
			params.add(dealStatus);
		}
		return super.queryCount(strSql.toString(),params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdDealorganize> queryByOrganizeSeqAndStatus(String organizeSeq, String dealStatus){
		StringBuffer strSql = new StringBuffer("select epd_dealorganize.*" +
					",sam_organize.organize_name AS organize_name" +
				" from epd_dealorganize" +
					" left join sam_organize on epd_dealorganize.dealorganize = sam_organize.organize_seq" +
				" where 1=1");
		List params = new ArrayList();
		
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and epd_dealorganize.organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != dealStatus && !"".equals(dealStatus)){
			strSql.append(" and epd_dealorganize.deal_status = ?");
			params.add(dealStatus);
		}
		strSql.append(" order by sam_organize.organize_code");
		List<EpdDealorganize> epdDealorganizes = (List<EpdDealorganize>) super.queryAll(strSql.toString(),params,new EpdDealorganize());
		return epdDealorganizes;
	}

}
