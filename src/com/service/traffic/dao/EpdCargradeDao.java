
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.traffic.model.EpdCargrade;

public class EpdCargradeDao extends BaseDao{
	public EpdCargradeDao(Connection conn){
		super(conn);
	}
	
	public EpdCargrade insert(EpdCargrade epdCargrade,Map<String, Object> config){
		String pk = super.insert(epdCargrade,config);
		epdCargrade.setCargradeSeq(pk);
		return epdCargrade;
	}
	
	public void update(EpdCargrade epdCargrade,Map<String, Object> config){
		super.update(epdCargrade,config);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String cargradeSeq){
		String strSql = "delete from epd_cargrade where cargrade_seq=?";
		List params = new ArrayList();
		params.add(cargradeSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdCargrade> queryByValid(EpdCargrade epdCargrade) {
		StringBuffer strSql = new StringBuffer("select * from epd_cargrade where organize_seq =? and cargrade_code = ?");
		List params = new ArrayList();
		params.add(epdCargrade.getOrganizeSeq());
		params.add(epdCargrade.getCargradeCode());
		String cargradeSeq = epdCargrade.getCargradeSeq();
		if (null != cargradeSeq && !"".equals(cargradeSeq)){
			strSql.append(" and cargrade_seq <> ?");
			params.add(cargradeSeq);
		}
		List<EpdCargrade> cargrades = (List<EpdCargrade>) super.queryAll(strSql.toString(),
				params,new EpdCargrade());
		return cargrades;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdCargrade> queryByPK(String cargradeSeq){
		String strSql = "select * from epd_cargrade where cargrade_seq=?";
		List params = new ArrayList();
		params.add(cargradeSeq);
		List<EpdCargrade> cargrades = (List<EpdCargrade>) super.queryAll(strSql,params,new EpdCargrade());
		return cargrades;
	}
	
	@SuppressWarnings("unchecked")
	public List<EpdCargrade> queryByAll(){
		String strSql = "select * from epd_cargrade";
		List<EpdCargrade> cargrades = (List<EpdCargrade>) super.queryAll(strSql,null,new EpdCargrade());
		return cargrades;
	}
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdCargrade> queryByByOrganizeSeq(String organizeSeq) {
		String strSql = "select * from epd_cargrade where organize_seq=?";
		List params = new ArrayList();
		params.add(organizeSeq);
		List<EpdCargrade> cargrades = (List<EpdCargrade>) super.queryAll(strSql,params,new EpdCargrade());
		return cargrades;
	}

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdCargrade> queryPageByCustom(String organizeSeq, String cargradeCode, String cargradeName,int start,int limit){
		StringBuffer strSql = new StringBuffer("select * from epd_cargrade where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != cargradeCode && !"".equals(cargradeCode)){
			strSql.append(" and cargrade_code = ?");
			params.add(cargradeCode);
		}
		if (null != cargradeName && !"".equals(cargradeName)){
			strSql.append(" and cargrade_name = ?");
			params.add(cargradeName);
		}
		strSql.append(" order by cargrade_code");
		List<EpdCargrade> cargrades = (List<EpdCargrade>) super.queryPage(strSql.toString(),
				params,new EpdCargrade(),start,limit);
		return cargrades;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdCargrade> queryAllByCustom(String organizeSeq, String cargradeCode, String cargradeName){
		StringBuffer strSql = new StringBuffer("select * from epd_cargrade where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != cargradeCode && !"".equals(cargradeCode)){
			strSql.append(" and cargrade_code = ?");
			params.add(cargradeCode);
		}
		if (null != cargradeName && !"".equals(cargradeName)){
			strSql.append(" and cargrade_name = ?");
			params.add(cargradeName);
		}
		strSql.append(" order by cargrade_code");
		List<EpdCargrade> cargrades = (List<EpdCargrade>) super.queryAll(strSql.toString(),params,new EpdCargrade());
		return cargrades;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int queryCountByCustom(String organizeSeq, String cargradeCode, String cargradeName){
		StringBuffer strSql = new StringBuffer("select count(1) from epd_cargrade where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != cargradeCode && !"".equals(cargradeCode)){
			strSql.append(" and cargrade_code = ?");
			params.add(cargradeCode);
		}
		if (null != cargradeName && !"".equals(cargradeName)){
			strSql.append(" and cargrade_name = ?");
			params.add(cargradeName);
		}
		return super.queryCount(strSql.toString(),params);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<EpdCargrade> queryByRouteSeq(String routeSeq) {
		StringBuffer strSql = new StringBuffer("select distinct epd_cargrade.* from epd_cargrade,epd_fare" +
				" where epd_cargrade.cargrade_seq = epd_fare.cargrade_seq");
		List params = new ArrayList();
		if (null != routeSeq && !"".equals(routeSeq)){
			strSql.append(" and route_seq = ?");
			params.add(routeSeq);
		}
		strSql.append(" order by cargrade_code");
		List<EpdCargrade> cargrades = (List<EpdCargrade>) super.queryAll(strSql.toString(),params,new EpdCargrade());
		return cargrades;
	}

}
